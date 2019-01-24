package cl.minsal.mail.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.minsal.mail.component.MailComponent;
import cl.minsal.mail.entity.Mail;
import cl.minsal.mail.response.Respuesta;
import io.swagger.annotations.ApiOperation;

/**
 * Created by jvasquez
 */
@RestController
@RequestMapping(value = "/mail")
public class MailController {

	private static Logger LOG = LogManager.getLogger(MailController.class);

	@Autowired
	MailComponent mailComponent;
	
	@ApiOperation(value = "mail", notes = "mandar email")
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Respuesta> sendMail(@RequestBody Mail mail) throws MessagingException, IOException {
		Respuesta responseMessage = new Respuesta();
		try {
			mailComponent.sendSimpleMessage(mail);
			responseMessage.setExplanation("");
			responseMessage.setMessage("Se envio el email con éxito");
			responseMessage.setStatus_code(200);
		} catch (MailException e) {
			responseMessage.setExplanation(e.getMessage());
			responseMessage.setMessage("No se ha enviado el email:::"+e.getMessage());
			responseMessage.setStatus_code(500);
			LOG.error(e.getMessage());
		}
		return new ResponseEntity<Respuesta>(responseMessage, HttpStatus.valueOf(responseMessage.getStatus_code()));
	}
	
	
}	
package cl.minsal.mail.controller;

import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.minsal.mail.component.MailComponent;
import cl.minsal.mail.entity.Mail;
import cl.minsal.mail.response.Respuesta;
import cl.minsal.mail.util.Utilitaries;
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
	
	@Autowired
	Utilitaries util;
	
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
	
//	@Deprecated
//	@ApiOperation(value = "FECHA", notes = "CAMBIO A FECHA")
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity<Respuesta> FECHATOESP(@PathVariable("id") Date id) {
//		Respuesta responseMessage = new Respuesta();
//		try {
//			LOG.info("Inicio servicio prueba");
//			responseMessage.setExplanation((util.DateTimeToSpanish(util.convertToLocalDateViaInstant(id))));
//			responseMessage.setMessage("");
//			responseMessage.setStatus_code(200);
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOG.info("Ocurrio un error al llamar al servicio prueba "
//					.concat(" : ".concat(e.getMessage().concat(e.getLocalizedMessage()))));
//			String mensajeError = "Error: " + e.getMessage() + e.getLocalizedMessage();
//			responseMessage.setMessage(mensajeError);
//			responseMessage.setStatus_code(1);
//		}
//		LOG.info("Fin servicio prueba");
//		return new ResponseEntity<Respuesta>(responseMessage, HttpStatus.OK);
//	}
//	
//	
	
}

package cl.minsal.mail.component;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import cl.minsal.mail.entity.Mail;

@Service
public class MailComponent {

	@Autowired
	private JavaMailSender emailSender;
	@Autowired
    private SpringTemplateEngine templateEngine;
	
//	public void sendSimpleMessage() throws MessagingException {
//		try {
//			SimpleMailMessage message = new SimpleMailMessage();
//			message.setSubject("Correo de prueba SMTP");
//			message.setText("OTRA COSA");
//			message.setTo("jvasquez@altiuz.com");
//			message.setFrom("cosses@altiuz.cl");
//			message.setSentDate(new Date());
//			emailSender.send(message);
//		} catch (MailException e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	
//public void sendSimpleMessage() throws MessagingException {
//	final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
//	final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
//	message.setFrom("cosses@altiuz.cl");
//	message.setTo("jvasquez@altiuz.com");
//	message.setSubject("Este es un correo de prueba MimeMessage");
//	message.setText("Feliz Año 2019");
//	this.emailSender.send(mimeMessage);
//}
	
	public void sendSimpleMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

       
//      helper.addAttachment("logo1.png", new ClassPathResource("/images/logoVertical.png"));
//      helper.addAttachment("logoHorizontal.png", new ClassPathResource("/images/logoHorizontal.png"));

        Context context = new Context();
        
        context.setVariable("username",mail.getUser());
        context.setVariable("message", mail.getMsg());

        String html = templateEngine.process("Confirmation", context);

        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom("agendaremota@minsal.cl");
     // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
        	 // open image
//        	 File imgPath = new File("classpath:/images/logoVertical.png");
//        	 BufferedImage bufferedImage = ImageIO.read(imgPath);
//
//        	 // get DataBufferBytes from Raster
//        	 WritableRaster raster = bufferedImage .getRaster();
//        	 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
//
//        
//        final InputStreamSource imageSource = new ByteArrayResource(data.getData());
//        helper.addInline("logoVertical.png", imageSource, "png");
        

        emailSender.send(message);
    }
	
	
}

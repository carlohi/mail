package cl.minsal.mail.conf;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConf {

	  @Resource
	    private Environment env;
	
	 @Value("${spring.mail.host}")
	    private String host;
	 
	 @Value("${spring.mail.port}")
	    private String portS;
	 
	 
	
	 @Bean
	    public JavaMailSender javaMailService() {
	        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//	        javaMailSender.setHost("smtp.gmail.com");
//	        javaMailSender.setPort(Integer.parseInt(portS));
	        
	        javaMailSender.setHost(env.getRequiredProperty("spring.mail.host"));
	        javaMailSender.setPort(Integer.parseInt(env.getRequiredProperty("spring.mail.port")));
	        javaMailSender.setPassword(env.getRequiredProperty("spring.mail.password"));
	        javaMailSender.setUsername(env.getRequiredProperty("spring.mail.username"));
	        javaMailSender.setProtocol(env.getRequiredProperty("spring.mail.properties.mail.transport.protocol"));
	        javaMailSender.setDefaultEncoding("UTF-8");
	        Properties prop = new Properties();
	        prop.setProperty("spring.mail.properties.mail.smtp.auth", "true");
	        prop.setProperty("mail.smtp.starttls.enable", "true");
	        prop.setProperty("mail.smtp.starttls.required", "false");
	        prop.setProperty("spring.mail.properties.mail.smtp.auth", "true");
	        javaMailSender.setJavaMailProperties(prop);
	        return javaMailSender;
	    }
	
	   @Bean
	    public SimpleMailMessage simpleMailMessage() {
	       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	       simpleMailMessage.setFrom("cosses@altiuz.cl");
	       simpleMailMessage.setSubject("PRUEBA CORREO");
	       return simpleMailMessage;
	    }
	   
	 
}

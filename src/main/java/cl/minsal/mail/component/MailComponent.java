package cl.minsal.mail.component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.LocaleUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	
	@Resource
    private Environment env;
	
    private static final String PROPERTY_NAME_IMG_CHECK_CIRCLE = "minsal.img.check.circle";
    private static final String PROPERTY_NAME_IMG_AGENDA_LOGO = "minsal.img.agenda.logo";
    private static final String PROPERTY_NAME_IMG_AGENDA_HD = "minsal.img.agenda.hd";
    private static final String PROPERTY_NAME_IMG_AGENDA_ICON_CALENDAR = "minsal.img.agenda.icon.calendar";
    private static final String PROPERTY_NAME_IMG_AGENDA_HOSPITAL = "minsal.img.agenda.hospital";
    private static final String PROPERTY_NAME_IMG_AGENDA_FECHA = "minsal.img.agenda.fecha";
    private static final String PROPERTY_NAME_IMG_AGENDA_INFO_CIRCLE = "minsal.img.agenda.info.circle";
    private static final String PROPERTY_NAME_IMG_AGENDA_LOGO_MINSAL = "minsal.img.agenda.logo.minsal";
    private static final String PROPERTY_NAME_IMG_AGENDA_HD_FOOTER = "minsal.img.agenda.hd.footer";
	
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
        context.setVariable("action", mail.getAction());
        context.setVariable("folio", mail.getFolio());
        context.setVariable("center", mail.getCenter());
        context.setVariable("especialist", mail.getProfessional());       
        context.setVariable("dayOfWeek", getSpanishDayOfWeek(mail.getDatetime()));
        context.setVariable("month", getSpanishMonth(mail.getDatetime()));
        context.setVariable("hour", getSpanishHour(mail.getDatetime()));

        //Images url of template
        context.setVariable("imageCheckCircle",env.getRequiredProperty(PROPERTY_NAME_IMG_CHECK_CIRCLE));
        context.setVariable("agendaLogo", env.getRequiredProperty(PROPERTY_NAME_IMG_AGENDA_LOGO));
        context.setVariable("agendaHd", env.getRequiredProperty(PROPERTY_NAME_IMG_AGENDA_HD));
        context.setVariable("agendaIconCalendar", env.getRequiredProperty(PROPERTY_NAME_IMG_AGENDA_ICON_CALENDAR));
        context.setVariable("agendaHospital", env.getRequiredProperty(PROPERTY_NAME_IMG_AGENDA_HOSPITAL));
        context.setVariable("agendaFecha", env.getRequiredProperty(PROPERTY_NAME_IMG_AGENDA_FECHA));
        context.setVariable("imageInfoCircle",env.getRequiredProperty(PROPERTY_NAME_IMG_AGENDA_INFO_CIRCLE));
        context.setVariable("agendaLogoMinsal", env.getRequiredProperty(PROPERTY_NAME_IMG_AGENDA_LOGO_MINSAL));
        context.setVariable("hdFooter", env.getRequiredProperty(PROPERTY_NAME_IMG_AGENDA_HD_FOOTER));

        String html = templateEngine.process("Confirmation", context);

        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom("agendaremota@minsal.cl");

        emailSender.send(message);
    }
	
	public static String getSpanishMonth(Date date) {
		DateTime dt = new DateTime(date);
		org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("DD 'de' MMMM ").withLocale(LocaleUtils.toLocale("es_ES"));
		return dt.toString(dtf);
	}
	
	public static String getSpanishDayOfWeek(Date date) {
		DateTime dt = new DateTime(date);
		org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("EEEE").withLocale(LocaleUtils.toLocale("es_ES"));
		return dt.toString(dtf);
	}
	
	public static String getSpanishHour(Date date) {
		DateTime dt = new DateTime(date);
		org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("'a las ' HH:mm ").withLocale(LocaleUtils.toLocale("es_ES"));
		return dt.toString(dtf);
	}
	
}

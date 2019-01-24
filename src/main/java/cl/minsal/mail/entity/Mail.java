package cl.minsal.mail.entity;

import java.util.Date;

public class Mail {
	
	String user;
	String folio;
	String center;
	Date datetime;
	String professional;
	String mailTo;
	//attributes for generic mail sender
	String subject;
	String msg;
	String action;
	
	public Mail() {}

	public Mail(String user, String folio, String center, Date datetime, String professional, String mailTo,
			 String subject, String msg, String action) {
		super();
		this.user = user;
		this.folio = folio;
		this.center = center;
		this.datetime = datetime;
		this.professional = professional;
		this.mailTo = mailTo;
		this.subject = subject;
		this.msg = msg;
		this.action = action;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
}

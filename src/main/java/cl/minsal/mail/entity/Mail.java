package cl.minsal.mail.entity;

public class Mail {
	
	String user;
	String folio;
	String center;
	String datetime;
	String professional;
	String mailTo;
	//attributes for generic mail sender
	String imgUrl;
	String subject;
	String title;
	String msg;
	Integer action;
	
	public Mail() {}

	public Mail(String user, String folio, String center, String datetime, String professional, String mailTo,
			String imgUrl, String subject, String title, String msg, Integer action) {
		super();
		this.user = user;
		this.folio = folio;
		this.center = center;
		this.datetime = datetime;
		this.professional = professional;
		this.mailTo = mailTo;
		this.imgUrl = imgUrl;
		this.subject = subject;
		this.title = title;
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

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
	
}

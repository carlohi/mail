package cl.minsal.mail.dto;

import java.util.Date;

public class BookingConfirmation {

	String folio;
	String email;
	String name;
	String message;
	Date datetime;
	String center;
	String professional;
	
	public BookingConfirmation() {}
	
	public BookingConfirmation(String folio, String email, String name, String message, Date datetime, String center,
			String professional) {
		super();
		this.folio = folio;
		this.email = email;
		this.name = name;
		this.message = message;
		this.datetime = datetime;
		this.center = center;
		this.professional = professional;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	
}

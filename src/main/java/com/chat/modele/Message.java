package com.chat.modele;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

public class Message {

	private String contenu;
	private User user;
	private Date date;

	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return user + ": " + contenu + "  ----" + date.toString() + "\n";
	}


	public String getHourFormatted() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		DateFormat formatter1 = new SimpleDateFormat("dd/mm/yy");

		String hour = formatter.format(this.date);
		String day = formatter1.format(this.date);
		return "Envoyé le "+day+" à "+hour;
	}
}
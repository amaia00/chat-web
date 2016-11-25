package com.chat.modele;

import java.util.Date;

public class Message {

	private String contenu;
	private String user;
	private Date date;

	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
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
		return user+": "+ contenu +"  ----" + date.toString()+"\n";
	}
	

}

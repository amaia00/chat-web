package com.chat.modele;

import java.util.Date;

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
		return user+": "+ contenu +"  ----" + date.toString()+"\n";
	}
	

}

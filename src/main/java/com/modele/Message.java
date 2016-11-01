package com.modele;

import java.util.Date;

public class Message {
	private String message;
	private String user;
	private Date date;
	
	public Message(){
	}
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
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
		return user+": "+message+"  ----" + date.toString()+"\n";
	}
	

}

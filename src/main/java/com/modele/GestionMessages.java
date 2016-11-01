package com.modele;

import java.text.SimpleDateFormat;
import java.util.*;
/**** 
 * 
 * @author sofia faddi
 *
 */

public class GestionMessages {

	private static Map<String,ArrayList<Message>> map= new HashMap<String,ArrayList<Message>>();
    // contsructeur par défaut pour le javabean
	    
	public GestionMessages() {
	}
// Méthode qui permet d'ajouter un message à un salon
	public static void addMessage(String message , String pseudo , String salon){
		if(map.containsKey(salon)){
			Message M = new Message();
			M.setMessage(message);
			M.setUser(pseudo);
			M.setDate(new Date());
			map.get(salon).add(M);
		}else{
			map.put(salon, new ArrayList<Message>());
			Message M = new Message();
			M.setMessage(message);
			M.setUser(pseudo);
			M.setDate(new Date());
			map.get(salon).add(M);		}
	}

	// Cette méthode permet d'ajouter un salon
	public static void addSalon(String salon){
		GestionMessages.map.put(salon, new ArrayList<Message>());
		
	}
	// Cette méthode permet de recupérer un message en fonction du salon 
	public static ArrayList<Message> getMessages(String salon){
		if(map.containsKey(salon)){
			return map.get(salon);
		}else{
			return map.put(salon, new ArrayList<Message>());
		}
	}
	// cette méthode permet de supprimer un message d'un salon
	public static void supprimerMessages(String salon){
		if(map.containsKey(salon)){
			map.remove(map.get(salon));
		}
	}
	// cette méthode permet de récuperer le nombre de message d'un salon 
	public static int nombreMessage(String salon){
		if(map.containsKey(salon)){
			return map.get(salon).size();
		}else{
			return -1;
		}
	}
	public static Map<String,ArrayList<Message>> getMap() {
		return map;
	}

	public static void setMap(Map<String,ArrayList<Message>> map) {
		GestionMessages.map = map;
	}

}

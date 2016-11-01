<jsp:useBean id="GestionMessages" scope="application"
	class="com.modele.GestionMessages" />
<jsp:useBean id="Utilitaire" scope="application"
	class="com.modele.Utilitaire" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML5">
<html>
	<head>
	<!-- Encodage CSS BOOSTRAP et personalisé -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
		<title>Affichage des messages</title>
	</head>
	<body class="body_display">
	
	<%
		response.setIntHeader("Refresh", 3);
		String pseudo = (String) session.getAttribute("pseudo");
		String salon = (String) session.getAttribute("salon");

		String methode = request.getMethod();
		System.out.println("fuck" + salon);

		String nomCookie = "lastModifiedChatRoom";
		Cookie tmpCookie = Utilitaire.getCookie(request.getCookies(), nomCookie);

		int nbMessClient = 0;
		int nbMessServeur = 0;

		//gestion.ajouterMessage(new Message("Bob", "test")); //test

		//teste de l'existence du cookie, création si nécessaire
		if (tmpCookie == null) {

			Cookie creation = new Cookie(nomCookie, "0");
			creation.setMaxAge(60);
			response.addCookie(creation);
			//response.setStatus(204);
			tmpCookie = creation;
		} else {
			if (tmpCookie.getMaxAge() == 0) {
				tmpCookie.setMaxAge(60);
			}

		}
		//teste la méthode utilisée
		if (methode.equalsIgnoreCase("get")) {

			//nb messages côté client
			nbMessClient = Integer.parseInt(tmpCookie.getValue());
			//nb messages côté serveur
			nbMessServeur = GestionMessages.nombreMessage(salon);
			System.out.println("nbMessServeur=" + nbMessServeur + "  // nbMessClient = " + nbMessClient);
			/*
			* Comparaison du nombre de messages, client/serveur.
			* Si < est vrai, alors on va chercher les nouveaux messages,
			* sinon on dit au client qu'il n'y a pas de nouveau contenu à récupérer (204)
			*/

			if (GestionMessages.nombreMessage(salon) != 0) {
				if (nbMessClient < nbMessServeur) {
					//tmpCookie.setValue(gestion.stringSize()); //-> ne fonctionne pas
					response.addCookie(new Cookie(nomCookie, "" + GestionMessages.nombreMessage(salon)));
				} else if(nbMessClient == nbMessServeur){
					//Envoie de "204 No Content" => aucun nouveau message à récupérer (par défaut : 200 OK)
					response.setStatus(204);
				}else{
					Cookie creation = new Cookie(nomCookie, "0");
					creation.setMaxAge(60);
					response.addCookie(creation);
					//response.setStatus(204);
					tmpCookie = creation;
				}
					
			} else {
				Cookie creation = new Cookie(nomCookie, "0");
				creation.setMaxAge(60);
				response.addCookie(creation);
				//response.setStatus(204);
				tmpCookie = creation;
			}

		}
		//Un message va être ajouté
		else if (methode.equalsIgnoreCase("post")) {
			//response.setStatus(204);
		}
	%>
	
	
	<%
		out.println("<div class='panel panel-info'><div class='panel-heading'><h3 class='panel-title'>Listes des messages</h3> </div><div class='panel-body'><br>");
		// recupération du pseudo et du salon
	//String pseudo = (String) session.getAttribute( "pseudo" ); 
	//SString salon = (String) session.getAttribute("salon");
	// condition pour 
	if (salon.equals("null")) {
		// Boucle qui parcout 
		for (String mapKey : GestionMessages.getMap().keySet()) {
			out.println(" <h4>Binevenue au salon : " + mapKey + " , Dorenavant vous avez la possibilité de discuter avec tous les membres du salon</h4>");
			for (int i = 0; i < GestionMessages.nombreMessage(mapKey); i++) {

				out.println("  <LI>" + GestionMessages.getMessages(mapKey).get(i).toString() + "</UL>");
				
			}
			
		}
	} else {
		out.println("<h4> Bienvenue au salon : " + salon + " Dorenavant vous avez la possibilité de discuter avec tous les membres du salon </h4>");
		for (int i = 0; i < GestionMessages.nombreMessage(salon); i++) {

			out.println("<div class='block_message'><h4 class='user_user'><i class='glyphicon glyphicon-user'></i>" + GestionMessages.getMessages(salon).get(i).getUser() + " a dit :</h4>");
			out.println("<h5 class='message_user'>"+GestionMessages.getMessages(salon).get(i).getMessage()+"</h5>");
			out.println("<div class='date_user'>" + GestionMessages.getMessages(salon).get(i).getDate() + "</div></div>");
		}
		out.println("</div>");
	}
	
	%>
</body>
</html>
<jsp:useBean id="GestionMessages" scope="application"
	class="com.modele.GestionMessages" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML5>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chat online, Bienvenue</title>
		<!-- Responsive-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 1 BOOTSTRAP / 2 Style CSS -->
		 <link href="resources/css/bootstrap.min.css" rel="stylesheet">
		 <link href="resources/css/style.css" rel="stylesheet">
	</head>
	<body>
	<div class="container">
		<div class="row">
	 		<div class="col-xs-12 col-md-offset-2 col-md-8 col-sm-offset-2 col-sm-8 col-lg-offset-2 col-lg-8">
				<div class="image_accueil"><img alt="Image de bienvenue" src="resources/images/chatimage.png"></div>
				<h2>Bienvenue sur le chat en ligne</h2>
				<form method ="POST" action="Init">
					<p>Entrer votre nom pour se connecter et choisissez un salon si vous vous voulez accèder un salon existant cliquez sur la flêche" </p>
					<div class="col-xs-12 col-md-6 col-sm-6 col-lg-6">
						<div class="input-group">
			 				 <span class="input-group-addon" id="basic-addon1">@</span>
			  				<input type="text" class="form-control" placeholder="Pseudo" name="pseudo" aria-describedby="basic-addon1">
			  			</div>
		  			</div>
		  			<div class="col-xs-12 col-md-6 col-sm-6 col-lg-6">
						<div class="input-group">
			 				 <span class="input-group-addon" id="basic-addon2"><i class="glyphicon glyphicon-home"></i></span>
			  				<input id="basicaddon2input" type="text" class="form-control" placeholder="salon" name="salon" aria-describedby="basic-addon2"><a class="clickbt" href=#><i class="glyphicon glyphicon-play"></i></a>
						</div>
						
						
          <%int i=0;   
          // Verifier si la listes des salons n'est pas vide pour donner à l'utilisateur le droit de choisir un salon)
    		if (GestionMessages.getMap().keySet().size()!=0){ %>
    			<select class="salon form-control" name="salon" >	
    			<option class="optionnumber" value=""></option> 
    		<% for (String mapKey : GestionMessages.getMap().keySet() ) {
		%> <option class="optionnumber" value="<%=mapKey%>"><%=mapKey%></option> 
		<% }}%>
       </select>
					</div>
					<button type="submit" class="btn btn-primary">Se connecter</button>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="resources/js/script.js"></script>
</body>
</html>
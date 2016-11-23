<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML5">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Page des Messages</title>
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
	</head>
<body>
<div class="container">
	<iframe src="restreint/Affichage.jsp" name="Affichageframe"></iframe>	
	<form action="Stockage" class="formmessage" method="POST">
		<label style="margin:30px 0;">Message: </label><br/>
	<textarea class="form-control" rows="3" name="contenu"></textarea>
		<input type="submit" class="btn btn-primary" value="envoyer">
	</form>

	<!--Récupération de l'objet depuis la session -->
	<!-- String contenu = (String) session.getAttribute( "contenu" ); `*/ -->
    <!--  Bouton se déconnecter avec le parametre name deco  -->
	<a data-method="get" class='btn btn-success' href="/TPChat1/Init" data-parameter-name="deco" data-parameter-value="true">Déconnexion</a>
</div>

</body>
</html>
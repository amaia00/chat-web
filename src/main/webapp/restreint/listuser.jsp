<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.chat.util.Utilitaire" %>
<jsp:useBean id="gestion" scope="application"
             class="com.chat.modele.ChatGestionService"/>
<jsp:useBean id="util" scope="application"
             class="com.chat.util.Utilitaire"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html5">
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
    //response.setIntHeader("Refresh", 3);
    String salon = (String) session.getAttribute("salon");
%>



<div class="all-users  bgelement"> <div class ="panel-info"><div class='panel-heading'>
<h3 class='panel-title'>Listes des messages</h3>
</div>
<ul>
<li>
<span class="glyphicon glyphicon-user" aria-hidden="true"></span> user1
</li>
<li>
<span class="glyphicon glyphicon-user" aria-hidden="true"></span> user1
</li>
<li>
<span class="glyphicon glyphicon-user" aria-hidden="true"></span> user1
</li>
<li>
<span class="glyphicon glyphicon-user" aria-hidden="true"></span> user1
</li>
<li>
<span class="glyphicon glyphicon-user" aria-hidden="true"></span> user1
</li>
<li>
<span class="glyphicon glyphicon-user" aria-hidden="true"></span> user1
</li>

</ul>
</div>
</div>
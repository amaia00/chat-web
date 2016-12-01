<%@ page import="com.chat.modele.Salon" %>
<%@ page import="com.chat.util.DataException" %>
<jsp:useBean id="gestion" scope="session"
			 class="com.chat.service.ChatMessageService"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html5">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%

out.println("<HTML>\n<BODY>---Liste des salons: <br><br>"); 

for (Salon salon : gestion.getMap().keySet() ) {
	/* TODO voir comment prend le salon du session ou un truck comme ça*/
	 out.println("--"+salon.getName()+" :<br>");
	try {
		for (int i=0; i<gestion.nombreMessage(salon.getName()) ;i++ ){

               out.println("  <LI>"+ gestion.getMessages(salon.getName()).get(i).getUser()+": "
                   + gestion.getMessages(salon.getName()).get(i).getContenu() + "\n" +
                       "</UL>" );
               }
	} catch (DataException e) {
		/* TODO ameliorer ça*/
		e.printStackTrace();
	}
	out.println("</BODY><br></HTML>");
	 } 
 
%>

</body>
</html>
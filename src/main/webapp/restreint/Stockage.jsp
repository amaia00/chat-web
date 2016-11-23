<jsp:useBean id="GestionMessages"  scope="session" 
             class="com.modele.GestionMessages"/>
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

for (String mapKey : GestionMessages.getMap().keySet() ) {
	 out.println("--"+mapKey+" :<br>");
	 for (int i=0; i<GestionMessages.nombreMessage(mapKey) ;i++ ){
		 
			out.println("  <LI>"+ GestionMessages.getMessages(mapKey).get(i).getUser()+": "
				+ GestionMessages.getMessages(mapKey).get(i).getContenu() + "\n" +
					"</UL>" );
			}
	 out.println("</BODY><br></HTML>"); 
	 } 
 
%>

</body>
</html>
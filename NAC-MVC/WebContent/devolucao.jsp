<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Devolução</title>
</head>
<body>
<%if(session.getAttribute("logininfo") == null){ response.sendRedirect("index.jsp");}%>
<%if(request.getAttribute("erro") != null)
{%>
	<script>alert("<%=request.getAttribute("erro")%>");
	window.location = "locacao";
	</script>
<%} %>
<% if(request.getAttribute("status") != null) {%>
<script>
	if (confirm("Locação finalizada com sucesso. Deseja pontuar o KM de Vantagens para o cliente?") == true) {
		window.location = "pontuar?idloc=${cliente}";
	} else {
		window.location = "locacao";
	}
</script>
<%} %>
<% if(request.getAttribute("pontuar") != null) {%>
<script>
	alert("<%=request.getAttribute("pontuar")%>");	
	window.location = "locacao";
</script>
<%} %>


</body>
</html>
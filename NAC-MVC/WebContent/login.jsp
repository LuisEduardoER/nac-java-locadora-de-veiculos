<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Login Locadora de Veiculos</h1>
<c:choose>
		<c:when test="${erro != null}">
			<h3>Falha no login.</h3>
		</c:when>
	</c:choose>
<form action="Login" method="post">
	<input type="text" name="login">
	<input type="password" name="passwd">
	<input type="submit" value="Enviar">
</form>
</body>
</html>
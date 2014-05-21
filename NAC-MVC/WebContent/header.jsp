<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div id="header">
		<div id="logo">
			<a href="index.jsp" ><img src="images/logo.jpg" alt="Logo" /></a>
		</div>
		<div id="navigation">
			<ul>
				<li class="first selected"><a href="index.jsp">Home</a></li>
				<li><a href="cadastro" >Usuários</a></li>
				<li><a href="clientes">Clientes</a></li>
				<li><a href="marcas">Marcas</a></li>
				<li><a href="carros">Carros</a></li>
				<li><a href="precos" >Preços</a></li>
				<li><a href="locacao">Locação</a></li>
				<c:choose>
					<c:when test="${sessionScope.logininfo != null}">
					<li><a href="Login?logout=1">Logout</a></li>
					</c:when>
				</c:choose>
			</ul>
		</div>
	</div>
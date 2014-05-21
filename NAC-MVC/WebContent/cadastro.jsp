<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema de Aluguel de Carros</title>
<link rel="stylesheet" href="css/style.css" type="text/css"
	charset="utf-8" />
</head>
<body>
<%if(session.getAttribute("logininfo") == null){ response.sendRedirect("index.jsp");}%>
	<c:import url="header.jsp" />
	<div id="adbox">
		<div class="body">
			<div class="details">
				
				

					<form action="cadastro" method="post">
					
								<h1>Cadastro</h1>
					<c:choose>
					<c:when test="${status != null}">
				${status}
			</c:when>
				</c:choose>
								<label> <span style="color: #999">Login:&nbsp;</span> <input
									type="text" name="usuario" value="${usereditdata.usuario}">
								</label>
								<label> <span style="color: #999">Senha:&nbsp;</span> <input
									type="password" name="senha" value="${usereditdata.senha}">
								</label>
								<% if (request.getAttribute("usereditdata") != null) {%>
								<input type="hidden" value="true" name="editar">
								<input type="hidden" value="${usereditdata.id}" name="id">
								<%} %>
						<input style="margin-left: 150px" type="submit" value="Cadastrar">
					</form>
					
					<br />
	<hr>
	<h1>Registros</h1>

	<table>
		<tr style="font-weight: bold; text-align: center;">
			<td width="100px">Usuarios Cadastrados</td>
			<td width="100px"></td>
		</tr>

		<c:choose>
			<c:when test="${listauser != null && listauser.size() > 0}">
				<c:forEach items="${listauser}" var="d">
					<tr>
						<td><a href="cadastro?id=${d.id}">${d.usuario}</a></td>
						<td><a href="cadastro?ide=${d.id}">X</a>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2"><p>Não há registros</p></td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>
				</div>
			</div>
		</div>
		<div class="footer">
			<span class="bottom-shadow"></span>
		</div>
	<!-- /#adbox -->
	<%
		if (request.getAttribute("erro") != null) {
	%>
	<script>
		alert("Login ou senha incorretos!");
	</script>
	<%
		}
	%>
</body>
</html>

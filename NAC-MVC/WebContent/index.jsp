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
	<c:import url="header.jsp" />
	<div id="adbox">
		<div class="body">
			<div class="details">
				<c:choose>
					<c:when test="${status != null}">
				${status}
			</c:when>
				</c:choose>
				<div id="box-login">

					<form action="Login" method="post">
						<c:choose>
							<c:when test="${sessionScope.logininfo == null}">
								<h1>Login</h1>
								<%
									String usuario = new String();
											Cookie[] cookies = request.getCookies();
											if (cookies != null) {
												for (int i = 0; i < cookies.length; i++) {
													if (cookies[i].getName().equals("usuario")) {
														usuario = cookies[i].getValue();
														break;
													}
												}

											}
											//if (cookies != null) {
											//usuario = cookies[cookies.length-1].getValue();
											//}
								%>
								<label> <span style="color: #999">Login:&nbsp;</span> <input
									type="text" name="login" value="<%=usuario%>">
								</label>
								<label> <span style="color: #999">Senha:&nbsp;</span> <input
									type="password" name="passwd">
								</label>

								<input type="checkbox" name="lembrar" style="margin-left: 150px"
									value="true"> Lembrar de mim
						<input style="margin-left: 150px" type="submit" value="Logar">
							</c:when>
							<c:otherwise>
								<h1>Sistema de Locação de Veículos LDTA</h1>
							</c:otherwise>
						</c:choose>
					</form>
				</div>
			</div>
		</div>
		<div class="footer">
			<span class="bottom-shadow"></span>
		</div>
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

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro Marcas</title>
<link rel="stylesheet" href="css/style.css" type="text/css" charset="utf-8" />
</head>
<body>
	<c:import url="header.jsp"/>
<div id="adbox">
		<div class="body">
			<div class="details">
				<c:choose>
			<c:when test="${status != null}">
				${status}
			</c:when>
		</c:choose>
	<h1>Cadastro de Marcas</h1>
	<form action="marcas" method="post">
		<c:choose>
			<c:when test="${edicao != null && edicao.equals('true')}">
				<p>Digite a nova marca e confirme para atualizar</p>
				<input type="hidden" value="false" name="cadastro">
				<input type="hidden" value="${marcaedit.id}" name="id">
				<table>
					<tr>
						<td><input type="text" name="descricao"
							value="${marcaedit.descricao}" /></td>
						<td><input type="submit" value="Salvar" name="btnSalvar"></td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<input type="hidden" value="true" name="cadastro">
				<table>
					<tr>
						<td><input type="text" name="descricao"
							placeholder="Descrição" /></td>
						<td><input type="submit" value="Salvar" name="btnSalvar"></td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</form>
	<br />
	<hr>
	<h1>Registros</h1>

	<table>
		<tr style="font-weight: bold; text-align: center;">
			<td width="100px">Marca</td>
			<td>Ops</td>
		</tr>

		<c:choose>
			<c:when test="${listagemMarcas != null && listagemMarcas.size() > 0}">
				<c:forEach items="${listagemMarcas}" var="d">
					<tr>
						<td><a href="marcas?edit=1&id=${d.id}">${d.descricao}</a></td>
						<td><a href="marcas?delete=1&id=${d.id}">X</a></td>
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
<c:import url="footer.jsp"/>
</div>
</body>
</html>
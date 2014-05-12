<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Pre�os</title>
	
</head>
<body>
	<c:choose>
		<c:when test="${status != null}">
			${status}
		</c:when>
	</c:choose>
		<h1>Cadastro de Pre�os</h1>
	<form action="precos" method="post">
		<c:choose>
			<c:when test="${edicao != null && edicao.equals('true')}">
				<p>Digite p novo pre�o e confirme para atualizar</p>
				<input type="hidden" value="false" name="cadastro">
				<input type="hidden" value="${precoedit.id}" name="id">
				<table>
					<tr>
						<td><input type="text" name="descricao"
							value="${maprecoedit.descricao}" /></td>
							<td><input type="text" name="valor"
							value="${maprecoedit.valor}" /></td>
						<td><input type="submit" value="Salvar" name="btnSalvar"></td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<input type="hidden" value="true" name="cadastro">
				<table>
					<tr>
						<td><input type="text" name="descricao"
							placeholder="Descri��o" /></td>
							<td><input type="text" name="valor"
							placeholder="Valor" /></td>
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
			<td width="100px">Pre�o</td>
			<td>Ops</td>
		</tr>

		<c:choose>
			<c:when test="${listagemPrecos != null && listagemPrecos.size() > 0}">
				<c:forEach items="${listagemPrecos}" var="d">
					<tr>
						<td><a href="preco?edit=1&id=${d.id}">${d.descricao}</a></td>
						<td>${d.valor}</td>
						<td><a href="preco?delete=1&id=${d.id}">X</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2"><p>N�o h� registros</p></td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>
</body>
</html>
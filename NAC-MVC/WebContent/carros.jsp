<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Carros</title>
</head>
<body>
	<c:choose>
		<c:when test="${status != null}">
			${status}
		</c:when>
	</c:choose>
	<h1>Cadastro de Carros</h1>
	<form action="clientes" method="post">
		<c:choose>
			<c:when test="${edicao != null && edicao.equals('true')}">
				<p>Digite os dados corretos e confirme para atualizar</p>
				<input type="hidden" value="false" name="cadastro">
				<input type="hidden" value="${clienteedit.id}" name="id">
				<table>
					<tr>
						<td><input type="text" name="nome"
							placeholder="Nome" value="${clienteedit.nome}" /></td>
							<td><input type="text" name="sobrenome"
							placeholder="Sobrenome" value="${clienteedit.sobrenome}" /></td>
							<td><input type="text" name="cpf"
							placeholder="CPF" value="${clienteedit.cpf}" /></td>
							<td><input type="text" name="logradouro"
							placeholder="Logradouro" value="${clienteedit.logradouro}" /><input type="text" name="num"
							placeholder="Número" value="${clienteedit.logradouro_num}" /></td>
							<td><input type="text" name="bairro"
							placeholder="Bairro" value="${clienteedit.bairro}" /></td>
							<td><input type="text" name="cep"
							placeholder="CEP" value="${clienteedit.cep}" /></td>
							<td><input type="text" name="nascimento"
							placeholder="Nascimento" value="${clienteedit.nascimento}" /></td>
						<td><input type="submit" value="Salvar" name="btnSalvar"></td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<input type="hidden" value="true" name="cadastro">
				<table>
					<tr>
						<td><input type="text" name="nome"
							placeholder="Nome" /></td>
							<td><input type="text" name="sobrenome"
							placeholder="Sobrenome" /></td>
							<td><input type="text" name="cpf"
							placeholder="CPF" /></td>
							<td><input type="text" name="logradouro"
							placeholder="Logradouro" /><input type="text" name="num"
							placeholder="Número" /></td>
							<td><input type="text" name="bairro"
							placeholder="Bairro" /></td>
							<td><input type="text" name="cep"
							placeholder="CEP" /></td>
							<td><input type="text" name="nascimento"
							placeholder="nascimento" /></td>
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
			<td width="10px">ID</td>
			<td width="150px">ID Marca</td>
			<td width="150px">Modelo</td>
			<td width="100px">Placa</td>
			<td width="200px">Ano</td>
			<td width="10px">KM</td>
			<td width="100px">Preco</td>
			<td> </td>
		</tr>

		<c:choose>
			<c:when test="${listagemCarros != null && listagemCarros.size() > 0}">
				<c:forEach items="${listagemCarros}" var="d">
					<tr>
						<td><a href="carros?edit=1&id=${d.id}">${d.id}</a></td>
						<td>${d.idmarca}</td>
						<td>${d.modelo}</td>
						<td>${d.placa}</td>
						<td>${d.ano}</td>
						<td>${d.km}</td>
						<td>${d.idpreco}</td>
						<td><a href="carros?delete=1&id=${d.id}">X</a></td>
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

</body>
</html>
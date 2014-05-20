<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Cadastro de Carro</title>
	<link rel="stylesheet" href="css/style.css" type="text/css" charset="utf-8" />		
</head>

<body>
	<div id="header">
		<div id="logo">
			<a href="index.html" ><img src="images/patetaMotorista.jpg" alt="Logo" /></a>
		</div>
		<div id="navigation">
			<ul>
				<li class="first selected"><a href="index.html">Home</a></li>
				<li><a href="clientes">Cadastro Cliente</a></li>
				<li><a href="marcas">Cadastro de Marcas</a></li>
				<li><a href="carros">Cadastro de Automóveis</a></li>
				<li><a href="precos" >Cadastro de Preços</a></li>
				<li><a href="locacao">Locação</a></li>
			</ul>
		</div>
	</div> <!-- /#header -->
	<div id="adbox">
		<div class="body">
			<div class="details">
			<c:choose>
			<c:when test="${status != null}">
				${status}
			</c:when>
		</c:choose>
		<span class="titulo">Cadastro de Carros</span>
		<form action="carros" method="post">
			<c:choose>
				<c:when test="${edicao != null && edicao.equals('true')}">
					<p>Digite os dados corretos e confirme para atualizar</p>
					<input type="hidden" value="false" name="cadastro">
					<input type="hidden" value="${carroedit.id}" name="id">
						<select name="marcas">
							<option value="${carroedit.idmarca}" selected>Manter marca atual</option>
							<c:forEach items="${listademarcas}" var="g">
								<option value="${g.id}">${g.descricao}</option>
							</c:forEach>
							</select> 
									</td>
									
								<td>	
								<input type="text" name="modelo" value="${carroedit.modelo}"/> </td>
								<td><input type="text" name="placa"
								value="${carroedit.placa}" /></td>
								<td><input type="text" name="ano"
								value="${carroedit.ano}" /></td>
								<td><input type="text" name="km"
								value="${carroedit.km}" /></td>
								<td>
							
							<select name="preco">
							<option value="${carroedit.idpreco}" selected>Manter tabela de preco atual</option>
							<c:forEach items="${listadeprecos}" var="h">
								<option value="${h.id}">${h.descricao}</option>
							</c:forEach>
							</select> 
									</td>
						
					<input type="submit" value="Salvar" name="btnSalvar">
				</c:when>
				<c:otherwise>
					<input type="hidden" value="true" name="cadastro">
					<table>
						<tr>
							<td>
							
							<select name="marcas">
							<c:forEach items="${listademarcas}" var="g">
								<option value="${g.id}">${g.descricao}</option>
							</c:forEach>
							</select> 
									</td>
									
								<td>	
								<input type="text" name="modelo"
								placeholder="Modelo" /> </td>
								<td><input type="text" name="placa"
								placeholder="Placa" /></td>
								<td><input type="text" name="ano"
								placeholder="Ano" /></td>
								<td><input type="text" name="km"
								placeholder="KM" /></td>
								<td>
							
							<select name="preco">
							<c:forEach items="${listadeprecos}" var="h">
								<option value="${h.id}">${h.descricao}</option>
							</c:forEach>
							</select> 
									</td>
								
							<td><input type="submit" value="Salvar" name="btnSalvar"></td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
			<hr>
		<h1>Registros</h1>
	
		<table>
			<tr style="font-weight: bold; text-align: center;">
				<td width="10px">ID</td>
				<td width="150px">Marca</td>
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
							<td>${d.marca}</td>
							<td>${d.modelo}</td>
							<td>${d.placa}</td>
							<td>${d.ano}</td>
							<td>${d.km}</td>
							<td>${d.preco}</td>
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
		</form>
			</div>
		</div>
		<div class="footer">
			<span class="bottom-shadow"></span>
		</div>
	</div> <!-- /#adbox -->	
</body>
</html>
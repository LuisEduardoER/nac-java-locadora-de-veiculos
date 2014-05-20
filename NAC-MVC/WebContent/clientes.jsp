<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cadastro de Clientes</title>
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
	</div>
	<div id="adbox">
		<div class="body">
			<div class="details">
				<c:choose>
			<c:when test="${status != null}">
				${status}
			</c:when>
		</c:choose>
		<span class="titulo">Cadastro de Carros</span>
			<form action="clientes" method="post" class="formcadastro">
				<c:choose>
					<c:when test="${edicao != null && edicao.equals('true')}">
						<p>Digite os dados corretos e confirme para atualizar</p>
						<input type="hidden" value="false" name="cadastro">
						<input type="hidden" value="${clienteedit.id}" name="id">
						
						<span >Nome:</span>
							<input type="text" name="nome"	placeholder="Nome" value="${clienteedit.nome}" />
						<br/>
						<span>Sobrenome:</span>
							<input type="text" name="sobrenome"	placeholder="Sobrenome" value="${clienteedit.sobrenome}" />
						<br/>
						<span>CPF:</span>
							<input type="text" name="cpf"
									placeholder="CPF" value="${clienteedit.cpf}" />
						<br/>
						<span>Logradouro:</span>
							<input type="text" name="logradouro" placeholder="Logradouro" value="${clienteedit.logradouro}" /> / <input type="text" name="num"	placeholder="Número" value="${clienteedit.logradouro_num}" />
						<br/>
						<span>Bairro:</span>
							<input type="text" name="bairro" placeholder="Bairro" value="${clienteedit.bairro}" />
						<br/>
						<span>CEP:</span>
							<input type="text" name="cep" placeholder="CEP" value="${clienteedit.cep}" />
						<br/>
						<span>Data de Nascimento:</span>
							<input type="text" name="nascimento" placeholder="Nascimento" value="${clienteedit.nascimento}" />
						<br/>		
						<input type="submit" value="Salvar" name="btnSalvar">
					</c:when>
					<c:otherwise>
						<input type="hidden" value="true" name="cadastro">
								<span class="dados">Nome:</span>
								<span>
									<input type="text" name="nome"placeholder="Nome" />
								</span>
								<br>
								<span>Sobrenome:</span>
								<span>
									<input type="text" name="sobrenome"placeholder="Sobrenome" />
								</span>
								<br>
								<span>CPF:</span>
								<span>
									<input type="text" name="cpf" placeholder="CPF" />
								</span>
								<br>
								<span>Logradouro:</span>
								<span>
									<input type="text" name="logradouro" placeholder="Logradouro" />/
									<input type="text" name="num" placeholder="Número" />
								</span>
								<br>
								<span>Bairro:</span>
								<span>
									<input type="text" name="bairro"	placeholder="Bairro" />
								</span>
								<br>
								<span>CEP:</span>
								<span>
									<input type="text" name="cep"	placeholder="CEP" />
								</span>
								<br>
								<span>Data de Nascimento:</span>
								<span>
									<input type="text" name="nascimento" placeholder="nascimento" />
								</span>
								<br>
								<span>
									<input type="submit" value="Salvar" name="btnSalvar">
								</span>
							
					</c:otherwise>
				</c:choose>
			<hr>
					<br />
					<hr>
					<h1>Registros</h1>
					
					<table>
						<tr style="font-weight: bold; text-align: center;">
							<td width="10px">ID</td>
							<td width="150px">Nome</td>
							<td width="150px">Sobrenome</td>
							<td width="100px">CPF</td>
							<td width="200px">Logradouro</td>
							<td width="10px">Numero</td>
							<td width="100px">Bairro</td>
							<td width="100px">CEP</td>
							<td width="100px">Nascimento</td>
							<td> </td>
						</tr>
					
						<c:choose>
							<c:when test="${listagemClientes != null && listagemClientes.size() > 0}">
								<c:forEach items="${listagemClientes}" var="d">
									<tr>
										<td><a href="clientes?edit=1&id=${d.id}">${d.id}</a></td>
										<td>${d.nome}</td>
										<td>${d.sobrenome}</td>
										<td>${d.cpf}</td>
										<td>${d.logradouro}</td>
										<td>${d.logradouro_num}</td>
										<td>${d.bairro}</td>
										<td>${d.cep}</td>
										<td>${d.nascimento}</td>
										<td><a href="clientes?delete=1&id=${d.id}">X</a></td>
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
	</div>
</body>
</html>
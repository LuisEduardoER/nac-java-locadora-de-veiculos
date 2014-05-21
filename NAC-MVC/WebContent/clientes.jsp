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
	<%if(session.getAttribute("logininfo") == null){ response.sendRedirect("index.jsp");}%>
	<c:import url="header.jsp"/>
	<div id="adbox">
		<div class="body"  style="min-height: 400px">
			<div class="details">
				<c:choose>
			<c:when test="${status != null}">
				${status}
			</c:when>
		</c:choose>
		<h1>Cadastro de Clientes</h1>
			<form action="clientes" method="post" class="formcadastro">
				<c:choose>
					<c:when test="${edicao != null && edicao.equals('true')}">
						<p>Digite os dados corretos e confirme para atualizar</p>
						<input type="hidden" value="false" name="cadastro">
						<input type="hidden" value="${clienteedit.id}" name="id">
						
						<label>
							<span>Nome:&nbsp;</span>
							<input type="text" name="nome"	value="${clienteedit.nome}" />
						</label>
						<label>
						<span>Sobrenome:&nbsp;</span>
							<input type="text" name="sobrenome"	value="${clienteedit.sobrenome}" />
						</label>
						<label>
						<span>CPF:&nbsp;</span>
							<input type="text" name="cpf"
									value="${clienteedit.cpf}" />
					    </label>
					    <label>
						<span>Logradouro:&nbsp;</span>
							<input type="text" name="logradouro" value="${clienteedit.logradouro}" /> / <input type="text" name="num"	placeholder="Número" value="${clienteedit.logradouro_num}" />
						</label>
						<label>
							<span>Bairro:&nbsp;</span>
							<input type="text" name="bairro"  value="${clienteedit.bairro}" />
						</label>
						<label>						
							<span>CEP:&nbsp;</span>
							<input type="text" name="cep" value="${clienteedit.cep}" />
						</label>
						<label>
							<span>Data de Nascimento:&nbsp;</span>
							<input type="text" name="nascimento" value="${clienteedit.nascimento}" />
						</label>	
						<label>
							<input type="submit" value="Salvar" name="btnSalvar">
						</label>
					</c:when>
					<c:otherwise>
						<label>
							<input type="hidden" value="true" name="cadastro">
							<span class="dados">Nome:&nbsp;</span>
							<input type="text" name="nome"/>
						</label>
						<label>
							<span>Sobrenome:&nbsp;</span>
							<input type="text" name="sobrenome"/>
						</label>
						<label>
							<span>CPF:&nbsp;</span>
							<input type="text" name="cpf"/>
						</label>
						<label>
							<span>Logradouro:&nbsp;</span>
							<input type="text" name="logradouro"/>/
							<input type="text" name="num"/>
						</label>
						<label>
							<span>Bairro:&nbsp;</span>
							<input type="text" name="bairro"/>
						</label>
						<label>
							<span>CEP:&nbsp;</span>
							<input type="text" name="cep"/>
						</label>
						<label>
							<span>Data de Nascimento:&nbsp;</span>
							<input type="text" name="nascimento"/>
						</label>
						<label>
							<input type="submit" value="Salvar" name="btnSalvar">
						</label>
					</c:otherwise>
				</c:choose>
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
							<td width="100px">Pontos</td>
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
										<td>${d.pontos}</td>
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
		<c:import url="footer.jsp"/>
	</div>
</body>
</html>
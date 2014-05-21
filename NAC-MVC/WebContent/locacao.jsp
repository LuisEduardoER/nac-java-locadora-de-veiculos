<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Controle de Locações</title>
	<link rel="stylesheet" href="css/style.css" type="text/css" charset="utf-8" />		
</head>
<body>
<%if(session.getAttribute("logininfo") == null){ response.sendRedirect("index.jsp");}%>
<c:import url="header.jsp"/>
		<div id="adbox">
		<div class="body">
			<div class="details">
			<c:choose>
			<c:when test="${status != null}">
				${status}
			</c:when>
		</c:choose>
		<h1>Locação de Carros</h1>
		<form action="locacao" method="post">
					<input type="hidden" value="true" name="cadastro">
					<label>						
						<span>Carro:&nbsp;</span>
						<select name="idcarro">
							<option value="-1" selected>Selecione...</option>
								<c:forEach items="${listagemCarros}" var="g">
									<option value="${g.id}">${g.modelo} - ${g.placa}</option>
								</c:forEach>
						</select>
					</label> 
					<label> 
						<span>Cliente:&nbsp;</span>
							<select name="idcliente">
							<option value="-1" selected>Selecione...</option>
								<c:forEach items="${listagemClientes}" var="i">
									<option value="${i.id}">${i.nome} ${i.sobrenome}</option>
								</c:forEach>
							</select>
					</label>
									
					<label>
						<span>Data da Locação:&nbsp;</span>
						<input type="text" name="data_loc"
								value="" />
					</label>
					<label>
						<span>Qtd. de dias:&nbsp;</span>
						<input type="text" name="qtd_dias"
								value="" />
					</label>
					<label>
						<span>Pagamento:&nbsp;</span>
						<input type="text" name="ds_pagamento"
								value="" />
					</label>
					<label>
						<span>Observações:&nbsp;</span>
						<textarea name="data_loc">
							
						</textarea>
					</label>
					
					<label>
						<input type="submit" value="Salvar" name="btnSalvar">
					</label>		
			<hr>
		<h1>Registros</h1>
	
		<table>
			<tr style="font-weight: bold; text-align: center;">
				<td width="10px">ID</td>
				<td width="150px">Carro</td>
				<td width="150px">Preço</td>
				<td width="100px">Cliente</td>
				<td width="200px">Data da Locação</td>
				<td width="10px">Qtd de dias</td>
				<td width="100px">Data de Entrega</td>
				<td>Situação</td>
				<td>Desc. Pagamento</td>
				<td>Obs.</td>
			</tr>
	
			<c:choose>
				<c:when test="${listagemLocacao != null && listagemLocacao.size() > 0}">
					<c:forEach items="${listagemLocacao}" var="d">
						<tr>
							<td>${d.id}</td>
							<td>${d.carro}</td>
							<td>${d.preco}</td>
							<td>${d.cliente}</td>
							<td>${d.data_loc}</td>
							<td>${d.qtdDias}</td>
							<td>${d.data_entrega}</td>
							<td>${d.dsSituacao}</td>
							<td>${d.dsPagamento}</td>
							<td>${d.obs}</td>
							<td><a href="locacao?action=devolve&id=${d.id}">Devolver</a></td>
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
	</div> <!-- /#adbox -->	
</body>
</html>
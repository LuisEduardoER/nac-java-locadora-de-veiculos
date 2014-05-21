package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Carro;
import info.filipe.sis.nac.bean.Cliente;
import info.filipe.sis.nac.bean.Locacao;
import info.filipe.sis.nac.dao.CarroDAO;
import info.filipe.sis.nac.dao.ClienteDAO;
import info.filipe.sis.nac.dao.LocacaoDAO;
import info.filipe.sis.nac.dao.PrecoDAO;
import info.filipe.sis.nac.login.Logon;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/locacao")
public class LocacaoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	LocacaoDAO dao = new LocacaoDAO();
	Logon logon = new Logon();
	CarroDAO cdao = new CarroDAO();
	ClienteDAO cldao = new ClienteDAO();
	PrecoDAO pdao = new PrecoDAO();

	public LocacaoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(logon.isLogado(request, response)){
//			if (request.getParameter("delete") != null
//					&& request.getParameter("delete").equals("1")) {
//				deletar(request, response);
//			} else 
			if (request.getParameter("edit") != null
					&& request.getParameter("edit").equals("1")) {
				selecteditar(request, response);
			} else {
				listar(request, response);
			}			
		} else {
			response.sendRedirect("login.jsp");
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(logon.isLogado(request, response)){
			if (request.getParameter("cadastro") != null
					&& request.getParameter("cadastro").equals("true")) {
				// inclusao
				cadastro(request, response);
			} else if (request.getParameter("cadastro") != null
					&& request.getParameter("cadastro").equals("false")) {
				// update
				atualizar(request, response);
			}
		}
	}
	
	private void selecteditar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Locacao l = dao.getPK(Integer.parseInt(request.getParameter("id")));
		ArrayList<Carro> ca = cdao.getAll();
		
		ArrayList<Cliente> cl = cldao.getAll();

		request.setAttribute("listadeCarros", ca);
		request.setAttribute("listadeClientes", cl);

		request.setAttribute("edicao", "true");
		request.setAttribute("locacaoedit", l);

		listar(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = "locacao.jsp";

		ArrayList<Locacao> locacao = new ArrayList<Locacao>();
		
		locacao = dao.getAll();
		
		ArrayList<Carro> ca = cdao.getAll();
		
		ArrayList<Cliente> cl = cldao.getAll();

		request.setAttribute("listagemLocacao", locacao);
		request.setAttribute("listagemCarros", ca);
		request.setAttribute("listagemClientes", cl);

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
	
	private void atualizar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Locacao l = new Locacao();

		l.setId(Integer.parseInt(request.getParameter("id")));
		l.setIdCliente(Integer.parseInt(request.getParameter("idcliente")));
		l.setIdCarro(Integer.parseInt(request.getParameter("idcarro")));
		l.setIdPreco(Integer.parseInt(request.getParameter("idpreco")));
		l.setQtdDias(Integer.parseInt(request.getParameter("qtd_dias")));
		l.setDsPagamento(request.getParameter("ds_pagamento"));
		l.setDsSituacao(request.getParameter("ds_situacao"));
		l.setData_entrega(request.getParameter("data_entrega"));
		l.setData_loc(request.getParameter("data_loc"));

		if (dao.devolucaoVeiculo(l)) {
			request.setAttribute("status", "Locação atualizada com sucesso!");
		} else {
			request.setAttribute("status",
					"Ocorreu uma falha ao atualizar locação.");
		}

		listar(request, response);
	}
	
	private void cadastro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Locacao l = new Locacao();

		l.setIdCliente(Integer.parseInt(request.getParameter("idcliente")));
		l.setIdCarro(Integer.parseInt(request.getParameter("idcarro")));
	    l.setIdPreco(pdao.getIdPreco(Integer.parseInt(request.getParameter("idcarro"))));
		l.setQtdDias(Integer.parseInt(request.getParameter("qtd_dias")));
		l.setDsPagamento(request.getParameter("ds_pagamento"));
		l.setDsSituacao(request.getParameter("ds_situacao"));
		l.setData_entrega((request.getParameter("data_entrega")));
		l.setData_loc((request.getParameter("data_loc")));

		if (dao.locarVeiculo(l)) {
			request.setAttribute("cadastro", "true");
			request.setAttribute("status", "Locação gerada com sucesso!");
		} else {
			request.setAttribute("cadastro", "false");
			request.setAttribute("status", "Falha ao gerar locação.");
		}

		listar(request, response);
	}
	
}

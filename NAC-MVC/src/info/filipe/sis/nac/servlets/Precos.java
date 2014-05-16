package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Preco;
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

@WebServlet("/precos")
public class Precos extends HttpServlet{
	private static final long serialVersionUID = 1L;
	PrecoDAO dao = new PrecoDAO();
	Logon logon = new Logon();
	
	public Precos(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(logon.isLogado(request, response)){
			if (request.getParameter("delete") != null && request.getParameter("delete").equals("1")) {
				deletar(request, response);
			} else if(request.getParameter("edit") != null && request.getParameter("edit").equals("1")){
				selecteditar(request, response);
			} else {
				listar(request, response);
			} 			
		} else {
			response.sendRedirect("login.jsp");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(logon.isLogado(request, response)){
			if (request.getParameter("cadastro") != null && request.getParameter("cadastro").equals("true")) {
				//inclusao
				cadastro(request, response);
			} else if (request.getParameter("cadastro") != null && request.getParameter("cadastro").equals("false")){
				//update
				atualizar(request, response);
			}			
		} else {
			response.sendRedirect("login.jsp");
		}
	}
	
	public void cadastro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Preco p = new Preco();
		
		p.setDescricao(request.getParameter("descricao"));
		p.setValor(Double.parseDouble(request.getParameter("valor")));
		
		if (dao.inserirPreco(p)) {
			request.setAttribute("cadastro", "true");
			request.setAttribute("status", "Preco inserido com sucesso!");
		}else{
			request.setAttribute("cadastro", "false");
			request.setAttribute("cadastro", "Falha ao inserir preco.");
		}
		
		listar(request, response);
	}
	
	private void deletar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if(dao.deletarPreco(Integer.parseInt(request.getParameter("id"))) == true){
			request.setAttribute("delete", "true");
			request.setAttribute("status", "Preco deletado com sucesso!");
		} else {
			request.setAttribute("delete", "false");
			request.setAttribute("status", "Ocorreu uma falha ao deletar o preco.");
		}
		
		listar(request, response);
	}
	
	private void selecteditar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Preco c = dao.getPK(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("edicao", "true");
		request.setAttribute("precoedit", c);
		
		listar(request, response);
	}
	
	private void atualizar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Preco p = new Preco();
		
		p.setId(Integer.parseInt(request.getParameter("id")));
		p.setDescricao(request.getParameter("descricao"));
		p.setValor(Double.parseDouble(request.getParameter("valor")));
		
		if(dao.atualizarPreco(p)){
			request.setAttribute("status", "Preco atualizado com sucesso!");
		} else{
			request.setAttribute("status", "Ocorreu uma falha ao atualizar preco.");
		}
		
		listar(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = "preco.jsp";

		ArrayList<Preco> precos = new ArrayList<Preco>();

		precos = dao.getAll();
		
		request.setAttribute("listagemPrecos", precos);

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}

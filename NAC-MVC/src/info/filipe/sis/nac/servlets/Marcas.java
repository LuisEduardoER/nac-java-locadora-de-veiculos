package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Marca;
import info.filipe.sis.nac.dao.MarcasDAO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/marcas")
public class Marcas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MarcasDAO dao = new MarcasDAO();

	public Marcas() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("delete") != null && request.getParameter("delete").equals("1")) {
			deletar(request, response);
		} else if(request.getParameter("edit") != null && request.getParameter("edit").equals("1")){
			selecteditar(request, response);
		} else {
			listar(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cadastro") != null && request.getParameter("cadastro").equals("true")) {
			//inclusao
			cadastro(request, response);
		} else if (request.getParameter("cadastro") != null && request.getParameter("cadastro").equals("false")){
			//update
			atualizar(request, response);
		}
	}

	private void cadastro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("descricao");

		Marca m = new Marca();

		m.setDescricao(nome);

		if (dao.inserirMarca(m)) {
			request.setAttribute("cadastro", "true");
			request.setAttribute("status", "Marca inserida com sucesso!");
		} else {
			request.setAttribute("cadastro", "false");
			request.setAttribute("status", "Falha ao inserir marca.");
		}

		listar(request, response);
	}

	private void deletar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if(dao.deletarMarca(Integer.parseInt(request.getParameter("id"))) == true){
			request.setAttribute("delete", "true");
			request.setAttribute("status", "Marca deletada com sucesso!");
		} else {
			request.setAttribute("delete", "false");
			request.setAttribute("status", "Ocorreu uma falha ao deletar a marca.");
		}
		
		listar(request, response);
	}
	
	private void selecteditar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Marca m = dao.getPK(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("edicao", "true");
		request.setAttribute("marcaedit", m);
		
		listar(request, response);
	}
	
	private void atualizar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Marca marca = new Marca();
		
		marca.setDescricao(request.getParameter("descricao"));
		marca.setId(Integer.parseInt(request.getParameter("id")));
		
		if(dao.atualizarMarca(marca)){
			request.setAttribute("status", "Marca atualizada com sucesso!");
		} else{
			request.setAttribute("status", "Ocorreu uma falha ao atualizar a marca.");
		}
		
		listar(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = "marcas.jsp";

		ArrayList<Marca> marcas = new ArrayList<Marca>();

		marcas = dao.getAll();
		
		request.setAttribute("listagemMarcas", marcas);

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}

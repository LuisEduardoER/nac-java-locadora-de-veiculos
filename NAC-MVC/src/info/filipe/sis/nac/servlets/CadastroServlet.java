package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Login;
import info.filipe.sis.nac.dao.CadastroDAO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CadastroDAO dao = new CadastroDAO();

	public CadastroServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Login lg = new Login();
		if(request.getParameter("id") != null){
			lg = dao.getPK(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("usereditdata", lg);
		}
		if(request.getParameter("ide") != null){
			dao.deletar(Integer.parseInt(request.getParameter("ide")));
			request.setAttribute("status", "Usuario deletado com sucesso!");
		}
		
		listar(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Login l = new Login();
		l.setUsuario(request.getParameter("usuario"));
		l.setSenha(request.getParameter("senha"));

		if(request.getParameter("editar") != null && request.getParameter("editar").equals("true")){
			l.setId(Integer.parseInt(request.getParameter("id")));
			if(dao.alteraSenha(l)) {
				request.setAttribute("status", "Usuario alterado com sucesso!");
				listar(request, response);
			}
		} else {
			if (dao.cadastro(l)) {
				request.setAttribute("status", "Usuario inserido com sucesso!");
				listar(request, response);
			}
			
		}

	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = "cadastro.jsp";

		ArrayList<Login> lo = new ArrayList<Login>();

		lo = dao.getAll();

		request.setAttribute("listauser", lo);

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}

package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.dao.ClienteDAO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pontuar")
public class PontuarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO dao = new ClienteDAO();

	String pagina = "devolucao.jsp";

	public PontuarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("idloc") != null) {
			int id = Integer.parseInt(request.getParameter("idloc"));
			if (dao.pontuar(id)) {
				request.setAttribute("pontuar",
						"Pontuado com sucesso. +1000 pontos.");
				dispatcher(request, response);
			} else {
				// falhou.
				request.setAttribute("erro", "Falha ao pontuar.");
				dispatcher(request, response);
			}
		} else {
			request.setAttribute("erro", "Impossível pontuar.");
			dispatcher(request, response);
		}
	}

	private void dispatcher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}

package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Carro;
import info.filipe.sis.nac.dao.CarroDAO;
import info.filipe.sis.nac.login.Logon;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/carros")
public class CarroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CarroDAO dao = new CarroDAO();
    Logon logon = new Logon();
    
    public CarroServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logon.isLogado(request, response)){
			listar(request, response);			
		} else {
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logon.isLogado(request, response)){
			listar(request, response);			
		} else {
			response.sendRedirect("login.jsp");
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = "carros.jsp";

		ArrayList<Carro> ca = new ArrayList<Carro>();

		ca = dao.getAll();
		
		request.setAttribute("listagemCarros", ca);

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}

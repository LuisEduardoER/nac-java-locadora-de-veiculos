package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Carro;
import info.filipe.sis.nac.dao.CarroDAO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/carros")
public class Carros extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CarroDAO dao = new CarroDAO();
    
    public Carros() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listar(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listar(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = "clientes.jsp";

		ArrayList<Carro> carros = new ArrayList<Carro>();

		carros = dao.getAll();
		
		request.setAttribute("listagemClientes", carros);

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}

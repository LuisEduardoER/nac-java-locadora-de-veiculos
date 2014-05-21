package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Carro;
import info.filipe.sis.nac.bean.Login;
import info.filipe.sis.nac.bean.Marca;
import info.filipe.sis.nac.bean.Preco;
import info.filipe.sis.nac.dao.CadastroDAO;
import info.filipe.sis.nac.login.Logon;

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
	Logon logon = new Logon();
    CadastroDAO dao = new CadastroDAO();
    
    public CadastroServlet() {
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
			Login l = new Login();
			l.setUsuario(request.getParameter("usuario"));
			l.setSenha(request.getParameter("senha"));
			
			if(dao.cadastro(l)){
				request.setAttribute("status", "Usuario inserido com sucesso!");
				listar(request, response);
			}
		} else {
			response.sendRedirect("login.jsp");
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

package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Login;
import info.filipe.sis.nac.dao.LoginDAO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginDAO dao = new LoginDAO();
	
	String pagina = "";
	
	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("logout") != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("logininfo", null);
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		
		if(!request.getParameter("login").equals("") && !request.getParameter("passwd").equals("")){
			Login l = new Login();
			l.setUsuario(request.getParameter("login"));
			l.setSenha(request.getParameter("passwd"));
			
			if(dao.verifyLogin(l)){
				HttpSession session = request.getSession(true);
				session.setAttribute("logininfo", l);
				pagina = "index.jsp";
				dispatcher(request, response);
			} else {
				pagina = "index.jsp";
				request.setAttribute("erro", true);
				dispatcher(request, response);
			}
			
		}
	}
	
	private void dispatcher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(!pagina.equals("")){
			RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
		}
	}

}

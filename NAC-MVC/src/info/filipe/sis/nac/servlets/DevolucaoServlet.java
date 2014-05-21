package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Locacao;
import info.filipe.sis.nac.dao.LocacaoDAO;
import info.filipe.sis.nac.login.Logon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/devolucao")
public class DevolucaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logon logon = new Logon();   
	LocacaoDAO dao = new LocacaoDAO();
	String pagina = "devolucao.jsp";

    public DevolucaoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (logon.isLogado(request, response)) {
			if(request.getParameter("id") != null){
				Locacao l = new Locacao();
				l.setId(Integer.parseInt(request.getParameter("id")));
				l.setIdCarro(Integer.parseInt(request.getParameter("carro")));
				if(dao.devolucaoVeiculo(l)){
					request.setAttribute("status", "Devolução registrada com sucesso.");
					dispatcher(request, response);
				} else {
					//falhou.
					request.setAttribute("erro", "Falha ao devolver veículo.");
					dispatcher(request, response);
				}
			} else {
				request.setAttribute("erro", "Impossível devolver veículo. Favor informar ID.");
				dispatcher(request, response);
			}
		} else {
			response.sendRedirect("login.jsp");
		}
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//not available
	}
	
	private void dispatcher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}

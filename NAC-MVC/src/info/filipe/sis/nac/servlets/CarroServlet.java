package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Carro;
import info.filipe.sis.nac.bean.Marca;
import info.filipe.sis.nac.bean.Preco;
import info.filipe.sis.nac.dao.CarroDAO;
import info.filipe.sis.nac.dao.MarcasDAO;
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

@WebServlet("/carros")
public class CarroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CarroDAO dao = new CarroDAO();
    MarcasDAO mdao = new MarcasDAO();
    Logon logon = new Logon();
    PrecoDAO pdao = new PrecoDAO();
    
    public CarroServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Carro carro = new Carro();
		
		carro.setAno(Integer.parseInt(request.getParameter("ano")));
		carro.setIdmarca(Integer.parseInt(request.getParameter("marcas")));
		carro.setKm(Float.parseFloat(request.getParameter("km")));
		carro.setModelo(request.getParameter("modelo"));
		carro.setIdpreco(Integer.parseInt(request.getParameter("preco")));
		carro.setPlaca(request.getParameter("placa"));
		
		if (dao.inserirCarro(carro)) {
			request.setAttribute("cadastro", "true");
			request.setAttribute("status", "Carro inserido com sucesso!");
		}else{
			request.setAttribute("cadastro", "false");
			request.setAttribute("status", "Falha ao inserir carro.");
		}
		
		listar(request, response);
	}
	
	private void selecteditar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Carro car = dao.getPK(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("edicao", "true");
		request.setAttribute("carroedit", car);
		
		listar(request, response);
	}
	
	private void atualizar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Carro carro = new Carro();
		
		carro.setAno(Integer.parseInt(request.getParameter("ano")));
		carro.setIdmarca(Integer.parseInt(request.getParameter("marcas")));
		carro.setKm(Float.parseFloat(request.getParameter("km")));
		carro.setModelo(request.getParameter("modelo"));
		carro.setIdpreco(Integer.parseInt(request.getParameter("preco")));
		carro.setPlaca(request.getParameter("placa"));
		carro.setId(Integer.parseInt(request.getParameter("id")));
		
		if (dao.atualizarCarro(carro)) {
			request.setAttribute("status", "Carro atualizado com sucesso!");
		}else{
			request.setAttribute("status", "Ocorreu uma falha ao atualizar o carro.");
		}
		
		listar(request, response);
	}
	
	private void deletar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if(dao.deletarCarro(Integer.parseInt(request.getParameter("id"))) == true){
			request.setAttribute("delete", "true");
			request.setAttribute("status", "Carro deletado com sucesso!");
		} else {
			request.setAttribute("delete", "false");
			request.setAttribute("status", "Ocorreu uma falha ao deletar o carro.");
		}
		
		listar(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = "carros.jsp";

		ArrayList<Carro> ca = new ArrayList<Carro>();

		ca = dao.getAll();
		
		ArrayList<Marca> ma = mdao.getAll();
		
		ArrayList<Preco> pa = pdao.getAll();
		
		request.setAttribute("listagemCarros", ca);
		request.setAttribute("listademarcas", ma);
		request.setAttribute("listadeprecos", pa);

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}

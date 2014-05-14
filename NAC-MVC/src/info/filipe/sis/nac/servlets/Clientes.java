package info.filipe.sis.nac.servlets;

import info.filipe.sis.nac.bean.Cliente;
import info.filipe.sis.nac.bean.Login;
import info.filipe.sis.nac.dao.ClienteDAO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/clientes")
public class Clientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO dao = new ClienteDAO();

	public Clientes() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(isLogado(request, response)){
			if (request.getParameter("delete") != null
					&& request.getParameter("delete").equals("1")) {
				deletar(request, response);
			} else if (request.getParameter("edit") != null
					&& request.getParameter("edit").equals("1")) {
				selecteditar(request, response);
			} else {
				listar(request, response);
			}			
		} else {
			response.sendRedirect("login.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if(isLogado(request, response)){
			if (request.getParameter("cadastro") != null
					&& request.getParameter("cadastro").equals("true")) {
				// inclusao
				cadastro(request, response);
			} else if (request.getParameter("cadastro") != null
					&& request.getParameter("cadastro").equals("false")) {
				// update
				atualizar(request, response);
			}
		}
	}

	private void cadastro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Cliente c = new Cliente();

		c.setNome(request.getParameter("nome"));
		c.setSobrenome(request.getParameter("sobrenome"));
		c.setCpf(request.getParameter("cpf"));
		c.setLogradouro(request.getParameter("logradouro"));
		c.setLogradouro_num(request.getParameter("num"));
		c.setBairro(request.getParameter("bairro"));
		c.setCep(request.getParameter("cep"));
		c.setNascimento(request.getParameter("nascimento"));

		if (dao.inserirCliente(c)) {
			request.setAttribute("cadastro", "true");
			request.setAttribute("status", "Cliente inserido com sucesso!");
		} else {
			request.setAttribute("cadastro", "false");
			request.setAttribute("status", "Falha ao inserir cliente.");
		}

		listar(request, response);
	}

	private void deletar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (dao.deletarCliente(Integer.parseInt(request.getParameter("id"))) == true) {
			request.setAttribute("delete", "true");
			request.setAttribute("status", "Cliente deletado com sucesso!");
		} else {
			request.setAttribute("delete", "false");
			request.setAttribute("status",
					"Ocorreu uma falha ao deletar o cliente.");
		}

		listar(request, response);
	}

	private void selecteditar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cliente c = dao.getPK(Integer.parseInt(request.getParameter("id")));

		request.setAttribute("edicao", "true");
		request.setAttribute("clienteedit", c);

		listar(request, response);
	}

	private void atualizar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cliente c = new Cliente();

		c.setId(Integer.parseInt(request.getParameter("id")));
		c.setNome(request.getParameter("nome"));
		c.setSobrenome(request.getParameter("sobrenome"));
		c.setCpf(request.getParameter("cpf"));
		c.setLogradouro(request.getParameter("logradouro"));
		c.setLogradouro_num(request.getParameter("num"));
		c.setBairro(request.getParameter("bairro"));
		c.setCep(request.getParameter("cep"));
		c.setNascimento(request.getParameter("nascimento"));

		if (dao.atualizarCliente(c)) {
			request.setAttribute("status", "Cliente atualizado com sucesso!");
		} else {
			request.setAttribute("status",
					"Ocorreu uma falha ao atualizar cliente.");
		}

		listar(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = "clientes.jsp";

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		clientes = dao.getAll();

		request.setAttribute("listagemClientes", clientes);

		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

	private Boolean isLogado(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("logininfo") != null){
			Login l = (Login) session.getAttribute("logininfo");
			if (!l.getUsuario().equals("") && !l.getSenha().equals("")) {
				return true;
			}			
		}

		return false;
	}

}

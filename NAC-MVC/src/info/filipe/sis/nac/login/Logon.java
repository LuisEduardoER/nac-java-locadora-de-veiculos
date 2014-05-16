package info.filipe.sis.nac.login;

import info.filipe.sis.nac.bean.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logon {
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	public Logon(){
		request = null;
		response = null;
	}
	
	public Logon(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		
		isLogado(this.request, this.response);
	}
	
	public Boolean isLogado(HttpServletRequest request,
			HttpServletResponse response) {
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

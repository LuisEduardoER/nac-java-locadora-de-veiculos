package info.filipe.sis.nac.dao;

import info.filipe.sis.nac.bean.Login;
import info.filipe.sis.nac.connector.DBConnectorSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	
	static Connection conn = null;

	public LoginDAO() {

		try {
			conn = DBConnectorSQLite.getConnection();
		} catch (SQLException ex) {
		}

	}
	
	public Boolean verifyLogin(Login l){
		
		String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, l.getUsuario());
			stmt.setString(2, l.getSenha());
			
			ResultSet result = stmt.executeQuery();
			
			if(result.next()){
				return true;
			}

		} catch (SQLException ex) {

		}

		
		return false;
	}

}

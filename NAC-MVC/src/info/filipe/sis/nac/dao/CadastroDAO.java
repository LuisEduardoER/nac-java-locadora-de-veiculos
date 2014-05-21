package info.filipe.sis.nac.dao;

import info.filipe.sis.nac.bean.Login;
import info.filipe.sis.nac.connector.DBConnectorSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CadastroDAO {

	static Connection conn = null;
	
	public CadastroDAO(){
		try {
			conn = DBConnectorSQLite.getConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public Boolean cadastro(Login lo) {
		String sql = "INSERT INTO usuarios VALUES (NULL, ?, ?);";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, lo.getUsuario());
			stmt.setString(2, lo.getSenha());

			if (stmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean alteraSenha(Login lo) {
		String sql = "UPDATE usuarios SET senha = ? WHERE id = ?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);;
			stmt.setString(1, lo.getSenha());
			stmt.setInt(2, lo.getId());

			if (stmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<Login> getAll() {

		String sql = "SELECT * FROM usuarios";

		ArrayList<Login> listuser = new ArrayList<Login>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				Login login = new Login();
				login.setId(result.getInt(1));
				login.setUsuario(result.getString(2));
				login.setSenha(result.getString(3));
				listuser.add(login);
			}
			
		} catch (SQLException ex) {
				ex.printStackTrace();
			}

			return listuser;
	}
	
	public Login getPK(int id) {

		String sql = "SELECT * FROM usuarios WHERE id = ?";
		Login log = new Login();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {					
				log.setId(result.getInt(1));
				log.setUsuario(result.getString(2));
				log.setSenha(result.getString(3));
			}
			
		} catch (SQLException ex) {
		}
		

		return log;
	}
	
	public Boolean deletar(int id) {
		String sql = "DELETE FROM usuarios WHERE id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			if (stmt.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
}

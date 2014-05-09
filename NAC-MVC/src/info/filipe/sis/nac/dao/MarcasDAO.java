package info.filipe.sis.nac.dao;

import info.filipe.sis.nac.bean.Marca;
import info.filipe.sis.nac.connector.DBConnectorSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarcasDAO {

	static Connection conn = null;

	public MarcasDAO() {
		try {
			conn = DBConnectorSQLite.getConnection();
		} catch (SQLException ex) {
		}
	}

	public Boolean inserirMarca(Marca m) {

		String sql = "INSERT INTO marcas VALUES (NULL, ?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, m.getDescricao());

			if (stmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException ex) {

		}

		return false;
	}

	public Boolean deletarMarca(int id) {

		String sql = "DELETE FROM marcas where idmarcas = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			if (stmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException ex) {

		}

		return false;
	}

	public Boolean atualizarMarca(Marca m) {

		String sql = "UPDATE marcas SET descricao = ? WHERE idmarcas = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, m.getDescricao());
			stmt.setInt(2, m.getId());

			if (stmt.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException ex) {

		}

		return false;

	}

	public ArrayList<Marca> getAll() {

		String sql = "SELECT * FROM marcas";
		ArrayList<Marca> marcas = new ArrayList<Marca>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {				
				Marca c = new Marca();
				c.setId(result.getInt(1));
				c.setDescricao(result.getString(2));
				marcas.add(c);
			}
			
		} catch (SQLException ex) {
		}
		
		return marcas;
	}
	
	public Marca getPK(int id){
		String sql = "SELECT * FROM marcas where idmarcas = ?";
		Marca c = new Marca();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {					
				c.setId(result.getInt(1));
				c.setDescricao(result.getString(2));
			}
			
		} catch (SQLException ex) {
		}
		
		return c;
	}

}

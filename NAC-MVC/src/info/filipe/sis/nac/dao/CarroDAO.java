package info.filipe.sis.nac.dao;

import info.filipe.sis.nac.bean.Carros;
import info.filipe.sis.nac.connector.DBConnectorSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroDAO {

	static Connection conn = null;
	
	public CarroDAO(){
		try {
			conn = DBConnectorSQLite.getConnection();
		} catch (SQLException ex) {
		}
	}
	
	public Boolean inserirCarro(Carros ca){
		
		String sql = "INSERT INTO carros VALUES (NULL, ?, ?, ?, ?, ?, ?);";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ca.getIdmarca());
			stmt.setString(2, ca.getModelo());
			stmt.setString(3, ca.getPlaca());
			stmt.setInt(4, ca.getAno());
			stmt.setFloat(5, ca.getKm());
			stmt.setInt(6, ca.getIdpreco());

			if (stmt.executeUpdate() == 1) {
				return true;
			} 
		} catch (SQLException ex) {

		}
		
		return false;
	}
	
	public Boolean deletarCarro(int id){
		
		String sql = "DELETE FROM carros where id = ?";
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
	
	public Boolean atualizarCarro(Carros ca){
		
		String sql = "UPDATE carros SET idmarca = ?, modelo= ?, placa = ?, ano = ?, km = ?, preco = ? WHERE id = ?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ca.getIdmarca());
			stmt.setString(2, ca.getModelo());
			stmt.setString(3, ca.getPlaca());
			stmt.setInt(4, ca.getAno());
			stmt.setFloat(5, ca.getKm());
			stmt.setInt(6, ca.getIdpreco());
			stmt.setInt(7, ca.getId());

			if (stmt.executeUpdate() == 1) {
				return true;
			} 
		} catch (SQLException ex) {

		}
		
		return false;
	}
	
	public ArrayList<Carros> getAll(){
		
		String sql = "SELECT * FROM clientes";
		
		ArrayList<Carros> listcarros = new ArrayList<Carros>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {				
				Carros ca = new Carros();
				ca.setId(result.getInt(1));
				ca.setIdmarca(result.getInt(2));
				ca.setModelo(result.getString(3));
				ca.setPlaca(result.getString(4));
				ca.setAno(result.getInt(5));
				ca.setKm(result.getFloat(6));
				ca.setIdpreco(result.getInt(7));
				listcarros.add(ca);
				
			}
			
		} catch (SQLException ex) {
		}
		
		return listcarros;
		
	}
	
}

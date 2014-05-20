package info.filipe.sis.nac.dao;

import info.filipe.sis.nac.bean.Carro;
import info.filipe.sis.nac.connector.DBConnectorSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroDAO {

	static Connection conn = null;
	
	MarcasDAO mdao = new MarcasDAO();
	PrecoDAO pdao = new PrecoDAO();
	
	public CarroDAO(){
		try {
			conn = DBConnectorSQLite.getConnection();
		} catch (SQLException ex) {
		}
	}
	
	public Boolean inserirCarro(Carro ca){
		
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
	
	public Boolean atualizarCarro(Carro ca){
		
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
	
	public ArrayList<Carro> getAll(){
		
		String sql = "SELECT * FROM carros";
		
		ArrayList<Carro> listcarros = new ArrayList<Carro>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {				
				Carro ca = new Carro();
				ca.setId(result.getInt(1));
				ca.setIdmarca(result.getInt(2));
				ca.setMarca(mdao.getPK(result.getInt(2)));
				ca.setModelo(result.getString(3));
				ca.setPlaca(result.getString(4));
				ca.setAno(result.getInt(5));
				ca.setKm(result.getFloat(6));
				ca.setIdpreco(result.getInt(7));
				ca.setPreco(pdao.getPK(result.getInt(7)));
				listcarros.add(ca);
				
			}
			
		} catch (SQLException ex) {
		}
		
		return listcarros;
		
	}
	
	public Carro getPK(int id){
		String sql = "SELECT * FROM carros where id = ?";
		Carro ca = new Carro();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {					
				ca.setId(result.getInt(1));
				ca.setIdmarca(result.getInt(2));
				ca.setModelo(result.getString(3));
				ca.setPlaca(result.getString(4));
				ca.setAno(result.getInt(5));
				ca.setKm(result.getFloat(6));
				ca.setIdpreco(result.getInt(7));
			}
			
		} catch (SQLException ex) {
		}
		
		return ca;
	}
	
}

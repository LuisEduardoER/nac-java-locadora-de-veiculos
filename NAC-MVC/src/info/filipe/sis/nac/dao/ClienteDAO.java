package info.filipe.sis.nac.dao;

import info.filipe.sis.nac.bean.Cliente;
import info.filipe.sis.nac.connector.DBConnectorSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

	static Connection conn = null;

	public ClienteDAO() {

		try {
			conn = DBConnectorSQLite.getConnection();
		} catch (SQLException ex) {
		}

	}

	public Boolean inserirCliente(Cliente c) {

		String nome = c.getNome();
		String sobrenome = c.getSobrenome();
		String cpf = c.getCpf();
		String logradouro = c.getLogradouro();
		String logradouro_num = c.getLogradouro_num();
		String bairro = c.getBairro();
		String cep = c.getCep();
		//String nascimento = c.getNascimento();

		String sql = "INSERT INTO clientes VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, sobrenome);
			stmt.setString(3, cpf);
			stmt.setString(4, logradouro);
			stmt.setString(5, logradouro_num);
			stmt.setString(6, bairro);
			stmt.setString(7, cep);
			//stmt.setString(8, nascimento);

			if (stmt.executeUpdate() == 1) {
				return true;
			} 
		} catch (SQLException ex) {

		}

		return false;

	}
	
	public Boolean deletarCliente(int id){
		
		String sql = "DELETE FROM clientes where idcliente = ?";
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
	
	public Boolean atualizarCliente(Cliente c){
		
		String sql = "UPDATE clientes SET nome = ?, sobrenome = ?, cpf = ?, logradouro = ?, logradouro_num = ?, bairro = ?, cep = ? WHERE idclientes = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getSobrenome());
			stmt.setString(3, c.getCpf());
			stmt.setString(4, c.getLogradouro());
			stmt.setString(5, c.getLogradouro_num());
			stmt.setString(6, c.getBairro());
			stmt.setString(7, c.getCep());
			stmt.setInt(8, c.getId());

			if (stmt.executeUpdate() == 1) {
				return true;
			} 
		} catch (SQLException ex) {

		}
		
		return false;
		
	}

	public ArrayList<Cliente> getAll() {

		String sql = "SELECT * FROM clientes";
		ArrayList<Cliente> cliente = new ArrayList<Cliente>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {				
				Cliente c = new Cliente();
				c.setId(result.getInt(1));
				c.setNome(result.getString(2));
				c.setSobrenome(result.getString(3));
				c.setCpf(result.getString(4));
				c.setLogradouro(result.getString(5));
				c.setLogradouro_num(result.getString(6));
				c.setBairro(result.getString(7));
				c.setCep(result.getString(8));
				cliente.add(c);
			}
			
		} catch (SQLException ex) {
		}
		
		return cliente;
	}
	
	public Cliente getPK(int id){
		String sql = "SELECT * FROM clientes where idcliente = ?";
		Cliente c = new Cliente();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {					
				c.setId(result.getInt(1));
				c.setNome(result.getString(2));
				c.setSobrenome(result.getString(3));
				c.setCpf(result.getString(4));
				c.setLogradouro(result.getString(5));
				c.setLogradouro_num(result.getString(6));
				c.setBairro(result.getString(7));
				c.setCep(result.getString(8));
			}
			
		} catch (SQLException ex) {
		}
		
		return c;
	}
	
}

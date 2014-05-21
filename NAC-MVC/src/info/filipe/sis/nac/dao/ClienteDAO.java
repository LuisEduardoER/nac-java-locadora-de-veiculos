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
		String nascimento = c.getNascimento();

		String sql = "INSERT INTO clientes VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, sobrenome);
			stmt.setString(3, cpf);
			stmt.setString(4, logradouro);
			stmt.setString(5, logradouro_num);
			stmt.setString(6, bairro);
			stmt.setString(7, cep);
			stmt.setString(8, nascimento);
			stmt.setInt(9, 0);

			if (stmt.executeUpdate() == 1) {
				return true;
			} 
		} catch (SQLException ex) {

		}

		return false;

	}
	
	public Boolean deletarCliente(int id){
		
		String sql = "DELETE FROM clientes where id = ?";
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
		
		String sql = "UPDATE clientes SET nome = ?, sobrenome = ?, cpf = ?, logradouro = ?, logradouro_num = ?, bairro = ?, cep = ?, nascimento = ? WHERE id = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getSobrenome());
			stmt.setString(3, c.getCpf());
			stmt.setString(4, c.getLogradouro());
			stmt.setString(5, c.getLogradouro_num());
			stmt.setString(6, c.getBairro());
			stmt.setString(7, c.getCep());
			stmt.setString(8, c.getNascimento());
			stmt.setInt(9, c.getId());

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
				c.setNascimento(result.getString(9));
				c.setPontos(result.getInt(10));
				cliente.add(c);
			}
			
		} catch (SQLException ex) {
		}
		
		return cliente;
	}
	
	public Cliente getPK(int id){
		String sql = "SELECT * FROM clientes where id = ?";
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
				c.setNascimento(result.getString(9));
				c.setPontos(result.getInt(10));
			}
			
		} catch (SQLException ex) {
		}
		
		return c;
	}
	
	public Boolean pontuar(int id){
		String sql = "UPDATE clientes SET pontos = pontos+1000 WHERE id = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			if (stmt.executeUpdate() == 1) {
				return true;
			} 
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
}

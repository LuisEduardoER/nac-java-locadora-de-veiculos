package info.filipe.sis.nac.dao;

import info.filipe.sis.nac.bean.Preco;
import info.filipe.sis.nac.connector.DBConnectorSQLite;

import java.sql.*;
import java.util.ArrayList;

public class PrecoDAO {
	static Connection conn = null;

	public PrecoDAO() {
		try {
			conn = DBConnectorSQLite.getConnection();
		} catch (SQLException ex) {
		}
	}

	public Boolean inserirPreco(Preco p) {
		String descricao = p.getDescricao();
		double valor = p.getValor();

		String sql = "INSERT INTO preco VALUES (NULL, ?, ?);";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, descricao);
			stmt.setDouble(2, valor);

			if (stmt.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	public Boolean deletarPreco(int id) {
		String sql = "DELETE FROM preco WHERE id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			if (stmt.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	public Boolean atualizarPreco(Preco p) {
		String sql = "UPDATE preco SET descricao = ?, valor = ? WHERE id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getDescricao());
			stmt.setDouble(2, p.getValor());
			stmt.setInt(3, p.getId());

			if (stmt.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	public ArrayList<Preco> getAll() {
		String sql = "SELECT * FROM preco";
		ArrayList<Preco> preco = new ArrayList<Preco>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				Preco p = new Preco();
				p.setId(result.getInt(1));
				p.setDescricao(result.getString(2));
				p.setValor(result.getDouble(3));
				preco.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return preco;
	}

	public Preco getPK(int id) {
		String sql = "SELECT * FROM preco WHERE id = ?";
		Preco p = new Preco();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				p.setId(result.getInt(1));
				p.setDescricao(result.getString(2));
				p.setValor(result.getDouble(3));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return p;
	}
	
	public int getIdPreco(int idcarro)
	{
		String sql = "SELECT preco FROM carros WHERE id = ?";
		int idpreco = 0;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idcarro);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				idpreco = result.getInt(1);				
			}
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idpreco;
	}

}

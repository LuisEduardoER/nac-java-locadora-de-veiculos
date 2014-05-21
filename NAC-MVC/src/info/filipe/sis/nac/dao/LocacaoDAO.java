package info.filipe.sis.nac.dao;

import info.filipe.sis.nac.bean.Locacao;
import info.filipe.sis.nac.connector.DBConnectorSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LocacaoDAO {
	
	static Connection conn = null;
	
	CarroDAO cdao = new CarroDAO();
	ClienteDAO cldao = new ClienteDAO();
	PrecoDAO pdao = new PrecoDAO();
	
	public LocacaoDAO() {
		try {
			conn = DBConnectorSQLite.getConnection();
		} catch (Exception e) {
		}
	}
	
	public Boolean locarVeiculo(Locacao l)
	{
		String sql = "INSERT INTO locacao VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, l.getIdCarro());
			stmt.setInt(2, l.getIdPreco());
			stmt.setInt(3, l.getIdCliente());
			stmt.setString(4, l.getData_loc());
			stmt.setInt(5, l.getQtdDias());
			stmt.setString(6, "LOCADO");
			stmt.setString(7, l.getDsPagamento());
			stmt.setString(8, l.getObs());
			
			if(stmt.executeUpdate() == 1)
			{
				CarroDAO carroDAO = new CarroDAO();
				if(carroDAO.locar(l.getIdCarro())){
					return true;					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean devolucaoVeiculo(Locacao l)
	{
		String sql = "UPDATE locacao SET data_entrega = ?,ds_situacao = ?,ds_pagamento = ?,obs =  ? WHERE id = ?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(6, l.getData_entrega());
			stmt.setString(7, l.getDsPagamento());
			stmt.setString(8, l.getDsSituacao());
			stmt.setString(9, l.getObs());
			stmt.setInt(5, l.getId());
			
			if(stmt.executeUpdate() == 1)
			{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	
	}
	
	public ArrayList<Locacao> getAll() {

		String sql = "SELECT * FROM locacao";
		ArrayList<Locacao> locacoes = new ArrayList<Locacao>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {				
				Locacao l = new Locacao();
				l.setId(result.getInt(1));
				l.setIdCarro(result.getInt(2));
				l.setCarro(cdao.getPK(result.getInt(2)));
				l.setIdPreco(result.getInt(3));
				l.setPreco(pdao.getPK(result.getInt(3)));
				l.setIdCliente(result.getInt(4));
				l.setCliente(cldao.getPK(result.getInt(4)));
				l.setData_loc(result.getString(5));
				l.setQtdDias(result.getInt(6));
				l.setDsSituacao(result.getString(7));
				l.setDsPagamento(result.getString(8));
				l.setObs(result.getString(9));
				locacoes.add(l);
			}
			
		} catch (SQLException ex) {
		}
		
		return locacoes;
	}
	
	public Locacao getPK(int id){
		String sql = "SELECT * FROM locacao where id = ?";
		Locacao l = new Locacao();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {					
				l.setId(result.getInt(1));
				l.setIdCarro(result.getInt(2));
				l.setIdPreco(result.getInt(3));				
				l.setIdCliente(result.getInt(4));
				l.setData_loc(result.getString(5));
				l.setQtdDias(result.getInt(6));
				l.setData_entrega(result.getString(7));
				l.setDsSituacao(result.getString(8));
				l.setDsPagamento(result.getString(9));
				l.setObs(result.getString(10));
			}
			
		} catch (SQLException ex) {
		}
		
		return l;
	}

	
	

}

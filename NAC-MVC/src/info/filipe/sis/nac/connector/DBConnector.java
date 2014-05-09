package info.filipe.sis.nac.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	public static Connection getConnection() throws SQLException{
		
		try {
			   Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException ex){ }
		
		String server = "localhost";
		String database = "nacjava";
				
		String url = "jdbc:mysql://" + server + "/" + database;
		String user = "root";
		String senha = "fiap";

		Connection conn = DriverManager.getConnection(url, user, senha);

		return conn;
	}
	
}

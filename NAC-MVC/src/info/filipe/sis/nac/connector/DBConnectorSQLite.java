package info.filipe.sis.nac.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorSQLite {

	public static Connection getConnection() throws SQLException {
		
		try {
			   Class.forName("org.sqlite.JDBC");
		} 
		catch (ClassNotFoundException ex){ }

		try{
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Eclipse 4.3/workspace/NAC-MVC/baseJAVA.db");
		
			return conn;
		
		} catch (NullPointerException ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
}

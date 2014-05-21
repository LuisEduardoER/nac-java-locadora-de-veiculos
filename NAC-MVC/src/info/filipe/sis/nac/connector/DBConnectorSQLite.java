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
			//String dblocation = DBConnectorSQLite.class.getResource("baseJAVA.db").toString();
			//Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dblocation);
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/rm67044/Desktop/work/NAC-MVC/WebContent/WEB-INF/baseJAVA.db");
		
			return conn;
		
		} catch (NullPointerException ex){
			ex.printStackTrace();
			return null;
		}
		
	}
	
}

package model;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionDB {
	private static Connection connection = null;
	private static ConnectionDB instance;
	
	private ConnectionDB()throws SQLException, NamingException{
		
			try {

				Context context = new InitialContext();
				DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Orianteed_Project");
				connection = dataSource.getConnection();

			}
			catch (SQLException e) {
				e.printStackTrace();

			}
			
	}
		
	
	public Connection getConnection()
	{		 
		   return connection;
	}
	
	 public static ConnectionDB getInstance() throws SQLException, NamingException {
	        if (instance == null) {
	            instance = new ConnectionDB();
	        } 
	        
	        return instance;
	    }
	 
	 public void closeConnection() throws SQLException {
	        if (connection != null && !connection.isClosed()) {
	            connection.close();
	        }
	    }
	
}


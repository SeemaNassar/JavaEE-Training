package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;


	
    

public class UserModelDAO {
	private Connection connection;
    private UserModel user;
    public UserModelDAO() {
    	
    }
	private static final String INSERT_USERS_SQL = "INSERT INTO public.\"User\" (rule_id, first_name, last_name, email, password, birthday) VALUES " +
	        " (?, ?, ?, ?, ?,?);";
	private static final String SELECT_USERS_SQL = "select * from public.\"User\" where email =?";
	
	public static int addUser(UserModel user) throws SQLException, NamingException{  
		int status =- 2; 
		Connection connection = ConnectionDB.getInstance().getConnection();
		
		
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL); 
		
		preparedStatement.setInt(1,user.getRuleId());
		preparedStatement.setString(2,user.getFirstName());
		preparedStatement.setString(3,user.getLastName());
		preparedStatement.setString(4,user.getEmail());  
		preparedStatement.setString(5,user.getPassword());  
		preparedStatement.setDate(6,new java.sql.Date(user.getBirthday().getTime()));           
		status = preparedStatement.executeUpdate(); 
		
		   
		return status;  
	}
	
	public UserModel userModelLogin(String name, String pass) throws SQLException, NamingException {
		
		connection = ConnectionDB.getInstance().getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.\"User\" WHERE email = ? AND password = ?");
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, pass);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
		   user = new UserModel(resultSet.getInt("id"),resultSet.getInt("rule_id"),resultSet.getString("first_name"),
		   resultSet.getString("last_name"),resultSet.getString("email"),resultSet.getString("password"),resultSet.getDate("birthday"));
		   resultSet.close();
		   preparedStatement.close();
		   return user;
		    
	   }else {
	     resultSet.close();
	     preparedStatement.close();
	     return null;
	     }
      }	
	
	public static boolean isExistEmail(String email) throws SQLException, NamingException {
		boolean check = false;
		Connection con = ConnectionDB.getInstance().getConnection();

     	PreparedStatement preparedStatement = con.prepareStatement(SELECT_USERS_SQL); 
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();  
			
	    while (resultSet.next()) {
	         check = true;
	          break;
	        }
		 	      
		return check;
	}

}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

public class CategoryModelDAO {

    public CategoryModelDAO() {
		
	}
    static CategoryModel category ;
	private static final String INSERT_CATEGORY_SQL = "INSERT INTO public.\"category\" (name) VALUES (?);";
	private static final String SELECT_CATEGORY_BY_ID_SQL = "SELECT id FROM public.\"category\" WHERE name=?;";
	private static final String DELETE_CATEGORY_SQL = "UPDATE public.\"category\" SET \"markOfDelete\" = ? WHERE name=?;";
	private static final String UPDATE_CATEGORY_SQL = "UPDATE public.\"category\" SET name = ? WHERE id=(SELECT id FROM public.\"category\" WHERE name=?);";
	private static final String SELECT_PARENT_CATEGORY_SQL = "SELECT name from public.\"category\" where \"markOfDelete\" = false and name not like '%-%';";
	private static final String SELECT_SUB_CATEGORY_SQL = "SELECT name from public.category as c\r\n"
			+ "join public.category_relations as cr on (c.id = cr.\"childId\")\r\n"
			+ "where c.\"markOfDelete\" = false\r\n"
			+ "and cr.\"parentId\" = (select id from public.category where \"name\" = ?);";
	

	
    
    public  Boolean addCategory(CategoryModel catagory) throws SQLException, NamingException {
    	System.out.println("enter ");
    	Boolean status ; 
		Connection connection = ConnectionDB.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL); 
		
		System.out.println(catagory.getName());
		preparedStatement.setString(1,catagory.getName());
		         
		status = preparedStatement.executeUpdate() > 0; 
		
		   
		return status;  
    
    }
    
    public  boolean deleteCategory(CategoryModel catagory) throws SQLException, NamingException {
    	
    	
		Connection connection = ConnectionDB.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_SQL); 
	
		preparedStatement.setBoolean(1,true);
		preparedStatement.setString(2,catagory.getName());
	    preparedStatement.executeUpdate(); 
	    boolean result = preparedStatement.executeUpdate() > 0; 
	    return result;
		 
    }
    
    public  boolean updateCategory(String catagoryName , CategoryModel catagory ) throws SQLException, NamingException {
    	
		Connection connection = ConnectionDB.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SQL); 
	
		preparedStatement.setString(1,catagoryName);
		preparedStatement.setString(2,catagory.getName());
	    boolean result = preparedStatement.executeUpdate() > 0; 
	    return result;
		 
    }
    public  Integer getCategoryId(CategoryModel catagory) throws SQLException, NamingException {
    	int id=0;
		Connection connection = ConnectionDB.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID_SQL); 
	
		preparedStatement.setString(1,catagory.getName());
		
		ResultSet resultSet = preparedStatement.executeQuery(); 
		while(resultSet.next()) {
			 id = resultSet.getInt("id");
			 
	       }
		return id;
		 
    }
    
    public  List<String> getParentCategory() throws SQLException, NamingException {
    	
  		Connection connection = ConnectionDB.getInstance().getConnection();
  		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PARENT_CATEGORY_SQL); 
  		List<String> listCategory = new ArrayList<String>();
  		ResultSet resultSet = preparedStatement.executeQuery(); 
  		
  		return getList(resultSet,listCategory);
  		 
      }
    public  List<String> getChildCategory(String parent) throws SQLException, NamingException {
    	
  		Connection connection = ConnectionDB.getInstance().getConnection();
  		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUB_CATEGORY_SQL); 
  		preparedStatement.setString(1,parent);
  	    List<String> listSubCategory = new ArrayList<String>();

  		ResultSet resultSet = preparedStatement.executeQuery(); 
  		return getList(resultSet,listSubCategory);
  		
      }
    
     public  List <String> getList(ResultSet resultSet, List<String> listCategoryType) throws SQLException {
    	 
    	 while(resultSet.next()) {
   			String name= resultSet.getString("name"); 
   			listCategoryType.add(name);
   	       }
   		 return listCategoryType;
     }


    

 
 
    

}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

public class CategoryRelationDAO {

	public CategoryRelationDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String INSERT_PARENT_CATEGORYID_SQL = "INSERT INTO public.\"category_relations\" (\"childId\" ,\"parentId\") VALUES (?,? );";
	//private static final String UPDATE_SUB_CATEGORY_SQL = "UPDATE public.\"category_relations\" SET parentId = ? WHERE childId=?;";
	
	public static boolean addSubCategory(CategoryRelation subCatagory) throws SQLException, NamingException {
    	
		Connection connection = ConnectionDB.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PARENT_CATEGORYID_SQL); 
		
		preparedStatement.setInt(1,subCatagory.getChildId());
		preparedStatement.setInt(2,subCatagory.getParentId());
		         
		Boolean status = preparedStatement.executeUpdate() > 0; 
		return status;  
    
    }
	
	  
	

}

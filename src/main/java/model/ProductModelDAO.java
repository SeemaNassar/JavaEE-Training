package model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;


public class ProductModelDAO {

    private static int status ;
	private static ProductModel productModel;
	private List<ProductModel> listProductModel = new ArrayList<ProductModel>();
	
	private static final String INSERT_PRODUCT_SQL = "INSERT INTO public.\"product\" (\"partNumber\", name, price, quantity) VALUES " +
	        " (?, ?, ?, ?);";
	private static final String SELECT_PRODUCTS_BY_ID_SQL = "SELECT * FROM public.\"product\" WHERE id = ?"; 
	private static final String SELECT_PRODUCTS_BY_PART_NUMBER_SQL = "SELECT * FROM public.\"product\" WHERE \"partNumber\" = ?"; 
	private static final String UPDATE_PRODUCT_SQL = "update public.\"product\" set name = ?, price = ?, quantity = ?, \"updatedAt\" = ? where id = ?;";
    private static final String UPDATE_PUBLISHED_PRODUCT_SQL = "update public.\"product\" set published = ? where id = ?;";
    private static final String DELETE_PRODUCT_SQL = "delete from public.\"product\" where id = ?;";
    private static final String SELECT_CATEGORY_PRODUCTS_SQL ="SELECT p.*, \n"
    		+ "  (SELECT array_agg(m.\"URLimage\") FROM public.product_media m WHERE m.\"productId\" = p.id) AS \"imageURLs\"\n"
    		+ "FROM public.product p \n"
    		+ "LEFT JOIN public.product_category c ON (c.\"productId\" = p.id) \n"
    		+ "WHERE p.\"published\" = true \n"
    		+ "AND c.\"categoryId\" IN (SELECT id FROM public.category WHERE name LIKE ?);\n"
    		+ "";
    public ProductModelDAO() {

	}

	public static int addProduct(ProductModel product) throws SQLException, NamingException{  

		PreparedStatement preparedStatement = CreatStatment("insert");
	
		preparedStatement.setInt(1,product.getPartNumber());
		preparedStatement.setString(2,product.getName());
		preparedStatement.setDouble(3,product.getPrice());
		preparedStatement.setInt(4,product.getQuantity());            
		status = preparedStatement.executeUpdate(); 
		preparedStatement.close();
		   
		return status;  
	}
	public List<ProductModel> showProductBy(Integer search, String by) throws SQLException, NamingException {
		PreparedStatement preparedStatement = null ;
		switch (by) {
		case "Id": 
			preparedStatement = CreatStatment("selectById");
			break;
		case "PartNumber": 
			preparedStatement = CreatStatment("selectByPartNumber");
			break;
		}
		preparedStatement.setInt(1,search);
		createModel(preparedStatement);
		
	    return listProductModel;
	    
	}
	public List<ProductModel> showProducts(String CategoryName) throws SQLException, NamingException {
		
	    PreparedStatement preparedStatement = CreatStatment("select");
	    preparedStatement.setString(1,CategoryName+"%");
	    createModel(preparedStatement);
	    
	    return listProductModel;
	    
	}
	private void createModel(PreparedStatement preparedStatement) throws SQLException, NamingException {
	
		ResultSet resultSet = preparedStatement.executeQuery();
	    while(resultSet.next()) {
		    Array imageArray = resultSet.getArray("imageURLs");
	        if (imageArray != null) {
	        	
	        	String[] imageURLs = (String[]) imageArray.getArray();
	            productModel=new ProductModel(resultSet.getInt("id"),resultSet.getInt("partNumber"),resultSet.getString("name"),
		    			resultSet.getDouble("price"),resultSet.getInt("quantity"),resultSet.getTimestamp("createdAt"),
		    			resultSet.getTimestamp("updatedAt"),resultSet.getBoolean("published"),Arrays.asList(imageURLs));
	        }
	        listProductModel.add(productModel);
	    }

	    resultSet.close();
	    preparedStatement.close();
	}
	public static int deleteProduct(ProductModel product) throws SQLException, NamingException{  
 
		PreparedStatement preparedStatement = CreatStatment("delete");
		
		preparedStatement.setInt(1,product.getId());           
		status = preparedStatement.executeUpdate(); 
		preparedStatement.close();
		   
		return status;  
	}
	public static int updateProduct(ProductModel product) throws SQLException, NamingException{  
	
		PreparedStatement preparedStatement = CreatStatment("update");
		
		preparedStatement.setString(1,product.getName());
		preparedStatement.setDouble(2,product.getPrice());
		preparedStatement.setInt(3,product.getQuantity());   
		preparedStatement.setTimestamp(4,product.getUpdatedAt()); 
		preparedStatement.setInt(5,product.getId());  
		status = preparedStatement.executeUpdate(); 
		preparedStatement.close();
		   
		return status;  
	}
	public static int updatePublishedProduct(ProductModel product) throws SQLException, NamingException{  

		PreparedStatement preparedStatement = CreatStatment("published");
		
		preparedStatement.setBoolean(1,product.getPublished()); 
		preparedStatement.setInt(2,product.getId()); 
		status = preparedStatement.executeUpdate(); 
		preparedStatement.close();
		   
		return status;  
	} 

	private static PreparedStatement CreatStatment(String SQLquery) throws SQLException, NamingException {
		status = -2;
		Connection connection = ConnectionDB.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		switch (SQLquery) {
		case "insert":
			preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL); 
			break;
		case "select":
			preparedStatement = connection.prepareStatement(SELECT_CATEGORY_PRODUCTS_SQL); 
			break;
		case "selectById":
			preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_ID_SQL); 
			break;
		case "selectByPartNumber":
			preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_PART_NUMBER_SQL); 
			break;
		case "update":
		    preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
			break;
		case "published":
			preparedStatement = connection.prepareStatement(UPDATE_PUBLISHED_PRODUCT_SQL);
			break;
		case "delete":
			preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL); 
			break;
	
		}
		
		return  preparedStatement;
	}
	
	
}

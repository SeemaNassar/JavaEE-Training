package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;




public class RulesModelDAO {
	  private Connection connection;
	  private List<RulesModel> listRulesModel = new ArrayList<RulesModel>();
	  private static RulesModel model;
	    
	    public RulesModelDAO() {
	    
	    }
		public List<RulesModel> FillRulesModels() throws SQLException, NamingException {
			
			connection = ConnectionDB.getInstance().getConnection();
			
		    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.\"Rules\"");
		    ResultSet resultSet = preparedStatement.executeQuery();
		    
		    while(resultSet.next()) {
		        model=new RulesModel(resultSet.getInt("id"),resultSet.getString("User_Type"));
		        listRulesModel.add(model);
		       }
		    
		    resultSet.close();
		    preparedStatement.close();
		    return listRulesModel;
		    
		   }

}

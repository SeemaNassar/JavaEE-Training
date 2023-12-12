package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RulesModel;
import model.RulesModelDAO;
import model.UserModel;
import model.UserModelDAO;

/**
 * Servlet implementation class LoginS
 */

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	UserModelDAO userDao = new UserModelDAO();
    	UserModel userModelLogin;
    	String email = request.getParameter("username");  
        String password = request.getParameter("password");
        
        try {
        	validate(email,password);
        	
        	userModelLogin = userDao.userModelLogin(email,password);
        	
        	if (userModelLogin == null){
        		throw new Exception("notEqual");
        		 }

    		  switch (userModelLogin.getRuleId()) {
    		  case 1:
    			  request.setAttribute("UserType", "Administrator");
    			  break;
    		  case 2:
    			  request.setAttribute("UserType", "Buyer");
    			  break;
    		  default:
    			  throw new Exception("noRule");
    	
    			  
    		  }
    		  
            request.getSession().setAttribute("auth",userModelLogin);
  			request.setAttribute("UserName", userModelLogin.getFirstName() + " " + userModelLogin.getLastName());
  			request.getRequestDispatcher("/Login.jsp").forward(request, response);
  			
        }catch (SQLException e) {
        	e.printStackTrace();
            request.getRequestDispatcher("/Login.jsp").include(request,response); 
        }catch (ServletException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		request.getRequestDispatcher("/Login.jsp").include(request,response);
    	}
        catch (Exception e) {
        	System.out.print(e.getMessage());
    	  request.setAttribute("errorMessageType",e.getMessage());
    	  request.setAttribute("pageType","Login.jsp");
          request.getRequestDispatcher("/error.jsp").include(request,response); 
		} 

        
    }
    
  
    void validate(String name, String pass) throws Exception {
        if(name.isEmpty() || pass.isEmpty()) {
            throw new Exception("empty");
        }
    }

}


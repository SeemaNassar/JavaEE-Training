package controller;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;
import model.UserModelDAO;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int USER = 2;
	
	
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
        PrintWriter printWriter = response.getWriter();
        boolean checkEmail = false;
        UserModelDAO userModelDAO= new UserModelDAO();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("re_pass");
        String birthday =(String)request.getParameter("birthday");
        Date utilDate = null;
		
        
        
        try {
        	utilDate = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(birthday);
			validateParmeter(firstName,lastName,email,password,repassword,birthday); 
			if (UserModelDAO.isExistEmail(email)) 
				throw new IllegalStateException("ERROREMAILMASSAGE");
			
			if (!password.equals(repassword))
				throw new IllegalStateException("PASSWORD_MSG");
			checkAddUser(USER,firstName,lastName,email,password,utilDate,request,response);	
		        
				
			
		} catch (SQLException | ParseException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("/register.jsp");  
	        requestDispatcher.forward(request, response);
		}
        catch(IllegalStateException e) {
        	String errorMessage= e.getMessage();
        	request.setAttribute("errorMessageType", errorMessage);
        	request.setAttribute("pageType","register.jsp");
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/error.jsp");  
   	        requestDispatcher.forward(request, response);
            
        }
        
        
        
        
	}

	
	
	
	private void validateParmeter(String firstName, String lastName, String email, String password, String repassword, String birthday ) {
		if (firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() && password.isEmpty() && repassword.isEmpty() && birthday.isEmpty())
			throw new IllegalStateException("EMPTYFIELDMESSAGE");
		
	}
	
	private void checkAddUser(int userId,String firstName,String lastName,String email,String password,Date utilDate, HttpServletRequest request, HttpServletResponse response ) throws SQLException, NamingException, ServletException, IOException {
		UserModel user ;
		user = new UserModel(userId,firstName,lastName,email,password,utilDate);	
        int statusRegistration = -2;
        statusRegistration = UserModelDAO.addUser(user);
		
	      if (statusRegistration <= 0) {
	        	throw new SQLException();
	        }
	      
	      RequestDispatcher requestDispatcher=request.getRequestDispatcher("/register.jsp");  
	        requestDispatcher.forward(request, response);
	}
}

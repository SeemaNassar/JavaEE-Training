package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import model.CategoryModelDAO;

public class ShowCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCategory() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
         try {
        	
        	CategoryModelDAO categoryModelDAO = new CategoryModelDAO();
			req.setAttribute("List",categoryModelDAO.getParentCategory()); 
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("/mainPage.jsp");  
	        requestDispatcher.forward(req, resp);
	        
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	public void setCategory(String type) {
		ServletRequest req = null;
		req.setAttribute("CategorySelect",type); 
	}
	

}

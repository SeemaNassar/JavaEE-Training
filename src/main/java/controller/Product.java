package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductModel;
import model.ProductModelDAO;


public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductModelDAO dao = new ProductModelDAO();
		List<ProductModel> product = null;
	    request.setAttribute("CategorySelect", "woman");
   
		String CategorySelect = (String) request.getAttribute("CategorySelect");
		try {
			product = dao.showProducts(CategorySelect);
			request.setAttribute("productList", product);
			request.getRequestDispatcher("PLP.jsp").forward(request, response);

		} catch (SQLException | NamingException e) {
			
			e.printStackTrace();
		}
	
	}

	

	
}

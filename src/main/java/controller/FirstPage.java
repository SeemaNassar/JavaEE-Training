package controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FirstPage
 */

public class FirstPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstPage() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id = session.getId();
		if(session.getAttribute("auth") == null) {
			request.setAttribute("errorMessageType","You are not Login!");
			request.getRequestDispatcher("/error.jsp").include(request,response); 
			
		}
		else {
		System.out.print(id);
		request.setAttribute("UserName1", request.getAttribute("UserName"));
		request.setAttribute("UserType1", request.getAttribute("UserType"));
    	request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
		}
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	

}


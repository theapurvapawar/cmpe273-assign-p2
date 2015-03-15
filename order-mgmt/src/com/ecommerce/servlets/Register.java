package com.ecommerce.servlets;

import java.io.IOException;
//import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.database.RegisterUserTx;
import com.ecommerce.database.Transaction;

/**
 * Servlet implementation class Register
 */

@WebServlet(name="Register", urlPatterns={"/register"})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Transaction register = new RegisterUserTx(request.getParameter("email"),
				request.getParameter("firstname"),
				request.getParameter("lastname"),
				request.getParameter("username"),
				request.getParameter("password")
				);
		register.execute();
		
		//response.sendRedirect("/order-mgmt/login.jsp?_id="+request.getParameter("productID"));
		HttpSession session = request.getSession(false);
		session.setAttribute("productId", request.getParameter("productId"));
		session.setAttribute("userId", request.getParameter("username"));
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
		
	}

}

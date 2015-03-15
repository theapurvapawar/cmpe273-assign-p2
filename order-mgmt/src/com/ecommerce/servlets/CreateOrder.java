package com.ecommerce.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.database.CreateOrderTx;

/**
 * Servlet implementation class CreateOrder
 */
@WebServlet(name="CreateOrder", urlPatterns={"/createOrder"})
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
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
		HttpSession session = request.getSession(false);
		String productId = session.getAttribute("productId").toString();
		String userId = session.getAttribute("userId").toString();
		int qty = Integer.parseInt(request.getParameter("quantity"));
		
		//PrintWriter out = response.getWriter();
		
//		System.out.println(session.getAttribute("productId")+" "+
//		session.getAttribute("userId")+" "+
//		request.getParameter("quantity")
//		);
		
		new CreateOrderTx(userId, productId, qty).execute();
		response.sendRedirect("/order-mgmt/history.jsp");
	}

}

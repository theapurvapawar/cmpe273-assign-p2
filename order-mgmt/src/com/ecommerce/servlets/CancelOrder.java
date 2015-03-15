package com.ecommerce.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.database.CancelOrderTx;
import com.ecommerce.database.Transaction;

/**
 * Servlet implementation class CancelOrder
 */

@WebServlet(name="CancelOrder", urlPatterns={"/cancelOrder"})
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrder() {
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
		//PrintWriter out = response.getWriter();
		//out.print(request.getParameter("orderNum"));
		int orderNum = Integer.parseInt(request.getParameter("orderNum"));
		Transaction cancelOrder = new CancelOrderTx(orderNum);
		cancelOrder.execute();
		response.sendRedirect("/order-mgmt/history.jsp");
	}

}

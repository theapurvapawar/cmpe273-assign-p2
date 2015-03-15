package com.ecommerce.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.database.DBAccess;
import com.ecommerce.security.Generator;

/**
 * Servlet implementation class Authenticate
 */

@WebServlet(name="Authenticate", urlPatterns={"/authenticate"})
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticate() {
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
		PrintWriter out = response.getWriter();
		
		out.println(request.getParameter("username") +" "+ request.getParameter("password"));
		DBAccess db = new DBAccess("mydb", "root", "root");
		
		Statement st;
		try {
			st = db.getConn().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM user_auth "
					+ "WHERE user_userid='"+request.getParameter("username")
					+"';");
			//res.next();
			if (!res.isBeforeFirst() ) {    
				 //System.out.println("No data");
				 response.sendRedirect("/order-mgmt/login.jsp");
			}
			else{
				res.next();
				String salt = res.getString("salt");
				String concatenatedPassword = request.getParameter("password") + salt;
				String hashedPassword = Generator.getSHA1Hash(concatenatedPassword);
			
				if(res.getString("pass").equals(hashedPassword)){
					HttpSession session = request.getSession();
					session.setAttribute("userId", res.getString("user_userid"));
					if(request.getParameter("productId").equals("null")){
						response.sendRedirect("/order-mgmt/history.jsp");
					}
					else{
						session.setAttribute("productId", request.getParameter("productId"));
						request.getRequestDispatcher("/cart.jsp").forward(request, response);
					}
				}
				else{
					response.sendRedirect("/order-mgmt/login.jsp");
				}
			}
		} catch (SQLException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}

}

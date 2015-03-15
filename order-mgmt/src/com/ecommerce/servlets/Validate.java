package com.ecommerce.servlets;

import com.ecommerce.api.ComposedProductPOJO;

import java.io.IOException;
//import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name="Validate", urlPatterns={"/validate"})
      
public class Validate extends HttpServlet {
            
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        
		
//      
		HttpSession session = request.getSession();
		session.setAttribute("productID", request.getParameter("_id"));
        ObjectMapper mapper = new ObjectMapper();
        
        ComposedProductPOJO value = mapper.readValue(
				new URL("http://localhost:8080/order-mgmt/rest/json/getProductInfo?_id="+request.getParameter("_id")),
				ComposedProductPOJO.class
			
			);
        
       //out.print(value.getStartDate());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
			Date startDate = (Date) sdf.parse(value.getStartDate());
			Date endDate;
			Date currentDate = new Date();
			
			if(value.getEndDate() != ""){
				endDate = (Date) sdf.parse(value.getEndDate());
				if(currentDate.after(startDate) && currentDate.before(endDate) && value.getQuantity() > 0)
					checkSessionAndRedirect(request, response);
				else
					RedirectToStoreHome(request, response);					
			}else if(value.getEndDate() == ""){
				if(currentDate.after(startDate) && value.getQuantity() > 0)
					checkSessionAndRedirect(request, response);
				else
					RedirectToStoreHome(request, response);
			}	
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       // Date startDate = new Date(value.getStartDate());
        //if(value.getStartDate())

		
    }
	
	private void checkSessionAndRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		//PrintWriter out = response.getWriter();
		
		//System.out.println(session.getAttribute("userId"));
		
		if(session.getAttribute("userId") == null){
			//session = request.getSession();
			//response.sendRedirect("/order-mgmt/login.jsp");
			try {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			session.setAttribute("productId", request.getParameter("_id"));
			response.sendRedirect("/order-mgmt/cart.jsp");
		}
			
	}
	
	private void RedirectToStoreHome(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//PrintWriter out = response.getWriter();		
		//out.println("back to home");
		response.sendRedirect("http://localhost:3000/store");
	}
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }
 
}

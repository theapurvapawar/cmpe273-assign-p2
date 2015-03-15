package testing;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name="Order", urlPatterns={"/order"})
      
public class Order extends HttpServlet {
            
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        
        ObjectMapper mapper = new ObjectMapper();
        
        ArrayList<JsonTest> value = mapper.readValue(
				new URL("http://localhost:3000/api/products/product_id/"+request.getParameter("productId")),
				mapper.getTypeFactory().constructCollectionType(
	                    ArrayList.class, JsonTest.class
				)
			);
        
        
            out.println(
            		"<html>"+
            		value.get(0).getStartDate()+"<br>"+
            		value.get(0).getEndDate()
            		+"</html>");
                out.close();
    }
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }
 
}
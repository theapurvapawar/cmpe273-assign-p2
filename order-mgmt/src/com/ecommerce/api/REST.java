package com.ecommerce.api;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;





//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;







import com.ecommerce.database.DBAccess;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@Path("/json")
public class REST {
	
	private ArrayList<ProductPOJO> list = new ArrayList<ProductPOJO>();	
	
	private int getProductQuantity(String productId){
		
		try {
		
			DBAccess db = new DBAccess("mydb", "root", "root");

			Statement st = db.getConn().createStatement();
			ResultSet res = st.executeQuery("SELECT quantity FROM inventory "
					+ "WHERE productId='"+productId+"'");			
			res.next();
			int result = res.getInt("quantity");
			db.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;	
	}

	
	private void getDetailsFromNodeFor(String product_id){		
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			list = mapper.readValue(
					new URL("http://localhost:3000/api/products/product_id/"+product_id),
					mapper.getTypeFactory().constructCollectionType(
		                    ArrayList.class, ProductPOJO.class
					)
				);				
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	}
	
	@GET
	@Path("/getProductInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public ComposedProductPOJO getProductInfoJSON(@QueryParam("_id") String product_id){
 
		getDetailsFromNodeFor(product_id);
		ComposedProductPOJO jsonObject = new ComposedProductPOJO();
		jsonObject.setProduct_id(list.get(0).getProduct_id());
		jsonObject.setStartDate(list.get(0).getStartDate());
		jsonObject.setEndDate(list.get(0).getEndDate());
		int qty = getProductQuantity(list.get(0).getProduct_id());
		jsonObject.setQuantity(qty);
		jsonObject.setDescription(list.get(0).getDescription());
		jsonObject.setImage_url(list.get(0).getImage_url());
		jsonObject.setName(list.get(0).getName());
		jsonObject.setPrice(list.get(0).getPrice());
		return jsonObject;
 
	}
	
	@GET
	@Path("/getOrderInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public ComposedOrderPOJO getOrderInfoJSON(@QueryParam("_id") int order_num){
	
		ComposedOrderPOJO jsonObject = new ComposedOrderPOJO();
		
		//HashMap hm = getOrderDetails(order_num);
		
		DBAccess db = new DBAccess("mydb", "root", "root");

		Statement st;
		try {
			st = db.getConn().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM `order` "
					+ "WHERE order_num="+order_num+";");			
			res.next();
			getDetailsFromNodeFor(res.getString("inventory_productId"));
			
			jsonObject.setName(list.get(0).getName());
			jsonObject.setDescription(list.get(0).getDescription());
			
			jsonObject.setOrderDate(res.getTimestamp("timestamp").toString());
			jsonObject.setOrderNum(order_num);
			jsonObject.setQtyOrdered(res.getInt("qty_ordered"));
			jsonObject.setProductId(res.getString("inventory_productId"));
			jsonObject.setCancelledStatus(res.getBoolean("cancelled"));
			
			
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return jsonObject;
	}
	
	@GET
	@Path("/getOrdersForUser")
	@Produces("application/json")
	public String getOrdersForUserJSON(@QueryParam("_id") String username){
	
		List<ComposedOrderPOJO> jsonArray = new ArrayList<ComposedOrderPOJO>();
		
		//HashMap hm = getOrderDetails(order_num);
		
		DBAccess db = new DBAccess("mydb", "root", "root");

		Statement st;
		try {
			st = db.getConn().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM `order` "
					+ "WHERE user_userid='"+username+"';");	
			
			String status = null;

			while(res.next()){
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				Timestamp orderDate = res.getTimestamp("timestamp");
				//Date orderDate = (Date) sdf.parse(ts.toString());
				Date currentDate = new Date();
				long timeDiff = currentDate.getTime() - orderDate.getTime();
				int timeDiffInDays = (int) (timeDiff/(1000*60));
//				System.out.println(timeDiff);
//				System.out.println(timeDiffInDays);
				//String status;
				if(timeDiffInDays >= 0)
					status = "Processing";
				if(timeDiffInDays > 5)
					status = "Processed. Preparing for Delivery.";
				if(timeDiffInDays > 20)
					status = "Out for Delivery";
				if(timeDiffInDays > 30)
					status = "Delivered";
				
					
				
				getDetailsFromNodeFor(res.getString("inventory_productId"));
				jsonArray.add(
						new ComposedOrderPOJO(
								res.getInt("order_num"),
								res.getString("inventory_productId"),
								res.getTimestamp("timestamp").toString(),
								res.getInt("qty_ordered"),
								list.get(0).getName(),
								list.get(0).getDescription(),
								res.getBoolean("cancelled"),
								status,
								list.get(0).getImage_url())
								
						);
			}
			
			
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return jsonArray.toString();
	}
 
//	@POST
//	@Path("/post")index
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createTrackInJSON(Track track) {
// 
//		String result = "Track saved : " + track;
//		return Response.status(201).entity(result).build();
// 
//	}
 
}
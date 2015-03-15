package com.ecommerce.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateOrderTx implements Transaction{
	
	private String userName;
	private String productId;
	private int quantity;
	
	public CreateOrderTx(String userName, String productId, int quantity) {
		// TODO Auto-generated constructor stub
		this.userName = userName;
		this.productId = productId;
		this.quantity = quantity;		
	}

	private String getUserName() {
		return userName;
	}

	private String getProductId() {
		return productId;
	}

	private int getQuantity() {
		return quantity;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		createOrderAndUpdateInventory();		
	}

	private void createOrderAndUpdateInventory() {
		// TODO Auto-generated method stub
		DBAccess db = new DBAccess("mydb", "root", "root");

		try {
			db.getConn().setAutoCommit(false);
			
			//check if 'quantity' < 'inventory quantity'
			Statement st = db.getConn().createStatement();			
			ResultSet res = st.executeQuery("SELECT quantity FROM inventory "
					+ "WHERE productId='"+getProductId()+"'");			
			res.next();
							
			if((getQuantity() <= res.getInt("quantity")) && (res.getInt("quantity") > 0)){
				
				//update inventory table i.e. reduce quantity
				String query = "UPDATE inventory SET quantity=? WHERE productId=?";
				PreparedStatement ps = db.getConn().prepareStatement(query);
				ps.setInt(1, res.getInt("quantity") - getQuantity());
				ps.setString (2, getProductId());
				ps.executeUpdate();
				
				//insert into order table i.e. create order				
				String query2 =
						"insert into `order` "
						+ "(user_userid, inventory_productId, qty_ordered) "
						+ "values (?, ?, ?)";
				PreparedStatement ps2 = db.getConn().prepareStatement(query2);
				ps2.setString (1, getUserName());
				ps2.setString (2, getProductId());
				ps2.setInt (3, getQuantity());				
				ps2.executeUpdate();
			}
			else{
				try {
					throw new Exception("Not enough "+getProductId()+" in Inventory");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			db.getConn().commit();
			//end transaction
			
			db.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		//Transaction createOrder = new CreateOrderTx("user2", "fan3", 5);
		//createOrder.execute();
		//Transaction createOrder2 = new CreateOrderTx("testuser", "fan2", 1);
		//createOrder2.execute();
		new CreateOrderTx("testuser", "fan3", 1).execute();
	}

}

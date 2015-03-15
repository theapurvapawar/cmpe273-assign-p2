package com.ecommerce.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class CancelOrderTx implements Transaction{
	
	private int orderNum;
	
	private int getOrderNum() {
		return orderNum;
	}

	public CancelOrderTx(int orderNum) {
		// TODO Auto-generated constructor stub
		this.orderNum = orderNum;
	}
	
	private void updateOrderAndInventory(){
		DBAccess db = new DBAccess("mydb", "root", "root");

		try {
			db.getConn().setAutoCommit(false);

			//get order details
			Statement st = db.getConn().createStatement();			
			ResultSet res = st.executeQuery("SELECT inventory_productId, qty_ordered, timestamp "
					+ "FROM `order` "
					+ "WHERE order_num='"+getOrderNum()+"'");			
			res.next();

			//calculate days passed
			double timeDiffInMilliseconds = (double)((new Date()).getTime() - res.getTimestamp("timestamp").getTime());
			double daysPassed = timeDiffInMilliseconds / (24*60*60*1000);
			
			if(daysPassed < 3){
				//update order status to cancelled
				String query = "UPDATE `order` SET cancelled=1 WHERE order_num="+getOrderNum();
				PreparedStatement ps = db.getConn().prepareStatement(query);				
				ps.executeUpdate();
				
				//get product inventory detail
				Statement st2 = db.getConn().createStatement();			
				ResultSet res2 = st2.executeQuery("SELECT quantity "
						+ "FROM inventory "
						+ "WHERE productID='"+res.getString("inventory_productId")+"'");			
				res2.next();
				
				//update inventory table i.e. increment quantity
				String query2 = "UPDATE inventory SET quantity=? WHERE productId=?";
				PreparedStatement ps2 = db.getConn().prepareStatement(query2);
				ps2.setInt(1, res2.getInt("quantity") + res.getInt("qty_ordered"));
				ps2.setString (2, res.getString("inventory_productId"));
				ps2.executeUpdate();
			}
			else{
				try {
					throw new Exception("Order already processed. Cannot be cancelled.");
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

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		updateOrderAndInventory();
	}
	
	public static void main(String[] args){
		
		int orderNum = 3;
		Transaction cancelOrder = new CancelOrderTx(orderNum);
		cancelOrder.execute();
	}

	
}
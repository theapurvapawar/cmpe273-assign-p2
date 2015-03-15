package com.ecommerce.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {
	
	private String dbName;
	private String userName;
	private String password;
	private String url = "jdbc:mysql://localhost:3306/";
	private String driver = "com.mysql.jdbc.Driver";
	
	private Connection conn;
	
	public DBAccess(String dbName, String userName, String password){
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
		setConn();
		
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConn() {
		return conn;
	}
	private void setConn() {
		try {
			Class.forName (driver).newInstance ();
			this.conn = DriverManager.getConnection(url+dbName,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
	
	public static void main(String[] args) throws SQLException {



		DBAccess db = new DBAccess("mydb", "root", "root");

		db.getConn().setAutoCommit(false);
		//	 the mysql insert statement
		String query = " insert into user"
				+ " values (?, ?, ?, ?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = db.getConn().prepareStatement(query);
		preparedStmt.setString (1, "migrate@migration.net");
		preparedStmt.setString (2, "man");
		preparedStmt.setString (3, "Schow");
		preparedStmt.setString (4, "schowman7");

		//db.writeQuery(preparedStmt);
		preparedStmt.executeUpdate();
		
		query = " insert into user (email, firstname, lastname, userid)"
				+ " values (?, ?, ?, ?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt2 = db.getConn().prepareStatement(query);
		preparedStmt2.setString (1, "migrate@migration.net");
		preparedStmt2.setString (2, "man");
		preparedStmt2.setString (3, "Schow");
		preparedStmt2.setString (4, "schowman6");

		//db.writeQuery(preparedStmt2);
		preparedStmt2.executeUpdate();
		
		
		Statement st = db.getConn().createStatement();			
		ResultSet res = st.executeQuery("SELECT * FROM user");

		try {
			while (res.next())
			{
				String email = res.getString("email");
				String name = res.getString("userid");
				System.out.println(name + " " + email);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.getConn().commit();
		db.close();
	}

		

}


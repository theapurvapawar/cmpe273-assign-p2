package com.ecommerce.database;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ecommerce.security.Generator;

public class RegisterUserTx implements Transaction{

	private String email;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String salt;
	
	private String getSalt() {
		return salt;
	}
	private void setSalt() {
		this.salt = Generator.getSalt();
	}
	
	private String getEmail() {
		return email;
	}
	private String getFirstName() {
		return firstName;
	}
	private String getLastName() {
		return lastName;
	}
	private String getUserName() {
		return userName;
	}
	private String getPassword() {
		return password;
	}
	
	public RegisterUserTx(
			String email,
			String firstName,
			String lastName,
			String userName,
			String password){
		
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		setSalt();
	}
	
	private void updateUserAndUserAuthTables(){
		DBAccess db = new DBAccess("mydb", "root", "root");
		
		try {
			
			//begin transaction
			db.getConn().setAutoCommit(false);
			
			//insert into user table
			String query =
					"insert into user "
					+ "(email, firstname, lastname, userid) "
					+ "values (?, ?, ?, ?)";
			PreparedStatement ps = db.getConn().prepareStatement(query);
			ps.setString (1, getEmail());
			ps.setString (2, getFirstName());
			ps.setString (3, getLastName());
			ps.setString (4, getUserName());
			ps.executeUpdate();
			
			//insert into user_auth table
			String query2 =
					"insert into user_auth "
					+ "(user_userid, pass, salt) "
					+ "values (?, ?, ?)";
			PreparedStatement ps2 = db.getConn().prepareStatement(query2);
			ps2.setString (1, getUserName());
			ps2.setString (2, getHashedPassword());
			ps2.setString (3, getHashedSalt());
			ps2.executeUpdate();
			
			db.getConn().commit();
			//end transaction
			
			db.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	private String getHashedSalt() throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		return Generator.getSHA1Hash(getSalt());
	}
	
	
	private String getHashedPassword() throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		return Generator.getSHA1Hash(getPassword()+getHashedSalt()); 
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		updateUserAndUserAuthTables();		
	}
	
	public static void main(String[] args){
		Transaction regUser = new RegisterUserTx(
				"the@rambhau2.com",
				"ram2",
				"bhau2",
				"rambhau",
				"mypassword2");
		
		regUser.execute();
	}

}

package com.muskan.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	static Connection con=null;
	 public static Connection getConnection()
	 {
		 if(con==null)
		 {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver"); 
			con=DriverManager.getConnection("jdbc:mysql://muskanshop.cxjdnnmhajbv.ap-south-1.rds.amazonaws.com:3306/muskanshop","mukesh","root7878");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("can not connect to database");
		} 
		 }
		 return con;
}
}

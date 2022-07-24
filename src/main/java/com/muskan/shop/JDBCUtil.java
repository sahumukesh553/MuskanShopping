package com.muskan.shop;

import java.io.Serializable;
import java.lang.ref.Cleaner.Cleanable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

public class JDBCUtil {
	static Connection con=null;
	 static Logger log = Logger.getLogger(JDBCUtil.class);  
	 public static Connection getConnection()
	 {
		 if(con==null)
		 {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver"); 
			con=DriverManager.getConnection("jdbc:mysql://muskanshop.cxjdnnmhajbv.ap-south-1.rds.amazonaws.com:3306/muskanshop","mukesh","root7878");
			//con=DriverManager.getConnection("jdbc:mysql://ec2-13-232-15-111.ap-south-1.compute.amazonaws.com:3306/muskanshop","mysql","7878");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3360/mycart","root","0191Ec@151029");
			log.info("connected to database successfully");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("can not connect to database");
			log.debug(e.getMessage());
		} 
		 }
		 return con;
}
}

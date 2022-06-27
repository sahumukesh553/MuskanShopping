package com.muskan.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.muskan.shop.JDBCUtil;
import com.muskan.shop.model.User;

public class UserService {
public int registerUser(User user) throws SQLException
{
	String query="insert into user(`userName`,`userEmail`,`userPassword`,`userPhone`,`userAddress`,`userType`) values(?,?,?,?,?,?)";
	
	Connection con=JDBCUtil.getConnection();
	
	
	 
		PreparedStatement ps= con.prepareStatement(query);
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getUserEmail());
		ps.setString(3, user.getUserPassword());
		ps.setString(4, user.getUserPhone());
		ps.setString(5, user.getUserAddress());
		ps.setString(6, "normal");
		int result=ps.executeUpdate();
		System.out.println(result+" record inserted");
		ps.close();
		
		return result;
		
	
		
}
public User loginUser(String email,String password) throws SQLException
{
	User user=null;
	String query="Select * from user where userEmail=? and userPassword=?";
	Connection con=JDBCUtil.getConnection();
	
		PreparedStatement ps= con.prepareStatement(query);
		ps.setString(1, email);
		ps.setString(2, password);
	ResultSet rs=	ps.executeQuery();
	
	if(rs.next())
	{ user=new User();
	user.setUserId(rs.getInt("userId"));
	user.setUserName(rs.getString("userName"));
	user.setUserEmail(rs.getString("userEmail"));
	user.setUserType(rs.getString("userType"));
	user.setUserPhone(rs.getString("userPhone"));
	user.setUserAddress(rs.getString("userAddress"));
	
	}
	
	return user;
}
public int getUserCount()
{int count=0;
	String query="select count(*) from user";
	Connection con=JDBCUtil.getConnection();
	
	PreparedStatement ps=null;
	try {
		ps = con.prepareStatement(query);
		ResultSet rs=	ps.executeQuery();
		if(rs.next())
		{count =rs.getInt(1);
		System.out.println("user "+count);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		
		try {
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	return count;
	}
	

}

package com.muskan.shop.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;

import com.muskan.shop.JDBCUtil;
import com.muskan.shop.entity.Product;
import com.muskan.shop.entity.User;

public class UserService {
	
	static Logger log = Logger.getLogger(UserService.class);  
	

public int registerUser(User user) throws SQLException
{
	String query="insert into user(`userName`,`userEmail`,`userPassword`,`userPhone`,`userAddress`,`userType`,`userPic`) values(?,?,?,?,?,?,?)";
	
	Connection con=JDBCUtil.getConnection();
	int result=0;
	
	 
		PreparedStatement ps= con.prepareStatement(query);
		if(ps!=null)
		{
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getUserEmail());
		ps.setString(3, user.getUserPassword());
		ps.setString(4, user.getUserPhone());
		ps.setString(5, user.getUserAddress());
		ps.setString(6, "normal");
		ps.setBlob(7,user.getUserPhoto());
		 result=ps.executeUpdate();
		ps.close();
		log.info("user registered successfully");
		return result;
		}else {
			return result;
		}
		
		
		
	
		
}
public User loginUser(String email,String password) throws SQLException
{
	User user=null;
	String query="Select * from user where userEmail=? and userPassword=?";
	Connection con=JDBCUtil.getConnection();
	
		PreparedStatement ps= con.prepareStatement(query);
		if(ps!=null)
		{
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
	user.setUserPassword(rs.getString("userPassword"));
	byte[] imgData = rs.getBytes("userPic");
	if(imgData!=null) {
	String image = Base64.getEncoder().encodeToString(imgData);
	user.setUserPic(image);
	}
	}
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
		log.debug(e.getMessage());
	}
	finally {
		
		try {
			
			ps.close();
		} catch (SQLException |NullPointerException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
		}
	}
	
	return count;
	}
public List<User> getAllUser()
{
	String query="select * from user";
	Connection con=JDBCUtil.getConnection();
	List<User> users=null;
	PreparedStatement ps=null;
	try {
		ps = con.prepareStatement(query);
		ResultSet rs=	ps.executeQuery();
		 users=new ArrayList<User>();
		while(rs.next())
		{
		User user=new User();
		user.setUserId(rs.getInt("userId"));
		user.setUserName(rs.getString("userName"));
		user.setUserEmail(rs.getString("userEmail"));
		user.setUserPassword(rs.getString("userPassword"));
		user.setUserPhone(rs.getString("userPhone"));
		user.setUserType(rs.getString("userType"));
		user.setUserAddress(rs.getString("userAddress"));
		byte[] imgData = rs.getBytes("userPic");
		if(imgData!=null) {
		String image = Base64.getEncoder().encodeToString(imgData);
		user.setUserPic(image);
		}
		users.add(user);
		}
	} catch (SQLException |NullPointerException e) {
		// TODO Auto-generated catch block
		log.debug(e.getMessage());
	}
	finally {
		
		try {
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
		}
	}
	return users;
}

public User getUser(int id)
{
	String query="select * from user where userId=?";
	Connection con=JDBCUtil.getConnection();
	User user=null;
	PreparedStatement ps=null;
	try {
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs=	ps.executeQuery();
		 
		if(rs.next())
		{
		 user=new User();
		user.setUserId(rs.getInt("userId"));
		user.setUserName(rs.getString("userName"));
		user.setUserEmail(rs.getString("userEmail"));
		user.setUserPassword(rs.getString("userPassword"));
		user.setUserPhone(rs.getString("userPhone"));
		user.setUserPic(rs.getString("userPic"));
		user.setUserType(rs.getString("userType"));
		user.setUserAddress(rs.getString("userAddress"));
		byte[] imgData = rs.getBytes("userPic");
		if(imgData!=null) {
		String image = Base64.getEncoder().encodeToString(imgData);
		user.setUserPic(image);
		}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		log.debug(e.getMessage());
	}
	finally {
		
		try {
			
			ps.close();
		} catch (SQLException |NullPointerException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
		}
	}
	return user;
}
public void deleteUser(int userId)
{
	String query="delete from user where userId=?";
	Connection con=JDBCUtil.getConnection();
	User user=null;
	PreparedStatement ps=null;
	try {
		ps = con.prepareStatement(query);
		ps.setInt(1, userId);
			ps.executeUpdate();
		 
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		log.debug(e.getMessage());
	}
	finally {
		
		try {
			
			ps.close();
		} catch (SQLException |NullPointerException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
		}
	}
}
public boolean updateUser(User user) throws SQLException
{
	Connection con=JDBCUtil.getConnection();
	boolean result=false;
	PreparedStatement ps=null;
	if(user.getUserPhoto() instanceof FileInputStream) {
String query="update user set `userName`=?,`userEmail`=?,`userPassword`=?,`userPhone`=?,`userAddress`=?,`userType`=?,`userPic`=? Where userId=?";
	
	 
		ps= con.prepareStatement(query);
		if(ps!=null)
		{
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getUserEmail());
		ps.setString(3, user.getUserPassword());
		ps.setString(4, user.getUserPhone());
		ps.setString(5, user.getUserAddress());
		ps.setString(6, user.getUserType());
		ps.setBlob(7,user.getUserPhoto());
		ps.setInt(8,user.getUserId());
		ps.executeUpdate();
		
		log.info("user updated successfully");
		 result=true;
		}
	}else {
		String query="update user set `userName`=?,`userEmail`=?,`userPassword`=?,`userPhone`=?,`userAddress`=?,`userType`=? Where userId=?";
		
		 
		ps= con.prepareStatement(query);
		if(ps!=null)
		{
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getUserEmail());
		ps.setString(3, user.getUserPassword());
		ps.setString(4, user.getUserPhone());
		ps.setString(5, user.getUserAddress());
		ps.setString(6, "normal");
		ps.setInt(7,user.getUserId());
		ps.executeUpdate();
		
		log.info("user updated successfully");
		 result=true;
		}
	}
	if(ps==null)
		log.warn("database connection is not good");
	return result;
	
	 

}
public User getUserByEmail(String email)
{
		String query="select * from user where userEmail=?";
		Connection con=JDBCUtil.getConnection();
		User user=null;
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs=	ps.executeQuery();
			 
			if(rs.next())
			{
			 user=new User();
			user.setUserId(rs.getInt("userId"));
			user.setUserName(rs.getString("userName"));
			user.setUserEmail(rs.getString("userEmail"));
			user.setUserPassword(rs.getString("userPassword"));
			user.setUserPhone(rs.getString("userPhone"));
			user.setUserPic(rs.getString("userPic"));
			user.setUserType(rs.getString("userType"));
			user.setUserAddress(rs.getString("userAddress"));
			byte[] imgData = rs.getBytes("userPic");
			if(imgData!=null) {
			String image = Base64.getEncoder().encodeToString(imgData);
			user.setUserPic(image);
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
		}
		finally {
			
			try {
				
				ps.close();
			} catch (SQLException |NullPointerException e) {
				// TODO Auto-generated catch block
				log.debug(e.getMessage());
			}
		}
		return user;
}
public User getUserByNumber(String number) {
	String query="select * from user where userPhone=?";
	Connection con=JDBCUtil.getConnection();
	User user=null;
	PreparedStatement ps=null;
	try {
		ps = con.prepareStatement(query);
		ps.setString(1,number);
		ResultSet rs=	ps.executeQuery();
		 
		if(rs.next())
		{
		 user=new User();
		user.setUserId(rs.getInt("userId"));
		user.setUserName(rs.getString("userName"));
		user.setUserEmail(rs.getString("userEmail"));
		user.setUserPassword(rs.getString("userPassword"));
		user.setUserPhone(rs.getString("userPhone"));
		user.setUserPic(rs.getString("userPic"));
		user.setUserType(rs.getString("userType"));
		user.setUserAddress(rs.getString("userAddress"));
		byte[] imgData = rs.getBytes("userPic");
		if(imgData!=null) {
		String image = Base64.getEncoder().encodeToString(imgData);
		user.setUserPic(image);
		}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		log.debug(e.getMessage());
	}
	finally {
		
		try {
			
			ps.close();
		} catch (SQLException |NullPointerException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
		}
	}
	return user;
}
}

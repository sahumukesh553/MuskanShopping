package com.muskan.shop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.muskan.shop.JDBCUtil;
import com.muskan.shop.dao.UserService;
import com.muskan.shop.entity.User;
@MultipartConfig
public class UserRegisterServlet extends HttpServlet{
	private UserService userService=new UserService();
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	User user=new User();
user.setUserName(req.getParameter("userName").trim());
	user.setUserEmail(req.getParameter("userEmail").trim());
	user.setUserPassword(req.getParameter("userPassword").trim());
	user.setUserPhone(req.getParameter("userPhone").trim());
	user.setUserAddress(req.getParameter("userAddress").trim());
	Part photo=req.getPart("userPic");
	 InputStream in=photo.getInputStream();
	 user.setUserPhoto(in);
	
	
	
	 try {
		 RequestDispatcher requestDispatcher= req.getRequestDispatcher("login.jsp");
		userService.registerUser(user);
		
		req.setAttribute("message", "user registered with userName "+user.getUserName());
		 requestDispatcher.include(req, resp);
	} catch (SQLException e) {
	RequestDispatcher requestDispatcher= req.getRequestDispatcher("register.jsp");
	req.setAttribute("message", "something went wrong");
	requestDispatcher.include(req, resp);
		
	}
	
	
	
}
}

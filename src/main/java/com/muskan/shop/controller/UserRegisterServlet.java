package com.muskan.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muskan.shop.JDBCUtil;
import com.muskan.shop.dao.UserService;
import com.muskan.shop.model.User;

public class UserRegisterServlet extends HttpServlet{
	private UserService userService=new UserService();
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	User user=new User();
user.setUserName(req.getParameter("userName"));
	user.setUserEmail(req.getParameter("userEmail"));
	user.setUserPassword(req.getParameter("userPassword"));
	user.setUserPhone(req.getParameter("userPhone"));
	user.setUserAddress(req.getParameter("userAddress"));
	
	RequestDispatcher requestDispatcher= req.getRequestDispatcher("register.jsp");
	
	 try {
		userService.registerUser(user);
		
		req.setAttribute("message", "user registered with userName "+user.getUserName());
		
	} catch (SQLException e) {
	
	req.setAttribute("message", "something went wrong");
	requestDispatcher.include(req, resp);
		
	}
	
	 requestDispatcher.include(req, resp);
	
}
}

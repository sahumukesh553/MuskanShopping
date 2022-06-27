package com.muskan.shop.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class UserLoginServlet extends HttpServlet {
	private UserService userService=new UserService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("userEmail");
		String password=req.getParameter("userPassword");
		RequestDispatcher rd=req.getRequestDispatcher("login.jsp");
		
		try {
			User user=userService.loginUser(email, password);
		if(user!=null)
		{ 
		
		System.out.println("User logged in Successfully "+user);
		HttpSession session=req.getSession();
		session.setAttribute("loggedInUser",user);
		if(user.getUserType().trim().equalsIgnoreCase("admin"))
		{
			resp.sendRedirect("admin.jsp");
		}else
		{
			resp.sendRedirect("normal.jsp");
		}
		}else {
			req.setAttribute("message", "No user found with email : "+email);
			rd.forward(req, resp);
		}
		} catch (SQLException e) {
			req.setAttribute("message", "something Went wrong");
			rd.forward(req, resp);
			
		}
		
		
	}
	
}

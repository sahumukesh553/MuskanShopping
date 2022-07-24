package com.muskan.shop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.muskan.shop.dao.UserService;
import com.muskan.shop.entity.User;
@WebServlet("/update-user")
@MultipartConfig
public class UpdateUserServlet extends HttpServlet {
private UserService userService=new UserService();

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	User user=new User();
	user.setUserId(Integer.parseInt(req.getParameter("userId")));
user.setUserName(req.getParameter("userName"));
	user.setUserEmail(req.getParameter("userEmail"));
	user.setUserPassword(req.getParameter("userPassword"));
	user.setUserPhone(req.getParameter("userPhone"));
	user.setUserAddress(req.getParameter("userAddress"));
	user.setUserType(req.getParameter("userType"));
	Part photo=req.getPart("userPic");
	 InputStream in=photo.getInputStream();
	 user.setUserPhoto(in);
	
	RequestDispatcher requestDispatcher= req.getRequestDispatcher("user-list.jsp");
	
	 try {
		userService.updateUser(user);
		
		req.setAttribute("message", "user updated successfully");
		
	} catch (SQLException e) {
	
	req.setAttribute("message", "something went wrong");
	requestDispatcher.include(req, resp);
		
	}
	
	 requestDispatcher.include(req, resp);
	
}
}

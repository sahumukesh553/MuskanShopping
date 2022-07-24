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
@WebServlet("/update-profile")
@MultipartConfig
public class UpdateProfileServlet extends HttpServlet {
private UserService userService=new UserService();

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	User user=new User();
	user.setUserId(Integer.parseInt(req.getParameter("userId").trim()));
user.setUserName(req.getParameter("userName").trim());
	user.setUserEmail(req.getParameter("userEmail").trim());
	user.setUserPassword(req.getParameter("userPassword").trim());
	user.setUserPhone(req.getParameter("userPhone").trim());
	user.setUserAddress(req.getParameter("userAddress").trim());
	user.setUserType("normal");
	Part photo=req.getPart("userPic");
	 InputStream in=photo.getInputStream();
	 user.setUserPhoto(in);
	
	RequestDispatcher requestDispatcher= req.getRequestDispatcher("profile.jsp");
	
	 try {
		userService.updateUser(user);
		
		req.setAttribute("message", "user updated successfully");
		resp.sendRedirect("profile");
		
	} catch (SQLException e) {
	
	req.setAttribute("message", "something went wrong");
	requestDispatcher.include(req, resp);
		
	}
	
	 requestDispatcher.include(req, resp);
	
}
}

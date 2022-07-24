package com.muskan.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muskan.shop.dao.UserService;
import com.muskan.shop.entity.User;
@WebServlet("/get-user")
public class UserDataServlet extends HttpServlet {
	private UserService userService=new UserService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String id=req.getParameter("userId").trim();
       
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		int userId=Integer.parseInt(id);
		User user=userService.getUser(userId);
		
				
		
		out.print("<form action=\"update-user\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
				+ "<div class=\"form-group\">\r\n"
				+ "    \r\n"
				+ "    <input type=\"hidden\" class=\"form-control\" id=\"userId\" placeholder=\"Enter your userId\" name=\"userId\" value=\""+user.getUserId()+"\" required>\r\n"
				+ "  </div>\r\n"
				+ " <div class=\"form-group\">\r\n"
				+ "    <label for=\"userName\">Name</label>\r\n"
				+ "    <input type=\"text\" class=\"form-control\" id=\"userName\" placeholder=\"Enter your name\" name=\"userName\" value=\""+user.getUserName()+"\" required>\r\n"
				+ "  </div>\r\n"
				+ "  <div class=\"form-group\">\r\n"
				+ "    <label for=\"userEmail\">Email address</label>\r\n"
				+ "    <input type=\"email\" class=\"form-control\" id=\"userEmail\" placeholder=\"name@example.com\" name=\"userEmail\" value=\""+user.getUserEmail()+"\" required>\r\n"
				+ "  </div>\r\n"
				+ "   <div class=\"form-group\">\r\n"
				+ "    <label for=\"userPassword\">Password</label>\r\n"
				+ "    <input type=\"password\" class=\"form-control\" id=\"userPassword\" placeholder=\"Password\" name=\"userPassword\" value=\""+user.getUserPassword()+"\" required>\r\n"
				+ "  </div>\r\n"
				+ "  <div class=\"form-group\">\r\n"
				+ "    <label for=\"userPhone\">mobile number</label>\r\n"
				+ "    <input type=\"tel\" class=\"form-control\" id=\"exampleInputPassword4\" placeholder=\"Enter mobile number\" name=\"userPhone\" value=\""+user.getUserPhone()+"\" required>\r\n"
				+ "  </div>\r\n"
				+ "  <div class=\"form-group\">\r\n"
				+ "    <label for=\"userPic\">profile pic</label>\r\n"
				+ "    <input type=\"file\" class=\"form-control-file\" id=\"userPic\" name=\"userPic\">\r\n"
				+ "  </div>\r\n"
				+"<div class=\"form-group\">\r\n"
				+ "    <label for=\"userType\">User Type</label>\r\n"
				+ "    <select class=\"form-control\" name=\"userType\" id=\"userType\" required>\r\n"
				+ "     <option value=\"normal\" id=\"normal\">Normal</option>\r\n"
				+ "     <option value=\"admin\" id=\"admin\">Admin</option>\r\n"
				+ "    	  </select>\r\n"
				+ "    	   </div>"
				+ "  <div class=\"form-group\">\r\n"
				+ "  <label for=\"userAddress\">address</label>\r\n"
				+ "    <textarea class=\"form-control\" id=\"userAddress\" rows=\"2\" name=\"userAddress\"  required \">"+user.getUserAddress()+"</textarea>\r\n"
				+ "  </div>\r\n"
				+ "  <button type=\"submit\" class=\"btn btn-outline-success\">Update</button>\r\n"
				+ "    <button type=\"reset\" class=\"btn btn-outline-warning\">Reset</button>\r\n"
				+ "</form>");
		
	}

}

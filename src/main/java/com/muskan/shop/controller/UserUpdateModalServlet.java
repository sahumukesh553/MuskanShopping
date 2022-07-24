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
@WebServlet("/profile-update")
public class UserUpdateModalServlet extends HttpServlet {
	private UserService userService=new UserService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String id=req.getParameter("userId").trim();
       
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		int userId=Integer.parseInt(id);
		User user=userService.getUser(userId);
		
				
		
		out.print("<form action=\"update-profile\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
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
				+ "    <input type=\"email\" class=\"form-control\" id=\"userEmail\" placeholder=\"name@example.com\" name=\"userEmail\" value=\""+user.getUserEmail()+"\" required onblur=\"validateEmail()\">\r\n"
				+ "   <small id=\"emailValidation\"></small>"
				+ "</div>\r\n"
				+ "   <div class=\"form-group\">\r\n"
				+ "    <label for=\"userPassword\">Password</label>\r\n"
				+ "    <input type=\"password\" class=\"form-control\" id=\"userPassword\" placeholder=\"Password\" name=\"userPassword\" value=\""+user.getUserPassword()+"\" required>\r\n"
				+ "  </div>\r\n"
				+ "  <div class=\"form-group\">\r\n"
				+ "    <label for=\"userPhone\">mobile number</label>\r\n"
				+ "    <input type=\"tel\" class=\"form-control\" id=\"userPhone\" placeholder=\"Enter mobile number\" name=\"userPhone\" value=\""+user.getUserPhone()+"\" required onblur=\"validatePhone()\">\r\n"
				+ "  <small id=\"phoneValidation\"></small>"
				+ " </div>\r\n"
				+ "  <div class=\"form-group\">\r\n"
				+ "    <label for=\"userPic\">profile pic</label>\r\n"
				+ "    <input type=\"file\" class=\"form-control-file\" id=\"userPic\" name=\"userPic\">\r\n"
				+ "  </div>\r\n"
				+ "  <div class=\"form-group\">\r\n"
				+ "  <label for=\"userAddress\">address</label>\r\n"
				+ "    <textarea class=\"form-control\" id=\"userAddress\" rows=\"2\" name=\"userAddress\" required \">"+user.getUserAddress()+"</textarea>\r\n"
				+ "  </div>\r\n"
				+ "  <button type=\"submit\" class=\"btn btn-outline-success\">Update</button>\r\n"
				+ "    <button type=\"reset\" class=\"btn btn-outline-warning\">Reset</button>\r\n"
				+ "</form>");
		
	}

}

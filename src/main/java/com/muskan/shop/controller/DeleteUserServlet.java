package com.muskan.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muskan.shop.dao.UserService;
@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
	private UserService userService=new UserService();
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int userId=Integer.parseInt(req.getParameter("id").trim());
	userService.deleteUser(userId);
	resp.sendRedirect("user-list.jsp");
}
}

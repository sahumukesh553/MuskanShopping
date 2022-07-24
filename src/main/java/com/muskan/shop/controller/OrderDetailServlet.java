package com.muskan.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muskan.shop.dao.OrderDataService;
import com.muskan.shop.entity.OrderData;
import com.muskan.shop.entity.OrderedProduct;
import com.muskan.shop.entity.User;
@WebServlet("/order-detail")
public class OrderDetailServlet extends HttpServlet {
	
	private OrderDataService orderDataService=new OrderDataService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession(false);
		User user=(User) session.getAttribute("loggedInUser");
		
		
		
		
	}

}

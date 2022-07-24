package com.muskan.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muskan.shop.dao.OrderDataService;
import com.muskan.shop.dao.UserService;
import com.muskan.shop.entity.OrderData;
import com.muskan.shop.entity.User;
@WebServlet("/profile")
public class UserProfileServlet extends HttpServlet{
	UserService userService=new UserService();
	private OrderDataService orderDataService=new OrderDataService();
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession session=req.getSession(false);
if(session!=null)
{
User loggedInUser=(User)session.getAttribute("loggedInUser");
if(loggedInUser!=null)
{
int id=loggedInUser.getUserId();
List<OrderData> orders=orderDataService.getAllOrders(id);
System.out.println("profile id :"+id);;
User user=userService.getUser(id);
req.setAttribute("user",user);
req.setAttribute("orders", orders);
req.getRequestDispatcher("profile.jsp").forward(req, resp);
}
}
else {
	
	req.getRequestDispatcher("login.jsp").forward(req, resp);
}

}
}

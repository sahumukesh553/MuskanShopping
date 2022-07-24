package com.muskan.shop.validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muskan.shop.dao.UserService;
import com.muskan.shop.entity.User;
@WebServlet("/validate")
public class ValidatorServlet extends HttpServlet {
	UserService userService=new UserService();
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String number=req.getParameter("userPhone");
	
	PrintWriter out=resp.getWriter();
	resp.setContentType("text/html");
	if(email!=null)
	{
		if(!Pattern.matches("[a-z][a-z0-9]{5,}@[a-z]+(\\.[a-z&&[com]]+)", email))
		{
			out.print("not a valid email");
			return;
		}
		User user=userService.getUserByEmail(email);
		if(user!=null)
		{
			out.print("this email already registed please use any other email");
			return;
		}
		else {
			return;
		}
		
	}
	if(number!=null)
	{
		if(!Pattern.matches("[789][0-9]{9}",number))
		{
			out.print("not a valid number length should 10 digit starting 7,8 or 9");
			return;
		}
		User user=userService.getUserByNumber(number);
		if(user!=null)
		{
			out.print("this mobile number already registed please use any other mobile number");
			return;
		}
		else {
			return;
		}
	}
	return;
}
}

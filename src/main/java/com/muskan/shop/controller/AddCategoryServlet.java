package com.muskan.shop.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muskan.shop.dao.CategoryService;
import com.muskan.shop.entity.Category;

@WebServlet("/addCategory")
public class AddCategoryServlet extends HttpServlet{
	
	CategoryService categoryService=new CategoryService();
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Category category=new Category();
	category.setCategoryTitle(req.getParameter("categoryTitle").trim());
	
	category.setCategoryDescription(req.getParameter("categoryDescription").trim());
	System.out.println(category);
	RequestDispatcher rd=req.getRequestDispatcher("admin.jsp");
	
	try
	{
		categoryService.save(category);
		req.setAttribute("message", category.getCategoryTitle()+" is created successfully");
		rd.forward(req, resp);
		
	}catch(SQLException ex)
	{
		req.setAttribute("message","Something went wrong while saving category : "+category.getCategoryTitle());
		rd.forward(req, resp);
	}
	
}
}

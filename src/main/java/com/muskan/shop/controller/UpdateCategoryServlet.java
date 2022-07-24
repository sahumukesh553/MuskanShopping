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
@WebServlet("/update-category")

public class UpdateCategoryServlet extends HttpServlet{
private CategoryService categoryService=new CategoryService();

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Category category=new Category();
	category.setCategoryId(Integer.parseInt(req.getParameter("categoryId").trim()));
	category.setCategoryTitle(req.getParameter("categoryTitle").trim());
	
	category.setCategoryDescription(req.getParameter("categoryDescription").trim());

	RequestDispatcher rd=req.getRequestDispatcher("category-list.jsp");
	
	try
	{
		categoryService.updateCategory(category);
		req.setAttribute("message", "category  is updated successfully");
		rd.forward(req, resp);
		
	}catch(SQLException ex)
	{
		req.setAttribute("message","Something went wrong while updating category : "+category.getCategoryTitle());
		rd.forward(req, resp);
	}
	
}
}

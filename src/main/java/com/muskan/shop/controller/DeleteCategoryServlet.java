package com.muskan.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muskan.shop.dao.CategoryService;
@WebServlet("/delete-category")
public class DeleteCategoryServlet extends HttpServlet {
private CategoryService categoryService=new CategoryService();
	@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int categoryId=Integer.parseInt(req.getParameter("id").trim());
	categoryService.deleteCategory(categoryId);
	resp.sendRedirect("category-list.jsp");
}
}

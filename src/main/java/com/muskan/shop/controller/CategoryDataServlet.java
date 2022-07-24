package com.muskan.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muskan.shop.dao.CategoryService;
import com.muskan.shop.entity.Category;
@WebServlet("/get-category")
public class CategoryDataServlet extends HttpServlet {
	private CategoryService categoryService=new CategoryService();
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 String id=req.getParameter("categoryId").trim();
	PrintWriter out=resp.getWriter();
	int categoryId=Integer.parseInt(id);
	Category category=categoryService.getCategory(categoryId);
	resp.setContentType("text/html");
	out.print("<form action=\"update-category\" method=\"post\">\r\n"
			+ "      <div class=\"form-group\">\r\n"
			+ "    \r\n"
			+ "    <input type=\"hidden\" class=\"form-control\" id=\"categoryId\" placeholder=\"Enter Category CategoryId\" name=\"categoryId\" value=\""+category.getCategoryId()+"\" required>\r\n"
			+ "  </div>\r\n"
			+ "       <div class=\"form-group\">\r\n"
			+ "    <label for=\"categoryTitle\">Category Title</label>\r\n"
			+ "    <input type=\"text\" class=\"form-control\" id=\"categoryTitle\" placeholder=\"Enter Category Title\" name=\"categoryTitle\" value=\""+category.getCategoryTitle()+"\" required>\r\n"
			+ "  </div>\r\n"
			+ "       <div class=\"form-group\">\r\n"
			+ "  <label for=\"categoryDescription\">Category Description</label>\r\n"
			+ "    <textarea class=\"form-control\" id=\"categoryDescription\" rows=\"2\" name=\"categoryDescription\" required>"+category.getCategoryDescription()+"</textarea>\r\n"
			+ "  </div>\r\n"
			+ "  <button type=\"submit\" class=\"btn btn-outline-success\">update Category</button>\r\n"
			+ "    <button type=\"reset\" class=\"btn btn-outline-warning\">Reset</button>\r\n"
			+ "</form>");
	
}
}

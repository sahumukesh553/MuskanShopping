package com.muskan.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.muskan.shop.dao.CategoryService;
import com.muskan.shop.dao.ProductService;
import com.muskan.shop.model.Category;
import com.muskan.shop.model.Product;
@WebServlet(urlPatterns = {"/category-product","/home","/index.html","/normal.jsp"})
public class HomeServlet extends HttpServlet{
	
ProductService productService=new ProductService();
CategoryService categoryService=new CategoryService();
	@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	String category=req.getParameter("category");
	System.out.println(category);
	


	List<Category> categories=categoryService.getAllCategory();
	req.setAttribute("categories", categories);
	
	List<Product> products=productService.getAllProduct();
	if(category!=null)
	{
	if( category.trim().equals("all"))
	{
		products=productService.getAllProduct();
		req.setAttribute("products", products);
		
	}
	else {
		products=productService.getProductsByCategoryId(category);
		if(products.size()==0)
			req.setAttribute("message","no products found in this category");
		else
		req.setAttribute("products", products);
	      
	}
	}else {
products=productService.getAllProduct();
req.setAttribute("products", products);
	}
	
	req.getRequestDispatcher("index.jsp").forward(req, resp);
	
	
}
}

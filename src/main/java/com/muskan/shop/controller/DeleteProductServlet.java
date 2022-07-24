package com.muskan.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muskan.shop.dao.ProductService;
@WebServlet("/delete-product")
public class DeleteProductServlet extends HttpServlet {
	private ProductService productService=new ProductService();
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int productId=Integer.parseInt(req.getParameter("id").trim());
	productService.deleteProduct(productId);
	resp.sendRedirect("product-list.jsp");
	
}
}

package com.muskan.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muskan.shop.dao.ProductService;
import com.muskan.shop.entity.Product;
@WebServlet("/product-detail")
public class ProductDetailServlet extends HttpServlet{
	private ProductService productService=new ProductService();
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("productId").trim());
	Product product=productService.getProduct(id);
	req.setAttribute("product", product);
	req.getRequestDispatcher("product-detail.jsp").forward(req, resp);
	
}
}

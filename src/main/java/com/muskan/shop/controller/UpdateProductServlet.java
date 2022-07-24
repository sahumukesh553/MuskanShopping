package com.muskan.shop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.muskan.shop.dao.ProductService;
import com.muskan.shop.entity.Product;
@WebServlet("/update-product")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet{
	private ProductService productService=new ProductService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Product p= new Product();
		RequestDispatcher rd=req.getRequestDispatcher("product-list.jsp");
		p.setProductId(Integer.parseInt(req.getParameter("productId").trim()));
		p.setProductTitle(req.getParameter("productTitle").trim());
		p.setProductPrice(Integer.valueOf(req.getParameter("productPrice").trim()));
		p.setProductQuantity(Integer.valueOf(req.getParameter("productQuantity").trim()));
		p.setProductDiscount(Integer.valueOf(req.getParameter("productDiscount").trim()));
		p.setProductDesc(req.getParameter("productDesc").trim());
		p.setCid(Integer.valueOf(req.getParameter("productCategory").trim()));
		Part photo=req.getPart("productPhoto");
		
		 InputStream in=photo.getInputStream();
		
		 p.setProductPhoto(in);
		 try {
			productService.updateProduct(p);
			req.setAttribute("message", "product updated Successfully");
			rd.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("message", "not able update product :"+p.getProductTitle());
			rd.forward(req, resp);
		}
		 
		 
	}

}

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
import com.muskan.shop.model.Product;

@WebServlet("/addProduct")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
	ProductService productService=new ProductService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Product p= new Product();
		RequestDispatcher rd=req.getRequestDispatcher("admin.jsp");
		p.setProductTitle(req.getParameter("productTitle"));
		p.setProductPrice(Integer.valueOf(req.getParameter("productPrice")));
		p.setProductQuantity(Integer.valueOf(req.getParameter("productQuantity")));
		p.setProductDiscount(Integer.valueOf(req.getParameter("productDiscount")));
		p.setProductDesc(req.getParameter("productDesc"));
		p.setCid(Integer.valueOf(req.getParameter("productCategory")));
		Part photo=req.getPart("productPhoto");
		 InputStream in=photo.getInputStream();
		 p.setProductPhoto(in);
		 try {
			productService.saveProduct(p);
			req.setAttribute("message", p.getProductTitle()+"saved Successfully");
			rd.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("message", "not able save product :"+p.getProductTitle());
			rd.forward(req, resp);
		}
		 
		 
		
	}
	
	
	

}

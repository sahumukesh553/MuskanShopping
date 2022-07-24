package com.muskan.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muskan.shop.dao.OrderDataService;
import com.muskan.shop.entity.OrderData;
import com.muskan.shop.model.ProductRequestModel;

@WebServlet("/make-order")
public class MakeOrderServlet extends HttpServlet{
	private OrderDataService orderDataService=new OrderDataService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String products=req.getParameter("products");
		OrderData order=new OrderData();
		order.setUserId(Integer.parseInt(req.getParameter("userId")));
		order.setName(req.getParameter("name"));
		order.setMobile(req.getParameter("mobile"));
		order.setAddress(req.getParameter("address"));
		order.setCity(req.getParameter("city"));
		order.setState(req.getParameter("state"));
		order.setPincode(req.getParameter("pincode"));
		orderDataService.makeOrder(order,getProducts( products));
	
		resp.sendRedirect("home");
		
	}
	
	List<ProductRequestModel> getProducts(String products)
	{
		List<ProductRequestModel> productList=new ArrayList<>();
		String[] allProducts=products.split("}");
		
		for(int i=0;i<allProducts.length-1;i++)
		{String p=allProducts[i];
		ProductRequestModel product=new ProductRequestModel();
			product.setId(Integer.parseInt(p.substring(p.indexOf("productId")+"productId\":".length(), p.indexOf("productName")-2)));
			product.setTitle(p.substring(p.indexOf("productName")+"productName\":".length()+1, p.indexOf("productPrice")-3));
			product.setPrice(Integer.parseInt(p.substring(p.indexOf("productPrice")+"productPrice\":".length(), p.indexOf("productQuantity")-2)));
			product.setQuantity(Integer.parseInt(p.substring(p.indexOf("productQuantity")+"productQuantity\":".length())));
			productList.add(product);
		}
		return productList;

	}

}

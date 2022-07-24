package com.muskan.shop.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.log4j.Logger;

import com.muskan.shop.JDBCUtil;
import com.muskan.shop.entity.OrderedProduct;
import com.muskan.shop.entity.Product;
import com.muskan.shop.model.ProductRequestModel;

public class OrderedProductService {
	 static Logger log = Logger.getLogger(OrderedProductService.class);
	ProductService productService=new ProductService();
	public List<OrderedProduct> prepareOrderList(List<ProductRequestModel> products) {
		List<OrderedProduct> orderedProducts=new ArrayList<OrderedProduct>();
		for(ProductRequestModel product:products)
		{
			OrderedProduct orderedProduct=new OrderedProduct();
			Product p=productService.getProduct(product.getId());
			orderedProduct.setProductTitle(p.getProductTitle());
			orderedProduct.setProductDesc(p.getProductDesc());
			orderedProduct.setCid(p.getCid());
			orderedProduct.setProductPhoto(p.getProductPhoto());
			orderedProduct.setProductPrice(product.getPrice()*product.getQuantity());
			orderedProduct.setProductQuantity(product.getQuantity());
			orderedProducts.add(orderedProduct);
		}
		return orderedProducts;
	}
	
	public void saveOrderedProduct(List<ProductRequestModel> products,int orderId) throws SQLException
	{Connection con=JDBCUtil.getConnection();
		List<OrderedProduct> orderedProducts=prepareOrderList(products);
		String query="Insert into ordered_product(`productTitle`,`productDesc`,`productPhoto`,`productPrice`,`orderId`,`productQuantity`,`cid`) values(?,?,?,?,?,?,?)";
		 PreparedStatement prepStmt = null;
		 
		for(OrderedProduct orderProduct:orderedProducts)
		{
		    prepStmt = con.prepareStatement(query);
		    if(prepStmt!=null)
			{
		    	prepStmt.setString(1, orderProduct.getProductTitle());
		    	prepStmt.setString(2, orderProduct.getProductDesc());
		    	
				System.out.println(orderProduct.getProductPhoto().toString());
			
		    	prepStmt.setBlob(3,orderProduct.getProductPhoto());
		    	prepStmt.setInt(4, orderProduct.getProductPrice());
		    	prepStmt.setInt(5, orderId);
		    	prepStmt.setInt(6, orderProduct.getProductQuantity());
		    	prepStmt.setInt(7,orderProduct.getCid());
		    prepStmt.executeUpdate();
		    con.commit();
		    prepStmt.close();
			}else {
				log.debug("databse connection failed during saving product");
			}
		}
		 
	}

	public List<OrderedProduct> getAllOrderedProduct(int orderId)
	{
		
		String query="select * from ordered_product where orderId=?";
		Connection con=JDBCUtil.getConnection();
		List<OrderedProduct> products=null;
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, orderId);
			ResultSet rs=	ps.executeQuery();
			 products=new ArrayList<OrderedProduct>();
			while(rs.next())
			{
			OrderedProduct p=new OrderedProduct();
			p.setProductTitle(rs.getString("productTitle"));
			p.setProductDesc(rs.getString("productDesc"));
			p.setOrderId(rs.getInt("orderId"));
			p.setProductPrice(rs.getInt("productPrice"));
			p.setProductQuantity(rs.getInt("productQuantity"));
			p.setProductId(rs.getInt("productId"));
			p.setCid(rs.getInt("cid"));
			byte[] imgData = rs.getBytes("productPhoto");
			String image = Base64.getEncoder().encodeToString(imgData);
			p.setImageData(image);
			products.add(p);	
			}
		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
		finally {
			
			try {
				
				ps.close();
			} catch (SQLException |NullPointerException e) {
				log.debug(e.getMessage());
			}
		}
		return products;
	}
}

package com.muskan.shop.dao;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.muskan.shop.JDBCUtil;
import com.muskan.shop.entity.Product;

public class ProductService {
	
	 static Logger log = Logger.getLogger(ProductService.class);  
	public int getProductCount()
	{int count=0;
		String query="select count(*) from product";
		Connection con=JDBCUtil.getConnection();
		
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs=	ps.executeQuery();
			if(rs.next())
			{count =rs.getInt(1);
			System.out.println("products "+count);
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
		
		return count;
		}
	public boolean saveProduct(Product p) throws SQLException
	{ boolean result=false;
	
	Connection con=JDBCUtil.getConnection();
		String query="Insert into product(`productTitle`,`productDesc`,`productPhoto`,`productPrice`,`productDiscount`,`productQuantity`,`cid`) values(?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		
			ps = con.prepareStatement(query);
			if(ps!=null)
			{
			 ps.setString(1, p.getProductTitle());
			 ps.setString(2, p.getProductDesc());
			 ps.setBlob(3, p.getProductPhoto());
			 ps.setInt(4, p.getProductPrice());
			 ps.setInt(5, p.getProductDiscount());
			 ps.setInt(6, p.getProductQuantity());
			 ps.setInt(7,p.getCid());
			result= ps.execute();
			}else {
				log.debug("databse connection failed during saving product");
			}
			return result;
	
	}
	
	public List<Product> getAllProduct()
	{
		String query="select * from product";
		Connection con=JDBCUtil.getConnection();
		List<Product> products=null;
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs=	ps.executeQuery();
			 products=new ArrayList<Product>();
			while(rs.next())
			{
			Product p=new Product();
			p.setProductTitle(rs.getString("productTitle"));
			p.setProductDesc(rs.getString("productDesc"));
			p.setProductDiscount(rs.getInt("productDiscount"));
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
	public List<Product> getProductsByCategoryId(String category) {
		int catId=Integer.parseInt(category.trim());
		String query="select * from product where cid=?";
		Connection con=JDBCUtil.getConnection();
		List<Product> products=null;
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, catId);
			ResultSet rs=	ps.executeQuery();
			 products=new ArrayList<Product>();
			while(rs.next())
			{
			Product p=new Product();
			p.setProductTitle(rs.getString("productTitle"));
			p.setProductDesc(rs.getString("productDesc"));
			p.setProductDiscount(rs.getInt("productDiscount"));
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
public Product getProduct(int id)
{
	String query="select * from product where productId=?";
	Connection con=JDBCUtil.getConnection();
	
	PreparedStatement ps=null;
	Product p=null;
	try {
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs=	ps.executeQuery();
		
		if(rs.next())
		{
		p=new Product();
		p.setProductTitle(rs.getString("productTitle"));
		p.setProductDesc(rs.getString("productDesc"));
		p.setProductDiscount(rs.getInt("productDiscount"));
		p.setProductPrice(rs.getInt("productPrice"));
		p.setProductQuantity(rs.getInt("productQuantity"));
		p.setProductId(rs.getInt("productId"));
		p.setCid(rs.getInt("cid"));
		byte[] imgData = rs.getBytes("productPhoto");
		p.setProductPhoto(new ByteArrayInputStream(imgData));
		String image = Base64.getEncoder().encodeToString(imgData);
		p.setImageData(image);
			
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
	return p;
}
public void deleteProduct(int productId)
{
	String query="delete  from product where productId=?";
	Connection con=JDBCUtil.getConnection();
	
	PreparedStatement ps=null;
	Product p=null;
	try {
		ps = con.prepareStatement(query);
		ps.setInt(1, productId);
		ps.executeUpdate();
		
		
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
}
public boolean updateProduct(Product p) throws SQLException
{
boolean result=false;
	
	Connection con=JDBCUtil.getConnection();
		
		PreparedStatement ps=null;
		if(p.getProductPhoto() instanceof FileInputStream)
		{String query="update product set `productTitle`=?,`productDesc`=?,`productPrice`=?,`productDiscount`=?,`productQuantity`=?,`cid`=?,`productPhoto`=? where productId=?";
			ps = con.prepareStatement(query);
			if(ps!=null) {
			 ps.setString(1, p.getProductTitle());
			 ps.setString(2, p.getProductDesc());
			 ps.setInt(3, p.getProductPrice());
			 ps.setInt(4, p.getProductDiscount());
			 ps.setInt(5, p.getProductQuantity());
			 ps.setInt(6,p.getCid());
			 ps.setBlob(7,p.getProductPhoto());
			 ps.setInt(8, p.getProductId());
			 ps.executeUpdate();
			 result=true;
			
			}
		}else {
				String query2="update product set `productTitle`=?,`productDesc`=?,`productPrice`=?,`productDiscount`=?,`productQuantity`=?,`cid`=? where productId=?";
				ps = con.prepareStatement(query2);
				if(ps!=null) {
				 ps.setString(1, p.getProductTitle());
				 ps.setString(2, p.getProductDesc());
				 ps.setInt(3, p.getProductPrice());
				 ps.setInt(4, p.getProductDiscount());
				 ps.setInt(5, p.getProductQuantity());
				 ps.setInt(6,p.getCid());
				
				 ps.setInt(7, p.getProductId());
				 ps.executeUpdate();
				 result=true;
				
			}
			}
			
			if(ps==null){
				log.debug("databse connection failed during saving product");
				 result=false;
			}
			return result;
		
}

}

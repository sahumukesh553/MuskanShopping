package com.muskan.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.muskan.shop.JDBCUtil;
import com.muskan.shop.model.Product;

public class ProductService {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			try {
				
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			 ps.setString(1, p.getProductTitle());
			 ps.setString(2, p.getProductDesc());
			 ps.setBlob(3, p.getProductPhoto());
			 ps.setInt(4, p.getProductPrice());
			 ps.setInt(5, p.getProductDiscount());
			 ps.setInt(6, p.getProductQuantity());
			 ps.setInt(7,p.getCid());
			result= ps.execute();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			try {
				
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			try {
				
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return products;
			}


}

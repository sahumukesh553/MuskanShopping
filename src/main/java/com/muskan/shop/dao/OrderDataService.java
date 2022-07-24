package com.muskan.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;

import com.muskan.shop.JDBCUtil;
import com.muskan.shop.entity.OrderData;
import com.muskan.shop.entity.Product;
import com.muskan.shop.model.ProductRequestModel;

public class OrderDataService {
	 static Logger log = Logger.getLogger(OrderDataService.class);
OrderedProductService orderedProductService=new OrderedProductService();
	public void makeOrder(OrderData order, List<ProductRequestModel> products) {
		
		
		boolean result=false;
		
		
		Connection con=JDBCUtil.getConnection();
		
		String insertOrderQuery="Insert into order_data(`name`,`mobile`,`address`,`city`,`state`,`pincode`,`userId`) values(?,?,?,?,?,?,?)";
			
			
			 int orderId=0;
			  PreparedStatement prepStmt = null;
			  try {
				 con.setAutoCommit(false);
			    prepStmt = con.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
			    prepStmt.setString(1, order.getName());
			    prepStmt.setString(2, order.getMobile());
			    prepStmt.setString(3, order.getAddress());
			    prepStmt.setString(4, order.getCity());
			    prepStmt.setString(5, order.getState());
			    prepStmt.setString(6, order.getPincode());
			    prepStmt.setInt(7, order.getUserId());
			    
			    prepStmt.executeUpdate();
			    try (ResultSet generatedKeys = prepStmt.getGeneratedKeys()) {
			      if (generatedKeys.next()) {
			        orderId = generatedKeys.getInt(1);
			        System.out.println("orderId is- " + orderId);
			        orderedProductService.saveOrderedProduct(products, orderId);
			        con.commit();
			        
			      }
			      else {
			    	  con.rollback();
			       
			        throw new SQLException("OrderData insertion has problem. No ID returned.");
			      }
			    }
			
			  }
			  catch(Exception e)
			  {
				  try {
					con.rollback();
				} catch (SQLException e1) {
					 System.out.println(e1.getMessage());
				}
				  System.out.println(e.getMessage());
			  }
			finally{
			    if(prepStmt != null){
			      try {
					prepStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }
			  }
			}
			
			public List<OrderData> getAllOrders(int userId)
			{
				
				String query="select * from order_data where userId=?";
				Connection con=JDBCUtil.getConnection();
				List<OrderData> orders=null;
				PreparedStatement ps=null;
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1,userId);
					ResultSet rs=	ps.executeQuery();
					 orders=new ArrayList<OrderData>();
					while(rs.next())
					{
					OrderData od=new OrderData();
					od.setOrderId(rs.getInt("orderId"));
					od.setName(rs.getString("name"));
					od.setMobile(rs.getString("mobile"));
					od.setAddress(rs.getString("address"));
					od.setCity(rs.getString("city"));
					od.setPincode(rs.getString("pincode"));
					od.setState(rs.getString("state"));
					od.setUserId(rs.getInt("userId"));
					od.setProducts(orderedProductService.getAllOrderedProduct(od.getOrderId()));
					orders.add(od);
					
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
				return orders;
			}
			

}

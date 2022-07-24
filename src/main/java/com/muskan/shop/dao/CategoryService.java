package com.muskan.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.muskan.shop.JDBCUtil;
import com.muskan.shop.entity.Category;

public class CategoryService {
	 static Logger log = Logger.getLogger(CategoryService.class);  
	
	public int getCategoryCount()
	{int count=0;
		String query="select count(*) from category";
		Connection con=JDBCUtil.getConnection();
		
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs=	ps.executeQuery();
			if(rs.next())
			{count =rs.getInt(1);
			System.out.println("categories "+count);
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

	public void save(Category category) throws SQLException {
		String query="insert into category(`categoryTitle`,`categoryDescription`) values(?,?)";
		Connection con=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ps = con.prepareStatement(query);
		if(ps!=null)
		{
		ps.setString(1, category.getCategoryTitle());
		ps.setString(2,category.getCategoryDescription());
		ps.executeUpdate();
		ps.close();
		}
		else {
			log.debug("database connection failed during saving category");
		}
		}
	
	
	public List<Category> getAllCategory()
	{List<Category> ls=null;
		String query="select * from category";
		Connection con=JDBCUtil.getConnection();
		
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			ls=new ArrayList<Category>();
			while(rs.next())
			{
			ls.add(new Category(rs.getInt("categoryId"),rs.getString("categoryTitle"),rs.getString("categoryDescription")));
			}
		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
		finally {
			
			try {
				
				ps.close();
			} catch (SQLException |NullPointerException e ) {
				// TODO Auto-generated catch block
				log.debug(e.getMessage());
			}
		}
		return ls;
		
	}
	
	public Category getCategory(int id)
	{
		String query="select * from category where categoryId=?";
		Connection con=JDBCUtil.getConnection();
		Category cat=null;
		
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
			cat=new Category(rs.getInt("categoryId"),rs.getString("categoryTitle"),rs.getString("categoryDescription"));
			}
		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
		finally {
			
			try {
				
				ps.close();
			} catch (SQLException |NullPointerException e) {
				// TODO Auto-generated catch block
				log.debug(e.getMessage());
			}
		}
		return cat;
		
	}
public void deleteCategory(int categoryId)
{
	String query="delete from category where categoryId=?";
	Connection con=JDBCUtil.getConnection();
	Category cat=null;
	
	PreparedStatement ps=null;
	try {
		ps = con.prepareStatement(query);
		ps.setInt(1, categoryId);
		ps.executeUpdate();
		
		
	} catch (SQLException e) {
		log.debug(e.getMessage());
	}
	finally {
		
		try {
			
			ps.close();
		} catch (SQLException |NullPointerException e ) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
		}
	}
	
	
}
public boolean updateCategory(Category category) throws SQLException
{
	String query="update category set `categoryTitle`=?,`categoryDescription`=? where categoryId=?";
	Connection con=JDBCUtil.getConnection();
	PreparedStatement ps=null;
	
	boolean result=false;
	
	ps = con.prepareStatement(query);
	if(ps!=null)
	{
	ps.setString(1, category.getCategoryTitle());
	ps.setString(2,category.getCategoryDescription());
	ps.setInt(3, category.getCategoryId());
	ps.executeUpdate();
	result=true;
	return result;
	}


	return result;
}
}

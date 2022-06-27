package com.muskan.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.muskan.shop.JDBCUtil;
import com.muskan.shop.model.Category;

public class CategoryService {
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

	public void save(Category category) throws SQLException {
		String query="insert into category(`categoryTitle`,`categoryDescription`) values(?,?)";
		Connection con=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ps = con.prepareStatement(query);
		ps.setString(1, category.getCategoryTitle());
		ps.setString(2,category.getCategoryDescription());
		ps.executeUpdate();
		ps.close();
		}
	
	
	public List<Category> getAllCategory()
	{List<Category> ls=null;
		String query="select categoryId,categoryTitle from category";
		Connection con=JDBCUtil.getConnection();
		
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			ls=new ArrayList<Category>();
			while(rs.next())
			{
			ls.add(new Category(rs.getInt(1),rs.getString(2)));
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
		return ls;
		
	}
	
	


}

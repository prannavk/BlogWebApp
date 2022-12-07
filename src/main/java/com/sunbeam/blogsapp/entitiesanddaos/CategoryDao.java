package com.sunbeam.blogsapp.entitiesanddaos;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements Closeable {

	private Connection con;

	public CategoryDao() throws SQLException {
		con = DbUtil.getCOnnection();
	}

	@Override
	public void close() throws IOException {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Category> getAllCategories() throws SQLException {
		List<Category> allcats = null;
		String sqlq = "SELECT * FROM categories;";
		try (PreparedStatement ps = con.prepareStatement(sqlq)) {
			try (ResultSet rs = ps.executeQuery()) {
				allcats = new ArrayList<>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String desc = rs.getString("description");
					Category c = new Category(id, title, desc);
					allcats.add(c);
				}
			}
		}
		return allcats;
	}

}

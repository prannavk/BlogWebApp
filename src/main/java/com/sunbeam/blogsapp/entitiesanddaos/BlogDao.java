package com.sunbeam.blogsapp.entitiesanddaos;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDao implements Closeable {

	private Connection con;

	public BlogDao() throws SQLException {
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

	public List<Blog> findAllBlogs() throws Exception {
		List<Blog> userList = new ArrayList<Blog>();
		String sql = "SELECT * FROM blogs";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String email = rs.getString("contents");
					java.util.Date newDate = new Date(rs.getDate("created_time").getTime());
					int uid = rs.getInt("user_id");
					int catid = rs.getInt("category_id");
					Blog b = new Blog(id, title, email, newDate, uid, catid);
					userList.add(b);
				}
			}
		}
		return userList;
	}

	// SELECT blogs.id, blogs.title, blogs.contents, blogs.created_time,
	// blogs.user_id, user.full_name as "By user", blogs.category_id FROM blogs,
	// user WHERE (blogs.user_id = user.id);
	public List<Blog> findAllTheBlogsJoinedWithName() throws Exception {
		List<Blog> bList = new ArrayList<Blog>();
		// UserDao ud = new UserDao();
		String sql = "SELECT b.id, b.title, b.contents, b.created_time, b.user_id, u.full_name, b.category_id FROM blogs b INNER JOIN user u ON b.user_id = u.id;";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String email = rs.getString("contents");
					java.util.Date newDate = new Date(rs.getDate("created_time").getTime());
					int uid = rs.getInt("user_id");
					String e = rs.getString("full_name");
					System.out.println(e);
					int catid = rs.getInt("category_id");
					Blog b = new Blog(id, title, email, newDate, uid, catid);
					b.setExtraField(e);
					System.out.println(b.getExtraField());
					bList.add(b);
				}
			}
		}
		return bList;
	}

	public List<Blog> findMyBlogs(User u) throws Exception {
		List<Blog> blist = new ArrayList<Blog>();
		String sql = "SELECT * FROM blogs WHERE user_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, u.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String contents = rs.getString("contents");
					java.util.Date newDate = new Date(rs.getDate("created_time").getTime());
					int uid = rs.getInt("user_id");
					int catid = rs.getInt("category_id");
					Blog b = new Blog(id, title, contents, newDate, uid, catid);
					blist.add(b);
				}
			}
		}
		return blist;
	}

	public int save(Blog blog) throws Exception {
		int cnt = -1;
		try {
			con.setAutoCommit(false);
			String inssql = "INSERT INTO blogs(title, contents, created_time, user_id, category_id) VALUES(?,?,?,?,?)";
			try (PreparedStatement stmt = con.prepareStatement(inssql)) {
				stmt.setString(1, blog.getTitle());
				stmt.setString(2, blog.getContents());
				java.sql.Date b = new java.sql.Date(blog.getCreated_time().getTime());
				stmt.setDate(3, b);
				stmt.setInt(4, blog.getUserid());
				stmt.setInt(5, blog.getCategoryid());
				cnt = stmt.executeUpdate();
			}
			con.commit();
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		}
		return cnt;
	}

	public List<Blog> findThoseBlogs(String enteredValue) throws SQLException {
		List<Blog> tfb = null;
		System.out.println("error checking!!! - point b1");
		String sq = "SELECT * FROM blogs WHERE UPPER(title) = ?";
		try (PreparedStatement stmt = con.prepareStatement(sq)) {
			stmt.setString(1, enteredValue.trim().toUpperCase());
			tfb = new ArrayList<Blog>();
			System.out.println("error checking!!! - point b2");
			try (ResultSet rs = stmt.executeQuery()) {
				System.out.println("error checking!!! - point b3");
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String contents = rs.getString("contents");
					java.util.Date newDate = new Date(rs.getDate("created_time").getTime());
					int uid = rs.getInt("user_id");
					int catid = rs.getInt("category_id");
					Blog b = new Blog(id, title, contents, newDate, uid, catid);
					tfb.add(b);
				}
				System.out.println("error checking!!! - point b4");
			}
		}
		return tfb;
	}

//	public int save(User user) throws Exception {
//		int cnt = -1;
//		try {
//			con.setAutoCommit(false);
//			String inssql = "INSERT INTO user(full_name, email, password, phone_no, created_time) VALUES(?,?,?,?,?)";
//			try (PreparedStatement stmt = con.prepareStatement(inssql)) {
//				stmt.setString(1, user.getFname());
//				stmt.setString(2, user.getEmail());
//				stmt.setString(3, user.getPassword());
//				stmt.setString(4, user.getPhone());
//				java.sql.Date b = new java.sql.Date(user.getCdate().getTime());
//				stmt.setDate(5, b);
//				cnt = stmt.executeUpdate();
//			}
//			con.commit();
//			con.setAutoCommit(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			con.rollback();
//			throw e;
//		}
//		return cnt;
//	}

}

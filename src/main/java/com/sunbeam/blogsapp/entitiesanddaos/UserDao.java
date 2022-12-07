package com.sunbeam.blogsapp.entitiesanddaos;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserDao implements Closeable {

	private Connection con;

	public UserDao() throws SQLException {
		con = DbUtil.getCOnnection();
	}

	public List<User> findAllUsers() throws Exception {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM user";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("full_name");
					String email = rs.getString("email");
					String passwd = rs.getString("password");
					String phone = rs.getString("phone_no");
					java.util.Date newDate = new Date(rs.getDate("created_time").getTime());
					User u = new User(id, fname, email, passwd, phone, newDate);
					userList.add(u);
				}
			}
		}
		return userList;
	}

	public User findByEmail(String email) throws SQLException {
		User u = null;
		String sql = "SELECT u.id, u.full_name, u.email, u.password, u.phone_no, u.created_time FROM user u WHERE u.email = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("full_name");
					String emailid = rs.getString("email");
					String passwd = rs.getString("password");
					String phone = rs.getString("phone_no");
					java.util.Date newDate = new Date(rs.getDate("created_time").getTime());
					u = new User(id, fname, emailid, passwd, phone, newDate);
					return u;
				}
			}
		}
		return null;
	}

	public int save(User user) throws Exception {
		int cnt = -1;
		try {
			con.setAutoCommit(false);
			String inssql = "INSERT INTO user(full_name, email, password, phone_no, created_time) VALUES(?,?,?,?,?)";
			try (PreparedStatement stmt = con.prepareStatement(inssql)) {
				stmt.setString(1, user.getFname());
				stmt.setString(2, user.getEmail());
				stmt.setString(3, user.getPassword());
				stmt.setString(4, user.getPhone());
				java.sql.Date b = new java.sql.Date(user.getCdate().getTime());
				stmt.setDate(5, b);
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

//	public int save(Customer cust) throws Exception {
//		int cnt = -1;
//		try {
//			con.setAutoCommit(false);
//			String custSql = "INSERT INTO customers(name,password,mobile,address,email,birth,enabled) VALUES(?,?,?,?,?,?,?)";
//			try(PreparedStatement stmt=con.prepareStatement(custSql)) {
//				stmt.setString(1, cust.getName());
//				stmt.setString(2, cust.getPassword());
//				stmt.setString(3, cust.getMobile());
//				stmt.setString(4, cust.getAddress());
//				stmt.setString(5, cust.getEmail());
//				java.sql.Date birth = new Date(cust.getBirth().getTime());
//				stmt.setDate(6, birth);
//				stmt.setInt(7, cust.getEnabled());
//				cnt = stmt.executeUpdate();
//			}
//			String roleSql = "INSERT INTO roles(email,role) VALUES(?,?)";
//			try(PreparedStatement stmt=con.prepareStatement(roleSql)){
//				stmt.setString(1, cust.getEmail());
//				stmt.setString(2, cust.getRole());
//				stmt.executeUpdate();
//			}
//			con.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			con.rollback();
//			throw e; // re-throw exception
//		}
//		return cnt;
//	}

	@Override
	public void close() throws IOException {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

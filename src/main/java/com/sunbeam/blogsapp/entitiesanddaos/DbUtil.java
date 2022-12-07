package com.sunbeam.blogsapp.entitiesanddaos;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/blogsdb";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Amexbl618753ack";

	static {
		try {
			System.out.println("DBUtil Class");
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Connection getCOnnection() throws SQLException {
		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		return con;
	}

}

package com.isaac.sisgreja.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		try {
			Connection con =  DriverManager.getConnection("jdbc:mysql://localhost/sisgreja2.0?useTimezone=true&serverTimezone=UTC", "root", "123456");
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}

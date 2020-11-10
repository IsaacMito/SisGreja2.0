package com.isaac.sisgreja.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/SisGreja?useTimezone=true&serverTimezone=UTC", "root", "123456");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

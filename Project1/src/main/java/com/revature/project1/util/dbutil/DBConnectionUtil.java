package com.revature.project1.util.dbutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionUtil {
	private static Connection connection;

	public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {

		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		properties.load(loader.getResourceAsStream("connection.properties"));

		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		if (connection == null || connection.isClosed()) {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;
	}

}

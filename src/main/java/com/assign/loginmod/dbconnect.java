package com.assign.loginmod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dbconnect {

public static Connection getConnection(Properties appprop) {

		String driver=appprop.getProperty("driver");
		String url=appprop.getProperty("url");
		String user=appprop.getProperty("user");
		String password=appprop.getProperty("password");

		Connection conn=null;

		try {

			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,password);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		catch (SQLException e) {

			e.printStackTrace();

		}

		return conn;
	}

}

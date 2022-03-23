package com.uiafw.cn.pn.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.uiafw.cn.pn.helper.logger.LoggerHelper;

public class DatabaseHelper {
	private static Logger log = LoggerHelper.getLogger(DatabaseHelper.class);
	private static String url = "jdbc:mysql://localhost/demo";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String username = "root";
	private static String password = "root";
	private static Connection connection;
	private static DatabaseHelper instance = null;
	
	public DatabaseHelper() {
		connection = getSingleInstanceConnection();
	}
	
	public static DatabaseHelper getInstance() {
		if(instance==null) {
			instance = new DatabaseHelper();
		}
		return instance;
	}
	
	private Connection getSingleInstanceConnection() {
		try {
			Class.forName(driverName);
			try {
				connection = DriverManager.getConnection(url,username,password);
				if(connection!=null) {
					log.info("connected to database");
				}
			} catch (SQLException e) {
				log.error("Failed to create Database connection "+e);
			}
		} catch (ClassNotFoundException e) {
			log.error("Driver not found"+e);
		}
		return connection;
	}
	
	public Connection getConnection() {
		return connection;  
	}
	
	public static ResultSet getResultSet(String dbQuery) {
		instance = DatabaseHelper.getInstance();
		connection = instance.getConnection();
		log.info("Executing query "+ dbQuery);
		Statement statement;
		try {
			statement = connection.createStatement();
			return statement.executeQuery(dbQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

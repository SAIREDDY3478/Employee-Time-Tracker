package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Users;

public class DAO {
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/employee?allowPublicKeyRetrieval=true&useSSL=false";
	private static final String jdbcUsername = "root";
	private static final String jdbcPassword = "Saireddy@123";
	
	
	private static final String VALIDATE_USER_SQL = "SELECT * from users where username = ? and password = ?";
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		} catch ( SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return connection;
	}
	
	
	public static void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public Users validate(String Username , String Password) {
		Users user = null;
		String username_db="";
		String password_db="";
		String role_db="";
		
		Connection connection = null;
		PreparedStatement validateStmt = null;
		ResultSet rs = null;
		try{
			connection = getConnection();
			validateStmt = connection.prepareStatement(VALIDATE_USER_SQL);
//			String username = login.getUsername();
//			String password = login.getPassword();

			validateStmt.setString(1, Username);
			validateStmt.setString(2, Password);
			
			rs = validateStmt.executeQuery();
			if(rs.next()) {
				username_db = rs.getString("username");
				password_db = rs.getString("password");
				role_db = rs.getString("role");
				user = new Users();
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setRole(rs.getString("role"));
			}
			
			if(Username.equals(username_db) && Password.equals(password_db)  && role_db.equals("Admin")) {
				return user;
			}
			else if(Username.equals(username_db) && Password.equals(password_db)  && role_db.equals("Associate")) {
				return user;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(connection,validateStmt,rs);
		}
		
		return null;
	}

}

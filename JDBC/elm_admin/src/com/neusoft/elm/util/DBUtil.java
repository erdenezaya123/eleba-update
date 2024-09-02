package com.neusoft.elm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost/elm_admin?characterEncoding=utf-8";//127.0.0.1:3306
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME ="root";
	private static final String PASSWORD ="7838053";
	//获取Connection
	public static Connection getConnection() {
		Connection con=null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//关闭资源
	public static void close(ResultSet rs,PreparedStatement pst,Connection con) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(pst!=null){
			try {
				pst.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
	    	pst = null;
		}
		if(con!=null) {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}
}

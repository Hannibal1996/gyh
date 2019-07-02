package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static Connection conn = null;
	static{
		try {
			conn=DriverManager.getConnection(
"jdbc:mysql://127.0.0.1:3306/hggnb?characterEncoding=utf8&useSSL=false& serverTimezone=UTC",
"root", "root");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return conn;
	}
	
	
	
	
	
	
	
}

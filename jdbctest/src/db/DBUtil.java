package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String URL="jdbc:mysql://localhost:3306/gyh?characterEncoding=utf8&useSSL=false& serverTimezone=UTC";
	private static final String USER="root";
	private static final String PASSWORD="root";
	
	private static Connection conn=null;
	
	
	static{
		try {
			//加载驱动程序
					Class.forName("com.mysql.cj.jdbc.Driver");
			//获得数据库连接	
				     conn=DriverManager.getConnection(URL,USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
	}
	
	public static Connection getConnection(){
		return conn;
	}
	
}	
	
	
	
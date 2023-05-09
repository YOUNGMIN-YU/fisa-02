package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyUtil {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:mysql://localhost/projecta?characterEncoding=UTF-8&serverTimezone=UTC";
		String id = "root";
		String pw = "root";
		
		return DriverManager.getConnection(url, id, pw);
	}
	
	public static char[] strMapping(String message) {
		char[] ca = new char[message.length()];
		for(int i = 0; i < message.length(); i++) {
			ca[i] = message.charAt(i);
		}
		return ca;
	}
	
	public static String strMapping(char[] message) {
		String str = "";
		for (char c : message) {
			str += c;
		}
		return str;
	}
	public static String strInteger(Integer o) {
		if (o == null) {
			return "null";
		}
		return o.toString();
	}
	public static ResultSet executeQuery(PreparedStatement pstmt) throws SQLException {
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return rs;
	}
}

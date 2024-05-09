package jdbc_day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCTest3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = DBConnection.getConnection();
		
		System.out.print("검색하실 strdata : ");
		String strdata = sc.nextLine();//JDBC Test2
		
		//select * from test where strdata='JDBC Test2';
		String sql = "select * from test where strdata = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, strdata);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String timedata = rs.getString("timedata");
				
				System.out.println(num+" / "+strdata+" / "+timedata);
			}
		} catch (SQLException e) {
		}
	}
}














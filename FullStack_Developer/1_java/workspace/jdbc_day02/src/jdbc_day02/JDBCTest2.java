package jdbc_day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest2 {
	public static void main(String[] args) {
		Connection conn = DBConnection.getConnection();
		String sql = "select * from test";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//insert, update, delete: executeUpdate()
			//select				: executeQuery()
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String strdata = rs.getString("strdata");
				String timedata = rs.getString("timedata");
				
				System.out.println(num+" / "+strdata+" / "+timedata);
			}
		} catch (SQLException e) {
		}
	}
}











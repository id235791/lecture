package jdbc_day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest1 {
	public static void main(String[] args) {
		Connection conn = DBConnection.getConnection();
		String data1 = "JDBC Test2";
		String sql = "insert into test (strdata, timedata) values('"+data1+"',now())";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int result = ps.executeUpdate();
			
			if(result == 1) {
				System.out.println("성공");
			}
			else {
				System.out.println("실패");
			}
		} catch (SQLException e) {
			System.out.println("쿼리문 수행 실패");
		}
	}
}

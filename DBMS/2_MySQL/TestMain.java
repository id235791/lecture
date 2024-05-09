package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TestMain {
	public static void main(String[] args) {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO test (strdata,timedata) VALUES(?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "apple");
			ps.setString(2, "1993-12-07 00:15:12");
			
			int result = ps.executeUpdate();
			System.out.println(result == 1);
			
			sql = "SELECT * FROM test";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getInt(1)+" / ");
				System.out.print(rs.getString(2)+" / ");
				System.out.println(rs.getDate(3));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}

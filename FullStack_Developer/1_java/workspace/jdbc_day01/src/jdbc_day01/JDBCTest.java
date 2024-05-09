package jdbc_day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTest {
	public static void main(String[] args) {
		try {
//			설계도 불러오기
			Class.forName("com.mysql.cj.jdbc.Driver");
			
//			다리를 짓기위한 정보들
//			String url = "jdbc:mysql://127.0.0.1:3306/gb";
			//localhost : 127.0.0.1
			String url = "jdbc:mysql://localhost:3306/gb";
			String user = "root";
			String password = "1234";
			
//											다리좀 지어줘
			Connection conn = DriverManager.getConnection(url,user,password);
			
//			삽입할 데이터
			String data = "JDBC data1";
//			그 데이터를 삽입하기 위한 명령문(궁극적으로 우리가 JDBC를 통해 전달해야 하는 것)
			String sql = "insert into test (strdata) values('"+data+"')";
			
//			택배차 준비
			PreparedStatement ps = conn.prepareStatement(sql);
//			택배차 출발
			int result = ps.executeUpdate();
			
			if(result == 1) {
				System.out.println("당장 워크벤치로 가서 확인해보자!");
			}
			else {
				System.out.println("나는 오타쟁이입니다...");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("나는 오타쟁이입니다... : "+e);
		} catch (SQLException sqle) {
			System.out.println("나는 오타쟁이입니다... : "+sqle);
		}
	}
}






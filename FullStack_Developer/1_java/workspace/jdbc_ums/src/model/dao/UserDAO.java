package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.dto.UserDTO;

public class UserDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public UserDAO() {
		conn = DBConnection.getConnection();
	}
	public boolean insertUser(UserDTO user) {
		String sql = "insert into user (userid,userpw,username,userage,userphone,useraddr) "
				+ "values(?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getUserpw());
			ps.setString(3, user.getUsername());
			ps.setInt(4, user.getUserage());
			ps.setString(5, user.getUserphone());
			ps.setString(6, user.getUseraddr());
			
			//데이터베이스에 삽입
			int result = ps.executeUpdate();
			return result == 1;
		} catch (SQLException e) {
		}
		return false;
	}

	public UserDTO findUserById(String userid) {
		//데이터 로직은 순수하게 데이터에 관련된 로직만 구성되어 있다.
		//뭔진 몰라도 넘겨진 userid로 User객체 찾아서 돌려주기만 하면 끝
		//API 이용해서 데이터베이스에 접근 후 객체로 리턴
		
		//검색
		String sql = "select * from user where userid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				UserDTO user = new UserDTO();
				user.setUserid(rs.getString("userid"));
				user.setUserpw(rs.getString("userpw"));
				user.setUsername(rs.getString("username"));
				user.setUserage(rs.getInt("userage"));
				user.setUserphone(rs.getString("userphone"));
				user.setUseraddr(rs.getString("useraddr"));
				
				return user;
			}
		} catch (SQLException e) {
		}
		return null;
	}
	public boolean updateUser(String userid, int choice, String newData) {
//		//choice : 1(비밀번호 수정 - 1열), 2(핸드폰번호 수정 - 4열), 3(주소 수정 - 5열)
		String[] cols = {"","userpw","userphone","useraddr"};
		String sql = "update user set "+cols[choice]+" = ? where userid = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setString(2, userid);
			
			int result = ps.executeUpdate();
			
			return result == 1;
		} catch (SQLException e) {
		}
		
		return false;
	}
	public void deleteUser(String userid) {
		String sql = "delete from user where userid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			
			ps.executeUpdate();
		} catch (SQLException e) {
		}
	}
}









package model.dao;

import java.util.ArrayList;

import model.DBConnection;
import model.dto.UserDTO;

public class UserDAO {
	DBConnection conn;
	public UserDAO() {
		conn = new DBConnection("UserTable.txt");
	}
	public boolean insertUser(UserDTO user) {
		String data = String.format("%s\t%s\t%s\t%d\t%s\t%s",
				user.getUserid(),user.getUserpw(),user.getUsername(),user.getUserage(),
				user.getUserphone(),user.getUseraddr());
		//데이터베이스에 삽입
		return conn.insert(data);
	}

	public UserDTO findUserById(String userid) {
		//데이터 로직은 순수하게 데이터에 관련된 로직만 구성되어 있다.
		//뭔진 몰라도 넘겨진 userid로 User객체 찾아서 돌려주기만 하면 끝
		//API 이용해서 데이터베이스에 접근 후 객체로 리턴
		
		//검색
		ArrayList<String[]> result = conn.select(0, userid);
		//결과가 있다면
		if(result.size() > 0) {
			//있는 결과로 UserDTO 객체 만들어서 리턴
			return new UserDTO(result.get(0));
		}
		//결과가 없다면 null 리턴
		return null;
	}
	public boolean updateUser(String userid, int choice, String newData) {
		//choice : 1(비밀번호 수정 - 1열), 2(핸드폰번호 수정 - 4열), 3(주소 수정 - 5열)
		if(choice == 1) {
			return conn.update(userid, choice, newData);
		}
		else {
			return conn.update(userid, choice+2, newData);
		}
	}
	public void deleteUser(String userid) {
		conn.delete(userid);
	}
}









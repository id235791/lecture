package model.dto;

public class UserDTO {
//	Alt + Shift + A : 그리드 모드로 전환 / 해제
	private String userid;
	private String userpw;
	private String username;
	private int userage;
	private String userphone;
	private String useraddr;
	
	public UserDTO() {}

	public UserDTO(String userid, String userpw, String username, int userage, String userphone, String useraddr) {
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.userage = userage;
		this.userphone = userphone;
		this.useraddr = useraddr;
	}
	
	public UserDTO(String[] datas) {
		this.userid = datas[0];
		this.userpw = datas[1];
		this.username = datas[2];
		this.userage = Integer.parseInt(datas[3]);
		this.userphone = datas[4];
		this.useraddr = datas[5];
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserage() {
		return userage;
	}

	public void setUserage(int userage) {
		this.userage = userage;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUseraddr() {
		return useraddr;
	}

	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}
}












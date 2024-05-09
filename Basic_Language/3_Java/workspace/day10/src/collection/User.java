package collection;

public class User {
	String userid;
	String userpw;
	String username;
	
	public User(String userid, String userpw, String username) {
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
	}
	
	@Override
	public String toString() {
		return userid+"("+username+")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null) {
			if(obj instanceof User) {
				User target = (User)obj;
				
				if(this.userid.equals(target.userid)) {
					if(this.userpw.equals(target.userpw)) {
						if(this.username.equals(target.username)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return userid.charAt(0);
	}
}









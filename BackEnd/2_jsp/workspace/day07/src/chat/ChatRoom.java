package chat;

import java.util.Set;

import javax.websocket.Session;

public class ChatRoom {
	private String pk;
	private String name;
	private Set<String> members;
	
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getMembers() {
		return members;
	}
	public void setMembers(Set<String> members) {
		this.members = members;
	}
}

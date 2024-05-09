package collection;

import java.util.ArrayList;

public class ArListTest3 {
	public static void main(String[] args) {
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new User("apple", "1234", "김사과"));
		userList.add(new User("banana", "1234", "반하나"));
		userList.add(new User("cherry", "1234", "이체리"));
		
		System.out.println(userList);
		userList.remove(new User("banana", "1234", "반하나"));
		System.out.println(userList);
	}
}

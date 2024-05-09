package collection;

import java.util.HashMap;

public class HMapTest2 {
	public static void main(String[] args) {
		HashMap<User, Integer> users = new HashMap<User, Integer>();
		users.put(new User("apple","1234","김사과"), 10000);
		users.put(new User("banana","1234","반하나"), 20000);
		users.put(new User("cherry","1234","이체리"), 30000);

//		HashSet, HashMap은 hashCode()를 이용해서 두 객체가 동일한지를 비교
		users.put(new User("banana","1234","반하나"), 15000);
		System.out.println(users);
		users.remove(new User("banana","1234","반하나"));
		System.out.println(users);
		
	}
}

package collection;

import java.util.HashSet;
import java.util.Iterator;

public class HSetTest2 {
	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(10);
		set.add(20);
		set.add(30);
		set.add(40);
		set.add(50);
		
		System.out.println(set);
		
		Iterator<Integer> iter = set.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println("=======================");
		//iterator는 한 번 조회를 한 요소는 꺼내올 수 없다.
		//다시 객체화를 진행해서 순서를 부여하고 처음부터 다시 순회를 해야 한다.
		iter = set.iterator();
		System.out.println(iter.next());
	}
}












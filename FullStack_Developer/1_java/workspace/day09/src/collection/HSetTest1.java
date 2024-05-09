package collection;

import java.util.HashSet;

public class HSetTest1 {
	public static void main(String[] args) {
		HashSet<Integer> hSet = new HashSet<Integer>();
		
//		HashSet에 요소 추가하기
		hSet.add(10);
		hSet.add(20);
		hSet.add(30);
		hSet.add(40);
		hSet.add(50);
		hSet.add(50);
		hSet.add(50);
		hSet.add(50);
		hSet.add(50);
		hSet.add(50);
		
//		HashSet 간단하게 구조 파악하기
		System.out.println(hSet);
		
//							HashSet의 요소 개수
		for (int i = 0; i < hSet.size(); i++) {
//			System.out.println(hSet.get(i));
		}
		
//		HashSet의 요소 포함 여부 확인하기
		System.out.println(hSet.contains(70));
		
//		HashSet의 요소 삭제하기
		hSet.remove(20);
		System.out.println(hSet);
	}
}







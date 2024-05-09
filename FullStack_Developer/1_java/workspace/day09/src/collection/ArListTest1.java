package collection;

import java.util.ArrayList;
/**
 * @author 정다솔
 */
public class ArListTest1 {
	public static void main(String[] args) {
		ArrayList<String> arData1 = new ArrayList<String>();
		
//		ArrayList에 요소 추가하기
		arData1.add("Hello");
		arData1.add("Collection");
		arData1.add("So");
		arData1.add("EZ");
		
//		ArrayList의 구조 간단하게 파악하기
		System.out.println(arData1);
		
//							ArrayList의 요소 개수
		for (int i = 0; i < arData1.size(); i++) {
//											ArrayList에서 요소 가져오기
			System.out.println(i+" 번째 방에는 "+arData1.get(i));
		}
		
//		ArrayList의 요소 수정하기
		arData1.set(3, "Hard");
		System.out.println(arData1);
		
//		ArrayList의 요소 삭제하기
//		arData1.remove("Hard");
		arData1.remove(3);
		System.out.println(arData1);
		
		ArrayList<Integer> arData2 = new ArrayList<Integer>();
		arData2.add(10);
		arData2.add(20);
		arData2.add(30);
		arData2.add(40);
		arData2.add(50);
		
		int data = 30;
		
		System.out.println(arData2);
		arData2.remove((Integer)data);
		System.out.println(arData2);
	}
}












package collection;

import java.util.ArrayList;

public class ArListTest2 {
	public static void main(String[] args) {
		ArrayList<   ArrayList<  Integer  >   > list = new ArrayList<ArrayList<Integer>>();
		//각 데이터들이 담길 내부 리스트들(행의 역할, 소배열 역할)을 생성 후 추가
		list.add(new ArrayList<Integer>());
		list.add(new ArrayList<Integer>());
		
		//내부 리스트들을 가져와서 그 리스트에 데이터 추가
		list.get(0).add(10);
		list.get(0).add(20);
		list.get(0).add(30);
		
		list.get(1).add(40);
		list.get(1).add(50);
		list.get(1).add(60);
		list.get(1).add(70);
		
		//list의 요소 개수(소배열의 개수, 행의 개수)
		int len1 = list.size();
		
		for (int i = 0; i < len1; i++) {
			//내부 리스트의 요소 개수(각 행의 요소 개수, 열의 개수)
			int len2 = list.get(i).size();
			for (int j = 0; j < len2; j++) {
				//list[i][j]
				System.out.println(list.get(i).get(j));
			}
		}
	}
}









package day03;

public class ArTest2 {
	public static void main(String[] args) {
		int data1 = 10;
		int data2 = data1;
		data2 += 5;
		System.out.println(data1);
		
		int[] arData1 = {10,20,30};
		int[] arData2 = arData1;
		arData2[1] = 2000;
		System.out.println(arData1[1]);
	}
}

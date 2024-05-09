package day02;

public class ForTest {
	public static void main(String[] args) {
		//1. 정다솔
		//2. 정다솔
		//3. 정다솔
		//...
		//10. 정다솔
//		int cnt = 0;
//		while(cnt<10) {
//			System.out.println((cnt+1)+". 정다솔");
//			cnt++;
//		}
		
		for(int cnt = 0;cnt < 10;cnt++) {
			System.out.println((cnt+1)+". 정다솔");
		}
		
		//반복횟수 = (끝값-초기값)/증감값
		//10 = (x-4)/-4
		//-40 = x-4
		//-36
		
		//10번 반복하는 반복문
		for(int i=4;i>-36;i-=4) {
			
		}
		
		//100부터 -3씩 하면서 13번 반복
		for(int i=100;i>61;i -= 3) {
			
		}
		
//		for문도 결국 조건식이 참일동안 반복이다.
//		초기식, 조건식, 증감식은 다 생략이 가능하고
//		조건식을 생략할 시 무한반복
//		for(;true;) {
//			
//		}
		
//		2 4 6 8 10 12 14 16 18 20
		for (int i = 2; i < 22; i+=2) {
			System.out.println(i);
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println(2*i+2);
		}
//		i		표현할 값
//		0		2
//		1		4
//		2		6
//		...		...
//		9		20
//		(i+1)*2 = 표현할값
		
//		7 4 1 -2 -5 -8 -11 -14 -17 -20
		for (int i = 0; i < 10; i++) {
			System.out.println(i*-3+7);
		}
		
//		2 3 / 4 6 / 6 9 / 8 12 / 10 15
		for (int i = 0; i < 5; i++) {
			System.out.println(i*2+2);
			System.out.println(i*3+3);
		}
		
	}
}














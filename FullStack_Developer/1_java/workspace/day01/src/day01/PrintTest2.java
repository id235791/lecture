package day01;

public class PrintTest2 {
	//Ctrl + Spacebar : 자동 완성
	//main 작성 + 자동완성 : main 메소드 만들기
	public static void main(String[] args) {
		//Ctrl + Alt + ↓(↑) : 아래(위)로 선택한 줄 복사
		//Alt + ↓(↑) : 아래(위)로 선택한 줄 이동
		System.out.print(10);
		System.out.print('\n');
		System.out.print(20);
		System.out.print('\n');
		System.out.print("Hello");
		System.out.print('\n');
		//"정다솔"
		System.out.print("\"정다솔\"");
		System.out.print('\n');
		// \"
		System.out.print("\\\"");
		System.out.print('\n');
		//syso + 자동완성 : println() 메소드 작성
		System.out.println("줄바꿈이 되는 println()");
		
		System.out.println(5+3);
		System.out.println("파이 : "+3.14);
		
		//1 + 1 = 2
		System.out.println(1+" + "+1+" = "+2);
		System.out.printf("%d + %d = %d",1,1,2);
	}
}
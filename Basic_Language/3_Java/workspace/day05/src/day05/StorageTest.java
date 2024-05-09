package day05;

public class StorageTest {
//	전역변수는 지역변수와 서로 다른 메모리 영역에 할당되기 때문에 같은 이름으로 선언이 가능하다.
//	파란색
	int num;
	
	int gnum;
//	살짝 기울어짐(이탤릭체)
	static int snum;

//	Ctrl + Shift + F : 자동 정렬
	public static void main(String[] args) {
		StorageTest s = new StorageTest();
		s.f1();
		s.f2(100);
//		정적 메소드(static이 붙은 메소드)에서는 일반 전역변수를 사용할 수 없다.		
//		System.out.println(gnum);
		System.out.println(snum);
	}

	void f1() {
		// 똥색
		// 지역변수는 직접 초기화 하지 않으면 사용할 수 없다.
		int num;
//		System.out.println(num);
//		지역변수는 해당 지역에서만 접근 가능
//		System.out.println(data);
		
//		같은 이름의 변수가 지역, 전역 으로 선언되어 있다면 사용 시 지역변수가 우선시된다.
		num = 10;
		System.out.println("전역변수 gnum : "+gnum);
		System.out.println("정적변수 gnum : "+snum);
		gnum = 10000;
	}

	// 매개변수도 지역변수의 일종이다.
	void f2(int data) {
		// data = ???;
		// 매개변수는 호출시에 초기화가 되기 때문에(호출시 값을 넘겨주기 때문에) 사용이 가능하다.
		System.out.println("매개변수 data : "+data);
		
//		지역변수 num이 없기 때문에 그 다음으로 가까이에 있는 전역변수 num을 찾아 사용한다.
//		num = 10;
		System.out.println("전역변수 gnum : "+gnum);
		System.out.println("정적변수 gnum : "+snum);
	}
}




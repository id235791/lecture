package road;

public class Car {
	String model;
	String color;
	int price;
	static int wheel = 4;
	
	//클래스명 + 자동완성 : 기본생성자
//	보통 여러가지 이유로, 사라진 기본 생성자를 오버로딩으로 직접 선언해준다.
	public Car() { }
	
//	Alt + Shift + S > O : 필드(변수)를 이용해서 생성자 만들기
	public Car(String model, String color, int price) {
		this.model = model;
		this.color = color;
		this.price = price;
		
	}
	
//	생성자도 메소드와 비슷한 성질을 띄고 있기 때문에 오버로딩이 가능하다.
	public Car(int price) {
		this.price = price;
	}
	
//	오버로딩은 매개변수의 자료형으로 구별 / 자료형의 순서나 개수가 다르다면 선언 가능 
	public Car(String brand, int value,String color2) { }

	void engineStart() {
		System.out.println(model+" 열쇠로 시동 켜기");
	}
	void engineStop() {
		System.out.println(model+" 열쇠로 시동 끄기");
	}
}





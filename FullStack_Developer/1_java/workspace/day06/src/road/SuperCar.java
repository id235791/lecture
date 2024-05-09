package road;

public class SuperCar extends Car{
	String mode;
	
	public SuperCar() {
		//super : 상속받고 있는 부모 클래스의 이름
		//Car()
		super();
//		System.out.println();
	}

	//Alt + Shift + S > C : 부모 생성자를 받아와서 생성자 만들기
	public SuperCar(String model, String color, int price) {
		super(model, color, price);
		// TODO Auto-generated constructor stub
	}
	
	//Alt + Shift + S > O : 부모 생성자 선택 후 필드를 이용해서 생성자 만들기
	public SuperCar(String model, String color, int price, String mode) {
		super(model, color, price);
		this.mode = mode;
	}
	
	void engineStart() {
		System.out.println(model + " 지문으로 시동 켜기");
	}
	
	//@ : 어노테이션
	@Override
	void engineStop() {
		System.out.println(model + " 지문으로 시동 끄기");
	}
	
	void roofOpen() {
		System.out.println(model + " 뚜껑 열기");
	}

	void roofClose() {
		System.out.println(model + " 뚜껑 닫기");
	}
	
}





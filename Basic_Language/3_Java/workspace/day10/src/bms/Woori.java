package bms;

//잔액 볼 때마다 잔액 절반
public class Woori extends Bank {

	public Woori(String account, String pw, String name) {
		super(account, pw, name);
	}
	
	@Override
	void show() {
		balance /= 2;
		super.show();
	}	
	
}

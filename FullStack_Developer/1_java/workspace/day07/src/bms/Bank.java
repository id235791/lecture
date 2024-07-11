package bms;

public class Bank {
	String account;
	String pw;
	String name;
	int balance;
	
	public Bank(String account, String pw, String name) {
		this.account = account;
		this.pw = pw;
		this.name = name;
		this.balance = 0;
	}
	
	void deposit(int money) {
		balance += money;
	}
	
	boolean withdraw(int money) {
		if(balance >= money) {
			balance -= money;
			return true;
		}
		return false;
	}
	
	void show() {
		//김사과님 계좌(11111)
		//잔액 : 1000원
		System.out.printf("%s님 계좌(%s)\n잔액 : %d원\n",name,account,balance);
	}
}





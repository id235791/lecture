package thread;

public class ThreadTest {
	public static void main(String[] args) {
		MyThread t1 = new MyThread("☆");
		MyThread t2 = new MyThread("★");
		
		MyRunnable r1 = new MyRunnable("♡");
		MyRunnable r2 = new MyRunnable("♥");
		Thread t3 = new Thread(r1);
		Thread t4 = new Thread(r2);
		
		System.out.println("멀티 쓰레드 운영 시작!");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
//		join() 메소드는 다른 쓰레드들을 본인이 완료될 때까지 잠시 멈추는 역할을 한다.
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {}
		
		System.out.println("프로그램 끝!");
		
		
	}
}

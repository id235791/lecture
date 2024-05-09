package thread;

public class MyThread extends Thread{
	String data;

	public MyThread(String data) {
		this.data = data;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(data);
			try { sleep(1000); } catch (InterruptedException e) {}
		}
	}
}

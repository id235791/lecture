package zoo;

public class Ocapi extends Animal implements Runnable,Mammal{
	@Override
	void makeSomeNoise() {
		for (int i = 0; i < 10; i++) {
			System.out.println("히이잉ㅇ히ㅣ이잉~");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	@Override
	public void run() {
		makeSomeNoise();
	}
}

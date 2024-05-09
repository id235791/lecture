package generic;

public class GClassTest<RIIZE> {
	RIIZE data;
	
	public GClassTest(RIIZE data) {
		this.data = data;
	}
	
	void f(RIIZE data) {
		System.out.println(data);
	}
}

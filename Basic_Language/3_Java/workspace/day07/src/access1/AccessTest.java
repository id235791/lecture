package access1;

public class AccessTest {
	int data1 = 10;
	public int data2 = 20;
	private int data3 = 30;
	protected int data4 = 40;
	
	//Alt + Shift + S > R : getter, setter 만들기
	public int getData3() {
		return data3;
	}
	public void setData3(int data3) {
		this.data3 = data3;
	}
}

package factory;

public class Emart {
	public static void main(String[] args) {
		MatdongSan matdongSan = new MatdongSan();
		BbaSae bbaSae = new BbaSae();
		
		matdongSan.포장();
		bbaSae.포장();
		
		System.out.println(Cookie.질소);
	}
}

package cafe;

public class Starbucks {
	void openCafe(Cafe c) {
		System.out.println("스타벅스 오픈!");
		System.out.println("원두 그라인딩!");
		c.drip();
		c.sell();
		c.clean();
		System.out.println("스타벅스 마감!");
	}
}

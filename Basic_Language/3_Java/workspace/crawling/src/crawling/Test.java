package crawling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		String url = "https://www.coupang.com";
		WebDriver driver = new ChromeDriver();
		driver.get(url);
	}
}

package crawling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CGV {
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		String url = "http://www.cgv.co.kr/movies/?lt=1&ft=0";
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		
		driver.findElement(By.className("btn-more-fontbold")).click();
		Thread.sleep(500);
		
		List<WebElement> titleList = driver.findElements(By.className("title"));
		List<WebElement> scoreList = driver.findElements(By.className("score"));
		List<WebElement> txt_info_List = driver.findElements(By.className("txt-info"));
		
		for (int i = 0; i < titleList.size(); i++) {
			List<WebElement> percents = scoreList.get(i).findElements(By.className("percent"));
			String release_date = txt_info_List.get(i).getText();
			System.out.println("========================================");
			//웡카(
			System.out.print(titleList.get(i).getText()+"(");
			//2024.01.31)
			System.out.println(release_date.substring(0, 10)+")");
			System.out.println(percents.get(0).getText()+" / 에그스코어 : "+percents.get(1).getText());
		}
	
		driver.close();
		driver.quit();
		
	}
}













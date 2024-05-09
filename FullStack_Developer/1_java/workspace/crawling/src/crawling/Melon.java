package crawling;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Melon {
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		String url = "https://www.melon.com";
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어 : ");
		String keyword = sc.nextLine();
		
		WebElement top_search = driver.findElement(By.id("top_search"));
		top_search.click();
		top_search.sendKeys(keyword);
		top_search.sendKeys(Keys.ENTER);
		
		try {
			WebElement frm_searchArtist = driver.findElement(By.id("frm_searchArtist"));
			
			List<WebElement> numList = frm_searchArtist.findElements(By.className("no"));
			List<WebElement> titleList = frm_searchArtist.findElements(By.className("fc_gray"));
			List<WebElement> artistList = frm_searchArtist.findElements(By.id("artistName"));
			
			for (int i = 0; i < numList.size(); i++) {
				System.out.printf("%s. %s(아티스트 : %s)\n",
						numList.get(i).getText(),
						titleList.get(i).getText(),
						artistList.get(i).getText()
				);
			}
			System.out.print("가사를 볼 노래 번호 : ");
			int num = sc.nextInt();
			
			frm_searchArtist.findElements(By.className("btn_icon_detail")).get(num-1).click();
			Thread.sleep(500);
			
			try {
				try {
					driver.findElement(By.className("adult_register"));
					
					System.out.println("성인 노래 가사는 열람하실 수 없습니다.");
				} catch (NoSuchElementException nsee) {
					driver.findElement(By.className("button_more")).click();
					WebElement lyric = driver.findElement(By.className("lyric"));
					
					System.out.println(lyric.getText());
				}
				
				
			} catch (NoSuchElementException nsee) {
				System.out.println("가사가 없는 곡입니다.");
			}
		} catch (NoSuchElementException nsee) {
			System.out.println("검색 결과가 없습니다.");
		}
	}
}













package rahulShettyacademy_ActualProject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class RND_Class {

	public static void main(String[] args) {
		String expectedText = "";
		String ProductName = "ZARA COAT 3";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("johndeepak444@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Deepak@95");
		driver.findElement(By.id("login")).submit();
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']")).click();

//		List<WebElement> elements = driver.findElements(By.xpath("//tr//td[2]"));
//
//		for (int i = 0; i < elements.size(); i++) {
//			String text = elements.get(i).getText();
//			if (text.equals(ProductName)) {
//
//				text = expectedText;
//				System.out.println(expectedText);
//				break;
//			}
//
//		}
		
		List<WebElement> elements = driver.findElements(By.xpath("//tr//td[2]"));
		for (int i = 0; i < elements.size(); i++) {
			String text = elements.get(i).getText();
			if (text.equalsIgnoreCase("ZARA COAT 3")) {
				
				System.out.println(text);
				break;
			}
			
		}
		
	}

}

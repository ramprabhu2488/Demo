package rahulShettyacademy_ActualProject;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneRahulShettyProject {

	public static void main(String[] args) {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("johndeepak444@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Deepak@95");
		driver.findElement(By.id("login")).click();

		// we get the products by using loop. And Limiting the scope of search. And
		// click the required product figured out.

		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));

		// for (int i = 0; i < products.size(); i++) {
		// WebElement size = products.get(i);
		// String text2 = size.findElement(By.cssSelector("b")).getText();
		// if (text2.contains("ORIGINAL")) {
		// size.findElement(By.cssSelector("button.btn.w-40.rounded")).click();
		// break;
		// }
		// }
		// System.out.println("over");

		// By Using Stream
		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector("button.btn.w-10.rounded")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		// ABOVE and BELOW does the same work, but the below one improves the speed.
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		// Css selector ClassName -> Parent to child traverse
		List<WebElement> cardProducts = driver.findElements(By.cssSelector(".cartWrap.ng-star-inserted h3"));
		// anyMatch will return True or False
		boolean anyMatch = cardProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(anyMatch); // True

		driver.findElement(By.xpath("//*[text()='Checkout']")).click();

		// handling DropDown
		WebElement dropDown = driver.findElement(By.xpath("//div[@class='form-group']/input"));
		dropDown.click();
		dropDown.sendKeys("se");

		// handling dropdown by using Actions class.

		//		Actions a = new Actions(driver);
		//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item'])[2]")).click();
		//		driver.findElement(By.cssSelector(".action__submit")).click();

		// By using for Loop
		List<WebElement> listDropDown = driver
				.findElements(By.cssSelector("section.ta-results.list-group.ng-star-inserted span"));
		for (int i = 0; i < listDropDown.size(); i++) {
			String text = listDropDown.get(i).getText();
			if (text.contains("Senegal")) {
				listDropDown.get(i).click();
				break;

			}
		}
		driver.findElement(By.cssSelector("a.action__submit")).click();
		String orderConfirmationMessage = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertEquals(orderConfirmationMessage, "THANKYOU FOR THE ORDER.");

		String orderID = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
		// I'm replacing the unwanted symbols, and removing spaces.
		System.out.println(orderID.replace("|", "").trim());
		driver.close();

	}
}

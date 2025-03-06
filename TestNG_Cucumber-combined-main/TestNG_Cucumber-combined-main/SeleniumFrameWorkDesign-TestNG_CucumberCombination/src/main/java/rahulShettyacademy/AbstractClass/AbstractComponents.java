package rahulShettyacademy.AbstractClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulShettyacademy.pageobjects.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyacademy.pageobjects.CartPage;

public class AbstractComponents {
	//	ThumbNail: Assertions and validations should be present in Page class. It should be only present in Testclass

	WebDriver driver; //since scope is no life, so we are creating local variable.

	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
	}
	// This will be the parent of all the class. Since it contains all the reusable codes. So we have to extend the class in other child classes.


	//All these methods should be dynamic, we shouldn't hardcode this. we have to get and pass the values through paramater.


	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	// if we use from driver. => it means thats webelement
	// if its starts as By.id -> its locators.


	public void waitForElementToBeAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToBeAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}



	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	
	public OrderPage goToOrderPage() {
		
		orderHeader.click();
		OrderPage OrderPage = new OrderPage(driver);
		return OrderPage;

	}



}








package rahulShettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulShettyacademy.AbstractClass.AbstractComponents;

public class OrderConfirmationPage extends AbstractComponents {

	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver); 
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(className = "hero-primary")
	WebElement orderConfirmationMessage;

	@FindBy(css = "label.ng-star-inserted")
	WebElement getOrderID;

	/*
	 * String orderConfirmationMessage =
	 * driver.findElement(By.className("hero-primary")).getText();
	 * Assert.assertEquals(orderConfirmationMessage, "THANKYOU FOR THE ORDER.");
	 * 
	 * String orderID =
	 * driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
	 * 
	 * System.out.println(orderID.replace("|", "").trim());
	 */

	public String getOrderConfirmationMsg() {
		String text = orderConfirmationMessage.getText();
		return text;
		
		/**
		 * or we can say
		 * return orderConfirmationMessage.getText();
		 */

	}

	public String getOrderID() {
		String text = getOrderID.getText();
		String orderID = text.replace("|", "").trim();
		return orderID;

	}

}

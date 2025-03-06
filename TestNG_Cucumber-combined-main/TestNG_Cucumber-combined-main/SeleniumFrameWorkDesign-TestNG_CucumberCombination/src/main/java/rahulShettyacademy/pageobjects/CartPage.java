package rahulShettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulShettyacademy.AbstractClass.AbstractComponents;

public class CartPage extends AbstractComponents  {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver); 
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[text()='Checkout']")
	WebElement Checkout_btn;

	// driver.findElement(By.xpath("//*[text()='Checkout']")).click();

	@FindBy(css = ".cartWrap.ng-star-inserted h3")
	List<WebElement> cardProducts;

	// List<WebElement> cardProducts =
	// driver.findElements(By.cssSelector(".cartWrap.ng-star-inserted h3"));
	//
	// boolean anyMatch = cardProducts.stream().anyMatch(s ->
	// s.getText().equalsIgnoreCase(productName));
	// Assert.assertTrue(anyMatch);

	//List<WebElement> cardProduct = cartPage.cardProduct();
	public List<WebElement> cardProduct() {
		return cardProducts;
	}

	public boolean productPresent(String productName) {
		boolean anyMatch = cardProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return anyMatch;

	}

	public PaymentPage Checkout_btn() {

		Checkout_btn.click();
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
		
		
	}

}

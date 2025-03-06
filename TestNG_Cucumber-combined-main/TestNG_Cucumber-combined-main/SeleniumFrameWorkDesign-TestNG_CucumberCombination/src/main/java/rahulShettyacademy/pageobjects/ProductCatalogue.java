package rahulShettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulShettyacademy.AbstractClass.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	// ThumbNail: Assertions and validations should be present in Page class. It
	// should be only present in Testclass

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver); // all child should server super constractor.
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
	@FindBy(css = "div.mb-3")
	List<WebElement> products;

	// PageFactory is only for "driver.findelement"
	// for locators, we have to use like below,
	By productsBy = By.cssSelector("div.mb-3");

	/**
	 * @description if any of the locator, is not dependent on chrome driver. then
	 *              we can use BY class.
	 */
	By addToCart = By.cssSelector("button.btn.w-10.rounded");

	// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	By toasterMessage = By.id("toast-container");

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement ClickOnCart;

	public List<WebElement> getProductList() {
		waitForElementToBeAppear(productsBy); // we calling the resuable method here, this locator will be used in
		// utility class.
		return products;// Returning value is must

	}

	// WebElement prod = products.stream()
	// .filter(s ->
	// s.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst()
	// .orElse(null);
	// prod.findElement(By.cssSelector("button.btn.w-10.rounded")).click();

	/**
	 * @description we are filter our product
	 * @param productName
	 * @return desired product as prod
	 */
	public WebElement getproductName(String productName) { // calling this product from by Locator
		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst()
				.orElse(null);
		return prod;

	}

	/**
	 * @description we
	 * @param productName
	 */

	// prod.findElement(By.cssSelector("button.btn.w-10.rounded")).click();
	public void addProductToCart(String productName) {
		WebElement prod = getproductName(productName); // due to continuation, its taking the getproduct methods, and
		// starts from there.
		prod.findElement(addToCart).click();
		waitForElementToBeAppear(toasterMessage);
		waitForElementToDisappear(spinner);

	}

	public CartPage ClkToCart() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		ClickOnCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}

}

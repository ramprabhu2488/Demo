package rahulShettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyacademy.AbstractClass.AbstractComponents;

public class PaymentPage extends AbstractComponents  {
	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver); 
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='form-group']/input")
	WebElement dropdown;

	@FindBy(css = "section.ta-results.list-group.ng-star-inserted span")
	List<WebElement> dropdownList;

	@FindBy(css = "a.action__submit")
	WebElement placeOrder_btn;

	/*
	 * WebElement dropDown =
	 * driver.findElement(By.xpath("//div[@class='form-group']/input"));
	 * dropDown.click(); dropDown.sendKeys("se"); List<WebElement> listDropDown =
	 * driver .findElements(By.
	 * cssSelector("section.ta-results.list-group.ng-star-inserted span")); for (int
	 * i = 0; i < listDropDown.size(); i++) { String text =
	 * listDropDown.get(i).getText(); if (text.contains("Senegal")) {
	 * listDropDown.get(i).click(); break;
	 * 
	 * } }
	 * 
	 * driver.findElement(By.cssSelector("a.action__submit")).click();
	 */

	public void selectCountry(String value) {
		dropdown.click();
		dropdown.sendKeys(value);

	}

	public void selectCountry_DropdownOption() {

		for (int i = 0; i < dropdownList.size(); i++) {
			String text = dropdownList.get(i).getText();
			if (text.contains("Senegal")) {
				dropdownList.get(i).click();
				break;
			}
		}
	}

	public OrderConfirmationPage placeOrder_btn() {
		placeOrder_btn.click();
		
		OrderConfirmationPage confirmationPage = new OrderConfirmationPage(driver);
		return confirmationPage;
		
		/**
		 * or we can write, 
		 * return new OrderConfirmationPage(driver);
		 */

	}

}

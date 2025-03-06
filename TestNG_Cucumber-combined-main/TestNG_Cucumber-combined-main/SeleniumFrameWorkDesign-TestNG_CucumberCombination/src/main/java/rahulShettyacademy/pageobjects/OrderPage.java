package rahulShettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyacademy.AbstractClass.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	/**
	 * description = we checks the ordered table, whether the order items are present or not.
	 */

	@FindBy(xpath = "//tr//td[2]")
	List<WebElement> productNamesInTheTable;

	public String findOrderedItems(String ProductName) {

		String expectedText = null;

		for (int i = 0; i < productNamesInTheTable.size(); i++) {
			String text = productNamesInTheTable.get(i).getText();
			if (text.equalsIgnoreCase(ProductName)) {
				expectedText = text;
				break;

			}

		}
		return expectedText;

	}

}

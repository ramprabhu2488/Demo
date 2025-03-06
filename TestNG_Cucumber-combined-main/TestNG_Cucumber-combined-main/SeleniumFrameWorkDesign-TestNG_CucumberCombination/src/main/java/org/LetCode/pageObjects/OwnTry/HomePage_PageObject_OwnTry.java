package org.LetCode.pageObjects.OwnTry;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage_PageObject_OwnTry {
	
	// Advantange
			// If we maintain the locators at one place, we can update those if anything comes, that will be reflected in all the places. We no need to update, whereever we used that locator manually.
			// THis way, we can reduce the maintenance. 
			// Locators should be maintained separate classes, based on the modules. 
			// We are breaking them based on, how many pages or screens we have it in our application. 
	
	static WebDriver driver;

	public HomePage_PageObject_OwnTry() {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		HomePage_PageObject_OwnTry p = new HomePage_PageObject_OwnTry();
		p.OpenTab();
		p.SwitchToChildWindow();
		p.ClickOnAllCourses();
		p.SwitchToParentWindow();
		Thread.sleep(3000);

		if (p.GetText().equals("Practice Page")) {
			System.out.println("Texts are matching");

		} else {
			System.out.println("Not maching");

		}
		p.SelectvalueFromDropDown("Option2");

		System.out.println("over");

	}

	// Locator
	@FindBy(id = "opentab")
	WebElement OpenTab;

	@FindBy(xpath = "//a[text()='Access all our Courses']")
	WebElement AllCourses;

	@FindBy(xpath = "//h1[text()='Practice Page']")
	WebElement GetText;

	@FindBy(id = "dropdown-class-example")
	WebElement dropDown;

	// Action
	public void OpenTab() {
		OpenTab.click();

	}

	public void SelectvalueFromDropDown(String Option) {
		Select s = new Select(dropDown);
		dropDown.click();
		s.selectByVisibleText(Option);

	}

	public String GetText() {
		String text = GetText.getText();
		return text;

	}

	public void SwitchToChildWindow() {
		Set<String> parentWindow = driver.getWindowHandles();
		Iterator<String> it = parentWindow.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		
	}

	public void SwitchToParentWindow() {
		Set<String> parentWindow = driver.getWindowHandles();
		Iterator<String> it = parentWindow.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(parentId);

	}

	public void ClickOnAllCourses() {
		AllCourses.click();

	}
}

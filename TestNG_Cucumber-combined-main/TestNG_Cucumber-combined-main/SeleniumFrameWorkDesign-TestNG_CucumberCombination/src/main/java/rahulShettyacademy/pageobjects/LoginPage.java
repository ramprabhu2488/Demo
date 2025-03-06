package rahulShettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import rahulShettyacademy.AbstractClass.AbstractComponents;

public class LoginPage extends AbstractComponents {
	// ThumbNail: Assertions and validations should be present in Page class. It
	// should be only present in Testclass

	// PageObject nevers hold data. It should only focused on elements and their
	// actions -> Important to remember.

	WebDriver driver; // local variable

	// Constructor
	// Constructor Will be invoke first, if object is created.
	public LoginPage(WebDriver driver) {
		super(driver); // we are passing this driver knowledge to parent class of resuablecompenent.
		this.driver = driver; // both the driver has no life, since the actual driver placed in
		// RahulShettyProgramPage. we giving the life by using passing the constructor
		// arguments.
		// Received driver from constuctor, and we assigning in to local variable
		// driver.

		PageFactory.initElements(driver, this); // This method should come only when we use PageFactory
	}

	// driver.findElement(By.id("userEmail")).sendKeys("johndeepak444@gmail.com");
	// driver.findElement(By.id("userPassword")).sendKeys("Deepak@95");
	// we defining this example locators, in two different approach

	// Without pageFactory
	// WebElement userName = driver.findElement(By.id("userEmail"));
	// WebElement passWord = driver.findElement(By.id("userPassword"));

	// With PageFactory
	// if we go with this, we need to explicitly tell the driver, with help of
	// "InitElements" method to where the driver is. [check constructor]

	// Elements

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement passWord;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css  = "[class*='flyInOut']")
	WebElement IncorrectEmail_Password;
	
	//div[@aria-label='Incorrect email or password.']

	// Actions
	// We are specifying the actions for the elements. and we added into one method.
	public ProductCatalogue loginApplication(String email, String pswd) {
		userName.sendKeys(email);
		passWord.sendKeys(pswd);
		submit.click();
		
		// When we damn Sure, this method will take you to new page, so we can create the object of the class at the end of the methods.
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	/**
	 * => this is called comment section. we can use this block for giving
	 * comments.. to get this type /** and hit enter
	 */

	/**
	 * @param Provide URL
	 * @description hit the URL
	 */
	public void goToWebsite() {
		driver.get("http://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();

	}
	
	public String getErrorMessage() {
		waitForWebElementToBeAppear(IncorrectEmail_Password);
		return IncorrectEmail_Password.getText();
		

	}
	
	
	
	

}

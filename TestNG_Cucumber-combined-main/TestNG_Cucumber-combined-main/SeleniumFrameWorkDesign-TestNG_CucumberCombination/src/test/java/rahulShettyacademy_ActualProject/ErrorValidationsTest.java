package rahulShettyacademy_ActualProject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulShetty.TestComponents.BaseTest;
import rahulShetty.TestComponents.RetryMechanism;
import rahulShettyacademy.pageobjects.CartPage;
import rahulShettyacademy.pageobjects.OrderConfirmationPage;
import rahulShettyacademy.pageobjects.PaymentPage;
import rahulShettyacademy.pageobjects.ProductCatalogue;

// To orgainize imports, source->organize Imports


public class ErrorValidationsTest extends BaseTest {
	// These methods are just to see parallel execution and how to use them, in POM.XML

	// retryAnalyzer go to the class, where the retry mechanism is present
	@Test(groups = {"Smoke"}, retryAnalyzer =RetryMechanism.class)
	public void LoginErrorValidation() throws IOException {

		String IncorrectMsg = "Incorrect email or  password.";


		// We intentionally failing the Test to validate the error message.
		LoginPage.loginApplication("johndeepak444@gmail.com", "123"); 

		Assert.assertEquals(LoginPage.getErrorMessage(), IncorrectMsg, "Matching");
		System.out.println("Done");
	}

	@Test(groups = {"Regression"})
	public void ValidatedOrderConfirmation() throws IOException {
		String productName = "ZARA COAT 3";
		String confirmationMsg = "THANKYOU FOR THE ORDER.";

		//LoginPage LoginPage = launchApplication(); // This line no more required, once we create obj creation login page at as global level.
		ProductCatalogue productCatalogue = LoginPage.loginApplication("vdeepak7595@gmail.com", "Deepak@95"); // After added the new page obj at the end of method


		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.ClkToCart(); // Returning from ProductCatalgue

		boolean productPresent = cartPage.productPresent("123"); // Here we intentionally providing Different value, its returns false
		Assert.assertFalse(productPresent);


	}

}

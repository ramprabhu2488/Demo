package rahulShettyacademy_ActualProject;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShetty.TestComponents.BaseTest;
import rahulShettyacademy.pageobjects.CartPage;
import rahulShettyacademy.pageobjects.LoginPage;
import rahulShettyacademy.pageobjects.OrderConfirmationPage;
import rahulShettyacademy.pageobjects.OrderPage;
import rahulShettyacademy.pageobjects.PaymentPage;
import rahulShettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrder extends BaseTest {


	String productName = "ZARA COAT 3"; // -> Keep this active, since these required for "OrderHisToryTest" test

	// we have to specify the dataprovider method name
	//public void ValidatedOrderConfirmation(String email, String pass , String productName) throws IOException { // we passing the value, instead of hardcode
	/**
	 * 
	 * @param the above example is for getting the data by using dataprovider, without using hashmap
	 * 
	 */


	//This is example of using hashmap
	@Test(dataProvider = "getData", groups = {"Sample"} )
	public void ValidatedOrderConfirmation(HashMap<String, String> input) throws IOException { // we passing the value, instead of hardcode

		String confirmationMsg = "THANKYOU FOR THE ORDER.";

		// We call the Login Method here and passing the values.
		/**
		 * LP.loginApplication("johndeepak444@gmail.com", "Deepak@95"); before
		 */
		//LoginPage LoginPage = launchApplication(); // This line no more required, once we create obj creation login page at as global level.
		ProductCatalogue productCatalogue = LoginPage.loginApplication(input.get("email"), input.get("pass")); // After added the new page obj at the end of method

		// ProductCatalogue PL = new ProductCatalogue(driver); ==> No more required this
		// object creation

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product")); // we can use HashMap key pair, if we required here
		CartPage cartPage = productCatalogue.ClkToCart(); // Returning from ProductCatalgue

		boolean productPresent = cartPage.productPresent(input.get("product"));
		Assert.assertTrue(productPresent);
		PaymentPage paymentPage = cartPage.Checkout_btn();

		paymentPage.selectCountry("se");
		paymentPage.selectCountry_DropdownOption();
		OrderConfirmationPage confirmationPage = paymentPage.placeOrder_btn();

		String orderConfirmationMsg = confirmationPage.getOrderConfirmationMsg();
		Assert.assertEquals(orderConfirmationMsg, confirmationMsg, "Content Matching");

		String orderID = confirmationPage.getOrderID();
		System.out.println(orderID);

		// LoginPage.tearDown(); No more Required, since we added this in AfterMethod.

	}




	@Test(dependsOnMethods = {"ValidatedOrderConfirmation"}) // MethodName, it depends the specified method, if that method runs, then this method will work.
	public void OrderHisToryTest() {

		ProductCatalogue productCatalogue = LoginPage.loginApplication("johndeepak444@gmail.com", "Deepak@95");
		OrderPage OrderPage = productCatalogue.goToOrderPage();
		String orderedItems = OrderPage.findOrderedItems(productName);
		Assert.assertEquals(orderedItems, productName);
		System.out.println("ProductPresent");


	}

	/**
	 * This dataprovider helps to pass the value to this particular class
	 * object is the parent of all data types
	 * @return 
	 * 
	 * @disadvantage, when we go for multiple and huge set of data, then it will be messed up. to overcome, we can use key and values pair. 
	 * @throws IOException 
	 */

	//	@DataProvider
	//	public Object[][] getData() {
	//		return new Object[][] {{"johndeepak444@gmail.com", "Deepak@95", "ZARA COAT 3"}}; // I'm passing one set of data for example    
	//
	//	}


	// Using hashMap Concept

	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "johndeepak444@gmail.com");
//		map.put("pass", "Deepak@95");
//		map.put("product", "ZARA COAT 3"); 

		//To add one more set then simply do

		//		HashMap<String, String> map1 = new HashMap<String, String>();
		//		map1.put("email", "johndeepak444@gmail.com");
		//		map1.put("pass", "Deepak@95");
		//		map1.put("productName", "ZARA COAT 3");
		//return new Object[][] {{jsonData.get(0)}}; // we have to use map based on how many  testdata we pass. return new Object[][] {{map}, {map1}}
		
		/**
		 * We are getting the value from JSON file, so that we need to maintain any data in TestFile
		 */

		// we are calling the Method to get the data form JSON
		List<HashMap<String, String>> jsonData = getJSONData(System.getProperty("user.dir")+"//src//test//java//rahulShettyAcademy//data//Purchase.json");
		
		return new Object[][] {{jsonData.get(0)}}; // if we have another set of data, the mention, json.get(1),....

	}

}

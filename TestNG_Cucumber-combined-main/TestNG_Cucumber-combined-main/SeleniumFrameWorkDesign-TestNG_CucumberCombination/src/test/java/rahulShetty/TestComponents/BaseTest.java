package rahulShetty.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyacademy.pageobjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public  LoginPage LoginPage ; 

	public WebDriver initializeDriver() throws IOException {

		// properties class => in jave we have properties class, with help of this. we
		// can access the data from properties class.

		Properties prop = new Properties();
		//C:\\Users\\DeepakVaithylingam\\eclipse-workspace\\SeleniumFrameWorkDesign-projectName\\src\\main\\java\\rahulShettyacademy\\Resources\\Globaldata.properties

		// if we set out path, like above, it will falls under static. To Make dynamic. => we have system.getproperty
		//user.dir -> this will get all user location dynamically based on who accessing the project



		// user.div should be used
		FileInputStream path = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulShettyacademy//Resources//Globaldata.properties"); // file path of the properties class

		//FileInputStream path = new FileInputStream("C:\\Users\\DeepakVaithylingam\\eclipse-workspace\\SeleniumFrameWorkDesign-projectName\\src\\main\\java\\rahulShettyacademy\\Resources\\Globaldata.properties"); // file path of the properties class
		prop.load(path); // => inputstream

		String browserName = prop.getProperty("browser");// Need to specify the required property name. it act as key &
		// pair combination.

		// based on our browser values, required browser will launch
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
				
		
		/**
		 * To Run the Test in Headless mode, use this code instead
		 * if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
				
			}
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1440, 900));  // we are setting maximum resultion to avoid failures.
		}
		 * 
		 * This will check the value we pass from maven, and trigger the browser.
		 */

		else if (browserName.equalsIgnoreCase("firefox")) {

			// this block we should specify firefox driver set up
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver = (WebDriver) new GeckoDriverInfo();
		}

		// to acces this lines, we set out the driver as global variable in this class.
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	// It run once, before all the @Test methods
	@BeforeMethod(alwaysRun = true)  // alwaysRun will execute the methods, irrespetive of all scenario's.
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();  // this will handle the above method. and work on the other steps present in launchApplication
		// We creating an object for LoginPage Class
		LoginPage = new LoginPage(driver); // Passing the driver to the loginPage Constructor
		LoginPage.goToWebsite();// triggering browser by calling this method
		return LoginPage;

	}

	@AfterMethod(alwaysRun = true)   // It run once, After all the @Test methods

	public void tearDown() {
		driver.close();

	}

	// we sending the filepath generic way.
	public List<HashMap<String, String>> getJSONData(String Filepath) throws IOException {



		/**
		 * This helps to read the data from JSON and convert that into string. 
		 * Use user.dir
		 * This is one time Implementation
		 */

		// reading Json To String
		String JsonContent =	FileUtils.readFileToString(new File (Filepath), StandardCharsets.UTF_8);


		// String to HashMap -> Add new dependency called Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>(){

		});

		return data;

		// This data now holds the JSON values in LIST format

	}


	//This will take screenshot only when our testcase got failed
	
	// we giving the life for the driver from listeners class. 
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
		// we retruning the path where this method stores the screenshot
	}


	//Extend Reports
	
}

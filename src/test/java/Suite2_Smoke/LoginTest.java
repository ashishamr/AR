package Suite2_Smoke;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Util.StringEncrypt;

public class LoginTest {
	private static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		// between Selenium and the Chrome installation on your machine
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
				
		//Close all open browsers
		WebDriver chromeDriver = new ChromeDriver();
		for(String eachWindowHandle:chromeDriver.getWindowHandles()){
            chromeDriver.switchTo().window(eachWindowHandle).close();
        }
		
		// Start a new Chrome browser instance and maximize the browser window		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	@Parameters({ "userName", "Password" })
	//@Parameters("userName")
	// public void LoginTestNew(@Optional("Abc") String userName,
	// @Optional("xyz") String Password) throws InterruptedException {
	public void LoginTestWithParam(String userName, String Password) throws InterruptedException {
		// String strEncryptedString = "CRcADjkCCQEXOw0cEBw6HAg=";
		// String key = "lockUnlock";
		// String decodedPwd = StringEncrypt.decryptXOR(Password, key);

		driver.get("https://accounts.google.com");

		// declare and initialize the variable to store the expected title of the webpage;
		String expectedTitle1 = "Sign in"; //// â€œSign in â€“ Google Accountsâ€�;
		String expectedTitle2 = "with your Google Account"; 
		
		// fetch the title of the web page and save it into a string variable
		String actualTitle1 = driver.findElement(By.id("headingText")).getText();
		String actualTitle2 = driver.findElement(By.id("headingSubtext")).getText();

		// Verify the title
		Assert.assertEquals(expectedTitle1, actualTitle1, "The sub heading title 1 not matching");
		Assert.assertEquals(expectedTitle2, actualTitle2, "The sub heading title 2 not matching");

		// enter a valid username in the email textbox;
		WebElement username = driver.findElement(By.id("identifierId"));
		username.clear();
		//Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String key = "lockUnlock";
        String decodedUser = StringEncrypt.decryptXOR(userName, key);
		username.sendKeys(decodedUser);

		//Click on Next
		WebElement NextButtonUserName = driver.findElement(By.xpath("//*[@id='identifierNext']/content/span")); // XPath("//*[@id='identifierNext']/content/span"));
		NextButtonUserName.click();
		
		//Wait processing
		//Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// enter a valid password in the password textbox	
		WebElement password = driver.findElement(By.xpath("//input[contains(@aria-label,'Enter your password')][@autocomplete='current-password']"));
		password.clear();		

        String decodedPwd = StringEncrypt.decryptXOR(Password, key);		
		password.sendKeys(decodedPwd);//// sendKeys(â€œpassword123â€�);

		WebElement NextButtonPWd = driver.findElement(By.xpath("//*[@id='passwordNext']/content/span")); // XPath("//*[@id='identifierNext']/content/span"));
		NextButtonPWd.click();	
		
		//Wait processing
		//Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		////utilities.ScreenshotFile.screenshotFileSave(driver);
		
	}

	@AfterClass
	public static void cleanUp() {
		if (driver != null) {
			// Close the browser
			driver.close();
			driver.quit();
		}
	}
}
package old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Util.StringEncrypt;

public class AccountLogin {private static WebDriver driver;

@BeforeClass
public static void setUp() {
	// between Selenium and the Chrome installation on your machine
	System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
	// Start a new Chrome browser instance and maximize the browser window
	//// driver = new ChromeDriver();
	//// driver.manage().window().maximize();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--start-maximized");
	driver = new ChromeDriver(options);
}

@Test
@Parameters({ "userName", "Password" })
public void AccountLogin(@Optional("Abc") String userName, @Optional("xyz") String Password) throws InterruptedException {
	
	////String strEncryptedString = "CRcADjkCCQEXOw0cEBw6HAg=";
	//String key = "lockUnlock";
    //String decodedPwd = StringEncrypt.decryptXOR(Password, key);
	
	driver.get("https://accounts.google.com");
	
	// declare and initialize the variable to store the expected title of the webpage;
	String expectedTitle1 = "Sign in";     ////“Sign in – Google Accounts”;
	String expectedTitle2 = "with your Google Account";     ////“Sign in – Google Accounts”;

	// fetch the title of the web page and save it into a string variable
	//String actualTitle = driver.getTitle();
	String actualTitle1 = driver.findElement(By.id("headingText")).getText();
	String actualTitle2 = driver.findElement(By.id("headingSubtext")).getText();
	
	//Verify the title
	Assert.assertEquals(expectedTitle1,actualTitle1);
	Assert.assertEquals(expectedTitle2,actualTitle2);

	// enter a valid username in the email textbox;
	WebElement username = driver.findElement(By.id("identifierId"));
	username.clear();
	Thread.sleep(3000);
	username.sendKeys(userName);//sendKeys(“TestSelenium”);
	
	WebElement NextButtonUserName = driver.findElement(By.xpath("//*[@id='identifierNext']/content/span")); //XPath("//*[@id='identifierNext']/content/span"));
	NextButtonUserName.click();
	
	Thread.sleep(10);
	// enter a valid password in the password textbox
	////WebElement password = driver.findElement(By.id("password"));
	
	WebElement password = driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input"));
	password.clear();
	password.sendKeys(Password);////sendKeys(“password123”);
	
	WebElement NextButtonPWd = driver.findElement(By.xpath("//*[@id='passwordNext']/content/span")); //XPath("//*[@id='identifierNext']/content/span"));
	NextButtonPWd.click();	

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
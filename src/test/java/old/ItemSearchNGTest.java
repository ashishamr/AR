package old;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ItemSearchNGTest {

	private static WebDriver driver;

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
	public void testChromeSelenium() {
		driver.get("https://www.amazon.com/");
		// Type "Software testing" in the search window
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Software Testing");
		
		//twotabsearchtextbox

		// Click on the search button
		driver.findElement(By.xpath("//input[@value='Go']")).click();

		// Select the first item in the list of search results
		driver.findElement(By.xpath("(//div[@id='resultsCol']//a[contains(@class,'access-detail-page')])[1]")).click();

		// Check that the page title contains the term "Software Testing"
		Assert.assertTrue(driver.getTitle().contains("Software Testing"));
		/*
		 * // Check that the page title contains the term "Software Development"
		 * Assert.assertTrue(driver.getTitle().contains("Software Development"))
		 * ;
		 */
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
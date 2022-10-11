package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_p2 {
	WebDriver driver;
	WebElement element;
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Current_Url() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String loginPageUrl = driver.getCurrentUrl(); 
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");	
		// driver.quit();
	
	}
	@Test
	public void TC_02_Verrify_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		// driver.quit();
	}
	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");	
		driver.navigate().forward();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}
	@Test
	public void TC_04_Page_Source() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	
	


	
}
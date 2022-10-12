package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xppath_css_p1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		// Login Page Url matching
		// String loginPageUrl = driver.getCurrentUrl();
		// Assert.assertEquals(loginPageUrl, "https://www.facebook.com/");
		
		
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//input[@id ='txtEmail']"));
		
		//<input class="text form-control" 
		//id="txtEmail" 
		//ame="txtEmail" 
		//placeholder="Địa chỉ email" 
		//type="email" 
		//value="" >
		
		//tagname[@attribute='']
		//input[@class ='text form-control']
		//input[@id ='txtEmail']
		//input[@name ='txtEmail']
		//input[@placeholder ='Địa chỉ email']
		//input[@value='']
		
		//tuye doi
		//div[contains(@id,'required-entry-firstname')]
		
		//tuong doi
		////div[@id='advice-required-entry-firstname']
		
		// noi chuoi
		//span[text()=concat('Hello "John", What',"'s happened?")]
		// đi từ nốt Sony Xperia -> cha -> em-> tới class 
		//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']
		// 	
		
	}

	@Test
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
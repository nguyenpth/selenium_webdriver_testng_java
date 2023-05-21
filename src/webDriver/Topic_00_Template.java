package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_00_Template {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName =System.getProperty("os.name");


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			// mac 
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		else {
			//window 
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_() {
		driver.get("https://www.facebook.com/");
		
		//driver.quit();
	}
	
	//sleep cứng ()
	@Test
	public void TC_02_() throws InterruptedException  {
		//Thread.sleep(5000);
	
	}
	public void sleepInSecond(long timeInSecond ) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
}
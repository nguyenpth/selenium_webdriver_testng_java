package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browsers {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Test
	public void TC_01_Firefox() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		
		driver.quit();
	}
	@Test
	public void TC_02_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		
		driver.quit();
	}
	@Test
	public void TC_03_Edge() {
		System.setProperty("webdriver.edges.driver", projectPath + "/browserDrivers/msedgedriver");
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		
		driver.quit();
	}
}
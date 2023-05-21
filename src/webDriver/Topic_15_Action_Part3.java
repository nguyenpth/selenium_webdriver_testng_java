package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.AcceptAlert;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_15_Action_Part3 {
	WebDriver driver;
	Actions action ;
	JavascriptExecutor jsExecutor;
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
		
		action = new Actions(driver);
		
		// ep kieu tuong minh
		jsExecutor = (JavascriptExecutor)driver;
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// scroll den element do
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
		// scroll den element do
		
	}
	
	@Test
	public void TC_02_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		// scroll den element do
		
		
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		sleepInSecond(1);
		// nen dung hon vi no la native even hanh vi nguoi dung
		driver.findElement(By.cssSelector("li.context-menu-icon-quit")).click();
		// di chuyen chuot den element roi click
		// action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(1);
		driver.switchTo().alert().accept();
		sleepInSecond(1);
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	}
	
	//sleep cứng ()
	@Test
	public void TC_03_Drag_Drop()  {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(1);
		// verify text
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		// verify color
		String bigCircleColor = bigCircle.getCssValue("background-color");
		System.out.print(bigCircleColor);
		// chuyen doi qua hexa
		Assert.assertEquals(Color.fromString(bigCircleColor).asHex().toUpperCase(), "#03A9F4");
		
	}
	
	@Test
	public void TC_04_HTLM5()  {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		
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
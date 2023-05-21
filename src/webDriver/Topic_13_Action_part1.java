package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_13_Action_part1 {
	WebDriver driver;
	Actions action ;
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
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		// setTimeout(() => {debugger;},3000); => dung debug/ source de bat debug lay text trong trinh duyet
		// hoac nhan F8 ben firefox de bat debug
		// trong qua trinh chay minh di chuyen chuotj cho khac nen se fails => xung dot cac su kien
		
	}
	
	//sleep cứng ()
	@Test
	public void TC_02_Mytra() throws InterruptedException  {
		driver.get("http://www.myntra.com/"); // chan auto
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
		sleepInSecond(1);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']"))).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
	}
	
	@Test
	public void TC_03_Hover() throws InterruptedException  {
		driver.get("https://www.fahasa.com/"); 
		sleepInSecond(10);// hie thi len de tat popup vi chua hoc bai popup
		// hover lan 1
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(1);
		// hover lan 2
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text() ='Quản Trị - Lãnh Đạo']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class ='breadcrumb']//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed());
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
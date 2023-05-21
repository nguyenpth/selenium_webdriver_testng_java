package webDriver;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class Topic_14_Action_part2 {
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
	public void TC_01_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		// click vao so 1 (nguon) -> van giu chuot chua nha ra
		action.clickAndHold(listNumber.get(0))
				// di chuot toi taget
				.moveToElement(listNumber.get(7))
				// nha chuot ra	
				.release()
				//thuc thi
				.perform();
		sleepInSecond(2);
		List<WebElement> listResurt = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listResurt.size(), 8);
		
	}
	
	//sleep cứng ()
	@Test
	public void TC_02_ClickAndHoldRandom() throws InterruptedException  {
		driver.get("https://automationfc.github.io/jquery-selectable/"); // chan auto
		// chay dc cho ca mac va wwindow
		Keys key = null;
		if (osName.contains("Window")) {
			key=Keys.CONTROL;
		}else {
			key=Keys.COMMAND;
		}
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		action.keyDown(key).perform();
		// click cho random
		action.click();
		action.click(listNumber.get(0))
		.click(listNumber.get(3))
		.click(listNumber.get(5))
		.click(listNumber.get(10)).perform();
		// nha phim control
		action.keyUp(key).perform();
		List<WebElement> listResurt = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listResurt.size(), 4);
		sleepInSecond(3);
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
package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
public class Topic_11_12_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	Alert alert;
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
		expliciWait =new WebDriverWait(driver, 10);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);
		
		//1. co the switch qua va tuong tac luon
		// alert = driver.switchTo().alert();
		
		//2. can wait truoc khi nao dung thi swich qua va tuong tac => nen dung c2 on dinh, it false hon vi no co co che cho
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		// verify alert tilte expect
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		// step nay chay sau buoc getText() moi ra dc kq
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}
	
	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		
		//1. co the switch qua va tuong tac luon
		// alert = driver.switchTo().alert();
		
		//2. can wait truoc khi nao dung thi swich qua va tuong tac => nen dung c2 on dinh, it false hon vi no co co che cho
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		// verify alert tilte expect
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// step nay chay sau buoc getText() moi ra dc kq
		//clcik cancel
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}
	
	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		sleepInSecond(2);
		String couseName ="input text";
		
		alert.sendKeys(couseName);
		sleepInSecond(2);
		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+couseName);
	}
	
	@Test
	public void TC_04_Authentiaction_Alert_1() {
		//truyen truc tiep user/pw vao trong url nayf -> auto signin
		//http://user:pw @
		driver.get(passUserAndPassToUrl("http://the-internet.herokuapp.com/basic_auth","admin","admin"));
		
		//driver.quit();
	}
	
	@Test
	public void TC_04_Authentiaction_Alert_2() {
		//truyen truc tiep user/pw vao trong url nayf -> auto signin
		//http://user:pw @
		driver.get("http://the-internet.herokuapp.com");
		
		//driver.quit();
	}
	
	
	public String passUserAndPassToUrl(String url, String user, String pass) {
		String[] arrUrl = url.split("//");
		return arrUrl[0]+"//"+user+":"+pass+"@"+arrUrl[1];
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
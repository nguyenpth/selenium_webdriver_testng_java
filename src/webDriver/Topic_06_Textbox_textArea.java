package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_textArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName =System.getProperty("os.name");
	String firstName, lastName, editFirstName, editLastName; 
	Random rand = new Random();

	String employeeId= String.valueOf(rand.nextInt(99999));
	String passportNumber = "40517-402-96-7202";
	String comment = "test wwith textbox\n text area";
	Actions action;
	String password="Ab@123456";
	String cfPassword ="Ab@123456";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			// mac 
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else {
			//window 
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstName = "Nguyen";
		lastName = "Pham";
		editFirstName = "Nguyen 1";
		editLastName = "Pham 1";
		
		
	}

	@Test
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(3);
		// open page add employee
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		// input data vào 2 bien
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		// mac dung command+a, WWindow: control+a
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.COMMAND,"a"));
		sleepInSecond(1);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(employeeId);
		sleepInSecond(1);
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(firstName+employeeId);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(cfPassword);
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(5);
		// so sanh gia tri sau khi add success
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstName);
		//Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		//Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);
		// click vao 
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		//Add Immigration
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(6);
		//verrify Immigration
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(2);
		//verrify Immigration
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(2);
		// re-login
		driver.findElement(By.name("username")).sendKeys(firstName+employeeId);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
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
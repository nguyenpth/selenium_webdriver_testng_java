package webDriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_p3 {
	WebDriver driver;
	WebElement element;
	
	String projectPath = System.getProperty("user.dir");
	String osName =System.getProperty("os.name");
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_01_Is_Display() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//email textbox
		WebElement emailTexbox= driver.findElement(By.cssSelector("input#mail"));
		if(emailTexbox.isDisplayed()) {
			emailTexbox.sendKeys( "Automation testing textbox");
			System.out.println("Email textbox displayed");
		}else {
			System.out.println("Email textbox not displayed ");
		}
		
		// Age under 18 radio button
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		if(ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 radio displayed");
		}else {
			System.out.println("Age under 18 radio not displayed ");
		}
		
		// Education Text Area 
		WebElement educationTextarea = driver.findElement(By.cssSelector("textarea#edu"));
		if(educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys( " Automation testing");
			System.out.println("Education Text Area displayed");
		}else {
			System.out.println("Education Text Area not displayed ");
		}
		// Image 5 (Undisplayed)
				WebElement image5 = driver.findElement(By.xpath("//h5[text()= 'Name: User5']"));
				if(image5.isDisplayed()) {
					image5.sendKeys( " Automation testing");
					System.out.println("Education Text Area displayed");
				}else {
					System.out.println("Education Text Area not displayed ");
				}
	}
	@Test
	public void TC_02_Is_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//email textbox
		WebElement emailTexbox= driver.findElement(By.cssSelector("input#mail"));
		if(emailTexbox.isEnabled()) {
			emailTexbox.sendKeys( "Automation testing textbox");
			System.out.println("Email textbox displayed");
		}else {
			System.out.println("Email textbox not displayed ");
		}
		//Password Textbox
		WebElement passwordTextbox= driver.findElement(By.cssSelector("input#disable_password"));
		if(passwordTextbox.isEnabled()) {
			passwordTextbox.sendKeys( "Automation testing textbox");
			System.out.println("Password Textbox displayed");
		}else {
			System.out.println("Password Textbox not displayed ");
		}
	}
	@Test
	public void TC_03_Is_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//radio 
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		ageUnder18Radio.click();
		if(ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 radio selected");
		}else {
			System.out.println("Age under 18 radio not selected ");
		}
		
		//java checkbox 
		WebElement javaCheckbox = driver.findElement(By.cssSelector("input#java"));
		javaCheckbox.click();
		if(javaCheckbox.isSelected()) {
			System.out.println("Java checkbox selected");
		}else {
			System.out.println("Java checkbox not selected ");
		}
	}
	@Test
	public void TC_04_Email_Chimp() {
		driver.get("https://login.mailchimp.com/signup/post");
		driver.findElement(By.cssSelector("input#email")).sendKeys("nguyenpham123@gmail.com");
		sleepInSecond(1);
		WebElement passwordTextbox= driver.findElement(By.cssSelector("input#new_password"));
		
		// check lowercase
		passwordTextbox.sendKeys("aaa");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// check uppercase
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AAA");
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// check number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("123");
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());	
		
		// check special character
		passwordTextbox.clear();
		passwordTextbox.sendKeys("!@#$%^");
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());	
		
		// check 8 characters minimum
		passwordTextbox.clear();
		passwordTextbox.sendKeys("Ab@ab");
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());	
		
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
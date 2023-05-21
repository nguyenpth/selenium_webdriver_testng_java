package webDriver;

import static org.testng.Assert.assertEquals;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName =System.getProperty("os.name");


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			// mac 
			//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		else {
			//window 
			//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By loginButton = By.cssSelector("button.fhs-btn-login");
		// Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		String loginButtonBg = driver.findElement(loginButton).getCssValue("background-image");
		Assert.assertTrue(loginButtonBg.contains("rgb(224, 224, 224)"));
		
		//System.out.println(loginButtonBg);
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654321");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
		sleepInSecond(2);
		// verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		loginButtonBg = driver.findElement(loginButton).getCssValue("background-color");
		Color loginButtonBackgroundColor = Color.fromString(loginButtonBg);
		
		Assert.assertEquals(loginButtonBackgroundColor.asHex().toUpperCase(), "#C92127");
		//System.out.println(loginButtonBackgroundColor);
		//driver.quit();	
		
	}
	
	@Test
	public void TC_02_Default_Checkbox_Radio_single() throws InterruptedException  {
		driver.get("https://automationfc.github.io/multiple-fields/");
		//Thread.sleep(5000);
		//click chon 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Anemia')]/preceding-sibling::input")).click();
		//click chon 1 radio
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).click();
		// verify checkbox/ radio
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Anemia')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).isSelected());
		
		//checkbo tu bor chon
		driver.findElement(By.xpath("//label[contains(text(),'Anemia')]/preceding-sibling::input")).click();
		//Verify bo chon checkbox
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Anemia')]/preceding-sibling::input")).isSelected());
		//radio ko tu bo chon
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).isSelected());
		
	}
	@Test
	public void TC_02_Default_Checkbox_Radio_Multiple() throws InterruptedException  {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> allCheckboxs = driver.findElements(By.cssSelector("input.form-checkbox"));
		//duyêt qua vòng lập 
		for (WebElement checkbox : allCheckboxs) {
			checkbox.click();
			//sleepInSecond(1);
		}
		//verify bine chon thanh cong
		for (WebElement checkbox : allCheckboxs) {
			Assert.assertTrue(checkbox.isSelected());
		}
	}
	@Test
	public void TC_02_Default_Checkbox_Radio_Random() throws InterruptedException  {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> allCheckboxs = driver.findElements(By.cssSelector("input.form-checkbox"));
		for (WebElement checkbox : allCheckboxs) {
			if (checkbox.getAttribute("value").equals("Fainting Spells")) {
				checkbox.click();
			}
		}
	}
	
	@Test
	public void TC_03_Default_Checkbox() throws InterruptedException  {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		//tat qcao
		driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
		sleepInSecond(1);
		//click chon
		if (!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		sleepInSecond(1);
		// click bo chon
		if (driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
	
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
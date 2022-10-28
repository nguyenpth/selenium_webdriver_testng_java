package webDriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropbox {
	//khai bao
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName =System.getProperty("os.name");
	Select select;
	Random rand ;
	String firstName="Nguyen";
	String lastName = "Pham";
	String password = "Ab@123456";
	String cfpassword = "Ab@123456";
	String date ="13";
	String month = "August";
	String year ="1990";
	String company ="nguyen company";
	
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
		rand = new Random();
		
	}

	@Test
	public void TC_01_Defaut_Dropdown() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		
		//1. thao tac dropdown
		select =new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		// 2. chon itime cos texxt 13
		select.selectByVisibleText(date);
		
		////dùng sau
		//3. verify da cho 13 thanh cong
		//Assert.assertEquals(select.getFirstSelectedOption().getText(), "13");
		//4. (option) verify dropdow là muti hay single
		//Assert.assertFalse(select.isMultiple());
		
		//khởi tạo Month dropdown
		select =new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText(month);
		//khởi tạo Year dropdown
		select =new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		//Email
		String email = firstName+lastName+rand.nextInt(9999)+"@yopmail.com";
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Company")).sendKeys(company);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(cfpassword);
		driver.findElement(By.cssSelector("button#register-button")).click();
		// kiem tra dk thanh cong
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		//verify sau khi dk thanh cong
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),lastName);
		
		select =new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),date);
		select =new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
		select =new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),email);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"),company);
		driver.quit();
	}
	

	@Test
	public void TC_02_Defaut_Dropdown() {
		
		driver.get("https://www.rode.com/wheretobuy");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("button.cookie__bar__buttons__button__reqd.cookie-cust-button-no")).click();
		sleepInSecond(1);
		driver.findElement(By.cssSelector("button.swal2-confirm.swal2-styled")).click();
		
		select = new Select(driver.findElement(By.id("country")));
		select.selectByVisibleText("Vietnam");
		sleepInSecond(5);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		List<WebElement> dealers= driver.findElements(By.cssSelector("div.p-1 h4"));
		for (WebElement element : dealers) {
			System.out.println(element.getText());
		}
		driver.quit();
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
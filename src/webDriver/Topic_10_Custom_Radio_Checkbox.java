package webDriver;

import static org.testng.Assert.assertEquals;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_10_Custom_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
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
		
		// luon luo khoi tao sau bien driver
		jsExcutor =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}


	public void TC_01_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		// case 1:
		// thẻ input bị che ko thao tác dc 
		// thẻ input dc dung verify dc vi hàm isSelected()chỉ work với thẻ input
			
		//driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
	}
	
	@Test
	public void TC_02_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		// case 2: 
		// thẻ khác với input để lick (span/ div/ lable/...) đang hiển thị là được 
		// thẻ này ko dùng để verify dc -> vì hàmsSelected()chỉ work với thẻ input
		// thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).click();
		//tim element nao hover chuot dc ben chrome ma no ra hinh rang cua mau tim la click dc
		sleepInSecond(3);
		//veryfy chon thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).isSelected());
		// (span/ div/ lable/...) => luon luon tra ve false => nen cho nay ko chay dc la dung
		
	}
	@Test
	public void TC_03_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		// case 3: ket hop ca 2: dung label de thao tac click va input de verify
		
		// thẻ khác với input để lick (span/ div/ lable/...) đang hiển thị là được 
		// thẻ này ko dùng để verify dc -> vì hàmsSelected()chỉ work với thẻ input
		// thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		//tim element nao hover chuot dc ben chrome ma no ra hinh rang cua mau tim la click dc
		sleepInSecond(3);
		//veryfy chon thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
		// (span/ div/ lable/...) => luon luo tra ve false
		
	}
	
	@Test
	public void TC_04_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		// case 4: the input bi an van dung de clcik
		//ham click cua webElement nos se ko thao tac dc element bi an
		// nen se dung ham click cua javascriptde click vao elemen bi an
		//the input lai dung de verify dc -> vi ham isSelected() chir work voi the input
		// selenium cc thu vien co the nhung cac doan code JS vao kich ban test dc -> JavacriptExcutor
		// var radioButton= $x("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")[0];
		//radioButton.click();
		
		//tim element nao hover chuot dc ben chrome ma no ra hinh rang cua mau tim la click dc
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		// thao tác chọn
		jsExcutor.executeScript("arguments[0].click();",driver.findElement( radioButton));
		sleepInSecond(3);
		
		//veryfy chon thanh cong
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		// (span/ div/ lable/...) => luon luo tra ve false
		
	}
	
	
	@Test
	public void TC_05_googleForm() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);
		
		//tim element nao hover chuot dc ben chrome ma no ra hinh rang cua mau tim la click dc
		By radioButton = By.cssSelector("div[aria-label='Hà Nội']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Ngãi']");
		// thao tác chọn
		jsExcutor.executeScript("arguments[0].click();",driver.findElement( radioButton));
		sleepInSecond(1);
		jsExcutor.executeScript("arguments[0].click();",driver.findElement( checkbox));
		sleepInSecond(1);
		
		//veryfy chon thanh cong
		// cach 1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true']")).isDisplayed());
		// cach 2 dung ham 
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"),"true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"),"true");
		
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
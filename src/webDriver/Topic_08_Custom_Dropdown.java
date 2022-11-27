package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	// lúc này mới khai báo nên null
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExcutor;
	
	String projectPath = System.getProperty("user.dir");
	String osName =System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		//in giá trị trước khi khởi tạo
		//System.out.println(driver.toString());
		//Assert.assertNull(driver);
		if (osName.contains("Mac")) {
			// mac 
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		else {
			//window 
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		//khởi tạo driver
		driver = new FirefoxDriver();
		//khởi tạo wait
		expliciWait =new WebDriverWait(driver, 30);
		
		//khởi tạo JavascriptExecutor
		jsExcutor =(JavascriptExecutor) driver;
		
		
		//System.out.println(driver.toString());
		Assert.assertNotNull(driver);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		//driver.manage().window().setSize(null)
		
		
	}

	@Test
	public void TC_01_Custom_Dropbox_Jquery() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		//driver = new FirefoxDriver();
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemIbCustomDropdow("span#speed-button","ul#speed-menu div","Faster");
		sleepInSecond(1);
		selectItemIbCustomDropdow("span#files-button","ul#files-menu div","jQuery.js");
		sleepInSecond(1);
		selectItemIbCustomDropdow("span#number-button","ul#number-menu div","9");
		sleepInSecond(1);
		selectItemIbCustomDropdow("span#salutation-button","ul#salutation-menu div","Mr.");
		
		//driver.quit();
	}
	
	@Test
	public void TC_02_Custom_Dropbox_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		scrollElement("div.container");
		// tắt quảng cáo
		driver.findElement(By.cssSelector("i.x-cookie")).click();
		sleepInSecond(2);
		selectItemIbCustomDropdow("button#selectize-input","button#selectize-input+div>a","CITY L");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY L");
		
		
		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("TP. Hồ Chí Minh");
		sleepInSecond(2);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"TP. Hồ Chí Minh");
		
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực I");
		sleepInSecond(2);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Khu vực I");
	}
	
	public void scrollElement(String cssLocator) {
		jsExcutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(cssLocator)));
	}
	
	
	public void sleepInSecond(long timeInSecond ) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// dùng chung dropdown
	public void selectItemIbCustomDropdow(String parentLocator, String childLocator, String textExpectedItem) {
		//1. click vào 1 phần từ nào đó thuộc dropdown để nó xổ 
		driver.findElement(By.cssSelector(parentLocator)).click();
		//2. chờ cho tất cả các item dc load 
		// lưu ý: ko dùng sleep cứng, phải có 1 hàm wait ling động, 
		//nếu như chưa tìm thấy sẽ tìm lại trong khoảng time sx set.
		//nếu tìm thấy rồi sẽ ko chờ đến hết time 
		//bắt dc 1 locator để đại diện cho all item
		// presenceOfAllElementsLocatedBy: lấy hết
		//víible: lấy item đang hiển thị trên UI
		//bắt dc 1 locator đại diện cho all item
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		//3.1 nếu item cần chọn đang hiển thị
		//3.2 nếu item cần chọn ko hiển thị cần scroll down
		//4 kiểm tra text của item - nếu đúng vs cái mình cần thì click vào
		// viết code để duyệt qua từng item theo từng đk
		// duỵet qua từng item lấy ra text và kiểm tra nếu = text mình mong muốn thì click

		//lưu trữ tất cả item lại thì mới duyệt qua dc
		List <WebElement> allitems =  driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allitems) {
			// lấy text ra 
			String textActualItem =item.getText();
			if (textActualItem.equals(textExpectedItem)) {
				item.click();
				// dừng lại khi đã tìm thấy
				break;
			}
		}
	}
	
}
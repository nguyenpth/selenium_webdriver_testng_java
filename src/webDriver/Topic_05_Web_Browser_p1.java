package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_p1 {
	WebDriver driver;
	WebElement element;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Current_() {

		// Đóng cái browser nếu như 1 cửa sổ/ nhiều cửa số chỉ đóng tab nó đang đứng
		// (active) -> Handle Windows/ Tabs
		// driver.close();

		// Đóng cái browser
		// driver.quit();

		// Mở ra 1 Url (App link)
		driver.get("http://live.guru99.com");

		// Get ra cái url của cái page hiện tại
		String homePageUrl = driver.getCurrentUrl();

		// Verify 1 điều kiện đúng
		Assert.assertTrue(homePageUrl.equals("http://live.guru99.com"));

		// Verify 1 điều kiện đúng
		Assert.assertFalse(homePageUrl.equals("http://live.guru99.com/index.php/customer/account/"));

		// Verify 1 điều kiện đầu vào và đầu ra bằng nhau
		Assert.assertEquals(homePageUrl, "http://live.guru99.com");

		// assertTrue/False: Trả về kiểu boolean (true/ false) -> isDisplayed/
		// isEnabled/ isSelected/...
		// assertEquals: Trả về kiểu dữ liệu: String/ int/ double/ float/...

		// Trả về source code của page hiện tại: html/ css/ jquery/ js/...
		// String homePageSource = driver.getPageSource();
		// Assert.assertTrue(homePageSource.contains("This is demo site for"));

		// Trả về title của page hiện tại
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");

		// Trong bài xử lí Windows/ Tabs
		driver.getWindowHandle();
		driver.getWindowHandles();

		// Chờ cho element được tìm thấy
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Chờ cho page được load thành công
		// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Test GUI: Graphic User Interface
		driver.manage().window().maximize();

		// driver.navigate().back/refresh/forwar();

		// Tracking lại history
		// driver.navigate().to("http://live.guru99.com");

		// Alert/ Iframe (Frame)/ Windows
		driver.switchTo().alert().accept();
		driver.switchTo().frame("");
		driver.switchTo().window("");
	}

	@Test
	public void TC_02_Element() {
		// Tìm 1 element (nhiều) vs locator là gì

		// Cách 1: Nếu như mà element này chỉ dùng 1 lần
		driver.findElement(By.id("search")).sendKeys("Samsung");

		// Cách 2: Nếu như mà element này thao tác nhiều lần -> Khai báo biến
		WebElement searchTextbox = driver.findElement(By.id("search"));

		// Xóa dữ liệu trước khi sendkey
		searchTextbox.clear();

		// Nhập dữ liệu vào 1 textbox/ textarea
		searchTextbox.sendKeys("");

		// Click vào 1 element: button/ link/ radio/ checkbox/..
		searchTextbox.click();

		// Tìm và thao tác vs 1 element: findElement
		searchTextbox.findElement(By.id("search")).click();

		// Tìm và thao tác vs nhiều element: findElements
		searchTextbox.findElements(By.id("search")).get(0).click();

		// 0 1 2 3 4 5 -> index
		// A B C X Y Z -> data

		// tagname[@attribute='value'] = //input[@id='search']
		String searchPlaceholderValue = searchTextbox.getAttribute("placeholder");

		// Test GUI: font/ size/ color/ position/ size/....
		String loginButtonColor = searchTextbox.getCssValue("background");
		// #3399cc

		// Build framework: Chụp hình nhúng vào Report
		// searchTextbox.getScreenshotAs(arg0)

		WebElement searchTextbox_ = driver.findElement(By.cssSelector("#search"));
		// String searchTextboxTagname = searchTextbox_.getTagName();
		// searchTextboxTagname = input

		// Trả về text của 1 element: link/ button/ label/...
		String searchText = searchTextbox.getText();

		// assertTrue/ False
		Assert.assertTrue(searchTextbox.isDisplayed());
		Assert.assertTrue(searchTextbox.isEnabled());

		// Work vs Radio/ Checkbox
		Assert.assertTrue(searchTextbox.isSelected());

		boolean searchTextboxStatus = searchTextbox.isSelected();
		Assert.assertFalse(searchTextboxStatus);
		Assert.assertEquals(searchTextboxStatus, false);

		searchTextbox.click();

		// Work vs form (login/ register) -> Tagname = form
		// searchTextbox.submit();

	}

}
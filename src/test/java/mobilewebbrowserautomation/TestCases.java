package mobilewebbrowserautomation;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import applicationpage.ApplicaionWebElements;
import base.BaseUtil;
import base.WebDriverFactory;
import managers.FileReaderManager;

public class TestCases {
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	String timeStamp;
	String chromeDriverLocation;
	String geckDriverLocation;
	String fileWithPath;
	String prestoURL;
	ApplicaionWebElements applicaionWebElements;

	@BeforeClass
	public void setup() throws MalformedURLException {
		//Extent report configurations
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/reports/"+"DemoQATestResults.html");
		htmlReporter.config().setDocumentTitle("Test report for DemoQA Test");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test report");
		extent = new ExtentReports();
		extent.setSystemInfo("os", "Android");
		extent.setSystemInfo("DeviceName", "Android GoogleAPI Emulator");
		extent.attachReporter(htmlReporter);
		
		//Invoking the driver instance from WebDriverFactory class
		driver = WebDriverFactory.getMobileBrowserDriver();	
		applicaionWebElements = new ApplicaionWebElements();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test
	public void loginTest() throws Exception {
		
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationURL());
		test = extent.createTest("DemoQA_LoginValidation");

		fileWithPath = BaseUtil.takeSnapShot(driver);
		if(driver.findElements(applicaionWebElements.elementsOption_Link).size()>0) {
			test.pass("URL launched successfully and user is on home page").addScreenCaptureFromPath(fileWithPath);	
		}else
		{
			test.fail("User failed to launch the application").addScreenCaptureFromPath(fileWithPath);
		}

		Thread.sleep(2000);
		driver.findElement(applicaionWebElements.elementsOption_Link).click();
		Thread.sleep(2000);
		fileWithPath = BaseUtil.takeSnapShot(driver);
		if(driver.findElements(applicaionWebElements.bookStoreApplication_Link).size()>0) {
			test.pass("User successfully navigated to book store application option").addScreenCaptureFromPath(fileWithPath);	
		}else
		{
			test.fail("User failed to navigated to book store application option").addScreenCaptureFromPath(fileWithPath);
		}

		Thread.sleep(2000);
		driver.findElement(applicaionWebElements.bookStoreApplication_Link).click();
		Thread.sleep(2000);
		fileWithPath = BaseUtil.takeSnapShot(driver);
		if(driver.findElements(applicaionWebElements.login_Link).size()>0) {
			test.pass("User successfully able to view login option of book store application").addScreenCaptureFromPath(fileWithPath);	
		}else
		{
			test.fail("User failed view to login option of book store application").addScreenCaptureFromPath(fileWithPath);
		}

		driver.findElement(applicaionWebElements.login_Link).click();
		
		
		Thread.sleep(2000);
		fileWithPath = BaseUtil.takeSnapShot(driver);
		if(driver.findElements(applicaionWebElements.userName_WebEdit).size()>0) {
			test.pass("User successfully navigated to book store application login page").addScreenCaptureFromPath(fileWithPath);	
		}else
		{
			test.fail("User failed to navigate to book store application login page").addScreenCaptureFromPath(fileWithPath);
		}

		driver.findElement(applicaionWebElements.userName_WebEdit).sendKeys("appiumtest");
		Thread.sleep(2000);
		driver.findElement(applicaionWebElements.password_WebEdit).sendKeys("Password@123");
		fileWithPath = BaseUtil.takeSnapShot(driver);
		if(driver.findElement(applicaionWebElements.userName_WebEdit).getAttribute("value").contentEquals("appiumtest")) {
			test.pass("User successfully entered the login details in login page").addScreenCaptureFromPath(fileWithPath);	
		}else
		{
			test.fail("User failed to enter the login details in login page").addScreenCaptureFromPath(fileWithPath);
		}
		
		
		WebElement element = driver.findElement(applicaionWebElements.login_WebButton);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		fileWithPath = BaseUtil.takeSnapShot(driver);
		if(driver.findElement(applicaionWebElements.userName_lebel).getText().contentEquals("appiumtest")) {
			test.pass("Successfully verified that user has loggin in the application").addScreenCaptureFromPath(fileWithPath);	

		}else {
			test.fail("User failed to login in the application").addScreenCaptureFromPath(fileWithPath);
		}


	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
		extent.flush();
	}
}

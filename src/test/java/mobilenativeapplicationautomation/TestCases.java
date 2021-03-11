package mobilenativeapplicationautomation;

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
import io.appium.java_client.AppiumDriver;
import managers.FileReaderManager;

public class TestCases {
	AppiumDriver<WebElement> driver;
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
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/reports/"+"SwaglabsTestResults.html");
		htmlReporter.config().setDocumentTitle("Test report for Swaglabs Test");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test report");
		extent = new ExtentReports();
		extent.setSystemInfo("os", "Android");
		extent.setSystemInfo("DeviceName", "Android GoogleAPI Emulator");
		extent.attachReporter(htmlReporter);
		
		//Invoking the driver appium driver instance from WebDriverFactory class
		driver = WebDriverFactory.getNativeAppDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test
	public void loginTest() throws Exception {
		
		test = extent.createTest("SwaglabsApplication_LoginValidation");
		
		fileWithPath = BaseUtil.takeSnapShot(driver);
		
		if(driver.findElementByAccessibilityId("test-Username").isDisplayed()) {
			test.pass("Application launched successfully and user is on home page").addScreenCaptureFromPath(fileWithPath);	
		}else
		{
			test.fail("User failed to launch the application").addScreenCaptureFromPath(fileWithPath);
		}

		driver.findElementByAccessibilityId("test-Username").sendKeys("standard_user");
		driver.findElementByAccessibilityId("test-Password").sendKeys("secret_sauce");
		
		fileWithPath = BaseUtil.takeSnapShot(driver);
		if(driver.findElementByAccessibilityId("test-Username").getText().contentEquals("standard_user")) {
			test.pass("User successfully entered the login details in login page").addScreenCaptureFromPath(fileWithPath);	
		}else
		{
			test.fail("User failed to enter the login details in login page").addScreenCaptureFromPath(fileWithPath);
		}
		
		driver.findElementByAccessibilityId("test-LOGIN").click();
		
		
		fileWithPath = BaseUtil.takeSnapShot(driver);
		if(driver.findElementByAccessibilityId("test-Menu").isDisplayed()) {
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

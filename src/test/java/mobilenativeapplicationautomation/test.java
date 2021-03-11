package mobilenativeapplicationautomation;
import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class test {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		//protected AppiumDriver driver = null;
		//AndroidDriver androidDriver = (AndroidDriver) driver;
		//IOSDriver iosDriver = (IOSDriver) driver;

		// or assign correct (iOS/Android) driver with driver start
		//driver = new IOSDriver<>(new URL(url), capabilities);

		DesiredCapabilities caps = DesiredCapabilities.android();
		/*
		 * caps.setCapability("appiumVersion", "1.20.2");
		 * caps.setCapability("deviceName","Android GoogleAPI Emulator");
		 * caps.setCapability("deviceOrientation", "portrait");
		 * caps.setCapability("browserName", "");
		 * caps.setCapability("platformVersion","10.0");
		 * caps.setCapability("platformName","Android");
		 * //caps.setCapability("app","storage:filename=ShareMe_v1.28.27.apk");
		 * caps.setCapability("app",
		 * "storage:filename=Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
		 */
		caps.setCapability("deviceName", "Android Emulator");
		caps.setCapability("platformName", "Android");
		caps.setCapability("browserName", "");
		caps.setCapability("platformVersion", "8.0");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
		caps.setCapability("app", "storage:filename=Android.SauceLabs.Mobile.Sample.app.2.7.1.apk"); //changed
        //driver = new AndroidDriver(url, caps); // changed, removed APPIUM and replaced with url
		
		AppiumDriver<WebElement> driver = new AndroidDriver<WebElement>( new URL("https://appium_automation:7cdd23c6-a003-4324-b2f9-fa1626e325ee@ondemand.us-west-1.saucelabs.com:443/wd/hub"), caps);
	
		//driver = new IOSDriver<>(new URL("http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.us-west-1.saucelabs.com/wd/hub"), capabilities);
		//WebDriver driver = new Android
		//driver.launchApp();
Thread.sleep(3000);
		
//driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("SauceIsAwesome@email.com");
driver.findElementByAccessibilityId("test-Username").sendKeys("standard_user");
driver.findElementByAccessibilityId("test-Password").sendKeys("secret_sauce");
driver.findElementByAccessibilityId("test-LOGIN").click();
System.out.println(driver.findElementByAccessibilityId("test-Menu").isDisplayed());


		//WebElement emailInput = driver.findElement(By.id("user-name"));
		//emailInput.sendKeys("SauceIsAwesome@email.com");
		//assertEquals(emailInput.getText(), "SauceIsAwesome@email.com");

		//driver.quit();

	}

}

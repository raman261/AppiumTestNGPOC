package base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class WebDriverFactory{

	private static WebDriver webDriver;
	private static AppiumDriver<WebElement> nativeAppdriver;

	//Method to create the MobileBrowser driver object with all required capabilities
	private static void setMobileBrowserDriver() throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		//device infor need to be added here
		capabilities.setCapability("deviceName", "Android GoogleAPI Emulator");
		capabilities.setCapability("platformVersion", "10.0");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome"); //
		capabilities.setCapability(CapabilityType.VERSION, "5.1");
		
		                                      //Remote address provided by Cloud execution provider(saucelabs in this case)
		webDriver = new RemoteWebDriver( new URL("https://appium_automation:7cdd23c6-a003-4324-b2f9-fa1626e325ee@ondemand.us-west-1.saucelabs.com:443/wd/hub"), capabilities);

	}

	//Method to create the Appiumdriver object with all required capabilities
	private static void setNativeAppDriver() throws MalformedURLException {

		DesiredCapabilities caps = DesiredCapabilities.android();
		caps.setCapability("deviceName", "Android GoogleAPI Emulator");
		caps.setCapability("platformName", "Android");
		caps.setCapability("browserName", "");
		caps.setCapability("platformVersion", "10.0");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
		//file name for application uploaded in saucelabs is provided in provided in this app capability
		caps.setCapability("app", "storage:filename=Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        //driver = new AndroidDriver(url, caps); // changed, removed APPIUM and replaced with url
		
		nativeAppdriver = new AndroidDriver<WebElement>( new URL("https://appium_automation:7cdd23c6-a003-4324-b2f9-fa1626e325ee@ondemand.us-west-1.saucelabs.com:443/wd/hub"), caps);
	
	}
	
	//getter method to provide the Mobile browser webdriver instance for other classes
	public static WebDriver getMobileBrowserDriver() throws MalformedURLException
	{
		if(webDriver == null) {
			setMobileBrowserDriver();
			return webDriver;
		}else {
			return webDriver;	
		}
	}
	
	//getter method to provide the Appiumdriver instance for other classes
		public static AppiumDriver getNativeAppDriver() throws MalformedURLException
		{
			if(nativeAppdriver == null) {
				setNativeAppDriver();
				return nativeAppdriver;
			}else {
				return nativeAppdriver;	
			}
		}


}
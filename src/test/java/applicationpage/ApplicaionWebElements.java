package applicationpage;

import org.openqa.selenium.By;

//Page object model class for demoQA application webelements
public class ApplicaionWebElements {
		
	public By elementsOption_Link = By.xpath("(//div[contains(@class,'avatar')])[1]");
	public By bookStoreApplication_Link = By.xpath("//div[text()='Book Store Application']");
	public By login_Link = By.xpath("//span[text()='Login']");
	public By userName_WebEdit = By.xpath("//input[@id='userName']");
	public By password_WebEdit = By.xpath("//input[@id='password']");
	public By login_WebButton = By.xpath("//button[@id='login']");
	public By userName_lebel = By.xpath("(//label[@class='form-label'])[3]");
	
}

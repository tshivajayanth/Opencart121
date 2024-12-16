package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	//Constructor
	public HomePage(WebDriver driver) 
	{
		super(driver);//passing driver to parent page(base page)
	}
	
	//xpath
	@FindBy(xpath="//span[normalize-space()='My Account']")	
	WebElement lnkMyaccount;
	
	//@FindBy(xpath="//a[normalize-space()='Register']")
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	//action methods
	public void clickMyAccount() 
	{
		lnkMyaccount.click();
	}
	public void clickRegister() 
	{
		lnkRegister.click();
	}
	public void clickLogin() 
	{
		lnkLogin.click();
	}
	
}

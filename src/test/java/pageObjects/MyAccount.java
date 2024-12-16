package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage{
	
	public MyAccount(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement lnklogout;	
	
	public boolean isMyAccountPageExists() 
	{
		try 
		{
			return (msgHeading.isDisplayed());
		} 
		catch (Exception e) 
		{
			return false;
		}
	
		
	}
	public void clickLogout() {
		lnklogout.click();
	}
	
	
}

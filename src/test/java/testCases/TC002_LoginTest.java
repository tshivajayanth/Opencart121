package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"Sanity","Master"})
	public void verifyLogin() 
	{
		logger.info("**Starting TC002_LoginRegistration**");
		
		try {
			//HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			//LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			//My Account
			MyAccount ma = new MyAccount(driver);
			boolean targetPage = ma.isMyAccountPageExists();
			
			Assert.assertTrue(targetPage);
			//Assert.assertEquals(targetPage, true);
		} 
		catch (Exception e) 
		{
			Assert.fail();
		}
		logger.info("**Finished TC002_LoginRegistration**");
		
		
		
		
		
	}
}

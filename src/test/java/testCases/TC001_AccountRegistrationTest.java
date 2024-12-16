package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() 
	{
		logger.info("** Starting TC001_AccountRegistrationTest **"); //log4j2
		
		try { 
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				logger.info("** Clicked on MyAccount Link**");
				hp.clickRegister();
				logger.info("** Clicked on Register Link **");
				
				AccountRegistrationPage reg = new AccountRegistrationPage(driver);
				
				logger.info("** Providing Customer Details **");
				reg.setFirstName(randomeString().toUpperCase());
				
				logger.info("** Providing Second Name Details **");
				reg.setLastName(randomeString().toUpperCase());
				reg.setEmail(randomeString()+"@gmail.com");
				reg.setTelephone(randomeNumber());
				
				String password = randomeAlphaNumeric();
				reg.setPassword(password);
				reg.setConfirmPassword(password);
				
				logger.info("** Validating Password Complete **");
				reg.setPrivacyPolicy();
				reg.clickContinue();
				logger.info("** Validating Expected Message **");
				
				String confmsg = reg.getConfirmationMsg();
				if (confmsg.equals("Your Account Has Been Created!")) 
				{
					Assert.assertTrue(true);
				} 
				else 
				{
					logger.error("Test Failed..");
					logger.debug("Debug Logs..");
					Assert.assertTrue(false);
				}
				Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		    }
			catch(Exception e) 
			{				
				Assert.fail();
			}
		logger.info("** Finished TC001_AccountRegistrationTest **"); //log4j2
	}
}

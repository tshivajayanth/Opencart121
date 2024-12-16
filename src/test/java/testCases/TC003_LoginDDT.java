package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") // getting data provider from different class
    public void verify_loginDDT(String email, String pwd, String exp) {
        logger.info("***** Starting TC_003_LoginDDT *****");

        try {
            // HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            // LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            // MyAccountPage
            MyAccount macc = new MyAccount(driver);
            boolean targetPage = macc.isMyAccountPageExists();

            /*
             * Data is valid - login success - test pass - logout
             * 					login failed - test fail
             * 
             *  Data is invalid - login success - test fail - logout
             *  					login failed - test pass
             * */
            
            // Check the expected result based on 'exp' (Valid/Invalid)
            if (exp.equalsIgnoreCase("Valid")) 
            {
                if (targetPage==true) 
                {
                    macc.clickLogout();
                    Assert.assertTrue("Logout failed after valid login", true);
                } 
                else 
                {
                    Assert.assertTrue("Login failed for valid credentials.",false);
                }
            } 
             if (exp.equalsIgnoreCase("Invalid")) 
             {
                if (targetPage==true) 
                {
                    macc.clickLogout();
                    Assert.assertTrue("Login succeeded unexpectedly for invalid credentials.",false);
                } 
                else 
                {
                    Assert.assertTrue("Correct behavior for invalid login", true);
                }
            }



        } catch (Exception e) {
            // Log the exception and fail the test
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC_003_LoginDDT *****");
    }
}

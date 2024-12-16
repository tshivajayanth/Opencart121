package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException 
	{
		//loading config.properties file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
		    DesiredCapabilities capabilities = new DesiredCapabilities();

		    // os
		    if(os.equalsIgnoreCase("windows")) 
		    {
		        capabilities.setPlatform(Platform.WIN11);
		    } 
		    else if (os.equalsIgnoreCase("mac")) 
		    {
		        capabilities.setPlatform(Platform.MAC);
		    } 
		    else 
		    {
		        System.out.println("No matching os");
		        return;
		    }
		    
		 // browser
		    switch(br.toLowerCase()) 
		    {
		        case "chrome":
		            capabilities.setBrowserName("chrome");
		            break;
		        case "firefox":
		            capabilities.setBrowserName("firefox");
		            break;
		        case "edge":
		            capabilities.setBrowserName("MicrosoftEdge");
		            break;
		        default:
		            System.out.println("No matching browser");
		            return;
		    }
		    //launching browser
		    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		   
		}
		
		
			if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
			{
				switch (br.toLowerCase()) 
				{
					case "chrome": driver=new ChromeDriver();break;
					case "edge": driver=new EdgeDriver();break;
					case "firefox": driver=new FirefoxDriver();break;
					
					default:System.out.println("Invalid Browser Name..");
					return;
				}
			}
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://demo.opencart.com/en-gb?route=account/register");
		//driver.get("https://tutorialsninja.com/demo/");
		
		driver.get(p.getProperty("appURL1")); //reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown() 
	{
			driver.quit();
	}
	
	public String randomeString() 
	{
		String generatedstring =RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomeNumber() 
	{
		String generatednumber =RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomeAlphaNumeric() 
	{
		String generatedstring =RandomStringUtils.randomAlphabetic(3);
		String generatednumber =RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException {
	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath;
	}

}

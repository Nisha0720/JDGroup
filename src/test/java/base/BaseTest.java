package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	//setting up variables
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public static Properties prop = new Properties();
	public static Properties loc = new Properties();
	public static FileReader fr ;
	public static FileReader fr1 ;
	
	//While testing a single class/test we will use @BeforeClass annotation. For running suite we will use @BeforeSuite
	@BeforeSuite
	//@BeforeClass
	
	//This method is reading the config file and locator file and we will startup appium server in this method
	public void configureAppium() throws URISyntaxException, IOException
	{
		if(service == null) {
			System.out.print("service is starting");
			//Reading prop file and config file
			fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\configfiles\\config.properties");		
			fr1 = new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\configfiles\\locators.properties");
			prop.load(fr);
			loc.load(fr1);
		
			//Setup appium service start
			service = new AppiumServiceBuilder().withAppiumJS( new File(prop.getProperty("appiumlocation")))
					.withIPAddress("127.0.0.1").usingPort(4723).build();
			//service.start();	
		}
	}

	//This method is to setup the web driver, chrome driver has been downloaded and chrome browser setup has been done here, will do firefox, edge and other browser later
	@BeforeMethod
	public void configureDriver() throws URISyntaxException, IOException
	{	     
		//Setup device
	    UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("devicename"));
		
		//Setup web driver
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.out.print("inside chrome");
			//Setup chrome driver
			options.setChromedriverExecutable(prop.getProperty("chromedriverlocation"));
			options.setCapability("browserName", "Chrome");
			driver = new AndroidDriver(new URI(prop.getProperty("appiumserverurl")).toURL() , options);
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			System.out.print("inside else");
			//Setup for firefox driver will do in future
		}
	}

	//Tear down method to shut chrome driver
	@AfterMethod
	public void tearDown() 
	{	
		driver.quit();
	}
	
	//Tear down method to to stop appium server, will use @AfterClass notation to test class, while running suite we will use @AfterSuite notation
	//@AfterClass
	@AfterSuite
	public void tearDownService() 
	{
		//service.stop();
	}
}

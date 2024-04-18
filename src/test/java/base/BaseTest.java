package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public static Properties prop = new Properties();
	public static Properties loc = new Properties();
	public static FileReader fr ;
	public static FileReader fr1 ;
	@BeforeClass
	public void configureAppium() throws URISyntaxException, IOException
	{
		if(service == null) {
			System.out.print("driver is null");
			fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\configfiles\\config.properties");
			
			fr1 = new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\configfiles\\locators.properties");
			prop.load(fr);
			loc.load(fr1);
		}
		//Setup appium service start
	     service = new AppiumServiceBuilder().withAppiumJS( new File(prop.getProperty("appiumlocation")))
		.withIPAddress("127.0.0.1").usingPort(4723).build();
	     service.start();
	    //Setup appium service end
	     
	}

	@BeforeMethod
	public void configureDriver() throws URISyntaxException, IOException
	{	     
		//Setup device start
	    UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("devicename"));

		
		//Setup device end
		

		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.out.print("inside chrome");
			options.setChromedriverExecutable(prop.getProperty("chromedriverlocation"));
			options.setCapability("browserName", "Chrome");
			driver = new AndroidDriver(new URI(prop.getProperty("appiumserverurl")).toURL() , options);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			System.out.print("inside else");
			//Setup for firefox driver will do in future
		}
		

	}

	@AfterMethod
	public void tearDown() 
	{	
		driver.quit();
	}
	
	@AfterClass
	public void tearDownService() 
	{
		service.stop();
	}

}

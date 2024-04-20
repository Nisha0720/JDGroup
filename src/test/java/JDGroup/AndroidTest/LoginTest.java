package JDGroup.AndroidTest;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.readXlsxData;
//This class is to test the login functionality, both failed attempt and pass attempt is in the same class and same method
public class LoginTest extends BaseTest{
	//For the test data is being read from another class present in utilities and the dataprovider name is testdata.
	@Test( dataProviderClass = readXlsxData.class, dataProvider = "testdata" , retryAnalyzer = utilities.Retry.class)
	//This method is to test the login functionality, both failed attempt and pass attempt is in the same class and same method
	public void Login(String username , String password) throws InterruptedException
	{
		//opening the JD group app url
		driver.get(prop.getProperty("testingurl1"));
		
		//click and enter userid
		driver.findElement(By.name(loc.getProperty("userid"))).click();
		driver.findElement(By.name(loc.getProperty("userid"))).clear();
		driver.findElement(By.name(loc.getProperty("userid"))).sendKeys(username);
		
		//click and enter password
		driver.findElement(By.name(loc.getProperty("userpassword"))).click();
		driver.findElement(By.name(loc.getProperty("userpassword"))).clear();
		driver.findElement(By.name(loc.getProperty("userpassword"))).sendKeys(password);
		
		//click loginbutton
		driver.findElement(By.name(loc.getProperty("loginbutton"))).click();
		
		//putting some lag for browser to respond
		Thread.sleep(4000);
		
		//setting up a variable to check if error login text has appeared during signin process
		List<WebElement> dynamicElement =  driver.findElements(By.id(loc.getProperty("loginErrorMessage")));
		
		//if the size of above variable is greater than 0 then there is an error in signin process, if size is 0 its a success
		if (dynamicElement.size() >0) 
		{
			//Validating the error text 
			Assert.assertEquals(driver.findElement(By.id(loc.getProperty("loginErrorMessage"))).getText(), loc.getProperty("loginErrorMessagetext"));
			System.out.print("Passed: login unsuccessful");
		}
		else
		{
			System.out.print("Passed: login successful");
			//wait for 10 secs
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			Thread.sleep(4000);
			//click on FAQ after successful login
		    driver.findElement(By.xpath(loc.getProperty("faqbutton"))).click();
		    //click on Orders after successful login and from FAQ page
		    driver.findElement(By.xpath(loc.getProperty("orderbutton"))).click();
		    //click on Buy Now
		    driver.findElement(By.xpath(loc.getProperty("buynowbutton"))).click();  
		    //Assert a text present in Buy Now page
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			Thread.sleep(4000);
		    Assert.assertEquals(driver.findElement(By.xpath(loc.getProperty("searchcustomertext"))).getText(), "Search Customer");
		    //logout
		    driver.findElement(By.xpath(loc.getProperty("logout"))).click();
			System.out.print("Passed: All done");
		}		
	}
}

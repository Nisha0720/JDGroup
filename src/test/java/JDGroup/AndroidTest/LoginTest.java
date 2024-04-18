package JDGroup.AndroidTest;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.readXlsxData;

public class LoginTest extends BaseTest{
	@Test( dataProviderClass = readXlsxData.class, dataProvider = "testdata")
	public void browserTest(String username , String password) throws InterruptedException
	{
		driver.get("https://isa-qa.jdg.co.za");
		driver.findElement(By.name(loc.getProperty("userid"))).click();
		driver.findElement(By.name(loc.getProperty("userid"))).clear();
		driver.findElement(By.name(loc.getProperty("userid"))).sendKeys(username);
		
		driver.findElement(By.name(loc.getProperty("userpassword"))).click();
		driver.findElement(By.name(loc.getProperty("userpassword"))).clear();
		driver.findElement(By.name(loc.getProperty("userpassword"))).sendKeys(password);
		
		driver.findElement(By.name(loc.getProperty("loginbutton"))).click();
		
		Thread.sleep(4000);
		
		List<WebElement> dynamicElement =  driver.findElements(By.id(loc.getProperty("loginErrorMessage")));
		if (dynamicElement.size() >0) 
		{
			Assert.assertEquals(driver.findElement(By.id(loc.getProperty("loginErrorMessage"))).getText(), "Incorrect username or password.");
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
		    Assert.assertEquals(driver.findElement(By.xpath(loc.getProperty("searchcustomertext"))).getText(), "Search Customer");
		    //logout
		    driver.findElement(By.xpath(loc.getProperty("logout"))).click();
			System.out.print("Passed: All done");
		}		
	}
	

	
//	@DataProvider( name="testdata" )
//	public Object[][] testData()
//	{
//		return new Object[][] {
//			{"abcd","1234"},
//			{"test_agent@jdg.co.za","TestingUser_2024"}
//			
//		};
//	}
	

}

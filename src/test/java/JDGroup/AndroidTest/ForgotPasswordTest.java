package JDGroup.AndroidTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.readXlsxData;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordTest extends BaseTest{
	@Test
	//( dataProviderClass = readXlsxData.class, dataProvider = "testdata")
	//public void browserTest(String username , String password) throws InterruptedException
	public void browserTest() throws InterruptedException
	{
		//driver.get("https://isa-qa.jdg.co.za");
		
		
		//WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.nsg-button"))).click();
		//WebElement forgotpassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[2]/following::a[1]")));
		
		//driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[2]/following::a[1]")).click();
		//Thread.sleep(4000);
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://jdgisa-dev.auth.eu-west-1.amazoncognito.com/login?client_id=1e1j37l42tdffipiehud35shv2&client_secret=1o0ojqbl10qdt9jq8vs0tlpbrtlpkdev7iucak9lvl0telgd1hsp&scope=email+openid+phone+profile&response_type=code&redirect_uri=https://isa-qa.jdg.co.za/login");
		Thread.sleep(4000);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("abc@123.com");
	    Thread.sleep(4000);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	    driver.findElement(By.name("reset_my_password")).click();
	    
	    driver.findElement(By.id("forgot_password_code")).click();
	    driver.findElement(By.id("forgot_password_code")).clear();
	    driver.findElement(By.id("forgot_password_code")).sendKeys("365738");
	    

	    driver.findElement(By.id("new_password")).click();
	    driver.findElement(By.id("new_password")).clear();
	    driver.findElement(By.id("new_password")).sendKeys("Abc1234@ijklMno");
	    driver.findElement(By.id("confirm_password")).click();
	    driver.findElement(By.id("confirm_password")).clear();
	    driver.findElement(By.id("confirm_password")).sendKeys("Abc1234@ijklMno");
	    driver.findElement(By.name("reset_password")).click();
	    //driver.findElement(By.id("errorMessage")).click();
		
		Thread.sleep(4000);
		
		List<WebElement> dynamicElement =  driver.findElements(By.id(loc.getProperty("errorMessage")));
		if (dynamicElement.size() >0) 
		{
			Assert.assertEquals(driver.findElement(By.id(loc.getProperty("errorMessage"))).getText(), "Invalid verification code provided, please try again.");
			System.out.print("Passed: forgot password successful");
		}
	
	}
}

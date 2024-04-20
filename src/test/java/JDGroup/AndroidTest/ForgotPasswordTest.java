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
//This class is to test forgot password 
public class ForgotPasswordTest extends BaseTest{
	@Test( dataProviderClass = readXlsxData.class, dataProvider = "testdata" , retryAnalyzer = utilities.Retry.class)
	//This method is to implement forgot password testing
	public void Forgotpassword(String userid , String oldpassword, String newpassword) throws InterruptedException
	{
		//opening the JD group app url
		driver.get(prop.getProperty("testingurl1"));
		//putting some lag so that driver can open properly
		Thread.sleep(4000);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		//click on forgot password link
		driver.findElement(By.xpath(loc.getProperty("forgotpasswordlink"))).click();
		Thread.sleep(4000);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		//click and enter userid
	    driver.findElement(By.id(loc.getProperty("resetpasswordusername"))).click();
	    driver.findElement(By.id(loc.getProperty("resetpasswordusername"))).clear();
	    driver.findElement(By.id(loc.getProperty("resetpasswordusername"))).sendKeys(userid);
	    Thread.sleep(4000);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		//click and send reset my password
	    driver.findElement(By.name(loc.getProperty("reset_my_password"))).click();
	    
	    //click and send code
	    driver.findElement(By.id(loc.getProperty("forgot_password_code"))).click();
	    driver.findElement(By.id(loc.getProperty("forgot_password_code"))).clear();
	    driver.findElement(By.id(loc.getProperty("forgot_password_code"))).sendKeys(oldpassword);
	    
	    //click and send new password
	    driver.findElement(By.id(loc.getProperty("new_password"))).click();
	    driver.findElement(By.id(loc.getProperty("new_password"))).clear();
	    driver.findElement(By.id(loc.getProperty("new_password"))).sendKeys(newpassword);
	    
	    //click and set new password again
	    driver.findElement(By.id(loc.getProperty("confirm_password"))).click();
	    driver.findElement(By.id(loc.getProperty("confirm_password"))).clear();
	    driver.findElement(By.id(loc.getProperty("confirm_password"))).sendKeys(newpassword);
	    
	    //click on reset password button
	    driver.findElement(By.name(loc.getProperty("reset_password"))).click();
		Thread.sleep(4000);
		
		System.out.print(driver.findElement(By.id((loc.getProperty("errorMessage")))).getText());
		//Checking that the error message for wrong code is same in test
		Assert.assertEquals(driver.findElement(By.id((loc.getProperty("errorMessage")))).getText(), loc.getProperty("invalid_code_text"));
		System.out.print("Passed: forgot password successful");

	
	}
}

package com.selenium.qa.set4npl.empsuplier.fundreq;

import java.awt.AWTException;
import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.qa.Login;
import com.selenium.qa.set4npl.objects.HandlingBankobjects;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.AllureTestNg;
import utils.ConfigReader;
@Listeners({AllureTestNg.class})
public class T02_HANDLINGBANK_TEST extends Login{
	
	HandlingBankobjects 	HandlingBankobjects ;
	String date=ConfigReader.get("date");
	  public String userDir = System.getProperty("user.dir");
	    public String filePath = userDir + File.separator + "pdffolder" + File.separator + "compat.pdf";
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver=initializeBrowserAndOpenApplication(ConfigReader.get("browser"));
		driver=loginAs("handlingbank");
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    HandlingBankobjects=new HandlingBankobjects(driver);
		
	}
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	public void acceptsubsidy() throws InterruptedException
	{
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.findElement(By.xpath("//button[@class='flex items-center gap-1']")).click();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
		Thread.sleep(2000);
			driver.findElement(By.xpath("//button[.='Eligible']")).click();
			;
			js.executeScript("document.body.style.zoom = '75%';");
			Thread.sleep(2000);
			WebElement modalSection = wait.until(
	        ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@data-slot='card'])[4]")));
	        Actions actions = new Actions(driver);
	        
	        // Click on the modal section to focus it, then scroll
	         actions.moveToElement(modalSection).click().perform();
			 js.executeScript("arguments[0].scrollTop += 600;", modalSection);
			 Thread.sleep(1000);
			 WebElement uploadfile=driver.findElement(By.id("upload-handlingBankAgreementDocument")); 
			
		     uploadfile.sendKeys(filePath);
	         Thread.sleep(2000);
	         driver.findElement(By.xpath("//button[.='Accept']")).click();
			 Thread.sleep(2000);
			 
		//	wait.until(ExpectedConditions.elementToBeClickable())
	}
	public void incompletesubsidy() throws InterruptedException
	
	{
		  driver.findElement(By.xpath("//button[@class='flex items-center gap-1']")).click();
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
			Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[.='Incomplete']")).click();
		js.executeScript("document.body.style.zoom = '75%';");
		
		 
		WebElement modalSection = wait.until(
             ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@data-slot='card'])[4]")));
     Actions actions = new Actions(driver);
     
     // Click on the modal section to focus it, then scroll
     actions.moveToElement(modalSection).click().perform();
     js.executeScript("arguments[0].scrollTop += 600;", modalSection);
     driver.findElement(By.xpath("//textarea")).sendKeys("not completed");
     Thread.sleep(2000);
     driver.findElement(By.xpath("//button[.='Confirm']")).click();
     Thread.sleep(2000);
     
	}	
	public void rejectsubsidy() throws InterruptedException 
	{

		driver.findElement(By.xpath("//button[@class='flex items-center gap-1']")).click();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[.='Reject']")).click();
		 Thread.sleep(2000);
		 
		js.executeScript("document.body.style.zoom = '75%';");
		WebElement modalSection = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@data-slot='card'])[4]")));
        Actions actions = new Actions(driver);
        
        // Click on the modal section to focus it, then scroll
        
        actions.moveToElement(modalSection).click().perform();
        driver.findElement(By.xpath("//textarea")).sendKeys("rejected");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[.='Reject'])[2]")).click();
        Thread.sleep(2000);
	}
	public void reportsubsidy() throws InterruptedException

	{
		HandlingBankobjects.clickacceptedbutton();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(By.xpath("(//button[@class='flex items-center gap-1'])[1]")).click();
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='fundTransferDate']")).sendKeys(date);
		Thread.sleep(2000);
		driver.findElement(By.id("upload-bankTransaction")).sendKeys(filePath);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[.='Submit Report']")).click();
		Thread.sleep(2000);
		
	}
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=6,description="make submitted subsidy as eligible",groups="accessories")
	public void elegiblereq() throws InterruptedException, AWTException
	{
		
		// Step 1: Capture the first chassis before action
		WebElement firstSubmittedId = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
		String oldChassis = firstSubmittedId.getText();
		System.out.println("Old Chassis: " + oldChassis);

		
		acceptsubsidy();

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.stalenessOf(firstSubmittedId)); // wait until old row is gone

		
		WebElement newFirstRow = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
		String newChassis = newFirstRow.getText();
		System.out.println("New Chassis: " + newChassis);

	
		Assert.assertNotEquals(newChassis, oldChassis, 
		    "❌ Submitted subsidy is not accepted! Old chassis still present.");
		System.out.println("✅ Old chassis is removed after subsidy acceptance.");

		
	}
	@Severity(SeverityLevel.CRITICAL)
	   @Test(priority = 7,description="make submitted subsidy as incomplete")
	public void incompletereq() throws InterruptedException
	{
		// Step 1: Capture the first chassis before action
			WebElement firstSubmittedId = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
			String oldChassis = firstSubmittedId.getText();
			System.out.println("Old Chassis: " + oldChassis);

			
			incompletesubsidy();

			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.stalenessOf(firstSubmittedId)); // wait until old row is gone

			
			WebElement newFirstRow = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
			String newChassis = newFirstRow.getText();
			System.out.println("New Chassis: " + newChassis);

		
			Assert.assertNotEquals(newChassis, oldChassis, 
			    "❌ Submitted subsidy is not incompleted! .");
			System.out.println("✅ sucessful incomplete"); 
		
		
	}	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 8,description="make submitted subsidy as rejected")

	public void rejected() throws InterruptedException
	{
		WebElement firstSubmittedId = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
		String oldChassis = firstSubmittedId.getText();
		System.out.println("Old Chassis: " + oldChassis);

		
		incompletesubsidy();

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.stalenessOf(firstSubmittedId)); // wait until old row is gone

		
		WebElement newFirstRow = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
		String newChassis = newFirstRow.getText();
		System.out.println("New Chassis: " + newChassis);

	
		Assert.assertNotEquals(newChassis, oldChassis, 
		    "❌ Submitted subsidy is not rejected .");
		System.out.println("✅ sucessful rejection"); 
	}	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=9,description="make submitted subsidy as reported",groups="accessories")
	public void reportreq() throws InterruptedException

	{
		HandlingBankobjects.clickacceptedbutton();
		WebElement firstSubmittedId = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
		String oldChassis = firstSubmittedId.getText();
		System.out.println("Old Chassis: " + oldChassis);
		reportsubsidy();
		HandlingBankobjects.clickacceptedbutton();
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//	wait.until(ExpectedConditions.stalenessOf(firstSubmittedId)); // wait until old row is gone
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@role='tablist']/button[3]")).click();
		WebElement newFirstRow = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
		String newChassis = newFirstRow.getText();
		System.out.println("New Chassis: " + newChassis);	
		Assert.assertNotEquals(newChassis, oldChassis, 
		    "❌ Submitted subsidy is not reported .");
		System.out.println("✅ sucessfully reported"); 
		
	
		
	}

	


}

package com.selenium.qa.set4npl.empsuplier.fundreq;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.qa.Login;

public class practice extends Login{
	
	WebDriver driver;
	String date="20062025";
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	String userDir = System.getProperty("user.dir");
    String filePath = userDir + "\\pdffolder\\compat.pdf";
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver=initializeBrowserAndOpenApplication("edge");
		driver=loginAs("handlingbnk");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='flex items-center gap-1']")).click();
		Thread.sleep(1000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
		Thread.sleep(2000);
	}
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void elegiblereq() throws InterruptedException, AWTException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	
		driver.findElement(By.xpath("//button[.='Eligible']")).click();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
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
		 String userDir = System.getProperty("user.dir");
	     String filePath1 = userDir + "\\pdffolder\\compat.pdf";
	     uploadfile.sendKeys(filePath1);
         Thread.sleep(2000);
         driver.findElement(By.xpath("//button[.='Accept']")).click();
		 Thread.sleep(2000);
	//	wait.until(ExpectedConditions.elementToBeClickable())
		
	}
	   //@Test(priority = 2)
	public void incompletereq() throws InterruptedException
	{
		driver=loginAs("handlingbnk");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='flex items-center gap-1']")).click();
		Thread.sleep(1000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
		Thread.sleep(1000);
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.findElement(By.xpath("//button[.='Incomplete']")).click();
		 Thread.sleep(2000);
		 
		js.executeScript("document.body.style.zoom = '75%';");
		WebElement modalSection = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@data-slot='card'])[4]")));
        Actions actions = new Actions(driver);
        
        // Click on the modal section to focus it, then scroll
        actions.moveToElement(modalSection).click().perform();
        driver.findElement(By.xpath("//textarea")).sendKeys("not completed");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[.='Confirm']")).click();
        Thread.sleep(2000);
        
		
		
	}
	//@Test(priority = 3)

	public void rejected() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.findElement(By.xpath("//button[.='Reject']")).click();
		 Thread.sleep(2000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
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
	//@Test(priority=4)
	public void reportreq() throws InterruptedException
	{
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver=loginAs("handlingbnk");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//div[@role='tablist']/button[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@class='flex items-center gap-1'])[1]")).click();
		Thread.sleep(1000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='fundTransferDate']")).sendKeys(date);
		Thread.sleep(2000);
		driver.findElement(By.id("upload-bankTransaction")).sendKeys(filePath);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[.='Submit Report']")).click();
		Thread.sleep(2000);
		

		
	
		
	}

}

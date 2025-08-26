package com.selenium.qa;

import java.io.ObjectInputFilter.Config;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

public class Login extends Base{
	 public WebDriverWait wait;
	
	
	
	public WebDriver loginAs(String role)
	{
		 wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		if(role.equals("supplier"))
		{
		//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
		   String suppliermail=ConfigReader.get("supplieremail");
		    emailField.sendKeys(suppliermail);
			// driver.findElement(By.xpath("//input[@name='email']")).sendKeys("tester1@yopmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConfigReader.get("password"));
			
		}
		else if (role.equals("handlingbank"))
				{
			WebElement hemailField = wait.until(
				    ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='email']"))
				);
		
			// WebElement hemailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
			 String handlingemail=ConfigReader.get("handlingbankemail");
			 hemailField.click();
			hemailField.sendKeys(handlingemail);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConfigReader.get("password"));
			
				}
		else if(role.equals("admin"))
		{
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']"))).sendKeys(ConfigReader.get("adminemail"));
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConfigReader.get("password"));
			
		}
		else if(role.equals("adminview"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']"))).sendKeys(ConfigReader.get("adminvieweremail"));
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConfigReader.get("password"));
			
		}
		else if(role.equals("partnerbank"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']"))).sendKeys(ConfigReader.get("partnerbankemail"));
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConfigReader.get("password"));
			
		}
		 
		
		 
		
		driver.findElement(By.xpath("//button[.='Login']")).click();
		return driver;
		
		
	}

}

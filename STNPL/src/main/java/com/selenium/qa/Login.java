package com.selenium.qa;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends Base{
	 public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	
	
	
	public WebDriver loginAs(String role)
	{
		if(role.equals("supplier"))
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		    WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
		   
		    emailField.sendKeys("sandipsapkota7171@gmail.com");
			// driver.findElement(By.xpath("//input[@name='email']")).sendKeys("tester1@yopmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Admin@123");
			
		}
		else if (role.equals("handlingbnk"))
				{
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("nabil@yopmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Admin@123");
			
				}
		else if(role.equals("admin"))
		{
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("set4npl@yopmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Admin@123");
			
		}
		else if(role.equals("adminview"))
		{
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("adminviewer@yopmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Admin@123");
			
		}
		 
		driver.findElement(By.xpath("//button[.='Login']")).click();
		return driver;
		
		
	}

}

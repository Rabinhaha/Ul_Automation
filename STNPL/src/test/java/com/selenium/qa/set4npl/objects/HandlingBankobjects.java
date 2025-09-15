package com.selenium.qa.set4npl.objects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingBankobjects
{
	  private WebDriver driver;
	    private JavascriptExecutor js;
	    private WebDriverWait wait;
	    
	    // Constructor
	    public HandlingBankobjects(WebDriver driver) {
	        this.driver = driver;
	        this.js = (JavascriptExecutor) driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }
	    
	    
	private By  acceptedbutton= By.xpath("//div[@role='tablist']/button[3]") ;
	   private By submittedIdFirstRow=By.xpath("//table/tbody/tr[1]/td[3]");
	    // Methods for Basic Information
	    
	    
	    public String submittedFirstRow()
	    {
	    	WebElement id=driver.findElement(submittedIdFirstRow);
	    	String getid=id.getText();
	    	return getid;
	    }
	public void clickacceptedbutton()
	{
		driver.findElement(acceptedbutton).click();
	}
	    
	    
}

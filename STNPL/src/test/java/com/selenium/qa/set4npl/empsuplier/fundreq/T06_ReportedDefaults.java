package com.selenium.qa.set4npl.empsuplier.fundreq;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.qa.Login;
import com.selenium.qa.set4npl.objects.GuaranteeClaims;
import com.selenium.qa.set4npl.objects.ReportedDefaults;

import utils.ConfigReader;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

public class T06_ReportedDefaults extends Login {

    private WebDriver driver;
    private ReportedDefaults reportedDefaults;
    private GuaranteeClaims guaranteeFlow;
    private String userDir = System.getProperty("user.dir");
    private String filePath = userDir + File.separator + "pdffolder" + File.separator + "compat.pdf";

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = initializeBrowserAndOpenApplication("firefox");
        driver = loginAs("partnerbank");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        reportedDefaults = new ReportedDefaults(driver);
        guaranteeFlow = new GuaranteeClaims(driver);
    }
   
    @Test(priority=0, description="reported susidy to reported defaults")
    public void ReportedDefaultFlow() throws InterruptedException, AWTException {
   	 

        if (guaranteeFlow.hasPreInstalledNotReported()) {
            guaranteeFlow.clickEyeForPreInstalledNotReported();;
       // guaranteeFlow.clickEyeForPreInstalledNotReportedWithPagination();
        	
            
        } else {
            System.out.println("⚠️ No rows found with Pre-Installed + Not Reported");
        }
        guaranteeFlow.defaultBtn();
        guaranteeFlow.checkBox();
        guaranteeFlow.totalLoan();
        guaranteeFlow.defaultAmount();
        guaranteeFlow.selectDueDate(ConfigReader.get("date"));  
        guaranteeFlow.checkBox2();
        Thread.sleep(1000);
        guaranteeFlow.nextBtn();

        guaranteeFlow.rePaymentFile(filePath);
        Thread.sleep(1000);
        guaranteeFlow.previewButton();
        guaranteeFlow.submitBtn();
    
       //,dependsOnMethods="ReportedDefaultFlow"

    }

    @Test(priority = 1, description = "Open Reported Defaults and do gurantee claim flow")
    public void openReportedDefaultsAndDoGuranteeClaim() throws InterruptedException {

        Thread.sleep(2000);
        reportedDefaults.clickReportedDefaultsButton();
        Thread.sleep(2000);
        reportedDefaults.clickEyeButton();
        Thread.sleep(5000);
        reportedDefaults.clickNewGuaranteeClaimButton();
        reportedDefaults.clickPartnerBankCheckBox();
        reportedDefaults.publicAmount();
        reportedDefaults.checkBox2();
        reportedDefaults.claimedAmount();
        reportedDefaults.nextBtn();
        Thread.sleep(100);
        // Upload multiple files
        reportedDefaults.uploadFileById("upload-affidavit", "compat.pdf");
        Thread.sleep(1000);
        reportedDefaults.uploadFileById("upload-effortToRecoverOutstandingLoanAmount", "compat.pdf");
        Thread.sleep(1000);
        reportedDefaults.uploadFileById("upload-badDebtDeclaration", "compat.pdf");
        Thread.sleep(1000);
        reportedDefaults.uploadFileById("upload-optionalDocument", "compat.pdf");
        Thread.sleep(1000);
        reportedDefaults.previewBtn();
        Thread.sleep(1000);
        reportedDefaults.submitBtn();
        Thread.sleep(1500);

        
     
        
         }
    
    @Test(priority = 2, description = "Open Reported Defaults and do Immobiliser flow")
    
    public void immobilizerFlow () throws InterruptedException, AWTException {
    	 driver.findElement(By.xpath("//button[@role='combobox']")).sendKeys("1");
    	 Robot robot = new Robot();
         robot.keyPress(KeyEvent.VK_ENTER);   
         robot.keyRelease(KeyEvent.VK_ENTER);
    	  reportedDefaults.clickReportedDefaultsButton();
    	  reportedDefaults.clickEyeButton();
    	  reportedDefaults.immobilizerBtn();
    	  Thread.sleep(2000);
    	  reportedDefaults.selectDate(ConfigReader.get("date"));
    	  Thread.sleep(2000);
    	  reportedDefaults.comments();
    	  reportedDefaults.nextBtnForImmob();
    	  reportedDefaults.previewBtn2();
    	  Thread.sleep(1000);
    	  reportedDefaults.submitBtn2();
    	  reportedDefaults.clickPartnerBankArrow(); 
      	  Thread.sleep(2000); 
      	  reportedDefaults.logoutText();
      	  Thread.sleep(5000); 
      	 
      	    }
    
    @Test
    public void handlingBankForwardCase() throws InterruptedException {
        reportedDefaults.clickPartnerBankArrow();
        Thread.sleep(2000);
        reportedDefaults.logoutText();
        Thread.sleep(5000);

        loginAs("handlingbank");
        reportedDefaults.guranteeClaimBtn();
        Thread.sleep(2000);
        reportedDefaults.clickeyeButton();
        Thread.sleep(2000);
        reportedDefaults.forwardBtn();
        Thread.sleep(2000);
        reportedDefaults.amountProvided();
        Thread.sleep(2000);
        reportedDefaults.commentHere();
        reportedDefaults.uploadFileById("upload-handlingBankAgreementDocument", "compat.pdf");
        Thread.sleep(1000);
    }


    
    
    
    

   
}

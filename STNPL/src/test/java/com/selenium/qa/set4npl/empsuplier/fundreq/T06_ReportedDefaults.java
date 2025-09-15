package com.selenium.qa.set4npl.empsuplier.fundreq;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.qa.Login;
import com.selenium.qa.set4npl.objects.ReportedDefaults;

import utils.ConfigReader;

import java.time.Duration;

public class T06_ReportedDefaults extends Login {

    private WebDriver driver;
    private ReportedDefaults reportedDefaults;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = initializeBrowserAndOpenApplication("firefox");
        driver = loginAs("partnerbank");
        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        reportedDefaults = new ReportedDefaults(driver);
    }

    @Test(priority = 0, description = "Open Reported Defaults and do gurantee claim flow")
    public void openReportedDefaultsAndDoGuranteeClaim() throws InterruptedException {

        
        reportedDefaults.clickReportedDefaultsButton();
        reportedDefaults.clickEyeButton();
        reportedDefaults.clickNewGuaranteeClaimButton();
        reportedDefaults.clickPartnerBankCheckBox();
        reportedDefaults.publicAmount();
        reportedDefaults.checkBox2();
        reportedDefaults.claimedAmount();
        reportedDefaults.nextBtn();
        Thread.sleep(1000);
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
        
         }
    
    @Test(priority = 1, description = "Open Reported Defaults and do gurantee claim flow")
    
    public void immobilizerFlow () throws InterruptedException {
    	
    	  reportedDefaults.clickReportedDefaultsButton();
    	  reportedDefaults.clickEyeButton();
    	  reportedDefaults.immobilizerBtn();
    	  Thread.sleep(2000);
    	  reportedDefaults.selectDate(ConfigReader.get("date"));
    	  Thread.sleep(2000);
    	  reportedDefaults.uploadFileByIdInImmobilizer("upload-optionalDocument","compat.pdf");
    	  Thread.sleep(2000);
    	  reportedDefaults.previewBtn2();
    	  Thread.sleep(1000);
    	  reportedDefaults.submitBtn2();
    	  
    	  }
    
    @Test(description = "Verify a specific Loan ID appears in Submitted tab")
    public void verifySpecificLoanId() {
        String expectedLoanId = "4565165312";   // change if needed
        reportedDefaults.guranteeMenu();
        Assert.assertTrue(
        		reportedDefaults.isLoanIdPresent(expectedLoanId),
            "Loan ID " + expectedLoanId + " was NOT found in Submitted tab!"
        );
    }
    
    
    
    

   
}

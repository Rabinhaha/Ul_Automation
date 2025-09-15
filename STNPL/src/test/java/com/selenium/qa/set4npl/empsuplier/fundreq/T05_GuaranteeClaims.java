package com.selenium.qa.set4npl.empsuplier.fundreq;

import java.awt.AWTException;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.qa.Login;
import com.selenium.qa.set4npl.objects.GuaranteeClaims;

import utils.ConfigReader;

public class T05_GuaranteeClaims extends Login {

    private WebDriver driver;
    private GuaranteeClaims guaranteeFlow;
    private String userDir = System.getProperty("user.dir");
    private String filePath = userDir + File.separator + "pdffolder" + File.separator + "compat.pdf";

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = initializeBrowserAndOpenApplication("chrome");
        driver = loginAs("partnerbank");
        guaranteeFlow = new GuaranteeClaims(driver);
    }

    @Test
    public void testGuaranteeFlow() throws InterruptedException, AWTException {
    	 
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
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
        	    currentUrl.endsWith("/embs"),
        	    "Expected URL to end with /embs but got: " + currentUrl
        	);
       
    }
}

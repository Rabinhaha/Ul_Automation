package com.selenium.qa.set4npl.empsuplier.fundreq;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.qa.Login;
import com.selenium.qa.set4npl.objects.GuaranteeClaims;

public class T05_GuaranteeClaims extends Login {

    private WebDriver driver;
    private GuaranteeClaims guaranteeFlow;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = initializeBrowserAndOpenApplication("firefox");
        driver = loginAs("partnerbank");
        guaranteeFlow = new GuaranteeClaims(driver);
    }

    @Test(priority = 1,description = "partnerbank >> Embs >> pre-installed and do its flow  ")
    public void testGuaranteeFlow() throws InterruptedException {
        if (guaranteeFlow.hasPreInstalledNotReported()) {
            guaranteeFlow.clickAllMatchingEyes();
        } else {
            System.out.println("⚠️ No rows found with Pre-Installed + Not Reported");
        }
        guaranteeFlow.defaultBtn();
        guaranteeFlow.checkBox();
        guaranteeFlow.totalLoan();
        guaranteeFlow.defaultAmount();
        guaranteeFlow.selectDueDate("01-02-2025");  // must be yyyy-MM-dd format
        guaranteeFlow.checkBox2();
        guaranteeFlow.nextBtn();
        
    }
}

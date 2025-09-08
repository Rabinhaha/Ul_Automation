package com.selenium.qa.set4npl.empsuplier.fundreq;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.qa.Login;
import com.selenium.qa.set4npl.objects.AccessioriesSubsidy;

public class T03_AccessioriesSubsidy extends Login {

    private WebDriver driver;
    private AccessioriesSubsidy accessioriesFlow;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = initializeBrowserAndOpenApplication("firefox");
        driver = loginAs("supplier");
        accessioriesFlow = new AccessioriesSubsidy(driver);
    }

    public void addAccessoriesFlowForLast() throws InterruptedException {
        // Open Fund Request → Reported
        accessioriesFlow.clickFundRequestBtn();
        Thread.sleep(2000);
        accessioriesFlow.clickReportBtn();
        Thread.sleep(2000);

        // Click Next until last page
        while (true) {
            List<WebElement> nextBtn = driver.findElements(By.xpath("//button[contains(text(),'Next') and not(@disabled)]"));
            if (!nextBtn.isEmpty()) {
                WebElement btn = nextBtn.get(0);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
                Thread.sleep(500);
                btn.click();
                System.out.println("➡️ Clicked Next");
                Thread.sleep(2000);
            } else {
                System.out.println("✅ Reached last page.");
                break;
            }
        }

        // Get last Not Installed row
        List<WebElement> notInstalledRows = driver.findElements(
            By.xpath("//td//span[normalize-space(text())='Not Installed']/ancestor::tr")
        );

        if (notInstalledRows.isEmpty()) {
            System.out.println("⚠️ No 'Not Installed' rows found on last page.");
            return;
        }

        WebElement lastRow = notInstalledRows.get(notInstalledRows.size() - 1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", lastRow);
        Thread.sleep(500);
        lastRow.findElement(By.xpath("./td[7]")).click();
        Thread.sleep(2000);

        if (accessioriesFlow.isAccessoriesRequestAlreadySent()) {
            System.out.println("⚠️ Accessories request already sent for last row. Skipping.");
            return;
        }

 
        Thread.sleep(500);
        accessioriesFlow.addAccessories();
        Thread.sleep(1000);
        accessioriesFlow.eTicket();
        accessioriesFlow.gps();
        accessioriesFlow.immobilizer();
        accessioriesFlow.LED();
        Thread.sleep(1000);
        accessioriesFlow.next();
        Thread.sleep(1000);
        accessioriesFlow.uploadFileById("upload-installedLEDPanelPhoto", "compat.pdf");
        accessioriesFlow.uploadFileById("upload-accessoriesPaymentProof", "compat.pdf");
        accessioriesFlow.uploadFileById("upload-accessoriesRequestLetter", "compat.pdf");
        accessioriesFlow.preview();
        Thread.sleep(1000);
        accessioriesFlow.Submit();
        Thread.sleep(1000);
        accessioriesFlow.embAccessories();

        System.out.println("✅ Successfully submitted Accessories request for last Not Installed row on last page.");
    }

    @Test(priority = 4, description = "Add Accessories for last Not Installed row on last page")
    public void TC_addAccessories() throws InterruptedException {
        addAccessoriesFlowForLast();
    }
}

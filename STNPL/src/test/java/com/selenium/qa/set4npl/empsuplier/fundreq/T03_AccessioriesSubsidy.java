package com.selenium.qa.set4npl.empsuplier.fundreq;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.qa.Login;
import com.selenium.qa.set4npl.objects.AccessioriesSubsidy;

public class T03_AccessioriesSubsidy extends Login {

    private WebDriver driver;
    private AccessioriesSubsidy accessioriesFlow;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = initializeBrowserAndOpenApplication("firefox");
        driver = loginAs("supplier");
        accessioriesFlow = new AccessioriesSubsidy(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addAccessoriesFlowForLast() throws InterruptedException {
        // Open Fund Request → Reported
        accessioriesFlow.clickFundRequestBtn();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Report')]")));
        accessioriesFlow.clickReportBtn();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table")));

        // Click Next until last page
        while (true) {
            List<WebElement> nextBtn = driver.findElements(
                By.xpath("//button[contains(text(),'Next') and not(@disabled)]"));
            if (!nextBtn.isEmpty()) {
                WebElement btn = nextBtn.get(0);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
                wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
                System.out.println("➡️ Clicked Next");
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table")));
            } else {
                System.out.println("✅ Reached last page.");
                break;
            }
        }

        // Get last Not Installed row
        List<WebElement> notInstalledRows = driver.findElements(
            By.xpath("//td//span[normalize-space(text())='Not Installed']/ancestor::tr"));

        if (notInstalledRows.isEmpty()) {
            System.out.println("⚠️ No 'Not Installed' rows found on last page.");
            return;
        }

        WebElement lastRow = notInstalledRows.get(notInstalledRows.size() - 1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", lastRow);
        wait.until(ExpectedConditions.elementToBeClickable(lastRow.findElement(By.xpath("./td[7]")))).click();

        if (accessioriesFlow.isAccessoriesRequestAlreadySent()) {
            System.out.println("⚠️ Accessories request already sent for last row. Skipping.");
            return;
        }

        accessioriesFlow.addAccessories();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("upload-installedLEDPanelPhoto")));
        accessioriesFlow.eTicket();
        accessioriesFlow.gps();
        accessioriesFlow.immobilizer();
        accessioriesFlow.LED();
        accessioriesFlow.next();

        accessioriesFlow.uploadFileById("upload-installedLEDPanelPhoto", "compat.pdf");
        accessioriesFlow.uploadFileById("upload-accessoriesPaymentProof", "compat.pdf");
        accessioriesFlow.uploadFileById("upload-accessoriesRequestLetter", "compat.pdf");
        accessioriesFlow.preview();
        accessioriesFlow.Submit();
        accessioriesFlow.embAccessories();

        System.out.println("✅ Successfully submitted Accessories request for last Not Installed row on last page.");
    }

    @Test(priority = 4, description = "Add Accessories for last Not Installed row on last page")
    public void TC_addAccessories() throws InterruptedException {
        addAccessoriesFlowForLast();
    }
}

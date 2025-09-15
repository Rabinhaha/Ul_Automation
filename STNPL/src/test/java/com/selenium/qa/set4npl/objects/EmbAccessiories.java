package com.selenium.qa.set4npl.objects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmbAccessiories {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public EmbAccessiories(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void teardown() {
        if (driver != null) driver.quit();
    }

    // Locators
    private By embAccessoriesBtn = By.xpath("//a[text()='EMB Accessories']");
    private By eyeBtn = By.xpath("//*[name()='svg' and contains(@class,'lucide-eye')]/ancestor::button[1]");
    private By eligibleBtn = By.xpath("//button[contains(text(),'Eligible')]");
    private By incompleteBtn = By.xpath("//button[contains(text(),'Incomplete')]");
    private By commentHere = By.xpath("//textarea[@name='comments']");
    private By acceptBtn = By.xpath("//button[contains(text(),'Accept')]");
    private By confirmBtn = By.xpath("//button[contains(text(),'Confirm')]");
    private By rejectBtnLocator = By.xpath("//button[contains(text(),'Reject')]");

    // Click EMB Accessories link
    public void clickEmbAccessiories() {
        driver.findElement(embAccessoriesBtn).click();
    }

    // Click eye button and scroll to bottom
    public void clickEyeBtn() throws InterruptedException {
        driver.findElement(eyeBtn).click();
        Thread.sleep(2000);
        // Scroll to bottom of the new page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Eligible flow
    public void eligibleBtn() {
        driver.findElement(eligibleBtn).click();
    }

    public void commentHere() throws InterruptedException {
        driver.findElement(commentHere).sendKeys("eligible");
        Thread.sleep(2000);
    }

    public void acceptBtn() {
        driver.findElement(acceptBtn).click();
    }

    // Incomplete flow
    public void incompleteBtn() {
        driver.findElement(incompleteBtn).click();
    }

    public void commentIncomplete() throws InterruptedException {
        driver.findElement(commentHere).sendKeys("incomplete");
        Thread.sleep(2000);
    }

    public void confirmBtn() {
        driver.findElement(confirmBtn).click();
    }

    // Reject flow: Click first Reject button (opens comment box)
    public void clickFirstRejectBtn() {
        List<WebElement> buttons = driver.findElements(rejectBtnLocator);
        for (WebElement btn : buttons) {
            if (btn.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                break;
            }
        }
    }

    // Reject flow: Click second Reject button (confirm)
    public void clickSecondRejectBtn() {
        List<WebElement> buttons = driver.findElements(rejectBtnLocator);
        int count = 0;
        for (WebElement btn : buttons) {
            if (btn.isDisplayed()) {
                count++;
                if (count == 2) { // pick the second visible button
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                    break;
                }
            }
        }
    }

    public void commentReject() throws InterruptedException {
        driver.findElement(commentHere).sendKeys("reject");
        Thread.sleep(2000);
    }

    // File upload
    public void uploadFileById( String fileName) throws InterruptedException {
        Thread.sleep(1000);
        WebElement uploadFile = driver.findElement(By.id("upload-handlingBankAgreementDocument"));
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + "\\pdffolder\\" + fileName;
        uploadFile.sendKeys(filePath);
        Thread.sleep(2000);
        // Scroll to bottom after uploading
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
}

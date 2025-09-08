package com.selenium.qa.set4npl.objects;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ConfigReader;

public class AccessioriesSubsidy {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public AccessioriesSubsidy(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void teardown() {
        if (driver != null) driver.quit();
    }

 

    // Locators
    private By fundRequestBtn = By.xpath("//a[contains(text(),'Fund Request')]");
    private By clickReportBtn = By.xpath("//button[contains(text(), 'Reported')]");
    private By addAccessories = By.xpath("//button[normalize-space()='Add Accessories']");
    private By alertAccessoriesAlreadySent = By.xpath("//div[contains(text(),'Accessories request has already been sent!')]");
    private By comboboxBtn = By.xpath(
            "//button[@role='combobox' and @data-slot='select-trigger' " +
            "and starts-with(@aria-controls,'radix-')][.//span[@data-slot='select-value']]"
    );

    // Other locators
    private By eTicket = By.xpath("//input[@name='eTicketingSerial']");
    private By gps = By.xpath("//input[@name='gpsDeviceSerial']");
    private By immobilizer = By.xpath("//input[@name='immobilizerSerial']");
    private By LED = By.xpath("//input[@name='ledPanelSerial']");
    private By next = By.xpath("//button[contains(text(),'Next')]");
    private By preview = By.xpath("//button[contains(text(),'Preview')]");
    private By Submit = By.xpath("//button[contains(text(),'Submit')]");
    private By embAccessories = By.xpath("//a[@href='/emb-accessories']");

    // Methods
    public void clickFundRequestBtn() { driver.findElement(fundRequestBtn).click(); }
    public void clickReportBtn() { driver.findElement(clickReportBtn).click(); }

    public void addAccessories() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addAccessories));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            try { btn.click(); } catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn); }
            System.out.println("✅ Clicked on Add Accessories button");
        } catch (Exception e) {
            throw new RuntimeException("❌ Add Accessories button not found or not clickable: " + e.getMessage());
        }
    }

    public boolean isAccessoriesRequestAlreadySent() {
        try {
            WebElement alert = driver.findElement(alertAccessoriesAlreadySent);
            return alert.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    // Accessories input methods
    public void eTicket() { driver.findElement(eTicket).sendKeys(utils.TestDataGenerator.getRandometicketing()); }
    public void gps() { driver.findElement(gps).sendKeys(utils.TestDataGenerator.getRandomegps()); }
    public void immobilizer() { driver.findElement(immobilizer).sendKeys(utils.TestDataGenerator.getRandomemmobiliser()); }
    public void LED() { driver.findElement(LED).sendKeys(utils.TestDataGenerator.getRandomLed(5)); }
    public void next() { driver.findElement(next).click(); }
    public void preview() { driver.findElement(preview).click(); }
    public void Submit() { driver.findElement(Submit).click(); }
    public void embAccessories() { driver.findElement(embAccessories).click(); }

    public void uploadFileById(String elementId, String fileName) throws InterruptedException {
        Thread.sleep(1000);
        WebElement uploadFile = driver.findElement(By.id(elementId));
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + "\\pdffolder\\" + fileName;
        uploadFile.sendKeys(filePath);
        Thread.sleep(2000);
    }
}

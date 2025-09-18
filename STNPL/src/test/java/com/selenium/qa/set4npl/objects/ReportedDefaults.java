package com.selenium.qa.set4npl.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class ReportedDefaults {

    private WebDriver driver;
    private WebDriverWait wait;

    public ReportedDefaults(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By reportedBtn = By.xpath("//a[contains(text(),'Reported Defaults')]");
    private By tableRows = By.xpath("//table/tbody/tr");
    private By newGuaranteeClaimBtn = By.xpath("//button[contains(text(),'New Guarantee Claim')]");
    private By partnerBankCheckBox = By.id("partnerBank");
    private By opa = By.xpath("//input[@name='outstandingPrincipalAmount']");
    private By checkBox2 = By.id("badDebtDeclaration");
    private By claimAmount = By.xpath("//input[@name='claimedAmount']");
    private By next = By.xpath("//button[@type='submit']");
    private By previewBtn = By.xpath("//button[contains(text(),'Preview')]");
    private By submitBtn = By.xpath("//button[contains(text(),'Submit')]");

    // Click the Reported Defaults button smoothly
    public void clickReportedDefaultsButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(reportedBtn));
        driver.findElement(reportedBtn).click();
        Thread.sleep(2000); // wait for page to load
    }

    // Click the eye button in the second row and scroll after clicking
    public void clickEyeButton() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

        List<WebElement> rows = driver.findElements(tableRows);

        if (!rows.isEmpty()) {
            // Get the last row (last index = size - 1)
            WebElement lastRow = rows.get(rows.size() - 1);
            
            By eyeBtnInLastRow = By.xpath(".//*[name()='svg' and contains(@class,'lucide-eye')]/ancestor::button[1]");
            WebElement eyeButton = lastRow.findElement(eyeBtnInLastRow);

            wait.until(ExpectedConditions.elementToBeClickable(eyeButton));
            eyeButton.click();

            Thread.sleep(2000); // wait for modal/dialog to appear
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2000);"); // scroll after click
        }
    }

    // Click the "New Guarantee Claim" button
    public void clickNewGuaranteeClaimButton() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(newGuaranteeClaimBtn).click();
    }

    // Check the Partner Bank checkbox
    public void clickPartnerBankCheckBox() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(partnerBankCheckBox).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1500);"); // scroll after click
    }
    
    public void publicAmount() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(opa).sendKeys("20000000");
    }
    
    public void checkBox2() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(checkBox2).click();
    }
    
    public void claimedAmount() {
        driver.findElement(claimAmount).sendKeys("200000");
    }
    
    public void nextBtn() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(next).click();
    }

    public void uploadFileById(String elementId, String fileName) {
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + File.separator + "pdffolder" + File.separator + fileName;

        By locator = By.id(elementId);

        WebElement uploadInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.display='block'; arguments[0].style.visibility='visible';", uploadInput);

        uploadInput.sendKeys(filePath);
    }

    public void previewBtn() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2000);");
        driver.findElement(previewBtn).click();
    }
    
    public void submitBtn() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2000);");
        driver.findElement(submitBtn).click();
    }
    
    // locators for immobilizer request
    private By requestBtn = By.xpath("//button[contains(text(),'New Immobilizer Request')]");
    private By dateInput = By.name("reportedDate");
    private By comment=By.name("remark");
    private By previewBtn2 = By.xpath("//button[contains(text(),'Preview')]");
    private By submitBtn2 = By.xpath("//button[contains(text(),'Submit')]");
    private By nextBtn=By.xpath("//button[.='Next']");
    
    // Method
    public void nextBtnForImmob() {
        driver.findElement(nextBtn).click();
    }
   
    public void immobilizerBtn() {
        driver.findElement(requestBtn).click();
    }
    
    public void selectDate(String date) {
        WebElement dataField = wait.until(ExpectedConditions.elementToBeClickable(dateInput));
        dataField.sendKeys(date);
    }
    
    public void comments() {
        driver.findElement(comment).sendKeys("hahahahahaha");
    }
    
    public void uploadFileByIdInImmobilizer(String elementId, String fileName) {
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + "\\pdffolder\\" + fileName;

        WebElement uploadInput = driver.findElement(By.id(elementId));
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].style.display='block'; arguments[0].style.visibility='visible';", uploadInput);

        uploadInput.sendKeys(filePath);
    }
    
    public void previewBtn2() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2000);");
        driver.findElement(previewBtn2).click();
        WebElement previewField = wait.until(ExpectedConditions.elementToBeClickable(dateInput));
        previewField.click();
    }
    
    public void submitBtn2() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2000);");
        driver.findElement(submitBtn2).click();
    }
    
    private By guaranteeClaimsMenu = By.xpath("//a[contains(text(),'Guarantee Claims')]");
    
    public void guranteeMenu() {
        driver.findElement(guaranteeClaimsMenu).click();
    }
    
    public boolean isLoanIdPresent(String loanId) {
        By loanCell = By.xpath("//table/tbody/tr/td[normalize-space()='" + loanId + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody")));
        return !driver.findElements(loanCell).isEmpty();
    }
    
    // Updated arrowBtn() only
    private By svgLocator =  By.xpath("//span[normalize-space()='PARTNER BANK']");
    private By logOutBtn = (By.xpath("//div[normalize-space()='Logout']\r\n"
    		+ ""));

    public void clickPartnerBankArrow() {
        driver.findElement(svgLocator).click();
    }


    
    public void logoutText() {
        wait.until(ExpectedConditions.elementToBeClickable(logOutBtn));
        driver.findElement(logOutBtn).click();
    }
    
    
    //locators for handlingbank
    
    private By guaranteeClaim = By.xpath("//a[contains(text(),'Guarantee Claims')]");
    private By tableRowsInHandlingBAnk = By.xpath("//table/tbody/tr");
    private By forwardBtn = By.xpath("//button[contains(text(),'Forward')]");
    private By amountToProvided = By.xpath("//input[@name='providedGuaranteeAmount']");
    private By commentHere = By.xpath("//textarea[@name='comments']");
    
    
    
    public void guranteeClaimBtn() {
    	driver.findElement(guaranteeClaim).click();
    }
    
    public void clickeyeButton() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRowsInHandlingBAnk));

        List<WebElement> rows = driver.findElements(tableRows);

        if (!rows.isEmpty()) {
            // Get the last row (last index = size - 1)
            WebElement lastRow = rows.get(rows.size() - 1);
            
            By eyeBtnInLastRow = By.xpath(".//*[name()='svg' and contains(@class,'lucide-eye')]/ancestor::button[1]");
            WebElement eyeButton = lastRow.findElement(eyeBtnInLastRow);

            wait.until(ExpectedConditions.elementToBeClickable(eyeButton));
            eyeButton.click();

            Thread.sleep(2000); // wait for modal/dialog to appear
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2000);"); // scroll after click
        }
    }
    
    public void forwardBtn() {
    	driver.findElement(forwardBtn).click();
    }
    
    public void amountProvided() {
    	driver.findElement(amountToProvided).sendKeys("2000");
    }
    
    public void commentHere() {
    	driver.findElement(commentHere).sendKeys("hahahahahahaha");
    }
    
    
    
    
    
   
    
    
    
    
}

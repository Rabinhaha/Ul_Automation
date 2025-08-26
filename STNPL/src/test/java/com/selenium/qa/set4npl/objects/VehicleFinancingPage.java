package com.selenium.qa.set4npl.objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

public class VehicleFinancingPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    
    // Constructor
    public VehicleFinancingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }
    
    // Locators for Vehicle Financing Section
    private By loanRadio = By.xpath("(//div[@data-slot='radio-group'])[2]/div[1]/button[1]");
    private By interestRateField = By.xpath("//input[@name='interestRate']");
    private By loanNumberField = By.xpath("//input[@name='loanNumber']");
    private By bankCompanyDropDown = By.xpath("(//button[@type='button'])[9]");
    private By partnerBankBranchField = By.xpath("//input[@name='partnerBankBranch']");
    private By hirePurchaseRadio = By.xpath("(//div[@data-slot='radio-group'])[2]/div[2]/button[1]");
    private By fullPaymentRadio = By.xpath("(//div[@data-slot='radio-group'])[2]/div[3]/button[1]");
    private By vehicleCostField = By.xpath("(//input[@value=''])[1]");
  //  private By bankhirepurchasedropdown=By.xpath("(//button[@type='button'])[9]/following-sibling::select[1]");
    private By equityamountfield = By.xpath("//input[@name='partnerBankBranch']/../following-sibling::div[1]/input");
    // Methods for Vehicle Financing
    public void enterinterestrate()
    {
    	 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(interestRateField));
    	 element.sendKeys(ConfigReader.get("interestrate"));
    }
   
    	
    public void hirepurchasedropdown(String visibleText)
 {
        // 1️⃣ Click the dropdown button
        WebElement dropdownButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='button'])[9]"))
        );
        js.executeScript("arguments[0].click();", dropdownButton);

        // 2️⃣ Wait for the options to be rendered (adjust locator!)
        By optionLocator = By.xpath("//span[contains(text(),'" + visibleText + "')]");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));

        // 3️⃣ Click the option
        js.executeScript("arguments[0].click();", option);

        // 4️⃣ Verify selected value is shown in the dropdown
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
            By.xpath("(//button[@type='button'])[9]"),
            visibleText
        ));
    }





    
    public void selectLoanOption() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loanRadio));
        js.executeScript("arguments[0].click();", element);
    }
    
    public void enterInterestRate(String rate) {
        driver.findElement(interestRateField).sendKeys(rate);
    }
    
    public void enterLoanNumber(String loanNumber) {
        driver.findElement(loanNumberField).sendKeys(loanNumber);
    }
    
 
    
    public void enterPartnerBankBranch(String branchName) {
        driver.findElement(partnerBankBranchField).sendKeys(branchName);
    }
    
    public void selectHirePurchaseOption() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(hirePurchaseRadio));
        js.executeScript("arguments[0].click();", element);
    }
    
    public void selectFullPaymentOption() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(fullPaymentRadio));
        js.executeScript("arguments[0].click();", element);
    }
    
    public void enterVehicleCost(String cost) {
        driver.findElement(vehicleCostField).sendKeys(cost);
    }
    public void enterEquityAmount(String amount) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(equityamountfield));

        // Scroll into view
        js.executeScript("arguments[0].scrollIntoView(true);", field);

        // Click, clear, and type
        field.click();
        field.clear();
        field.sendKeys(amount);

        // Trigger JS events so frontend picks it up
        js.executeScript(
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            field
        );
    }

}
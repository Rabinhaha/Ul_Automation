package com.selenium.qa.set4npl.objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private By bankCompanyDropDown = By.xpath("(//button[@type='button'])[9]/following-sibling::select[1]");
    private By partnerBankBranchField = By.xpath("//input[@name='partnerBankBranch']");
    private By hirePurchaseRadio = By.xpath("(//div[@data-slot='radio-group'])[2]/div[2]/button[1]");
    private By fullPaymentRadio = By.xpath("(//div[@data-slot='radio-group'])[2]/div[3]/button[1]");
    private By vehicleCostField = By.xpath("//input[@value='']");
    
    // Methods for Vehicle Financing
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
    
    public void selectBankCompany(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(bankCompanyDropDown));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
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
}
package com.selenium.qa.set4npl.objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateFundRequestPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Constructor
    public CreateFundRequestPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    // Locators for Basic Information Section
    private By createFundRequestBtn = By.xpath("//button[.='Create Fund Request']");
    private By embModelDropDown = By.xpath("(//button[@type='button'])[1]/following-sibling::select[1]");
    private By chassisNumberField = By.xpath("//label[contains(text(), 'Vehicle ID (Chassis Number)')]/following-sibling::input[1]");
    private By firstNameField = By.xpath("//input[@name='firstName']");
    private By middleNameField = By.xpath("//input[@name='middleName']");
    private By lastNameField = By.xpath("//input[@name='lastName']");
    private By genderDropDown = By.xpath("(//button[@type='button'])[4]/following-sibling::select[1]");
    private By idTypeDropDown = By.xpath("(//button[@type='button'])[5]/following-sibling::select[1]");
    private By idNumberField = By.xpath("//input[@name='idNumber']");
    private By addressField = By.xpath("//input[@name='address']");
    
    // Methods for Basic Information
    public void clickCreateFundRequestButton() {
        driver.findElement(createFundRequestBtn).click();
    }
    
    public void selectEmbModel(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(embModelDropDown));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
    
    public void enterChassisNumber(String chassisNumber) {
        driver.findElement(chassisNumberField).sendKeys(chassisNumber);
    }
    
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }
    
    public void enterMiddleName(String middleName) {
        driver.findElement(middleNameField).sendKeys(middleName);
    }
    
    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    
    public void selectGender(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(genderDropDown));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
    
    public void selectIdType(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(idTypeDropDown));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
    
    public void enterIdNumber(String idNumber) {
        driver.findElement(idNumberField).sendKeys(idNumber);
    }
    
    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
}
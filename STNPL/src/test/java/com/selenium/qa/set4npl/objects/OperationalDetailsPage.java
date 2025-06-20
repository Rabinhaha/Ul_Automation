package com.selenium.qa.set4npl.objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperationalDetailsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Constructor
    public OperationalDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    // Locators for Operational Details Section
    private By scrappingYes = By.xpath("//button[@id='scrapping-yes']");
    private By ptoReplacingOldYes = By.id("yes");
    private By minibusRadio = By.id("old-minibus");
    private By microbusRadio = By.id("old-microbus");
    private By operatingDaysPerWeekField = By.xpath("//input[@name='operatingDaysPerWeek']");
    private By kmPerDayField = By.xpath("//input[@name='kmPerDay']");
    private By routeField = By.xpath("//input[@name='route']");
    private By employedPeopleField = By.xpath("//input[@name='employedPeople']");
    private By titleTransferDateField = By.xpath("//input[@name='titleTransferDate']");
    private By nextButton = By.xpath("//button[.='Next']");
    
    // Methods for Operational Details
    public void selectScrappingYes() {
        driver.findElement(scrappingYes).click();
    }
    
    public void selectPtoReplacingOldYes() {
        driver.findElement(ptoReplacingOldYes).click();
    }
    
    public void selectMinibusRadio() {
        driver.findElement(minibusRadio).click();
    }
    
    public void selectMicrobusRadio() {
        driver.findElement(microbusRadio).click();
    }
    
    public void enterOperatingDaysPerWeek(String days) {
        driver.findElement(operatingDaysPerWeekField).sendKeys(days);
    }
    
    public void enterKmPerDay(String km) {
        driver.findElement(kmPerDayField).sendKeys(km);
    }
    
    public void enterRoute(String route) {
        driver.findElement(routeField).sendKeys(route);
    }
    
    public void enterEmployedPeople(String people) {
        driver.findElement(employedPeopleField).sendKeys(people);
    }
    
    public void enterTitleTransferDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(titleTransferDateField));
        dateInput.sendKeys(date);
    }
    
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}
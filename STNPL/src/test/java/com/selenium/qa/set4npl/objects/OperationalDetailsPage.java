package com.selenium.qa.set4npl.objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.TestDataGenerator;

public class OperationalDetailsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;
    // Constructor
    public OperationalDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    // Locators for Operational Details Section
    private By scrappingYes = By.xpath("//button[@id='scrapping-yes']");
    private By scrappingNo = By.xpath("//button[@id='scrapping-no']");
    private By ptoReplacingOldYes = By.id("yes");
    
    private By minibusRadio = By.id("old-minibus");
    private By microbusRadio = By.id("old-microbus");
    private By operatingDaysPerWeekField = By.xpath("//input[@name='operatingDaysPerWeek']");
    private By kmPerDayField = By.xpath("//input[@name='kmPerDay']");
    private By routeField = By.xpath("//input[@name='route']");
    private By employedPeopleField = By.xpath("//input[@name='employedPeople']");
    private By titleTransferDateField = By.xpath("//input[@name='titleTransferDate']");
    private By nextButton = By.xpath("//button[.='Next']");
    private By covidno=By.id("covid-no");
    private By covidyes=By.id("covid-yes");
    private By PTOowing=By.id("owning-driving");
    private By ptoonlyowing=By.id("only-owning");
    private By acccesoriesyes=By.id("withAccessories-yes");
    private By eticketingmachinefield=By.xpath("//input[@name='accessories.eTicketingSerial']");
    private By eticketingmachinecheckbox=By.id("e-ticketing");
    private By gpscheckbox=By.id("gps-device");
    private By gpsmachinetextfield=By.xpath("//input[@name='accessories.gpsDeviceSerial']");
    private By immobilsercheckbox=By.id("immobilizer");
    private By immobilisertextfield=By.xpath("//input[@name='accessories.immobilizerSerial']");
    private By ledpanelcheckbox=By.id("led-panel");
    private By ledpaneltextfield=By.xpath("//input[@name='accessories.ledPanelSerial']");
    // Methods for Operational Details
    public void accesoriesyes()
    {
    	driver.findElement(acccesoriesyes).click();
    
    	 driver.findElement(eticketingmachinecheckbox).click();
    	 driver.findElement(eticketingmachinefield).sendKeys(TestDataGenerator.getRandometicketing());
    	
    	 driver.findElement(gpscheckbox).click();
    	 driver.findElement(gpsmachinetextfield).sendKeys(TestDataGenerator.getRandomegps());
    	 driver.findElement(immobilsercheckbox).click();
    	 driver.findElement(immobilisertextfield).sendKeys(TestDataGenerator.getRandomemmobiliser());
    
    	 driver.findElement(ledpanelcheckbox).click();
    	 driver.findElement(ledpaneltextfield).sendKeys(TestDataGenerator.getRandomLed(7));
    	
    	 
         
    }
    public void ptoowing() {
        driver.findElement(PTOowing).click();
    }
    public void ptoonlyowing() {
        driver.findElement(ptoonlyowing).click();
    }
    
    
    public void covidyes() {
        driver.findElement(covidyes).click();
    }
    public void covidno() {
        driver.findElement(covidno).click();
    }
    public void selectScrappingYes() {
        driver.findElement(scrappingYes).click();
    }
    public void selectScrappingNo() {
        driver.findElement(scrappingNo).click();
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
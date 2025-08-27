package com.selenium.qa.set4npl.objects;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUploadPage {
    
    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;
    
    // Constructor
    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    // Locators for File Upload Section
    private By ptoRegistrationCertificate = By.id("upload-ptoRegistrationCertificate");
    private By salesAgreement = By.id("upload-salesAgreement");
    private By transportManagementPermit = By.id("upload-transportManagementPermit");
    private By vendorIntimationLetter = By.id("upload-vendorIntimationLetter");
    private By signedMaintenanceContract = By.id("upload-signedMaintenanceContract");
    private By ptoIdDocument = By.id("upload-ptoIdDocument");
    private By stickerOnEMBPhoto = By.id("upload-stickerOnEMBPhoto");
    private By scrappingcertificate=By.id("upload-scrappingCertificate");
    private By invoicecharger=By.id("upload-chargerInvoice");
    private By optionalDocument = By.id("upload-optionalDocument");
    private By previewButton = By.xpath("//button[.='Preview']");
    private By submitButton = By.xpath("//button[.='Submit']");
    private By partnerintimationletter=By.id("upload-intimationLetter");
    private By uploadinstalledLEDPanelPhoto=By.id("upload-installedLEDPanelPhoto");
    private By  uploadaccessoriesPaymentProof=By.id("upload-accessoriesPaymentProof");
    private By uploadaccessoriesRequestLetter=By.id("upload-accessoriesRequestLetter");
    // Methods for File Upload
    public void uploadaccessoriesfiles(String filePath) throws InterruptedException {
        driver.findElement(uploadinstalledLEDPanelPhoto).sendKeys(filePath);
        Thread.sleep(1000);
        driver.findElement(uploadaccessoriesPaymentProof).sendKeys(filePath);
        Thread.sleep(1000);
        driver.findElement(uploadaccessoriesRequestLetter).sendKeys(filePath);
        Thread.sleep(1000);
        
        
    }
    public void uploadPtoRegistrationCertificate(String filePath) throws InterruptedException {
        driver.findElement(ptoRegistrationCertificate).sendKeys(filePath);
        Thread.sleep(1000);
    }
    public void uploadintimationletterpartner(String filePath) throws InterruptedException {
        driver.findElement(partnerintimationletter).sendKeys(filePath);
        Thread.sleep(1000);
    }
    
    
    public void uploadSalesAgreement(String filePath) throws InterruptedException {
        driver.findElement(salesAgreement).sendKeys(filePath);
        Thread.sleep(1000);
    }
    
    public void uploadTransportManagementPermit(String filePath) throws InterruptedException {
        driver.findElement(transportManagementPermit).sendKeys(filePath);
        Thread.sleep(1000);
    }
    
    public void uploadVendorIntimationLetter(String filePath) throws InterruptedException {
        driver.findElement(vendorIntimationLetter).sendKeys(filePath);
        Thread.sleep(1000);
    }
    
    public void uploadSignedMaintenanceContract(String filePath) throws InterruptedException {
        driver.findElement(signedMaintenanceContract).sendKeys(filePath);
        Thread.sleep(1000);
    }
    
    public void uploadPtoIdDocument(String filePath) throws InterruptedException {
        driver.findElement(ptoIdDocument).sendKeys(filePath);
        Thread.sleep(1000);
    }
    
    public void uploadStickerOnEMBPhoto(String filePath) throws InterruptedException {
        driver.findElement(stickerOnEMBPhoto).sendKeys(filePath);
        Thread.sleep(1000);
    }
    
    public void uploadOptionalDocument(String filePath) throws InterruptedException {
        driver.findElement(optionalDocument).sendKeys(filePath);
        Thread.sleep(1000);
    }
    
    public void uploadAllcommonRequiredFiles(String filePath) throws InterruptedException {
        uploadPtoRegistrationCertificate(filePath);
        uploadSalesAgreement(filePath);
        uploadTransportManagementPermit(filePath);
        js.executeScript("window.scrollBy(0, 350);");
        uploadVendorIntimationLetter(filePath);
        uploadSignedMaintenanceContract(filePath);
        uploadPtoIdDocument(filePath);
        uploadStickerOnEMBPhoto(filePath);
        js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
        Thread.sleep(1000);
        uploadOptionalDocument(filePath);
    }
    public void scrappingfileupload(String filePath) throws InterruptedException
    {
    	driver.findElement(scrappingcertificate).sendKeys(filePath);
    	Thread.sleep(2000);
    	
    }
 
    //public void up
    public void clickPreviewButton() throws InterruptedException {
    	WebElement previewBtn = wait.until(ExpectedConditions.elementToBeClickable(previewButton));
        System.out.println("Preview button found and clickable: " + previewBtn.isDisplayed()); // Debug
        previewBtn.click();
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
      //  wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
       
    }
    
    public void clickSubmitButton() throws InterruptedException {
        //js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
        
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}
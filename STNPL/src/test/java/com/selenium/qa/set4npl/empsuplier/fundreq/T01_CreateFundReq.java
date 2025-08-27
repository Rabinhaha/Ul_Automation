package com.selenium.qa.set4npl.empsuplier.fundreq;

import java.awt.AWTException;
import java.io.File;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.selenium.qa.Login;
import com.selenium.qa.set4npl.objects.CreateFundRequestPage;
import com.selenium.qa.set4npl.objects.FileUploadPage;

import com.selenium.qa.set4npl.objects.OperationalDetailsPage;
import com.selenium.qa.set4npl.objects.VehicleFinancingPage;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import utils.ConfigReader;
@Listeners(utils.AllureTestListener.class)
 class T01_CreateFundReqWithFullPayment extends Login {
    
    WebDriver driver;
    String insertedchassil;
    String date = ConfigReader.get("date");
   
    public String userDir = System.getProperty("user.dir");
    public String filePath = userDir + File.separator + "pdffolder" + File.separator + "compat.pdf";

    // Page Object instances
    CreateFundRequestPage createFundPage;
    VehicleFinancingPage vehicleFinancingPage;
    OperationalDetailsPage operationalDetailsPage;
    FileUploadPage fileUploadPage;
    JavascriptExecutor js;
  public void hundredperpayment_susidycreation_withoutaccesories() throws InterruptedException
  {
	  common();
      // Step 2: Vehicle Financing
    js.executeScript("window.scrollBy(0, 150);");
    vehicleFinancingPage.selectFullPaymentOption();
    Thread.sleep(500);
    operationalDetailsPage.selectScrappingYes();
    js.executeScript("window.scrollBy(0, 150);");
    vehicleFinancingPage.enterVehicleCost(ConfigReader.get("vehiclecost"));

    js.executeScript("window.scrollBy(0, 250);");
    
      operationalDetails();

    driver.findElement(By.id("no-charger")).click();
    
  
    operationalDetailsPage.enterTitleTransferDate(date);
    operationalDetailsPage.clickNextButton();
    
      // Step 4: File Upload
    fileUploadPage.uploadAllcommonRequiredFiles(filePath);
    fileUploadPage.scrappingfileupload(filePath);
    js.executeScript("window.scrollBy(0, 150);");
  
  
 
  }
  public void loan_withoutaccesories_subsidycreation() throws InterruptedException
  {
	  common();
      // Step 2: Vehicle Financing
    js.executeScript("window.scrollBy(0, 150);");
    vehicleFinancingPage.selectLoanOption();
    Thread.sleep(2000);
    vehicleFinancingPage.enterinterestrate();
    
    vehicleFinancingPage.enterVehicleCost(ConfigReader.get("vehiclecost"));
    vehicleFinancingPage.enterLoanNumber("5");
    Thread.sleep(2000);
    vehicleFinancingPage.hirepurchasedropdown("1-2-3");
   
    vehicleFinancingPage.enterPartnerBankBranch("NEPAL");
    Thread.sleep(2000);
    vehicleFinancingPage.enterEquityAmount("20000");;
    operationalDetailsPage.selectScrappingNo();
    operationalDetails();
    driver.findElement(By.id("no-charger")).click();
    operationalDetailsPage.enterTitleTransferDate(date);
    operationalDetailsPage.clickNextButton();
    fileUploadPage.uploadAllcommonRequiredFiles(filePath);
    fileUploadPage.uploadintimationletterpartner(filePath);
   
  }
  public void preview_and_submit() throws InterruptedException
  {
	  fileUploadPage.clickPreviewButton();
	    fileUploadPage.clickSubmitButton();
	    js.executeScript("window.scrollBy(0, 150);"); 
  }
    
    @BeforeMethod
    public void setup() {


        driver = initializeBrowserAndOpenApplication("chrome");
        driver = loginAs("supplier");
     
        int randomNum1 = ThreadLocalRandom.current().nextInt(1000, 10000);
        insertedchassil = "chassis01" + randomNum1;
        
        createFundPage = new CreateFundRequestPage(driver);
        vehicleFinancingPage = new VehicleFinancingPage(driver);
        operationalDetailsPage = new OperationalDetailsPage(driver);
        fileUploadPage = new FileUploadPage(driver);
        js = (JavascriptExecutor) driver;
    }
    
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
    public  void common() throws InterruptedException
    {
    	  
      
        Thread.sleep(500);
          // Step 1: Basic Information
        createFundPage.clickCreateFundRequestButton();
       
        createFundPage.selectEmbModel(1);
     
        createFundPage.enterChassisNumber(insertedchassil);
    
        createFundPage.enterFirstName(ConfigReader.get("firstname"));
        createFundPage.enterLastName(ConfigReader.get("lastname"));
        
        js.executeScript("window.scrollBy(0, 250);");
        
        createFundPage.selectGender(1);
        Thread.sleep(2000);
        createFundPage.selectIdType(1);
        Thread.sleep(2000);
        createFundPage.enterIdNumber("123san");
        Thread.sleep(2000);
        createFundPage.enterAddress("kathmandu");
    }
    public void operationalDetails()
    {
        // Step 3: Operational Details
        operationalDetailsPage.enterEmployedPeople("1"); js.executeScript("window.scrollBy(0, 150);");
        operationalDetailsPage.enterOperatingDaysPerWeek("2");
        js.executeScript("window.scrollBy(0, 150);");
        operationalDetailsPage.selectPtoReplacingOldYes();
        operationalDetailsPage.selectMicrobusRadio();
        operationalDetailsPage.selectPtoReplacingOldYes();
        operationalDetailsPage.covidno();
        operationalDetailsPage.ptoowing();
        operationalDetailsPage.enterKmPerDay("44");
        operationalDetailsPage.enterRoute("chit to ktm");
        js.executeScript("window.scrollBy(0, 150);");
        js.executeScript("window.scrollBy(0, 150);");
        
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 0, invocationCount = 1,description = "create subsidy req without accesories and with 100 payment and check subsidy is created ")
    public void CFR_001() throws InterruptedException, AWTException { 
          
    	hundredperpayment_susidycreation_withoutaccesories();
    	preview_and_submit();
    	checkwheathersusidycreated();
    }
    public void checkwheathersusidycreated()
    {
    	  WebElement fundRequestHeader = driver.findElement(By.xpath("//h2[.='Fund Request']"));

          // Assert that the element is visible
          Assert.assertTrue(fundRequestHeader.isDisplayed(), "fund couldnot submitted ");
    	  WebElement tableBody = driver.findElement(By.tagName("tbody"));
          List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
          WebElement lastRow = rows.get(rows.size() - 1);
          String chassisNo = lastRow.findElements(By.tagName("td")).get(2).getText();
          System.out.println("featchedchassisNo: "+chassisNo);  
          
          
          //String createdchassil= driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]")).getText();
          Assert.assertEquals(chassisNo,insertedchassil, "subsidy creation got failed ");
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1,description = "create subsidy with loan without accesories scrapping no")
    public void CFR_002() throws InterruptedException
    {
       loan_withoutaccesories_subsidycreation();
       preview_and_submit();
       checkwheathersusidycreated();

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2,description = "EMB is financed through Hire-Purchase and equity has been paid by PTO to Partner Bank and SCRAPPNG NO, accesories no")
    public void CFR_003() throws InterruptedException

    {
    	 common();
         // Step 2: Vehicle Financing
       js.executeScript("window.scrollBy(0, 150);");
       vehicleFinancingPage.selectHirePurchaseOption();
       Thread.sleep(2000);
       vehicleFinancingPage.enterinterestrate();
       
       vehicleFinancingPage.enterVehicleCost(ConfigReader.get("vehiclecost"));
       vehicleFinancingPage.enterLoanNumber("5");
       Thread.sleep(2000);
       vehicleFinancingPage.hirepurchasedropdown("1-2-3");
      
       vehicleFinancingPage.enterPartnerBankBranch("NEPAL");
       Thread.sleep(2000);
       vehicleFinancingPage.enterEquityAmount("20000");;
       operationalDetailsPage.selectScrappingNo();
       operationalDetails();
       driver.findElement(By.id("no-charger")).click();
       operationalDetailsPage.enterTitleTransferDate(date);
       operationalDetailsPage.clickNextButton();
       fileUploadPage.uploadAllcommonRequiredFiles(filePath);
       fileUploadPage.uploadintimationletterpartner(filePath);
       fileUploadPage.clickPreviewButton();
       fileUploadPage.clickSubmitButton();
       js.executeScript("window.scrollBy(0, 150);"); 
    	checkwheathersusidycreated();
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3,description = "100 percent payment from pto with accesories  ")
public void CFR004() throws InterruptedException

{
    	common();
        // Step 2: Vehicle Financing
      js.executeScript("window.scrollBy(0, 150);");
      vehicleFinancingPage.selectFullPaymentOption();
      Thread.sleep(500);
      operationalDetailsPage.selectScrappingYes();
      js.executeScript("window.scrollBy(0, 150);");
      vehicleFinancingPage.enterVehicleCost(ConfigReader.get("vehiclecost"));
      Thread.sleep(1000);

      js.executeScript("window.scrollBy(0, 250);");
      
        operationalDetails();
        operationalDetailsPage.accesoriesyes();
        driver.findElement(By.id("no-charger")).click();
        
        
        operationalDetailsPage.enterTitleTransferDate(date);
        operationalDetailsPage.clickNextButton();
        
          // Step 4: File Upload
        fileUploadPage.uploadAllcommonRequiredFiles(filePath);
        fileUploadPage.scrappingfileupload(filePath);
        fileUploadPage.uploadaccessoriesfiles(filePath);
        preview_and_submit() ;
        
        js.executeScript("window.scrollBy(0, 150);");
        checkwheathersusidycreated();
    	
    	
    	
}
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4,description = "LOAN NO SCRAPPING CERTIFCATE with accesories  ")
 public void CFR005 () throws InterruptedException
 {
    	  common();
          // Step 2: Vehicle Financing
        js.executeScript("window.scrollBy(0, 150);");
        vehicleFinancingPage.selectLoanOption();
        Thread.sleep(2000);
        vehicleFinancingPage.enterinterestrate();
        
        vehicleFinancingPage.enterVehicleCost(ConfigReader.get("vehiclecost"));
        vehicleFinancingPage.enterLoanNumber("5");
        Thread.sleep(2000);
        vehicleFinancingPage.hirepurchasedropdown("1-2-3");
       
        vehicleFinancingPage.enterPartnerBankBranch("NEPAL");
        Thread.sleep(2000);
        vehicleFinancingPage.enterEquityAmount("20000");;
        operationalDetailsPage.selectScrappingNo();
        operationalDetailsPage.accesoriesyes();
        operationalDetails();
        driver.findElement(By.id("no-charger")).click();
        operationalDetailsPage.enterTitleTransferDate(date);
        operationalDetailsPage.clickNextButton();
        fileUploadPage.uploadAllcommonRequiredFiles(filePath);
        
        fileUploadPage.uploadintimationletterpartner(filePath);
        fileUploadPage.uploadaccessoriesfiles(filePath);
        preview_and_submit();
        checkwheathersusidycreated();
	 
 }
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 5,description = "HIRE-PURCHASE WITH SCRAPPING CERTIFCATE with accesories  ")
    public void CFR006() throws InterruptedException
    {
    	  common();
          // Step 2: Vehicle Financing
        js.executeScript("window.scrollBy(0, 150);");
        vehicleFinancingPage.selectHirePurchaseOption();
        Thread.sleep(2000);
        vehicleFinancingPage.enterinterestrate();
        
        vehicleFinancingPage.enterVehicleCost(ConfigReader.get("vehiclecost"));
        vehicleFinancingPage.enterLoanNumber("5");
        Thread.sleep(2000);
        vehicleFinancingPage.hirepurchasedropdown("1-2-3");
       
        vehicleFinancingPage.enterPartnerBankBranch("NEPAL");
        Thread.sleep(2000);
        vehicleFinancingPage.enterEquityAmount("20000");;
        operationalDetailsPage.selectScrappingNo();
        operationalDetailsPage.accesoriesyes();
        operationalDetails();
        driver.findElement(By.id("no-charger")).click();
        operationalDetailsPage.enterTitleTransferDate(date);
        operationalDetailsPage.clickNextButton();
        fileUploadPage.uploadAllcommonRequiredFiles(filePath);
        
        fileUploadPage.uploadintimationletterpartner(filePath);
        fileUploadPage.uploadaccessoriesfiles(filePath);
        preview_and_submit();
        checkwheathersusidycreated();
	 
    }
 
}
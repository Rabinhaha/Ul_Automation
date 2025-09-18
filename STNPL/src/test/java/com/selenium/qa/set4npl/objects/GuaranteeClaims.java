package com.selenium.qa.set4npl.objects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class GuaranteeClaims {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public GuaranteeClaims(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 2 seconds wait
    }

    public void teardown() {
        if (driver != null) driver.quit();
    }

    // Locators
    private By targetRows = By.xpath("//tr[td[normalize-space(.)='Not Reported'] and td[normalize-space(.)='Pre-Installed']]");
    private By defaultBtn = By.xpath("//button[contains(text(),'Report New Default')]");
    private By checkBox = By.id("usGuarantee");
    private By totalLoan = By.xpath("//input[@name='loanAmount']");
    private By defaultAmount = By.xpath("//input[@name='defaultAmount']");
    private By dueDateInput = By.name("dueDate");
    private By checkBox2 = By.id("loanAcceleration");
    private By nextBtn = By.xpath("//button[@type='submit']");
    private By uploadRepaymentHistory=By.id("upload-repaymentHistory");
    private By previewButton=By.xpath("//button[.='Preview']");
   private By submitBtn = By.xpath("//button[normalize-space(.)='Submit']");
   private By combobox=By.xpath("//button[@role='combobox']");



    public boolean hasPreInstalledNotReported() throws AWTException 
{
    	driver.findElement(combobox).sendKeys("1");
    	Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);   
        robot.keyRelease(KeyEvent.VK_ENTER);
        List<WebElement> rows = driver.findElements(targetRows);
        return !rows.isEmpty();
    }

    public void clickEyeForPreInstalledNotReported() {
        // Always re-locate, don’t reuse old references
    	
    	
        WebElement row = wait.until(
            ExpectedConditions.visibilityOfElementLocated(targetRows)
        );

        // find the eye button inside that row
        WebElement eyeButton = row.findElement(By.xpath(".//button"));

        // scroll into view to avoid click interception
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", eyeButton);

        // wait and click
        wait.until(ExpectedConditions.elementToBeClickable(eyeButton)).click();

        System.out.println("Clicked eye button for Pre-Installed + Not Reported row");
    }
    public void clickEyeForPreInstalledNotReportedWithPagination() throws InterruptedException {
        while (true) {
            List<WebElement> rows = driver.findElements(targetRows);
            boolean clicked = false;

            for (WebElement row : rows) {
                String rowText = row.getText();
                if ( (rowText.contains("Pre-Installed") || rowText.contains("Post-Installed"))
                	    && rowText.contains("Not Reported")) {
                    WebElement eyeButton = row.findElement(By.xpath(".//button"));
                    ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", eyeButton);
                    wait.until(ExpectedConditions.elementToBeClickable(eyeButton)).click();
                    System.out.println("Clicked eye button for Pre-Installed + Not Reported row");
                    return; // exit after clicking
                }
            }

            // Check if next button is available
            List<WebElement> nextButtons = driver.findElements(By.xpath("//button[contains(text(),'Next') and not(@disabled)]"));
            if (nextButtons.isEmpty()) {
                System.out.println("Reached last page, target not found");
                break;
            } else {
                nextButtons.get(0).click();
                Thread.sleep(2000); // wait for page load
            }
        }
    }


    public void defaultBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(defaultBtn)).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        wait.until(driver -> true);
    }

    public void checkBox() {
        By locator = By.id("usGuarantee");
        int retries = 3;
        for (int i = 0; i < retries; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                return;
            } catch (StaleElementReferenceException e) {
                // retry if the element went stale
            }
        }
        throw new RuntimeException("Failed to click usGuarantee checkbox after retries");
    }


    public void totalLoan() {
        WebElement loan = wait.until(ExpectedConditions.visibilityOfElementLocated(totalLoan));
        loan.clear();
        loan.sendKeys("1000000");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        wait.until(driver -> true);
    }

    public void defaultAmount() {
        WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(defaultAmount));
        amount.clear();
        amount.sendKeys("2000000");
        wait.until(driver -> true);
    }

    // ✅ Updated selectDueDate: stable, reacts to UI updates
    public void selectDueDate(String date) {
        // Click input to open calendar
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(dueDateInput));
        dateField.sendKeys(date);

      
    }

    public void checkBox2() {
        wait.until(ExpectedConditions.elementToBeClickable(checkBox2)).click();
        wait.until(driver -> true);
    }

    public void nextBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
    }
    public void rePaymentFile(String filePath)
    {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// WebElement upload = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadRepaymentHistory));
   // wait.until(ExpectedConditions.elementToBeClickable(uploadRepaymentHistory)).sendKeys(filePath);
    	driver.findElement(uploadRepaymentHistory).sendKeys(filePath);
    }
    public void previewButton()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(previewButton)).click();
    }
    public void submitBtn()
    {   
    	  ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    	 driver.findElement(submitBtn).click();;

      

       
     
    }
}

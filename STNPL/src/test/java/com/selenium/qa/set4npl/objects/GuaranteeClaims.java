package com.selenium.qa.set4npl.objects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    // Methods

    public boolean hasPreInstalledNotReported() {
        List<WebElement> rows = driver.findElements(targetRows);
        return !rows.isEmpty();
    }

    public void clickAllMatchingEyes() {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(targetRows));
        System.out.println("Found " + rows.size() + " matching rows");

        for (WebElement row : rows) {
            try {
                WebElement eyeButton = row.findElement(By.xpath(".//button"));
                wait.until(ExpectedConditions.elementToBeClickable(eyeButton)).click();
                System.out.println("Clicked eye button for row");
                wait.until(driver -> true); // tiny pause
            } catch (Exception e) {
                System.out.println("⚠️ Could not click eye button for a row: " + e.getMessage());
                wait.until(driver -> true);
            }
        }
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
    }

    public void defaultBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(defaultBtn)).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        wait.until(driver -> true);
    }

    public void checkBox() {
        wait.until(ExpectedConditions.elementToBeClickable(checkBox)).click();
        wait.until(driver -> true);
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
        dateField.click();

        // Wait for calendar popup to appear
        By calendarPopup = By.xpath("//div[contains(@class,'calendar') or @role='dialog']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(calendarPopup));

        // Extract day from yyyy-MM-dd
        String day = date.split("-")[2];

        // Click the day in the calendar
        By dayLocator = By.xpath("//td[@role='gridcell' and text()='" + Integer.parseInt(day) + "']");
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        dayElement.click();

        // Wait until input field has the selected value
        wait.until(ExpectedConditions.attributeToBe(dueDateInput, "value", date));
    }

    public void checkBox2() {
        wait.until(ExpectedConditions.elementToBeClickable(checkBox2)).click();
        wait.until(driver -> true);
    }

    public void nextBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
    }
}

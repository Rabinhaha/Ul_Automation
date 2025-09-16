package com.selenium.qa.set4npl.empsuplier.fundreq;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.qa.Login;
import com.selenium.qa.set4npl.objects.EmbAccessiories;

public class T04_EMBaccessiories extends Login {

    private WebDriver driver;
    private EmbAccessiories embAccessioriesFlow;
    

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = initializeBrowserAndOpenApplication("chrome");
        driver = loginAs("handlingbank");
        embAccessioriesFlow = new EmbAccessiories(driver);
        
    }
    @AfterMethod
    public void tearDown()
    {
    	driver.quit();
    }

    @Test(description="eligible")
    public void clickEmbAccessioriesFlow() throws InterruptedException {
    	
        embAccessioriesFlow.clickEmbAccessiories();
        embAccessioriesFlow.clickEyeBtn();
        embAccessioriesFlow.eligibleBtn();
        embAccessioriesFlow.commentHere();
        embAccessioriesFlow.uploadFileById( "compat.pdf");
        embAccessioriesFlow.acceptBtn();
    }

    @Test(description="incomplete")
    public void clickEmbAccessioriesIncompleteFlow() throws InterruptedException {
        embAccessioriesFlow.clickEmbAccessiories();
        embAccessioriesFlow.clickEyeBtn();
        embAccessioriesFlow.incompleteBtn();   
        embAccessioriesFlow.commentIncomplete(); 
        embAccessioriesFlow.confirmBtn();
    }

    @Test(description="reject")
    public void clickEmbAccessioriesRejectFlow() throws InterruptedException {
        embAccessioriesFlow.clickEmbAccessiories();
        embAccessioriesFlow.clickEyeBtn();
        embAccessioriesFlow.clickFirstRejectBtn();  
        embAccessioriesFlow.commentReject();        
        embAccessioriesFlow.clickSecondRejectBtn(); 
    }
}

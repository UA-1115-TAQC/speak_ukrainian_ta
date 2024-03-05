package com.academy.ui.runners;

import com.academy.ui.pages.HomePage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class HomePageTestRunner extends BaseTestRunner{
   protected HomePage home;
   protected WebDriverWait wait;
   protected String initialUrl;
   protected SoftAssert softAssert;
   protected Actions actions;

    @BeforeMethod
    public void open_homepage(){
        home = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        initialUrl =driver.getCurrentUrl();
        softAssert=new SoftAssert();
        actions=new Actions(driver);
    }
}

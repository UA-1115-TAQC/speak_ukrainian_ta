package com.academy.ui.runners;

import com.academy.ui.pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class HomePageTestRunner extends BaseTestRunner{
   protected HomePage home;
   protected WebDriverWait wait;
   protected String initialUrl;
    @BeforeMethod
    public void open_homepage(){
        home = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        initialUrl =driver.getCurrentUrl();
    }
}
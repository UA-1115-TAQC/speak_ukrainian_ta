package com.academy.ui;

import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHeaderAndFooterNotLoggedIn extends BaseTestRunner {

    @Test(description = "TUA-869")
    public void testHeaderAndFooterNotLogged() {

        homePage = new HomePage(driver);

        boolean headerIsVisible = driver.findElement(By.xpath(".//header")).isDisplayed();
        Assert.assertTrue(headerIsVisible);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        boolean footerIsVisible = driver.findElement(By.xpath(".//footer")).isDisplayed();
        //how to use homePage.getFooter() for searching element
        Assert.assertTrue(footerIsVisible);
    }

}

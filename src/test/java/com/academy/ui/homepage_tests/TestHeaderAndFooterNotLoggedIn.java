package com.academy.ui.homepage_tests;

import com.academy.ui.components.FooterComponent;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHeaderAndFooterNotLoggedIn extends BaseTestRunner {
    @Test(description = "TUA-869")
    public void testHeaderAndFooterNotLogged() {

        homePage = new HomePage(driver);

        HeaderComponent HomePageHeader = homePage.getHeader();
        Assert.assertTrue(HomePageHeader.getWebElement().isDisplayed(), "The header is not visible." );

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        FooterComponent HomePageFooter = homePage.getFooter();
        Assert.assertTrue(HomePageFooter.getWebElement().isDisplayed(), "The footer is not visible.");
    }
}

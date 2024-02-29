package com.academy.ui.homepage_tests;

import com.academy.ui.components.FooterComponent;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHeaderAndFooterNotLoggedIn extends BaseTestRunner {
    @Test(description = "TUA-869")
    public void testHeaderAndFooterNotLogged() {
        HeaderComponent HomePageHeader = homePage.getHeader();
        Assert.assertTrue(HomePageHeader.getWebElement().isDisplayed(), "The header is not visible." );

        homePage.scrollToFooter();

        FooterComponent HomePageFooter = homePage.getFooter();
        Assert.assertTrue(HomePageFooter.getWebElement().isDisplayed(), "The footer is not visible.");
    }
}

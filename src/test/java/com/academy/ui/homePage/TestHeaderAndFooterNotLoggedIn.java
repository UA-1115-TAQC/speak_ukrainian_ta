package com.academy.ui.homePage;

import com.academy.ui.components.FooterComponent;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestHeaderAndFooterNotLoggedIn extends BaseTestRunner {
    private SoftAssert softAssert;
    @BeforeMethod
    public void beforeTestPreconditions(){
        softAssert = new SoftAssert();
    }

    @Test(description = "The user is not logged in")
    @Description("Verify that the header and the footer are visible")
    @Issue("TUA-869")
    public void testHeaderAndFooterNotLogged() {
        HeaderComponent HomePageHeader = homePage.getHeader();
        softAssert.assertTrue(HomePageHeader.getWebElement().isDisplayed(),
                "The header is not visible." );
        homePage.scrollToFooter();
        FooterComponent HomePageFooter = homePage.getFooter();
        softAssert.assertTrue(HomePageFooter.getWebElement().isDisplayed(),
                "The footer is not visible.");
        softAssert.assertAll();
    }
}

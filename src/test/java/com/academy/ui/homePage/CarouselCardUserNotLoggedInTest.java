package com.academy.ui.homePage;

import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CarouselCardUserNotLoggedInTest extends BaseTestRunner {

    private SoftAssert softAssert;

    @BeforeMethod(description = "Precondition: Make new softAssert object")
    public void beforeTestPreconditions(){
        softAssert = new SoftAssert();
    }

    @Test(description = "Verify 4 blocks are resent on cards carousel")
    @Description("[Home page] [The user is not logged in] Verify that on the carousel with suggested clubs are present 4 blocks.")
    @Issue("TUA-826")
    public void verify4BlocksPresentOnCardsCarousel(){

    softAssert.assertEquals(driver.getCurrentUrl(),
            "http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
    softAssert.assertTrue(homePage.isElementPresent(homePage.getCarouselCardComponentWebElement()),
            "Card Carousel should be present on the page");

    softAssert.assertEquals(homePage.getCarouselCardComponent().getActiveCarouselCards().size(), 4);

    softAssert.assertAll();

    }
}

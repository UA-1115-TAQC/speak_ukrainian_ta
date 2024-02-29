package com.academy.ui.components;

import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CarouselCardUserNotLoggedInTest extends BaseTestRunner {

    private SoftAssert softAssert;

    @BeforeMethod
    public void beforeTestPreconditions(){
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-826")
    public void verify4BlocksPresentOnCardsCarousel(){

    softAssert.assertEquals(driver.getCurrentUrl(),
            "http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
    softAssert.assertTrue(homePage.isElementPresent(homePage.getCarouselCardComponentWebElement()),
            "Card Carousel should be present on the page");

    softAssert.assertEquals(homePage.getCarouselCardComponent().getActiveCarouselCards().size(), 4);

    softAssert.assertAll();

    }
}

package com.academy.ui;

import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderComponentTest extends BaseTestRunner {
    private SoftAssert softAssert;
    public static final String DEFAULT_CITY = "Київ";

    @BeforeMethod
    public void beforeTestPreconditions(){
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-23")
    @Issue("TUA-23")
    public void testVerifyKyivIsShownByDefaultCity() {
        softAssert.assertTrue(homePage.header.getLocationIcon().isDisplayed());
        softAssert.assertEquals(homePage.header.getClubsLocationButton().getText(), DEFAULT_CITY,
                "Київ must be shown as the default");
        softAssert.assertAll();
    }

    @Test(description = "TUA-311")
    public void testVerifyLocationItem() {
        softAssert.assertTrue(homePage.header.getLocationIcon().isDisplayed());
        softAssert.assertEquals(homePage.header.getClubsLocationButton().getText(), DEFAULT_CITY,
                "Київ must be shown as the default");

        homePage.header.getClubsLocationButton().click();
        softAssert.assertTrue(homePage.header.openCityMenu().isDisplayed(), "List of city doesn't display");
        softAssert.assertAll();
    }
}

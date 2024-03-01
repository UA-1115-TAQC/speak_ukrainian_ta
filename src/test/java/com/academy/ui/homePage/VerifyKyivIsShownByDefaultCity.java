package com.academy.ui.homePage;

import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyKyivIsShownByDefaultCity extends BaseTestRunner {

    private SoftAssert softAssert;
    public static final String DEFAULT_CITY = "Київ";

    @BeforeMethod
    public void beforeTestPreconditions(){
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-23")
    public void testVerifyKyivIsShownByDefaultCity() {
        softAssert.assertTrue(homePage.header.getCityButton().isDisplayed());

        String defCity = homePage.header.getCityMenuElements().get(0).getText();
        softAssert.assertEquals(defCity, DEFAULT_CITY, "Київ must be shown as the default");

        softAssert.assertAll();
    }
}

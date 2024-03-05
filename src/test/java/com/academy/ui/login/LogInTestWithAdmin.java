package com.academy.ui.login;

import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LogInTestWithAdmin extends LoginWithAdminTestRunner {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void loginSetUp() {
        softAssert = new SoftAssert();
    }

    @Test(description = "Test Log In fields are empty after Admin Logged Out")
    public void checkLogInFieldsAreEmptyAfterAdminLoggedOut(){
        homePage.header.waitUntilIsLoggedIn(10);
        softAssert.assertTrue(homePage.header.isLoggedIn(),
                "Admin should be logged in");

        homePage.header.openAdminMenu().clickLogout();
        softAssert.assertFalse(homePage.header.isLoggedIn(),
                "Admin should be logged out");

        LoginPopupComponent login = homePage.header.openGuestMenu().openLoginForm();
        softAssert.assertTrue(login.getEmailInputElement().getInput().getText().isEmpty(),
                "Login Email field should be empty");
        softAssert.assertTrue(login.getPasswordInputElement().getInput().getText().isEmpty(),
                "Login Email field should be empty");

        softAssert.assertAll();
    }

}

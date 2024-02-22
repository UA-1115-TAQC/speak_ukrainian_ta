package com.academy.ui.runners;

import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.pages.HomePage;
import org.testng.annotations.BeforeMethod;

public class LoginWithAdminTestRunner extends BaseTestRunner{

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        LoginPopupComponent loginForm = homePage
                .header
                .openGuestMenu()
                .openLoginForm();
        loginForm.enterEmail(configProperties.getAdminEmail());
        loginForm.enterPassword(configProperties.getAdminPassword());

        loginForm.clickSubmitButton();
        homePage = new HomePage(driver);
    }
}

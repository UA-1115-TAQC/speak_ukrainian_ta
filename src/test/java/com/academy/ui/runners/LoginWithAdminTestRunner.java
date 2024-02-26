package com.academy.ui.runners;

import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import org.testng.annotations.BeforeMethod;

public class LoginWithAdminTestRunner extends BaseTestRunner {

    @BeforeMethod
    public void loginPrecondition() {
        LoginPopupComponent loginForm = homePage
                .header
                .openGuestMenu()
                .openLoginForm();
        loginForm.enterEmail(configProperties.getAdminEmail());
        loginForm.enterPassword(configProperties.getAdminPassword());

        loginForm.clickSubmitButton();
    }
}

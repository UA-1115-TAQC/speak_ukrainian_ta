package com.academy.ui.runners;

import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import org.testng.annotations.BeforeMethod;

public class LoginWithManagerTestRunner extends BaseTestRunner {
    @BeforeMethod(description = "Precondition login as manager")
    public void loginPrecondition() {
        LoginPopupComponent loginForm = homePage
                .header
                .openGuestMenu()
                .openLoginForm();
        loginForm.enterEmail(configProperties.getManagerEmail());
        loginForm.enterPassword(configProperties.getManagerPassword());

        loginForm.clickSubmitButton();
    }
}

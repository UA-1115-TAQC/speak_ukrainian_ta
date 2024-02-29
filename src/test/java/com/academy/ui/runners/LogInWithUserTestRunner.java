package com.academy.ui.runners;

import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import org.testng.annotations.BeforeMethod;

public class LogInWithUserTestRunner extends BaseTestRunner{

    public void loginPrecondition() {
        LoginPopupComponent loginForm = homePage
                .header
                .openGuestMenu()
                .openLoginForm();
        loginForm.enterEmail(configProperties.getUserEmail());
        loginForm.enterPassword(configProperties.getUserPassword());
        loginForm.clickSubmitButton();
    }
}

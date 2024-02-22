package com.academy.ui.runners;

import com.academy.ui.components.loginPopUpComponent.LoginPopupComponent;
import com.academy.ui.pages.HomePage;
import org.testng.annotations.BeforeClass;

public class LoginWithAdminTestRunner extends BaseTestRunner{

    @BeforeClass
    public void loginPrecondition() {
        LoginPopupComponent loginForm = new HomePage(driver)
                .header
                .openGuestMenu()
                .openLoginForm();
        loginForm.getEmailInputElement().setValue(configProperties.getAdminEmail());
        loginForm.getPasswordInputElement().setValue(configProperties.getAdminPassword());

        loginForm.clickSubmitButton();

        homePage = new HomePage(driver);
    }
}

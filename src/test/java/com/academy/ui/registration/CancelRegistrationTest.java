package com.academy.ui.registration;

import com.academy.ui.components.RegistrationPopup.RegistrationPopupComponent;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.pages.BasePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CancelRegistrationTest extends BaseTestRunner {

    @Test(description = "TUA-876")
    public void checkRedirectionAfterRegistrationCanceled(){
        HeaderComponent header = homePage.getHeader();
        header.newsButtonClick();
//        TODO wait
        String url = driver.getCurrentUrl();
        RegistrationPopupComponent registrationPopupComponent = header.openGuestMenu().openRegistrationForm();
        registrationPopupComponent.getLastNameInput().clearInput().setValue("Zxcvb");
        registrationPopupComponent.getFirstNameInput().clearInput().setValue("Zxcvb");
        registrationPopupComponent.getPhoneInput().clearInput().setValue("0991234567");
        registrationPopupComponent.close();
        //        TODO wait
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, url);
    }
}

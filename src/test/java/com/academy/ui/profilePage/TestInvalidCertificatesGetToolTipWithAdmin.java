package com.academy.ui.profilePage;

import com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu.CertificatesSubmenuPopup;
import com.academy.ui.pages.AdminGenerateCertificatePage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.security.cert.Certificate;

public class TestInvalidCertificatesGetToolTipWithAdmin extends LoginWithAdminTestRunner {
    SoftAssert softAssert;
    CertificatesSubmenuPopup popup;
    AdminGenerateCertificatePage adminGenerateCertificatePage;
    String minValidValue = "1";
    String maxValidValue = "999";
    @BeforeMethod
    public void setup(){
        softAssert = new SoftAssert();
        adminGenerateCertificatePage = homePage.header.openAdminMenu().openContentPopup().openCertificatesSubmenuPopup().clickGenerateCertificate();

    }

    @Test
    public void testInvalidCertificatesGetToolTip(){
       /* clearInputField(adminGenerateCertificatePage.getStudyDurationInput());
        adminGenerateCertificatePage.getStudyDurationInput().sendKeys("1000");*/
        enterInValidValue("1000");
        adminGenerateCertificatePage.getStudyDurationLabel().click();
        adminGenerateCertificatePage.sleep(1000);

        softAssert.assertAll();
    }
    private void clearInputField(WebElement inputField){
        inputField.sendKeys(Keys.COMMAND + "a");
        inputField.sendKeys(Keys.DELETE);
    }
    private void enterInValidValue(String value){
        if (!adminGenerateCertificatePage.getStudyDurationLabel().getAttribute("value").isEmpty()){
            clearInputField(adminGenerateCertificatePage.getStudyDurationInput());
        }
        adminGenerateCertificatePage.getStudyDurationInput().sendKeys(value);
        softAssert.assertTrue(adminGenerateCertificatePage.getStudyDurationInput().getAttribute("value").contains(value));
    }
}

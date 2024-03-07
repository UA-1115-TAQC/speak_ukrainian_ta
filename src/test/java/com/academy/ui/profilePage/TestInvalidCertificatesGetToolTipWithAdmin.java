package com.academy.ui.profilePage;

import com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu.CertificatesSubmenuPopup;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.security.cert.Certificate;

public class TestInvalidCertificatesGetToolTipWithAdmin extends LoginWithAdminTestRunner {
    SoftAssert softAssert;
    CertificatesSubmenuPopup popup;
    @BeforeMethod
    public void setup(){
        softAssert = new SoftAssert();
        homePage.header.openAdminMenu().openContentPopup().openCertificatesSubmenuPopup().clickGenerateCertificate();

    }

    @Test
    public void testInvalidCertificatesGetToolTip(){
        //popup.sleep(1000);
    }
}

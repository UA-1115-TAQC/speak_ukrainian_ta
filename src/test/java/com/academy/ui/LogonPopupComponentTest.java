package com.academy.ui;

import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;

public class LogonPopupComponentTest extends BaseTestRunner {

    @Test
    public void logonIntoProfile() {
        HomePage homePage = new HomePage(driver);
        homePage.logonPopupComponent.clickLogonPopupMenu();
    }
}

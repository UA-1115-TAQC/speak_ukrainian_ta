package com.academy.ui.profilePage;

import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestMainInformationOnProfilePageUI extends LoginWithManagerTestRunner {
    private SoftAssert softAssert;
    private ProfilePage profilePage;

    @BeforeMethod
    public void SetUp() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openUserMenu().clickProfile();

    }

    @Test(description = "Check the ‘Основна інформація’ tab on the ‘Редагувати гурток’ pop-up window (UI)")
    @Issue("TUA-45")
    public void checkMainInformationOnProfilePage(){

    }
}

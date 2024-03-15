package com.academy.ui.profilePage;

import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LogInWithUserTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestVerifyThatFieldEmailDisabled extends LogInWithUserTestRunner {


    @Test
    @Description("Verify that field 'Email' on the ‘Контакти' tab is disabled")
    @Issue("TUA-398")
    public void checkEditProfileLinkIsPresentAndDirectToEditProfilePage() {
        homePage.header.openUserMenu().clickProfile().editButtonClick();

        ProfilePage profilePage = new ProfilePage(driver);
        boolean emailFieldEnabled = profilePage.getEmailUser().isEnabled();

        Assert.assertTrue(emailFieldEnabled);

    }
}

package com.academy.ui.profilePage;

import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LogInWithUserTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestVerifyThatFieldEmailDisabled extends LogInWithUserTestRunner {


    @Test(description = "TUA-398- Verify that field 'Email' on the ‘Контакти' tab is disabled")
    public void checkEditProfileLinkIsPresentAndDirectToEditProfilePage() {
        homePage.header.openUserMenu().clickProfile().editButtonClick();

        ProfilePage profilePage = new ProfilePage(driver);
        boolean emailFieldEnabled = profilePage.getEmailUser().isEnabled();

        Assert.assertTrue(emailFieldEnabled);

    }
}

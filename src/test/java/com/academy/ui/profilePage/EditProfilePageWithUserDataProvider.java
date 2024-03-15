package com.academy.ui.profilePage;

import org.testng.annotations.DataProvider;

public class EditProfilePageWithUserDataProvider {

    @DataProvider(name = "invalidEmail")
    private Object[][] invalidEmailDataProvider() {
        return new String[][] {
                {"ag.com"},
                {"a@gcom"},
                {"a@g..com"},
                {"a@@g.com"},
        };
    }
}

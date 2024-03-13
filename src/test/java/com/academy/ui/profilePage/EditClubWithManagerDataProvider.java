package com.academy.ui.profilePage;

import org.testng.annotations.DataProvider;

public class EditClubWithManagerDataProvider {

    @DataProvider(name = "invalidTelephone")
    private Object[][] invalidTelephoneDataProvider() {
        return new String[][] {
                {"uuu", "Телефон не може містити спеціальні символи, літери та пробіли Телефон не відповідає вказаному формату "},
                {"@#$", "Телефон не може містити спеціальні символи, літери та пробіли Телефон не відповідає вказаному формату "},
                {"1", "Телефон не відповідає вказаному формату"},
                {"012345678999", "Телефон не відповідає вказаному формату"},
        };
    }

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

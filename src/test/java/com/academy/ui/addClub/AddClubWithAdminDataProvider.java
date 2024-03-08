package com.academy.ui.addClub;

import org.testng.annotations.DataProvider;

public class AddClubWithAdminDataProvider {

    @DataProvider(name = "validClubName")
    private Object[][] invalidFirstNameDataProvider() {
        return new String[][] {
                {"0123456789"},
                {"фЙїqfGJHdsmnФІля"},
                {"!@#$%^&*()_{:\"}]'"},
                {"%;?*(?:фЙїqfG123456789"},
                {"1&hЦ*"},
                {"123Qw*&#єЇ123Qw*&#єЇ123Qw*&#єЇ123Qw*&#єЇ123Qw*&#єЇ123Qw*&#єЇ123Qw*&#єЇ123Qw*&#єЇ123Qw*&#єЇ123Qw*&#єЇ"}
        };
    }
}

package com.academy.api.runners;

import org.testng.annotations.BeforeClass;

public class LoginWithUserAPITestRunner extends BaseAPITestRunner {

    @BeforeClass
    public void loginWithAdmin() {
        accessToken = getAccessToken(configProperties.getUserEmail(), configProperties.getUserPassword());
    }

}

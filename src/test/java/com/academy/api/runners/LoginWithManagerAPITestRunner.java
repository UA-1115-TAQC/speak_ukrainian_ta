package com.academy.api.runners;

import org.testng.annotations.BeforeClass;

public class LoginWithManagerAPITestRunner extends BaseAPITestRunner {

    @BeforeClass
    public void loginWithAdmin() {
        accessToken = getAccessToken(configProperties.getManagerEmail(), configProperties.getManagerPassword());
    }

}
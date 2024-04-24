package com.academy.api.rest.runners;

import org.testng.annotations.BeforeClass;

public class LoginWithAdminRunnerAPI extends BaseTestRunnerAPI{

    @BeforeClass
    public void loginWithAdmin(){
        accessToken = getAccessToken(configProperties.getAdminEmail(), configProperties.getAdminPassword());
    }
}

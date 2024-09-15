package com.academy.api.runners;

import org.testng.annotations.BeforeClass;

public class LoginWithAdminRunnerAPI extends BaseTestRunnerAPI{

    @BeforeClass
    public void loginWithAdmin(){
        accessToken = getAccessToken(configProperties.getAdminEmail(), configProperties.getAdminPassword());
    }
}

package com.academy.api.rest.runners;

import org.testng.annotations.BeforeClass;

public class LoginWithManagerRunnerAPI extends BaseTestRunnerAPI {

    @BeforeClass
    public void loginWithManager(){
        accessToken = getAccessToken(configProperties.getManagerEmail(), configProperties.getManagerPassword());
    }


}

package com.academy.api.runners;

import org.testng.annotations.BeforeClass;

public class LoginWithUserRunnerAPI extends BaseTestRunnerAPI{

    @BeforeClass
    public void loginWithUser(){
        accessToken = getAccessToken(configProperties.getUserEmail(), configProperties.getUserPassword());
    }



}

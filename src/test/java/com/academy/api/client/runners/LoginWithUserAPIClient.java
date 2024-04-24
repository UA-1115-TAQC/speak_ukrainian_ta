package com.academy.api.client.runners;

import com.academy.ApiException;
import org.testng.annotations.BeforeClass;

public class LoginWithUserAPIClient extends BaseTestRunnerAPIClient {

    @BeforeClass
    public void loginWithUser() throws ApiException {
        getAccessToken(configProperties.getUserEmail(), configProperties.getUserPassword());
    }
}

package com.academy.api.client.runners;

import com.academy.ApiException;
import org.testng.annotations.BeforeClass;

public class LoginWithManagerAPIClient extends BaseTestRunnerAPIClient {
    @BeforeClass
    public void loginWithManager() throws ApiException {
        getAccessToken(configProperties.getManagerEmail(), configProperties.getManagerPassword());
    }
}

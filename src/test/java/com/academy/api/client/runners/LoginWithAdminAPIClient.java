package com.academy.api.client.runners;

import com.academy.ApiException;
import org.testng.annotations.BeforeClass;

public class LoginWithAdminAPIClient extends BaseTestRunnerAPIClient {

    @BeforeClass
    public void loginWithAdmin() throws ApiException {
        getAccessToken(configProperties.getAdminEmail(), configProperties.getAdminPassword());
    }
}

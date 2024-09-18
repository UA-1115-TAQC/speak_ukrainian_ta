package com.academy.apiClient.runners;

import com.academy.api.invoker.ApiException;
import org.testng.annotations.BeforeClass;

public class LoginWithUserAPIClientTestRunner extends BaseAPIClientTestRunner {

    @BeforeClass
    public void loginWithUser() throws ApiException {
        getAccessToken(configProperties.getUserEmail(), configProperties.getUserPassword());
    }
}

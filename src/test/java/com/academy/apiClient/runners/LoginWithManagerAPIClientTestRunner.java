package com.academy.apiClient.runners;

import com.academy.api.ApiException;
import org.testng.annotations.BeforeClass;

public class LoginWithManagerAPIClientTestRunner extends BaseAPIClientTestRunner {

    @BeforeClass
    public void loginWithManager() throws ApiException {
        getAccessToken(configProperties.getManagerEmail(), configProperties.getManagerPassword());
    }

}

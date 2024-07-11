package com.academy.apiClient.runners;

import com.academy.api.ApiException;
import org.testng.annotations.BeforeClass;

public class LoginWithAdminAPIClientTestRunner extends BaseAPIClientTestRunner {

    @BeforeClass
    public void loginWithAdmin() throws ApiException {
        getAccessToken(configProperties.getAdminEmail(), configProperties.getAdminPassword());
    }

}

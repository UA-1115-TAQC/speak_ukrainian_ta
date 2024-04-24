package com.academy.api.client.runners;

import com.academy.ApiClient;
import com.academy.ApiException;
import com.academy.api.Login;
import com.academy.api.LoginApi;
import com.academy.api.SuccessLogin;
import com.academy.api.UserLogin;
import com.academy.ui.runners.utils.ConfigProperties;
import org.testng.annotations.BeforeClass;

public class BaseTestRunnerAPIClient {

    protected ConfigProperties configProperties;
    protected Long userId;
    protected ApiClient apiClient;

    @BeforeClass
    public void setUpClass() {
        configProperties = new ConfigProperties();
        apiClient = new ApiClient().setBasePath(configProperties.getBaseUrl());
    }

    protected void getAccessToken(String email, String password) throws ApiException {
        UserLogin userLogin = new UserLogin()
                .email(email)
                .password(password);

        Login login = new LoginApi(apiClient);
        SuccessLogin successLogin = login.signIn(userLogin);

        userId = successLogin.getId();
        apiClient.setBearerToken(successLogin.getAccessToken());
    }
}

package com.academy.apiClient.runners;

import com.academy.api.ApiClient;
import com.academy.api.ApiException;
import com.academy.api.api.LoginApi;
import com.academy.api.model.SuccessLogin;
import com.academy.api.model.UserLogin;
import com.academy.ui.runners.utils.ConfigProperties;
import org.testng.annotations.BeforeClass;

public class BaseAPIClientTestRunner {

    protected ConfigProperties configProperties;
    protected Long userId;
    protected ApiClient apiClient;

    @BeforeClass
    public void setUpClass() {
        configProperties = new ConfigProperties();
        apiClient = new ApiClient();
        apiClient.setBasePath(configProperties.getBaseUrl());
    }

    protected void getAccessToken(String email, String password) throws ApiException {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        LoginApi loginApi = new LoginApi(apiClient);
        SuccessLogin successLogin = loginApi.signIn(userLogin.email(userLogin.getEmail()));

        userId = successLogin.getId();
        apiClient.setBearerToken(successLogin.getAccessToken());
    }

}

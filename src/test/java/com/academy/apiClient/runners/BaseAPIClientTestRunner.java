package com.academy.apiClient.runners;

import com.academy.api.api.LoginApi;
import com.academy.api.invoker.ApiClient;
import com.academy.api.invoker.ApiException;
import com.academy.api.api.impl.LoginApiImpl;
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
        apiClient = new ApiClient().setBasePath(configProperties.getBaseUrl());
    }

    protected void getAccessToken(String email, String password) throws ApiException {
        UserLogin userLogin = new UserLogin()
                .email(email)
                .password(password);

        LoginApi loginApi = new LoginApiImpl(apiClient);
        SuccessLogin successLogin = loginApi.signIn(userLogin);

        userId = successLogin.getId();
        apiClient.setBearerToken(successLogin.getAccessToken());
    }
}

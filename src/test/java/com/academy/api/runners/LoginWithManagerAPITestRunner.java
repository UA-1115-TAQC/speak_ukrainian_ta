package com.academy.api.runners;

import com.academy.api.dto.login.LoginResponseDto;
import org.testng.annotations.BeforeClass;

public class LoginWithManagerAPITestRunner extends BaseAPITestRunner {

    @BeforeClass
    public void loginWithManager() {
        LoginResponseDto authResponseBody = getAuthResponseBody(configProperties.getManagerEmail(),
                configProperties.getManagerPassword());
        accessToken = getAccessToken(authResponseBody);
        refreshToken = getRefreshToken(authResponseBody);
        userId = getUserId(authResponseBody);
    }
}

package com.academy.api.runners;

import com.academy.api.dto.login.LoginResponseDto;
import org.testng.annotations.BeforeClass;

public class LoginWithUserAPITestRunner extends BaseAPITestRunner {

    @BeforeClass
    public void loginWithUser() {
        LoginResponseDto authResponseBody = getAuthResponseBody(configProperties.getUserEmail(),
                configProperties.getUserPassword());
        accessToken = getAccessToken(authResponseBody);
        refreshToken = getRefreshToken(authResponseBody);
        userId = getUserId(authResponseBody);
    }
}

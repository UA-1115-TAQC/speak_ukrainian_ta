package com.academy.api.runners;

import com.academy.api.dto.LoginResponseDto;
import org.testng.annotations.BeforeClass;

public class LoginWithAdminAPITestRunner extends BaseAPITestRunner {

    @BeforeClass
    public void loginWithAdmin() {
        LoginResponseDto authResponseBody = getAuthResponseBody(configProperties.getAdminEmail(),
                configProperties.getAdminPassword());
        accessToken = getAccessToken(authResponseBody);
        refreshToken = getRefreshToken(authResponseBody);
        userId = getUserId(authResponseBody);
    }
}

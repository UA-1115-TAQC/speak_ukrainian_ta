package com.academy.api.runners;

import com.academy.api.dto.LoginResponseDto;
import org.testng.annotations.BeforeClass;

public class LoginWithUserAPITestRunner extends BaseAPITestRunner {

    @BeforeClass
    public void loginWithUser() {
        LoginResponseDto authResponseBody = getAuthResponseBody(configProperties.getUserEmail(),
                configProperties.getUserPassword());
        getAccessToken(authResponseBody);
    }
}

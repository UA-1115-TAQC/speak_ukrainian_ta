package com.academy.api.runners;

import org.testng.annotations.BeforeClass;

public class LoginWithAdminAPITestRunner extends BaseAPITestRunner{

    @BeforeClass
    public void loginWithAdmin() {
        accessToken = getAccessToken(configProperties.getAdminEmail(), configProperties.getAdminEmail());
    }

}

package com.academy.apiClient.userData;

import com.academy.api.ApiException;
import com.academy.api.ApiResponse;
import com.academy.api.api.UserApi;
import com.academy.api.model.UserResponse;
import com.academy.apiClient.runners.LoginWithAdminAPIClientTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminPersonalDataTest extends LoginWithAdminAPIClientTestRunner {
    private SoftAssert softAssert;
    int OK_STATUS = 200;

    @BeforeMethod
    public void preconditions(){
        softAssert = new SoftAssert();
    }

    @Test(description = "Positive API test for verifying that admin can see personal data")
    @Description("[API]Verify if registered user can see personal data")
    @Issue("TUA-375")
    public void testRegisteredUserCanSeePersonalData() throws ApiException {
        UserApi userApi = new UserApi(apiClient);
        ApiResponse<UserResponse> userApiResponse = userApi.getUserByIdWithHttpInfo(userId);
        UserResponse userResponse = userApiResponse.getData();

        softAssert.assertEquals(userApiResponse.getStatusCode(), OK_STATUS,
                "Response status code of get user with id " + userId + " should be " + OK_STATUS);

        softAssert.assertEquals(userResponse.getFirstName(), configProperties.getAdminFirstName());
        softAssert.assertEquals(userResponse.getLastName(), configProperties.getAdminLastName());
        softAssert.assertEquals(userResponse.getEmail(), configProperties.getAdminEmail());
        softAssert.assertEquals(userResponse.getPhone(), configProperties.getAdminPhone());
        softAssert.assertEquals(userResponse.getRoleName(), configProperties.getAdminRoleName());
        softAssert.assertEquals(userResponse.getUrlLogo(), configProperties.getAdminUrlLogo());
        softAssert.assertEquals(userResponse.getStatus(), configProperties.getAdminStatus());
        softAssert.assertAll();
    }

}

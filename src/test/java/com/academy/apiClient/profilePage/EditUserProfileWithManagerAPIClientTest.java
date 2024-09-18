package com.academy.apiClient.profilePage;

import com.academy.api.invoker.ApiException;
import com.academy.api.invoker.ApiResponse;
import com.academy.api.api.impl.UserApiImpl;
import com.academy.api.model.UserResponse;
import com.academy.api.model.UserUpdateProfile;
import com.academy.apiClient.runners.LoginWithUserAPIClientTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class EditUserProfileWithManagerAPIClientTest extends LoginWithUserAPIClientTestRunner {

    private SoftAssert softAssert;

    int OK_STATUS = 200;

    @BeforeMethod
    public void preconditions(){
        softAssert = new SoftAssert();
    }


    @Test(description = "Positive API test for editing User profile with valid data using API Client")
    @Description("[API. Edit profile] User can edit profile with valid data")
    @Issue("TUA-408")
    public void userCanEditProfileWithValidData() throws ApiException {

        String NEW_FIRST_NAME = "Anna";
        String NEW_LAST_NAME = "Kukarska";
        String NEW_PHONE = "0234567891";

        UserApiImpl userApi = new UserApiImpl(apiClient);
        ApiResponse<UserResponse> userApiResponse = userApi.getUserByIdWithHttpInfo(userId);
        UserResponse userResponse = userApiResponse.getData();

        softAssert.assertEquals(userApiResponse.getStatusCode(), OK_STATUS,
                "Response status code of get user with id " + userId + " should be " + OK_STATUS);

        String currentFirstName = userResponse.getFirstName();
        String currentLastName = userResponse.getLastName();
        String currentPhone = userResponse.getPhone();

        UserUpdateProfile userUpdateProfile = new UserUpdateProfile();
        userUpdateProfile.setId(userId);
        userUpdateProfile.setEmail(userResponse.getEmail());
        userUpdateProfile.setFirstName(NEW_FIRST_NAME);
        userUpdateProfile.setLastName(currentLastName);
        userUpdateProfile.setPhone(currentPhone);
        userUpdateProfile.setUrlLogo(userResponse.getUrlLogo());
        userUpdateProfile.setStatus(userResponse.getStatus());
        userUpdateProfile.setRoleName(userResponse.getRoleName());

        userApi.updateUser(userId, userUpdateProfile);
        UserResponse userResponseWithNewFirstName = userApi.getUserById(userId);
        softAssert.assertEquals(userResponseWithNewFirstName.getFirstName(), NEW_FIRST_NAME,
                "User first name should be " + NEW_FIRST_NAME);

        userUpdateProfile.setLastName(NEW_LAST_NAME);
        userApi.updateUser(userId, userUpdateProfile);
        UserResponse userResponseWithNewLastName = userApi.getUserById(userId);
        softAssert.assertEquals(userResponseWithNewLastName.getLastName(), NEW_LAST_NAME,
                "User last name should be " + NEW_LAST_NAME);

        userUpdateProfile.setPhone(NEW_PHONE);
        userApi.updateUser(userId, userUpdateProfile);
        UserResponse userResponseWithNewPhone = userApi.getUserById(userId);
        softAssert.assertEquals(userResponseWithNewPhone.getPhone(), NEW_PHONE,
                "User phone name should be " + NEW_PHONE);

        userUpdateProfile.setFirstName(currentFirstName);
        userUpdateProfile.setLastName(currentLastName);
        userUpdateProfile.setPhone(currentPhone);

        userApi.updateUser(userId, userUpdateProfile);
        softAssert.assertEquals(userUpdateProfile.getFirstName(), currentFirstName,
                "User first name should be returned to " + currentFirstName);
        softAssert.assertEquals(userUpdateProfile.getLastName(), currentLastName,
                "User last name should be returned to " + currentLastName);
        softAssert.assertEquals(userUpdateProfile.getPhone(), currentPhone,
                "User phone should be returned to " + currentPhone);

        softAssert.assertAll();

    }



    @Test(description = "Negative API test for editing User profile with invalid data using API Client")
    @Description("[API. Edit profile] Verify that user can not save changes with invalid data (fields lastName and firstName)")
    @Issue("TUA-415")
    public void userCantEditProfileWithInvalidData() throws ApiException {

        String NUMBERS_ERROR_MESSAGE = " can`t contain numbers";
        String LENGTH_ERROR_MESSAGE = " can contain from 1 to 25 letters";
        String LANGUAGE_ERROR_MESSAGE = " can contain only ukrainian and english letters";

        UserApiImpl userApi = new UserApiImpl(apiClient);
        ApiResponse<UserResponse> userApiResponse = userApi.getUserByIdWithHttpInfo(userId);

        softAssert.assertEquals(userApiResponse.getStatusCode(), OK_STATUS,
                "Response status code of get user with id " + userId + " should be " + OK_STATUS);

        UserUpdateProfile invalidData = new UserUpdateProfile();

        invalidData.setId(userId);
        invalidData.setFirstName("Nastia1234");
        invalidData.setLastName("Kukh");
        invalidData.setPhone("999999922");
        invalidData.setEmail("soyec48727@busantei.com");
        invalidData.setRoleName("ROLE_MANAGER");
        invalidData.setStatus("true");

        assertInvalidProfileUpdate(userApi, invalidData,"firstName", NUMBERS_ERROR_MESSAGE);

        invalidData.setFirstName("fgfgfgfgfgfgfgfgfgfgfgfgfg");
        invalidData.setLastName("string");
        invalidData.setPhone("string");
        invalidData.setEmail("string");
        invalidData.setRoleName("string");
        invalidData.setStatus("string");

        assertInvalidProfileUpdate(userApi, invalidData,"firstName", LENGTH_ERROR_MESSAGE);

        invalidData.setFirstName("Anna!@£");

        assertInvalidProfileUpdate(userApi, invalidData,"firstName", LANGUAGE_ERROR_MESSAGE);

        invalidData.setFirstName("string");
        invalidData.setLastName("Mola12345");

        assertInvalidProfileUpdate(userApi, invalidData,"lastName", NUMBERS_ERROR_MESSAGE);

        invalidData.setLastName("sslorygndiejdpcngksoerfvgj");

        assertInvalidProfileUpdate(userApi, invalidData,"lastName", LENGTH_ERROR_MESSAGE);

        invalidData.setLastName("Mola!@£%^&");

        assertInvalidProfileUpdate(userApi, invalidData,"lastName", LANGUAGE_ERROR_MESSAGE);

        softAssert.assertAll();
    }

    private void assertInvalidProfileUpdate(UserApiImpl userApi, UserUpdateProfile invalidProfile, String field, String errorMessage) throws ApiException {
        try {
            userApi.updateUser(userId, invalidProfile);
        } catch (ApiException e) {
            String message = "\\\"" + field + "\\\"" + errorMessage;
            softAssert.assertTrue(e.getResponseBody().contains(message),
                    "Response message should contains message: " + message);
        }
    }


}

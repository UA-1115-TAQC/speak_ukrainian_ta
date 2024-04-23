package com.academy.apiClient.registration;

import com.academy.api.ApiException;
import com.academy.api.api.RegistrationApi;
import com.academy.api.model.UserProfile;
import com.academy.apiClient.runners.BaseAPIClientTestRunner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationTest extends BaseAPIClientTestRunner {
    private SoftAssert softAssert;

    private String ERROR_MESSAGE_EMPTY_FIRST_NAME = "\"firstName\" can`t be empty";
    private String ERROR_MESSAGE_EMPTY_LAST_NAME = "\"lastName\" can`t be empty";
    private String ERROR_MESSAGE_EMPTY_EMAIL = "\"email\" can`t be empty";
    private String ERROR_MESSAGE_EMPTY_PASSWORD = "\"password\" can`t be empty";
    private String ERROR_MESSAGE_EMPTY_PHONE = "\"phone\" can`t be empty";
    private String ERROR_MESSAGE_ROLE_NAME = "\"roleName\" can`t be empty";

    @BeforeMethod
    public void preconditions() {
        softAssert = new SoftAssert();
    }

    @Test(description = "Negative API test for registration in user with empty mandatory fields")
    @Description("[API.Реєстрація] Verify that a user with empty mandatory fields can`t be created")
    @Issue("TUA-420")
    public void testRegistrationUser() throws ApiException {
        UserProfile userProfile = new UserProfile();
        RegistrationApi registrationApi = new RegistrationApi(apiClient);

        userProfile.setFirstName("");
        userProfile.setLastName("");
        userProfile.setEmail("");
        userProfile.setPassword("");
        userProfile.setPhone("");
        userProfile.setRoleName("");

        assertInvalidRegistration(userProfile, registrationApi, ERROR_MESSAGE_EMPTY_FIRST_NAME);
        softAssert.assertAll();

    }

    private void assertInvalidRegistration(UserProfile userProfile, RegistrationApi registrationApi, String message) {
        try {
            registrationApi.signUp(userProfile);
        } catch (ApiException e) {
            String errorMessage = extractErrorMessage(e.getResponseBody());
            softAssert.assertTrue(errorMessage.contains(message),
                    "Response message should contains message: " + message);
        }
    }

    private String extractErrorMessage(String responseBody) {
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        return jsonObject.get("message").getAsString();
    }


    @Test(description = "API test for registration in user with empty mandatory fields")
    @Description("[API] Verify that user is not registered if at least one of the mandatory fields is empty")
    @Issue("TUA-377")
    public void testRegistrationUserWithOneEmptyField() throws ApiException {
        UserProfile userProfile = new UserProfile();
        RegistrationApi registrationApi = new RegistrationApi(apiClient);

        userProfile.setFirstName("");
        userProfile.setLastName("Когут");
        userProfile.setEmail("youremail");
        userProfile.setPassword("Kvitka1989@");
        userProfile.setPhone("0971151309");
        userProfile.setRoleName("ROLE_MANAGER");

        assertInvalidRegistration(userProfile, registrationApi, ERROR_MESSAGE_EMPTY_FIRST_NAME);

        userProfile.setFirstName("Ігор");
        userProfile.setLastName("");
        userProfile.setEmail("youremail");
        userProfile.setPassword("Kvitka1989@");
        userProfile.setPhone("0971151309");
        userProfile.setRoleName("ROLE_MANAGER");

        assertInvalidRegistration(userProfile, registrationApi, ERROR_MESSAGE_EMPTY_LAST_NAME);

        softAssert.assertAll();

    }

}

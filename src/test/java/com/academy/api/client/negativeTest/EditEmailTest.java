package com.academy.api.client.negativeTest;

import com.academy.ApiException;
import com.academy.api.UserApi;
import com.academy.api.UserUpdateProfile;
import com.academy.api.client.runners.LoginWithUserAPIClient;
import io.qameta.allure.Issue;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EditEmailTest extends LoginWithUserAPIClient {

    @Test
    @Description("Verify that user can not edit your 'Email'")
    @Issue("TUA-422")
    public void changeEmailByUser() {
        UserApi userApi = new UserApi(apiClient);

        UserUpdateProfile userUpdateProfile = getTestUserUpdateProfile();
        userUpdateProfile.setEmail("email@email.com@");

        ApiException exceptionActual = null;
        try {
            userApi.updateUser(userId, userUpdateProfile);
        } catch (ApiException ex) {
            exceptionActual = ex;
        }

        assertNotNull(exceptionActual);
        assertEquals(HttpStatus.SC_BAD_REQUEST, exceptionActual.getCode());
    }

    private UserUpdateProfile getTestUserUpdateProfile() {

        UserUpdateProfile userUpdateProfile = new UserUpdateProfile();
        userUpdateProfile.setFirstName(configProperties.getUserFirstname());
        userUpdateProfile.setLastName(configProperties.getUserLastname());
        userUpdateProfile.setId(configProperties.getUserId());
        userUpdateProfile.setPhone(configProperties.getUserPhone());
        userUpdateProfile.setUrlLogo(configProperties.getUserLogo());
        userUpdateProfile.setStatus(configProperties.getUserStatus());
        userUpdateProfile.setRoleName(configProperties.getUserRole());

        return userUpdateProfile;
    }
}



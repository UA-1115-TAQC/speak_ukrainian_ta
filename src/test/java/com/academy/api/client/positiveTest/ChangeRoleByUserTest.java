package com.academy.api.client.positiveTest;

import com.academy.ApiException;
import com.academy.api.UserApi;
import com.academy.api.UserResponse;
import com.academy.api.UserUpdateProfile;
import com.academy.api.client.runners.LoginWithUserAPIClient;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class ChangeRoleByUserTest extends LoginWithUserAPIClient {

    @Test
    public void changeRoleByUser() throws ApiException {
        UserApi userApi = new UserApi(apiClient);
        UserUpdateProfile userUpdateProfile = getTestUserUpdateProfile();
        userApi.updateUser(userId, userUpdateProfile);
        UserResponse userResponseWithNewRole = userApi.getUserById(userId);

        Assert.assertEquals(userResponseWithNewRole.getRoleName(), "ROLE_MANAGER");

    }

    private UserUpdateProfile getTestUserUpdateProfile() {
        UserUpdateProfile userUpdateProfile = new UserUpdateProfile();
        userUpdateProfile.setFirstName(configProperties.getUserFirstname());
        userUpdateProfile.setLastName(configProperties.getUserLastname());
        userUpdateProfile.setId(configProperties.getUserId());
        userUpdateProfile.setEmail(configProperties.getUserEmail());
        userUpdateProfile.setPhone(configProperties.getUserPhone());
        userUpdateProfile.setUrlLogo(configProperties.getUserLogo());
        userUpdateProfile.setStatus(configProperties.getUserStatus());
        userUpdateProfile.setRoleName("ROLE_MANAGER");

        return userUpdateProfile;
    }

    @AfterTest
    public void backToUserRole() throws ApiException {
        UserApi userApi = new UserApi(apiClient);

        UserUpdateProfile userUpdateProfile = new UserUpdateProfile();
        userUpdateProfile.setFirstName(configProperties.getUserFirstname());
        userUpdateProfile.setLastName(configProperties.getUserLastname());
        userUpdateProfile.setId(configProperties.getUserId());
        userUpdateProfile.setEmail(configProperties.getUserEmail());
        userUpdateProfile.setPhone(configProperties.getUserPhone());
        userUpdateProfile.setUrlLogo(configProperties.getUserLogo());
        userUpdateProfile.setStatus(configProperties.getUserStatus());
        userUpdateProfile.setRoleName("ROLE_USER");

        userApi.updateUser(userId, userUpdateProfile);
    }


}

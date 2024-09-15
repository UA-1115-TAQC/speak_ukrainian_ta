package com.academy.api.profilePage.positiveTests;

import com.academy.api.dto.request.UserDtoRequest;
import com.academy.api.runners.LoginWithUserRunnerAPI;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class ChangeRoleUserProfileTest extends LoginWithUserRunnerAPI {
    private Response response;

    @Test
    @Description("The user or manager can't change their role")
    @Issue("TUA-416")
    public void changeRoleUser() {

        UserDtoRequest userDtoRequest = UserDtoRequest.
                builder().
                roleName("ROLE_MANAGER").
                email(configProperties.getUserEmail()).
                firstName(configProperties.getUserFirstname()).
                lastName(configProperties.getUserLastname()).
                urlLogo(configProperties.getUserLogo()).
                id(configProperties.getUserId()).
                phone(configProperties.getUserPhone()).
                urlLogo(configProperties.getUserLogo()).
                status(configProperties.getUserStatus())
                .build();

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(userDtoRequest)
                .put("/api/user/" + userId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

    }

    @AfterTest
    public void backRoleToUser() {
        UserDtoRequest userDtoRequest = UserDtoRequest.
                builder().
                roleName("ROLE_USER").
                email(configProperties.getUserEmail()).
                firstName(configProperties.getUserFirstname()).
                lastName(configProperties.getUserLastname()).
                urlLogo(configProperties.getUserLogo()).
                id(configProperties.getUserId()).
                phone(configProperties.getUserPhone()).
                urlLogo(configProperties.getUserLogo()).
                status(configProperties.getUserStatus())
                .build();

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(userDtoRequest)
                .put("/api/user/" + userId);

    }

}



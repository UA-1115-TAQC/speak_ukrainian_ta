package com.academy.api.profilePage.positiveTests;

import com.academy.api.dto.request.UserDtoRequest;
import com.academy.api.runners.LoginWithManagerRunnerAPI;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class ChangeRoleManagerProfileTest extends LoginWithManagerRunnerAPI {

    private Response response;

    @Test
    @Description("The user or manager can't change their role")
    @Issue("TUA-416")
    public void changeRoleManagerToUser() {
        UserDtoRequest userDtoRequest = UserDtoRequest.
                builder().
                roleName("ROLE_USER").
                email(configProperties.getManagerEmail()).
                firstName(configProperties.getManagerFirstname()).
                lastName(configProperties.getManagerLastname()).
                urlLogo(configProperties.getManagerLogo()).
                id(configProperties.getManagerId()).
                phone(configProperties.getManagerPhone()).
                urlLogo(configProperties.getManagerLogo()).
                status(configProperties.getManagerStatus())
                .build();

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(userDtoRequest)
                .put("/api/user/" + userId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

    }

    @AfterTest
    public void backRoleToManager() {
        UserDtoRequest userDtoRequest = UserDtoRequest.
                builder().
                roleName("ROLE_MANAGER").
                email(configProperties.getManagerEmail()).
                firstName(configProperties.getManagerFirstname()).
                lastName(configProperties.getManagerLastname()).
                urlLogo(configProperties.getManagerLogo()).
                id(configProperties.getManagerId()).
                phone(configProperties.getManagerPhone()).
                urlLogo(configProperties.getManagerLogo()).
                status(configProperties.getManagerStatus())
                .build();

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(userDtoRequest)
                .put("/api/user/" + userId);

    }

}




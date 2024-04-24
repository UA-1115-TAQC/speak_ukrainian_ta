package com.academy.api.rest;

import com.academy.api.dto.request.UserDtoRequest;
import com.academy.api.rest.runners.LoginWithUserRunnerAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class MeasureResponseTimeTest extends LoginWithUserRunnerAPI {
    private Response response;

    @Test
    public void measureResponseTime() {
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

        ValidatableResponse valRes = response
                .then();

        valRes.time(Matchers.lessThan(500L));

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

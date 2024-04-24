package com.academy.api.rest.negativeTests;

import com.academy.api.dto.request.UserDtoRequest;
import com.academy.api.rest.runners.LoginWithUserRunnerAPI;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditEmailTest extends LoginWithUserRunnerAPI {

    private static final String ERROR_MESSAGE = "Email can`t be updated";
    private SoftAssert softAssert;

    @BeforeClass
    public void setUp() {

        softAssert = new SoftAssert();
    }

    @Test
    @Description("Verify that user can not edit your 'Email'")
    @Issue("TUA-422")
    public void changeEmailByUser() {
        UserDtoRequest userDtoRequest = UserDtoRequest.
                builder().
                roleName(configProperties.getUserRole()).
                email("email@email.com@").
                email(configProperties.getUserEmail()).
                firstName(configProperties.getUserFirstname()).
                lastName(configProperties.getUserLastname()).
                urlLogo(configProperties.getUserLogo()).
                id(configProperties.getUserId()).
                phone(configProperties.getUserPhone()).
                urlLogo(configProperties.getUserLogo()).
                status(configProperties.getUserStatus())
                .build();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(userDtoRequest)
                .put("/api/user/" + userId);

        JsonObject responseBody = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();
        String errorMessage = responseBody.get("message").getAsString();

        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
        softAssert.assertEquals(errorMessage, ERROR_MESSAGE);

    }
}

package com.academy.api;

import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class ApiRegistrationTest {
    @BeforeClass
    public static void setup(){
        RestAssured.baseURI ="http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/";
    }
    @Issue("TUA-418")
    @Test(description = "[API.Реєстрація] Verify that a user with already existing credentials cannot be created")
    public void verifyThatAUserWithExistingCredentialsCanNotBeCreated(){
        String requestBody = "{\n" +
                "   \"firstName\":\"Ted\",\n" +
                "   \"lastName\":\"Frankly\",\n" +
                "\"email\":\"tohep83544@undewp.com\"\n" +
                "   \"password\":\"Tohep8-9/\",\n" +
                "   \"phone\":\"0975483923\",\n" +
                "   \"roleName\":\"ROLE_MANAGER\"\n" +
                "}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/signup")
                .then()
                .statusCode(400).body("message", equalTo("Email tohep83544@undewp.com already exists"))
                .log().ifValidationFails();

    }
}

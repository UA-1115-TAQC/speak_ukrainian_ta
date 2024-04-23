package com.academy.api.restassured;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class ApiRegistrationTest {
    String ERROR_MESSAGE = "Email tohep83544@undewp.com already exists";
    SoftAssert softAssert;
    @BeforeClass
    public static void setup(){
        RestAssured.baseURI ="http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/";
    }
    @BeforeMethod
    public void renew(){
        softAssert = new SoftAssert();
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

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/signup");

        if(response.getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
            JsonObject responseBody = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();
            if(responseBody.has("message")) {
                String errorMessage = responseBody.get("message").getAsString();
                softAssert.assertEquals(errorMessage, ERROR_MESSAGE);
            } else {
                softAssert.fail("No error message found in the response");
            }
        } else {
            softAssert.fail("Expected status code 400, but received: " + response.getStatusCode());
        }
        softAssert.assertAll();
    }

}
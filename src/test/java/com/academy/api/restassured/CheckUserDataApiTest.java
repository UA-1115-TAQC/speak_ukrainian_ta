package com.academy.api.restassured;

import com.academy.ui.runners.utils.ConfigProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.sql.Time;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class CheckUserDataApiTest  {
    private ConfigProperties configProperties = new ConfigProperties();
    private static final long MAXIMUM_RESPONSE_TIME = 1000;
    public long startTime;
    public long endTime;
    SoftAssert softAssert;
    @BeforeClass
    public void setup(){
        RestAssured.baseURI ="http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/";
    }
    @BeforeMethod
    public void init(){
        softAssert = new SoftAssert();
    }
    @Issue("TUA-375")
    @Test(description = "[API]Verify if registered user can see personal data")
    public void verifyThatARegisteredUserCanSeePersonalData()  {
        String token = getAccessToken();
        sendRequestToCheckPersonalData(token);
    }
    private String getAccessToken(){
        return given()
                .contentType("application/json")
                .body("{ \"email\": \"" + configProperties.getAdminEmail() +
                        "\", \"password\": \"" + configProperties.getAdminPassword() + "\" }")
                .when()
                .post("/api/signin")
                .then()
                .extract()
                .path("accessToken");
    }
    private void sendRequestToCheckPersonalData(String token){
        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", 1)
                .when()
                .get("/api/user/{id}")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("Admin"))
                .body("lastName", equalTo("Admin"))
                .body("phone", equalTo("0671234567"))
                .body("email", equalTo(configProperties.getAdminEmail()))
                .body("roleName", equalTo("ROLE_ADMIN"))
                .body("urlLogo", equalTo("/static/images/user/avatar/user1.png"))
                .body("status", equalTo("true"));
    }
    @Test
    public void checkAdminJsonSchema(){
        String token = getAccessToken();
        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", 1)
                .when()
                .get("/api/user/{id}")
                .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("adminJsonSchema.json"));
    }
    @Test
    public void checkResponseTimeWhenCheckingPersonalData(){
        String token = getAccessToken();
        long actualTime = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", 1)
                .when()
                .get("/api/user/{id}").time();
        softAssert.assertTrue(actualTime <= MAXIMUM_RESPONSE_TIME);
        softAssert.assertAll();
    }
}
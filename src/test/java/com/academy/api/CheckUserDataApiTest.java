package com.academy.api;

import com.academy.ui.runners.utils.ConfigProperties;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class CheckUserDataApiTest  {
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
        ConfigProperties configProperties = new ConfigProperties();
        String token = given()
                .contentType("application/json")
                .body("{ \"email\": \"" + configProperties.getAdminEmail() +
                        "\", \"password\": \"" + configProperties.getAdminPassword() + "\" }")
                .when()
                .post("/api/signin")
                .then()
                .extract()
                .path("accessToken");
        long startTime = System.currentTimeMillis();
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
               // .body("password", equalTo(configProperties.getAdminPassword()));

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;
        System.out.println("Response time: " + responseTime + " milliseconds");
        long maximumResponseTime = 1000;
        softAssert.assertTrue(responseTime <= maximumResponseTime, "Response time exceeds maximum limit");
        softAssert.assertAll();
    }
}

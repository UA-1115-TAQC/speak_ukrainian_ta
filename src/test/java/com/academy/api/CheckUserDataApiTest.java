package com.academy.api;

import com.academy.ui.runners.utils.ConfigProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.examples.Utils;
import com.github.fge.jsonschema.main.JsonSchemaFactory;


import com.github.fge.jsonschema.main.JsonSchema;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

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
    public void verifyThatARegisteredUserCanSeePersonalData() throws ProcessingException, IOException {
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

        String responseBody = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", 1)
                .when()
                .get("/api/user/{id}")
                .then()
                .statusCode(200)
                .extract().asString();

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        //?

       /* JsonSchema schema = factory.getJsonSchema(RestAssured.baseURI+"components/schemas/UserResponse");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseBodyNode = mapper.readValue(responseBody, JsonNode.class);
        try {
            ProcessingReport report = schema.validate(responseBodyNode);
            if (!report.isSuccess()) {
                System.out.println("Validation failed! Report: " + report);
                softAssert.fail("JSON Schema validation failed!");
            }
        } catch (ProcessingException e) {
            System.err.println("Error during JSON Schema validation: ");
        }*/

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;
        System.out.println("Response time: " + responseTime + " milliseconds");
        long maximumResponseTime = 1000;
        softAssert.assertTrue(responseTime <= maximumResponseTime, "Response time exceeds maximum limit");
        softAssert.assertAll();
    }
}

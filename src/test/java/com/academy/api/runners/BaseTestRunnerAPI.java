package com.academy.api.runners;

import com.academy.ui.runners.utils.ConfigProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;

public class BaseTestRunnerAPI {

    protected ConfigProperties configProperties;
    protected String accessToken;
    protected String userId;

    @BeforeClass
    public void setUpClass() {
        configProperties = new ConfigProperties();
        RestAssured.baseURI = configProperties.getBaseUrl();
    }

    protected String getAccessToken(String email, String password) {
        String requestBody = "{" +
                "\"email\":\"" + email + "\", " +
                "\"password\":\"" + password +
                "\"}";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/signin");

        if (response.getStatusCode() == HttpStatus.SC_OK) {
            userId = response.jsonPath().getString("id");
            return response.jsonPath().getString("accessToken");
        } else {
            throw new RuntimeException("Login failed. Status code: " + response.getStatusCode());
        }
    }
}

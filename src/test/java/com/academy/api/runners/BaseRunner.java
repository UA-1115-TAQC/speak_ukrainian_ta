package com.academy.api.runners;

import com.academy.ui.runners.utils.ConfigProperties;
import io.restassured.RestAssured;
import org.json.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.util.Optional;

public class BaseRunner {
    protected static ConfigProperties configProperties;

    @BeforeClass
    public void setUp() {
        configProperties = new ConfigProperties();
        RestAssured.baseURI = configProperties.getBaseUrl();
    }

    public String getAccessToken(String email, String password) {
        JSONObject requestBody = new JSONObject()
                .put("email", email)
                .put("password", password);

        Response loginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/signin");

        return Optional.of(loginResponse)
                .filter(response -> response.getStatusCode() == 200)
                .map(r -> new JSONObject(r.getBody().asString()))
                .map(json -> json.getString("accessToken"))
                .orElseThrow(() -> new RuntimeException("Invalid sign-in. Status code: "
                        + loginResponse.getStatusCode()));
    }

    public String getRefreshedAccessToken(String refreshToken) {
        JSONObject requestBody = new JSONObject()
                .put("refreshToken", refreshToken);

        Response loginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/token/refresh");

        return Optional.of(loginResponse)
                .filter(response -> response.getStatusCode() == 200)
                .map(r -> new JSONObject(r.getBody().asString()))
                .map(json -> json.getString("accessToken"))
                .orElseThrow(() -> new RuntimeException("Refresh token is invalid or has been expired. "
                        + " Status code: " + loginResponse.getStatusCode()));
    }
}

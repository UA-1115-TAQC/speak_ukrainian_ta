package com.academy.api.runners;

import com.academy.ui.runners.utils.ConfigProperties;
import io.restassured.RestAssured;
import org.json.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.util.Optional;

public class BaseAPIRunner {
    protected static ConfigProperties configProperties;

    @BeforeClass
    public void setUp() {
        configProperties = new ConfigProperties();
        RestAssured.baseURI = configProperties.getBaseUrl();
    }

    public Response getAuthResponseBody(String email, String password) {
        JSONObject requestBody = new JSONObject()
                .put("email", email)
                .put("password", password);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/signin");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Invalid request, password or email incorrect."
                    + " Status code: " + response.getStatusCode());
        }
        return response;
    }

    public String getUserId(Response response) {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        return jsonResponse.getString("id");
    }

    public String getAccessToken(Response response) {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        return jsonResponse.getString("accessToken");
    }

    public String getRefreshToken(Response response) {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        return jsonResponse.getString("refreshToken");
    }

    public String getRefreshedAccessToken(Response response, String refreshToken) {
        JSONObject requestBody = new JSONObject()
                .put("refreshToken", refreshToken);

        Response loginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/token/refresh");

        return Optional.of(loginResponse)
                .filter(res -> res.getStatusCode() == 200)
                .map(r -> new JSONObject(r.getBody().asString()))
                .map(json -> json.getString("accessToken"))
                .orElseThrow(() -> new RuntimeException("Refresh token is invalid or has been expired. "
                        + " Status code: " + loginResponse.getStatusCode()));
    }
}

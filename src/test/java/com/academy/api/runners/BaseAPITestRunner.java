package com.academy.api.runners;

import com.academy.api.dto.login.LoginRequestDto;
import com.academy.api.dto.login.LoginResponseDto;
import com.academy.ui.runners.utils.ConfigProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;

public class BaseAPITestRunner {
    protected static ConfigProperties configProperties;
    protected String accessToken;
    protected String userId;
    protected String refreshToken;

    @BeforeClass
    public void setUp() {
        configProperties = new ConfigProperties();
        RestAssured.baseURI = configProperties.getBaseUrl();
    }

    public LoginResponseDto getAuthResponseBody(String email, String password) {

        Response response = RestAssured.with()
                .body(new LoginRequestDto(email, password))
                .contentType(ContentType.JSON)
                .post("/api/signin");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Invalid request, password or email incorrect."
                    + " Status code: " + response.getStatusCode());
        }
        return response.then()
                .extract()
                .body()
                .as(LoginResponseDto.class);
    }

    public String getUserId(LoginResponseDto response) {
        userId = String.valueOf(response.getId());
        return userId;
    }

    public String getAccessToken(LoginResponseDto response) {
        accessToken = response.getAccessToken();
        return accessToken;
    }

    public String getRefreshToken(LoginResponseDto response) {
        refreshToken = response.getRefreshToken();
        return refreshToken;
    }

    public String getRefreshedAccessToken(String refreshToken) {
        JSONObject requestBody = new JSONObject().put("refreshToken", refreshToken);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/token/refresh");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Refresh token is invalid or has been expired. "
                    + " Status code: " + response.getStatusCode());
        }
        return response.jsonPath().getString("accessToken");
    }
}

package com.academy.api.runners;

import com.academy.api.dto.LoginResponseDto;
import com.academy.ui.runners.utils.ConfigProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;

public class BaseAPIRunner {
    protected static ConfigProperties configProperties;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public record LoginRequestDto(String email,
                                  String password
    ) {
    }

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
        return String.valueOf(response.getId());
    }

    public String getAccessToken(LoginResponseDto response) {
        return response.getAccessToken();
    }

    public String getRefreshToken(LoginResponseDto response) {
        return response.getRefreshToken();
    }

    public String getRefreshedAccessToken(LoginResponseDto loginResponseDto) {
        JSONObject requestBody = new JSONObject().put("refreshToken", getRefreshToken(loginResponseDto));

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

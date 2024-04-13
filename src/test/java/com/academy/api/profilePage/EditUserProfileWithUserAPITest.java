package com.academy.api.profilePage;

import com.academy.api.runners.LoginWithUserAPITestRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

import static org.hamcrest.Matchers.equalTo;


public class EditUserProfileWithUserAPITest extends LoginWithUserAPITestRunner {

    @Test(description = "API test for editing User profile with valid data")
    @Description("[API. Edit profile] User can edit profile with valid data")
    @Issue("TUA-408")
    public void userCanEditProfileWithValidData() {

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/user/" + userId)
                .then()
                .statusCode(200)
                .log()
                .ifValidationFails();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/user/" + userId);

        JsonPath jsonPath = response.jsonPath();

        String currentFirstName = jsonPath.get("firstName");
        String currentLastName = jsonPath.get("lastName");
        String currentPhone = jsonPath.get("phone");

        String newFirstName = "Anna";
        String newLastName = "Kukarska";
        String newPhone = "0234567891";

        LinkedHashMap<String, Object> jsonBodyMap = new LinkedHashMap<>();
        jsonBodyMap.put("id", userId);
        jsonBodyMap.put("email", jsonPath.get("email"));
        jsonBodyMap.put("firstName", newFirstName);
        jsonBodyMap.put("lastName", jsonPath.get("lastName"));
        jsonBodyMap.put("phone", jsonPath.get("phone"));
        jsonBodyMap.put("urlLogo", jsonPath.get("urlLogo"));
        jsonBodyMap.put("status", jsonPath.get("status"));
        jsonBodyMap.put("roleName", jsonPath.get("roleName"));

        String requestedBodyWithNewName = mapToJson(jsonBodyMap);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(requestedBodyWithNewName)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(200)
                .log()
                .ifValidationFails();

        jsonBodyMap.put("lastName", newLastName);


        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(requestedBodyWithNewName)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(200)
                .log()
                .ifValidationFails();

        jsonBodyMap.put("phone", newPhone);
        requestedBodyWithNewName = mapToJson(jsonBodyMap);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(requestedBodyWithNewName)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(200)
                .log()
                .ifValidationFails();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/user/" + userId)
                .then()
                .statusCode(200)
                .assertThat()
                .body("firstName", equalTo(newFirstName))
                .body("lastName", equalTo(newLastName))
                .body("phone", equalTo(newPhone))
                .log()
                .ifValidationFails();


        jsonBodyMap.put("firstName", currentFirstName);
        jsonBodyMap.put("lastName", currentLastName);
        jsonBodyMap.put("phone", currentPhone);
        requestedBodyWithNewName = mapToJson(jsonBodyMap);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(requestedBodyWithNewName)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(200)
                .log()
                .ifValidationFails();

    }

    private String mapToJson(LinkedHashMap<String, Object> jsonBodyMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(jsonBodyMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

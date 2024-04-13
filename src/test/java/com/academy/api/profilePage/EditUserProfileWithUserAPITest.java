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

    @Test(description = "Positive API test for editing User profile with valid data")
    @Description("[API. Edit profile] User can edit profile with valid data")
    @Issue("TUA-408")
    public void userCanEditProfileWithValidData() {
        String NEW_FIRST_NAME = "Anna";
        String NEW_LAST_NAME = "Kukarska";
        String NEW_PHONE = "0234567891";

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

        LinkedHashMap<String, Object> jsonBodyMap = new LinkedHashMap<>();
        jsonBodyMap.put("id", userId);
        jsonBodyMap.put("email", jsonPath.get("email"));
        jsonBodyMap.put("firstName", NEW_FIRST_NAME);
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

        jsonBodyMap.put("lastName", NEW_LAST_NAME);


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

        jsonBodyMap.put("phone", NEW_PHONE);
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
                .body("firstName", equalTo(NEW_FIRST_NAME))
                .body("lastName", equalTo(NEW_LAST_NAME))
                .body("phone", equalTo(NEW_PHONE))
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


    @Test(description = "Negative API test for editing User profile with invalid data")
    @Description("[API. Edit profile] Verify that user can not save changes with invalid data (fields lastName and firstName)")
    @Issue("TUA-415")
    public void userCantEditProfileWithInvalidData() {
        String INVALID_DATA_WITH_NUMBERS_IN_FIRSTNAME = "{\n" +
                "    \"id\": " + userId + ",\n" +
                "    \"firstName\": \"Nastia1234\",\n" +
                "    \"lastName\": \"Kukh\",\n" +
                "    \"phone\": \"999999922\",\n" +
                "    \"email\": \"soyec48727@busantei.com\",\n" +
                "    \"roleName\": \"ROLE_MANAGER\",\n" +
                "    \"urlLogo\": null,\n" +
                "    \"status\": \"true\"\n" +
                "}";

        String INVALID_DATA_WITH_MORE_25_SYMBOLS_IN_FIRSTNAME = "{\n" +
                "  \"id\": " + userId + ",\n" +
                "  \"firstName\": \"fgfgfgfgfgfgfgfgfgfgfgfgfg\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"roleName\": \"string\",\n" +
                "  \"urlLogo\": \"string\",\n" +
                "  \"status\": \"string\"\n" +
                "}";

        String INVALID_DATA_WITH_CHARACTERS_IN_FIRSTNAME = "{\n" +
                "  \"id\": " + userId + ",\n" +
                "  \"firstName\": \"Anna!@£\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"roleName\": \"string\",\n" +
                "  \"urlLogo\": \"string\",\n" +
                "  \"status\": \"string\"\n" +
                "}";

        String INVALID_DATA_WITH_NUMBERS_IN_LASTNAME = "{\n" +
                "  \"id\": " + userId + ",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"Mola12345\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"roleName\": \"string\",\n" +
                "  \"urlLogo\": \"string\",\n" +
                "  \"status\": \"string\"\n" +
                "}";

        String INVALID_DATA_WITH_MORE_25_SYMBOLS_IN_LASTNAME = "{\n" +
                "  \"id\": " + userId + ",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"sslorygndiejdpcngksoerfvgj\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"roleName\": \"string\",\n" +
                "  \"urlLogo\": \"string\",\n" +
                "  \"status\": \"string\"\n" +
                "}";

        String INVALID_DATA_WITH_CHARACTERS_IN_LASTNAME = "{\n" +
                "  \"id\": " + userId + ",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"Mola!@£%^&\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"roleName\": \"string\",\n" +
                "  \"urlLogo\": \"string\",\n" +
                "  \"status\": \"string\"\n" +
                "}";

        String NUMBERS_ERROR_MESSAGE = " can`t contain numbers";
        String LENGTH_ERROR_MESSAGE = " can contain from 1 to 25 letters";
        String LANGUAGE_ERROR_MESSAGE = " can contain only ukrainian and english letters";


        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/user/" + userId)
                .then()
                .statusCode(200)
                .log()
                .ifValidationFails();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(INVALID_DATA_WITH_NUMBERS_IN_FIRSTNAME)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(400)
                .body("message", equalTo("\"firstName\"" + NUMBERS_ERROR_MESSAGE))
                .log()
                .ifValidationFails();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(INVALID_DATA_WITH_MORE_25_SYMBOLS_IN_FIRSTNAME)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(400)
                .body("message", equalTo("\"firstName\"" + LENGTH_ERROR_MESSAGE))
                .log()
                .ifValidationFails();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(INVALID_DATA_WITH_CHARACTERS_IN_FIRSTNAME)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(400)
                .body("message", equalTo("\"firstName\"" + LANGUAGE_ERROR_MESSAGE))
                .log()
                .ifValidationFails();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(INVALID_DATA_WITH_NUMBERS_IN_LASTNAME)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(400)
                .body("message", equalTo("\"lastName\"" + NUMBERS_ERROR_MESSAGE))
                .log()
                .ifValidationFails();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(INVALID_DATA_WITH_MORE_25_SYMBOLS_IN_LASTNAME)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(400)
                .body("message", equalTo("\"lastName\"" + LENGTH_ERROR_MESSAGE))
                .log()
                .ifValidationFails();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(INVALID_DATA_WITH_CHARACTERS_IN_LASTNAME)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(400)
                .body("message", equalTo("\"lastName\"" + LANGUAGE_ERROR_MESSAGE))
                .log()
                .ifValidationFails();
    }

}
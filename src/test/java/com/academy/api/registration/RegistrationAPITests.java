package com.academy.api.registration;

import com.academy.api.runners.BaseAPITestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class RegistrationAPITests extends BaseAPITestRunner {

    @Test(description = "Negative API test for registration in user with empty mandatory fields")
    @Description("[API.Реєстрація] Verify that a user with empty mandatory fields can`t be created")
    @Issue("TUA-420")
    public void testThatUserCanNotBeCreatedWithEmptyMandatoryFields() {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("firstName", "");
        requestParams.put("lastName", "");
        requestParams.put("email", "");
        requestParams.put("phone", "");
        requestParams.put("roleName", "");

        RestAssured.given()
                .body(requestParams)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/signup")
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("\"firstName\" can`t be empty"))
                .log()
                .ifValidationFails();
    }

    @Test(description = "Verify that a new user is not registered when all entered data upon registration" +
            " is valid and at least one of the mandatory fields is empty.")
    @Description("[API] Verify that user is not registered if at least one of the mandatory fields is empty")
    @Issue("TUA-377")

    public void testUserCanNotBeCreatedWithOneEmptyField() {
        String EMPTY_FIRST_NAME = "{\n" +
                "    \"firstName\": \"\",\n" +
                "    \"lastName\": \"Когут\",\n" +
                "    \"email\": \"youremail\",\n" +
                "    \"password\": \"Kvitka1989@\",\n" +
                "    \"phone\": \"0971151309\",\n" +
                "    \"roleName\": \"ROLE_MANAGER\",\n" +
                "}";

        Map<String, Object> LAST_NAME = new HashMap<>();
        LAST_NAME.put("firstName", "Ігор");
        LAST_NAME.put("lastName", "");
        LAST_NAME.put("email", "youremail");
        LAST_NAME.put("password", "Kvitka1989@");
        LAST_NAME.put("phone", "0971151309");
        LAST_NAME.put("roleName", "ROLE_MANAGER");

//        String EMPTY_LAST_NAME = "{\n" +
//                "    \"firstName\": \"Ігор\",\n" +
//                "    \"lastName\": \"\",\n" +
//                "    \"email\": \"youremail\",\n" +
//                "    \"password\": \"Kvitka1989@\",\n" +
//                "    \"phone\": \"0971151309\",\n" +
//                "    \"roleName\": \"ROLE_MANAGER\",\n" +
//                "}";

        String EMPTY_EMAIL = "{\n" +
                "    \"firstName\": \"Ігор\",\n" +
                "    \"lastName\": \"Когут\",\n" +
                "    \"email\": \"\",\n" +
                "    \"password\": \"Kvitka1989@\",\n" +
                "    \"phone\": \"0971151309\",\n" +
                "    \"roleName\": \"ROLE_MANAGER\",\n" +
                "}";

        String EMPTY_PASSWORD = "{\n" +
                "    \"firstName\": \"Ігор\",\n" +
                "    \"lastName\": \"Когут\",\n" +
                "    \"email\": \"youremail\",\n" +
                "    \"password\": \"\",\n" +
                "    \"phone\": \"0971151309\",\n" +
                "    \"roleName\": \"ROLE_MANAGER\",\n" +
                "}";

        String EMPTY_PHONE = "{\n" +
                "    \"firstName\": \"Ігор\",\n" +
                "    \"lastName\": \"Когут\",\n" +
                "    \"email\": \"youremail\",\n" +
                "    \"password\": \"Kvitka1989@\",\n" +
                "    \"phone\": \"\",\n" +
                "    \"roleName\": \"ROLE_MANAGER\",\n" +
                "}";

        String EMPTY_ROLE_NAME = "{\n" +
                "    \"firstName\": \"Ігор\",\n" +
                "    \"lastName\": \"Когут\",\n" +
                "    \"email\": \"youremail\",\n" +
                "    \"password\": \"Kvitka1989@\",\n" +
                "    \"phone\": \"0971151309\",\n" +
                "    \"roleName\": \"\",\n" +
                "}";

        String EMPTY_FIRST_NAME_ERROR_MESSAGES = "\"firstName\" can`t be empty";
        String EMPTY_LAST_NAME_ERROR_MESSAGES = "\"lastName\" can`t be empty";
        String EMPTY_EMAIL_ERROR_MESSAGES = "\"email\" can`t be empty";
        String EMPTY_PASSWORD_ERROR_MESSAGES = "\"password\" can`t be empty";
        String EMPTY_PHONE_ERROR_MESSAGES = "\"phone\" can`t be empty";
        String ROLE_NAME_ERROR_MESSAGES = "\"roleName\" can`t be empty";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(EMPTY_FIRST_NAME)
                .when()
                .post("/api/signup")
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo(EMPTY_LAST_NAME_ERROR_MESSAGES))
                .log()
                .ifValidationFails();

    }

}

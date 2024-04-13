package com.academy.api.profilePage;

import com.academy.api.runners.LoginWithUserAPITestRunner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class UserSchemaValidationWithUserTest extends LoginWithUserAPITestRunner {

    @Test(description = "Validate scheme for User Response")
    public void validateSchemeForUserResponse() {
        String USER_SCHEME = "schemas/user_response_scheme.json";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/user/" + userId)
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath(USER_SCHEME))
                .log()
                .ifValidationFails();
    }

}

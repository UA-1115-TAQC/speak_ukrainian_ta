package com.academy.api.schema_validation;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.academy.api.runners.BaseAPITestRunner;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class ClubSchemaValidationTest extends BaseAPITestRunner {
    private static final String CLUB_PATH_BY_ID = "/api/club/%d";

    @Test
    @Description("Validate club response schema for club with required parameters")
    public void validate_Club_Response_Schema_Required_Parameters() {
        RestAssured.get(CLUB_PATH_BY_ID.formatted(546))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/club-schema.json"));
    }
}

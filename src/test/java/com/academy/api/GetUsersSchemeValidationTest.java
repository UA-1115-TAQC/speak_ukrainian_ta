package com.academy.api;

import com.academy.api.runners.LoginWithAdminRunnerAPI;
import com.academy.ui.runners.utils.ConfigProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetUsersSchemeValidationTest extends LoginWithAdminRunnerAPI {

    protected ConfigProperties configProperties;


    @Test
    public void getAllUsersScheme() {
        given().
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + accessToken).
                log().all().
                when().
                get("/api/users").
                then().
                statusCode(200).
                log().all().

                assertThat().body(matchesJsonSchemaInClasspath("responseGetUsers.json"));
    }
}

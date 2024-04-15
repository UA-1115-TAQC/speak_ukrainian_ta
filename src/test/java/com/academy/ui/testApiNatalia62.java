package com.academy.ui;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class testApiNatalia62 {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/api";
    }

    //positive
    // TUA-375
    @Test
    public void testRegisteredUserCanSeePersonalData() {
        String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE3MTMxNzE2NjB9." +
                "TYgYrqZtRGDvV5fUrKb13FhcPKNWuVrRH3jlq3F5gGTUzQB-881n_maSYUH-_mmxZEI1PrEzyezyRI5MyVCG8w";
        given()
                .pathParams("id", 1)
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get("/user/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("firstName", equalTo("Admin"))
                .body("lastName", equalTo("Admin"))
                .body("phone", equalTo("0671234567"))
                .body("email", equalTo("admin@gmail.com"))
                .body("password", equalTo("$2a$10$2upubYTMpxmzDso0a/8xfuEg1xeFN0bw.GqMW37XXg3PzpWnvfv7q"))
                .body("roleName", equalTo("ROLE_ADMIN"))
                .body("urlLogo", equalTo("/static/images/user/avatar/user1.png"))
                .body("status", equalTo("true"));
    }


    //negative
    //TUA-420
    @Test
    public void testThatUserCanNotBeCreatedWithEmptyMandatoryFields() {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("firstName", "");
        requestParams.put("lastName", "");
        requestParams.put("email", "");
        requestParams.put("password", "");
        requestParams.put("phone", "");
        requestParams.put("roleName", "");

        given()
                .body(requestParams)
                .contentType("application/json")
                .when()
                .post("/signup")
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("\"firstName\" can`t be empty"));
    }


    //schema validation test
    @Test
    public void checkSchema() {
        String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE3MTMxNzE2NjB9." +
                "TYgYrqZtRGDvV5fUrKb13FhcPKNWuVrRH3jlq3F5gGTUzQB-881n_maSYUH-_mmxZEI1PrEzyezyRI5MyVCG8w";
        given()
                .pathParams("id", 1)
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get("/user/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body(matchesJsonSchemaInClasspath("user_data_schema.json"));
    }

    //response time test
    @Test
    public void testResponseTime() {
        String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE3MTMxNzE2NjB9." +
                "TYgYrqZtRGDvV5fUrKb13FhcPKNWuVrRH3jlq3F5gGTUzQB-881n_maSYUH-_mmxZEI1PrEzyezyRI5MyVCG8w";
        long responseTime = RestAssured.given()
                .pathParams("id", 1)
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get("/user/{id}")
                .time();

        assert responseTime < 1000 : "Response time is too long: " + responseTime + " ms";
    }

}

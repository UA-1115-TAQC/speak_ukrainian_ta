package com.academy.api.userData;

import com.academy.api.runners.LoginWithAdminAPITestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.Matchers.equalTo;

public class TestAdminPersonalData extends LoginWithAdminAPITestRunner {

    @Test(description = "Positive API test for verifying that admin can see personal data")
    @Description("[API]Verify if registered user can see personal data")
    @Issue("TUA-375")
    public void testRegisteredUserCanSeePersonalData() {
        RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/user/" + userId)
                .then()
                .statusCode(200)
                .body("id", equalTo(configProperties.getAdminId()))
                .body("firstName", equalTo(configProperties.getAdminFirstName()))
                .body("lastName", equalTo(configProperties.getAdminLastName()))
                .body("phone", equalTo(configProperties.getAdminPhone()))
                .body("email", equalTo(configProperties.getAdminEmail()))
                .body("roleName", equalTo(configProperties.getAdminRoleName()))
                .body("urlLogo", equalTo(configProperties.getAdminUrlLogo()))
                .body("status", equalTo(configProperties.getAdminStatus()));
    }

    @Test(description = "Schema validation test for admin personal data")
    public void testSchemaValidationForAdminPersonalData() {
        RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/user/" + userId)
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user_data_schema.json"));
    }

    @Test(description = "Verify response time of API")
    public void testResponseTime() {
        long responseTime = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/user/" + userId)
                .time();
        assert responseTime < 1000 : "Response time is too long: " + responseTime + " ms";
    }
}

package com.academy.api;

import com.academy.ui.runners.utils.ConfigProperties;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class CheckUserDataApiTest  {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI ="http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/";
    }

    @Issue("TUA-375")
    @Test(description = "[API]Verify if registered user can see personal data")
    public void verifyThatARegisteredUserCanSeePersonalData()  {
        ConfigProperties configProperties = new ConfigProperties();
        String token = given()
                .contentType("application/json")
                .body("{ \"email\": \"" + configProperties.getAdminEmail() +
                        "\", \"password\": \"" + configProperties.getAdminPassword() + "\" }")
                .when()
                .post("/api/signin")
                .then()
                .extract()
                .path("accessToken");

        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", 1)
                .when()
                .get("/api/user/{id}")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("Admin"))
                .body("lastName", equalTo("Admin"))
                .body("phone", equalTo("0671234567"))
                .body("email", equalTo(configProperties.getAdminEmail()))
                .body("roleName", equalTo("ROLE_ADMIN"))
                .body("urlLogo", equalTo("/static/images/user/avatar/user1.png"))
                .body("status", equalTo("true"))
                .body("password", equalTo(configProperties.getAdminPassword()));
    }
}

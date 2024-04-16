package com.academy.api.pages.editProfile;

import com.academy.api.dto.user.UserRequestDto;
import com.academy.api.dto.user.UserResponseDto;
import com.academy.api.runners.LoginWithUserAPITestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;

public class EditProfileWithUserAPITest extends LoginWithUserAPITestRunner {
    private UserResponseDto userResponseDto;
    private Response userResponse;

    @BeforeMethod
    public void userSigninSetUp() {
         userResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/user/" + userId);

        if (userResponse.statusCode() == 200) {
            userResponseDto = userResponse.then()
                    .extract()
                    .body()
                    .as(UserResponseDto.class);
        } else {
            userResponse
                    .then()
                    .log()
                    .ifValidationFails();
        }
    }

    @Test
    @Description("The user or manager can change their role on MANAGER")
    @Issue("TUA-417")
    public void checkUserCanChangeRole() {
        UserRequestDto userRequestDto = new UserRequestDto(userResponseDto.getId(),
                userResponseDto.getFirstName(), userResponseDto.getLastName(),
                userResponseDto.getPhone(), userResponseDto.getEmail(),"ROLE_MANAGER",
                userResponseDto.getUrlLogo(), userResponseDto.getStatus());

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(userRequestDto)
                .put("/api/user/" + userId)
                .then()
                .statusCode(200)
                .log()
                .ifValidationFails();

        userResponseDto.setRoleName("ROLE_USER");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(userRequestDto)
                .put("/api/user/" + userId)
                .then()
                .statusCode(200)
                .log()
                .ifValidationFails();
    }

    @Test(description = "Description in JIRA for this test case incorrect!")
    @Description("The user or manager can not change their role on ADMIN")
    @Issue("TUA-417")
    public void checkUserCanNotChangeRole() {
        UserRequestDto userRequestDto = new UserRequestDto(203,
                userResponseDto.getFirstName(), userResponseDto.getLastName(),
                userResponseDto.getPhone(), userResponseDto.getEmail(),"ROLE_ADMIN",
                userResponseDto.getUrlLogo(), userResponseDto.getStatus());

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(userRequestDto)
                .put("/api/user/" + userRequestDto.id())
                .then()
                .statusCode(403)
                .body("message", containsString("Forbidden"))
                .body("path", containsString("dev/api/user/203"))
                .log()
                .ifValidationFails();
    }

    @Test
    public void validateUserResponseSchema() {
        userResponse.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemes/user-response.json"))
                .log()
                .ifValidationFails();
    }
}

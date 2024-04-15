package com.academy.api.pages.editProfile;

import com.academy.api.dto.UserResponseDto;
import com.academy.api.runners.LoginWithAdminAPITestRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class EditProfileWithAdminAPITest extends LoginWithAdminAPITestRunner {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test(enabled = true, dataProvider = "invalidUserData")
    @Description("Verify that user can not save changes with invalid data: lastName and firstName")
    @Issue("TUA-415")
    public void checkEditUserProfileWithInvalidData(String id,
                                                    String firstName,
                                                    String lastName,
                                                    String phone,
                                                    String email,
                                                    String roleName,
                                                    String urlLogo,
                                                    String status) {

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/user/" + userId)
                .then()
                .statusCode(200)
                .log()
                .ifValidationFails();

//        UserResponseDto userResponseDto = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + accessToken)
//                .when()
//                .get("/api/user/" + userId)
//                .then()
//                .extract()
//                .body()
//                .as(UserResponseDto.class);

//        try {
//            requestBody = objectMapper.writeValueAsString(userResponseDto);
//        } catch (JsonProcessingException err) {
//            throw new RuntimeException("Invalid mapping of requestBody. ", err);
//        }

        String requestBody = String.format("{"
                        + "\"id\": %s, "
                        + "\"firstName\": \"%s\", "
                        + "\"lastName\": \"%s\", "
                        + "\"phone\": \"%s\", "
                        + "\"email\": \"%s\", "
                        + "\"roleName\": \"%s\", "
                        + "\"urlLogo\": \"%s\","
                        + " \"status\": \"%s\"}",
                userId, firstName, lastName, phone, email, roleName, urlLogo, status);

        Response authorization = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody)
                .when()
                .put("/api/user/" + userId);

        System.out.println(authorization.getBody().asString());

//                .then()
//                .statusCode(400)
//                .body("message", equalTo("\"firstName\" can`t contain numbers"))
//                .log()
//                .ifValidationFails();
    }

    @DataProvider(name = "invalidUserData")
    private Object[][] userInvalidData() {
        return new Object[][] {
                {"30", "string", "string", "string", "string", "string", "sting", "string"}
//                {new UserResponseDto(203, "Nastia1234", "Kukh", "999999922", "soyec48727@busantei.com", "ROLE_MANAGER", null, "true")},
//                {new UserResponseDto(0, "Anna!@£", "string", "string", "string", "string", "string", "string")},
//                {new UserResponseDto(0, "string", "Mola!@£%^&", "string", "string", "string", "string", "string")},
//                {new UserResponseDto(0, "string", "sslorygndiejdpcngksoerfvg", "string", "string", "string", "string", "string")},
//                {new UserResponseDto(0, "string", "Mola12345", "string", "string", "string", "string", "string")}
        };
    }
}

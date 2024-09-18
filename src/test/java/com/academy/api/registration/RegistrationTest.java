package com.academy.api.registration;

import static io.restassured.RestAssured.with;

import com.academy.api.registration.dto.RegistrationRequestDto;
import com.academy.api.registration.dto.RegistrationResponseDto;
import com.academy.api.runners.BaseAPITestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationTest extends BaseAPITestRunner {
    private static final String SIGN_UP_PATH = "/api/signup";
    private static final String DELETE_USER_PATH = "/api/user/%d";
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }

    @Test
    public void create_User_Valid_Data_Ok() {
        var registrationDto = new RegistrationRequestDto(
                "test.email@gmail.com", "User",
                "User", "0671234567", "Entermail13/", "ROLE_USER");
        Response response = with()
                .body(registrationDto)
                .contentType(ContentType.JSON)
                .post(SIGN_UP_PATH)
                .then()
                .extract()
                .response();
        softAssert.assertTrue(response.statusCode() == HttpStatus.SC_OK,
                "Status code should be %d".formatted(HttpStatus.SC_OK));

        RegistrationResponseDto responseDto = response.as(RegistrationResponseDto.class);
        softAssert.assertNotNull(responseDto.getId(),
                "Id must not be null");
        softAssert.assertEquals(responseDto.getEmail(), registrationDto.email(),
                "Response email must be '%s'".formatted(registrationDto.email()));
        softAssert.assertEquals(responseDto.getRoleName(), registrationDto.roleName(),
                "Response role must be '%s'".formatted(registrationDto.roleName()));

        deleteUserById(responseDto.getId());
        softAssert.assertAll();
    }

    @Test
    @Description("[API.Реєстрація] Verify that a user with non-existing role can`t be created")
    @Issue("TUA-419")
    public void register_User_Invalid_Role_Status_NotFound() {
        with()
                .body(new RegistrationRequestDto("test.email@ukr.net", "User",
                        "User", "0671234567", "Entermail13/", "ROLE_CUSTOMER"))
                .contentType(ContentType.JSON)
                .when()
                .post(SIGN_UP_PATH)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private void deleteUserById(Integer userId) {
        var adminToken = getAccessToken(configProperties.getAdminEmail(),
                configProperties.getAdminPassword());

        with()
                .header("Authorization", "Bearer %s"
                        .formatted(adminToken))
                .delete(DELETE_USER_PATH.formatted(userId))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}

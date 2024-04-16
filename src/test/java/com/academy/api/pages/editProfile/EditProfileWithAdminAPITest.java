package com.academy.api.pages.editProfile;

import com.academy.api.dto.user.UserRequestDto;
import com.academy.api.dto.user.UserResponseDto;
import com.academy.api.runners.LoginWithAdminAPITestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

public class EditProfileWithAdminAPITest extends LoginWithAdminAPITestRunner {
    private UserResponseDto userResponseDto;

    @Test(dataProvider = "userDataWithExpectedMessage")
    @Description("Verify that user can not save changes with invalid data: lastName and firstName")
    @Issue("TUA-415")
    public void checkEditUserProfileWithInvalidData(UserRequestDto userRequestDto, String expectedMessage) {
        Response userResponse = RestAssured.given()
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

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(userRequestDto)
                .when()
                .put("/api/user/" + userId)
                .then()
                .statusCode(400)
                .body("message", containsString(expectedMessage))
                .log()
                .ifValidationFails();
    }

    private Object[][] userInvalidData() {
        return new Object[][] {
                {new UserRequestDto(203, "Nastia1234", "Kukh",
                        "0635224393", "soyec48727@busantei.com",
                        "ROLE_MANAGER", null, "true")},
                {new UserRequestDto(0, "fgfgfgfgfgfgfgfgfgfgfgfgff", "string",
                        "0635224393", "string",
                        "string", "string", "string")},
                {new UserRequestDto(0, "Anna!@£", "string",
                        "0635224393", "string",
                        "string", "string", "string")},
                {new UserRequestDto(0, "string", "Mola!@£%^&",
                        "0635224393", "string",
                        "string", "string", "string")},
                {new UserRequestDto(0, "string", "sslorygndiejdpcngksoerfvgf",
                        "0635224393", "string",
                        "string", "string", "string")},
                {new UserRequestDto(0, "string", "Mola12345",
                        "0635224393", "string",
                        "string", "string", "string")}
        };
    }

    private String[][] expectedMessage() {
        return new String[][] {
                {"\"firstName\" can`t contain numbers"},
                {"\"firstName\" can contain from 1 to 25 letters"},
                {"\"firstName\" can contain only ukrainian and english letters"},
                {"\"lastName\" can contain only ukrainian and english letters"},
                {"\"lastName\" can contain from 1 to 25 letters"},
                {"\"lastName\" can`t contain numbers"}
        };
    }

    @DataProvider(name = "userDataWithExpectedMessage")
    private Object[][] userDataWithExpectedMessage() {
        Object[][] userData = userInvalidData();
        String[][] expectedMessages = expectedMessage();

        Object[][] combinedData = new Object[userData.length][2];
        for (int i = 0; i < userData.length; i++) {
            combinedData[i][0] = userData[i][0];
            combinedData[i][1] = expectedMessages[i][0];
        }
        return combinedData;
    }
}

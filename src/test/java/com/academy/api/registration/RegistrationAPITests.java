package com.academy.api.registration;

import com.academy.api.dto.registration.RegistrationRequestDto;
import com.academy.api.runners.BaseAPITestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationAPITests extends BaseAPITestRunner {

    @Test(description = "Negative API test for registration in user with empty mandatory fields")
    @Description("[API.Реєстрація] Verify that a user with empty mandatory fields can`t be created")
    @Issue("TUA-420")
    public void testThatUserCanNotBeCreatedWithEmptyMandatoryFields() {
        Object registrationRequestDto = new RegistrationRequestDto("",
                                                                    "",
                                                                    "",
                                                                    "",
                                                                    "",
                                                                    "");
        RestAssured.given()
                .body(registrationRequestDto)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/signup")
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("\"firstName\" can`t be empty"))
                .log()
                .ifValidationFails();
    }

    @Test(dataProvider = "registrationDataWithExpectedMessage")
    @Description("[API] Verify that user is not registered if at least one of the mandatory fields is empty")
    @Issue("TUA-377")
    public void testUserNotRegisteredWhenOneMandatoryFieldsEmpty(RegistrationRequestDto registrationRequestDto,
                                                    String expectedMessage) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(registrationRequestDto)
                .when()
                .post("/api/signup")
                .then()
                .statusCode(400)
                .body("message", containsString(expectedMessage))
                .log()
                .ifValidationFails();
    }

    private Object[][] registrationInvalidData() {
        return new Object[][] {
                {new RegistrationRequestDto("",
                                            "Когут",
                                            "youremail",
                                            "Kvitka1989@",
                                            "0971151309",
                                            "ROLE_MANAGER")},
                {new RegistrationRequestDto("Ігор",
                                            "",
                                            "youremail",
                                            "Kvitka1989@",
                                            "0971151309",
                                            "ROLE_MANAGER")},
                {new RegistrationRequestDto("Ігор",
                                            "Когут",
                                            "",
                                            "Kvitka1989@",
                                            "0971151309",
                                            "ROLE_MANAGER")},
                {new RegistrationRequestDto("Ігор",
                                            "Когут",
                                            "youremail",
                                            "",
                                            "0971151309",
                                            "ROLE_MANAGER")},
                {new RegistrationRequestDto("Ігор",
                                            "Когут",
                                            "youremail",
                                            "Kvitka1989@",
                                            "",
                                            "ROLE_MANAGER")},
                {new RegistrationRequestDto("Ігор",
                                            "Когут",
                                            "youremail",
                                            "Kvitka1989@",
                                            "0971151309",
                                            "")},

        };
    }

    private String[][] expectedMessage() {
        return new String[][] {
                {"\"firstName\" can`t be empty"},
                {"\"lastName\" can`t be empty"},
                {"\"email\" can`t be empty"},
                {"\"password\" can`t be empty"},
                {"\"phone\" can`t be empty"},
                {"\"roleName\" can`t be empty"}
        };
    }

    @DataProvider(name = "registrationDataWithExpectedMessage")
    private Object[][] registrationDataWithExpectedMessage() {
        Object[][] registrationData = registrationInvalidData();
        String[][] expectedMessages = expectedMessage();

        Object[][] combinedData = new Object[registrationData.length][2];
        for (int i = 0; i < registrationData.length; i++) {
            combinedData[i][0] = registrationData[i][0];
            combinedData[i][1] = expectedMessages[i][0];
        }
        return combinedData;
    }

}

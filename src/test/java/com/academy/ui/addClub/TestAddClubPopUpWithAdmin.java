package com.academy.ui.addClub;

import com.academy.ui.runners.LoginWithAdminTestRunner;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestAddClubPopUpWithAdmin extends LoginWithAdminTestRunner {
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp_init() {
        softAssert = new SoftAssert();
    }

    @Test(description = "LVTEACH-23")
    public void checkFillInNameFieldWithInvalidData_ErrorMessage() {
        final var testData = List.of("ÄыЁЪùראפ", "ƻ®©¥¼µ€", "       ", "@fЙ8",
                "123Qw*&#єЇ".repeat(10) + "o");
        final var incorrectDataErrorMsg = "Некоректна назва гуртка";
        final var emptyNameFieldErrorMsg = "Введіть назву гуртка";

        var addClubPopUpComponent = homePage.header.addClubButtonClick();
        addClubPopUpComponent.waitPopUpOpen(5);
        var clubNameInputElement = addClubPopUpComponent.getStepOneContainer()
                .getClubNameInputElement();

        testData.forEach(data -> {
            clubNameInputElement.setValue(data);
            softAssert.assertEquals(clubNameInputElement.getErrorMessagesTextList().get(0), incorrectDataErrorMsg);
            softAssert.assertTrue(clubNameInputElement.getValidationCircleIcon().isDisplayed());
            clubNameInputElement.clearInput();
            softAssert.assertTrue(clubNameInputElement.getValidationCircleIcon().isDisplayed());
            softAssert.assertEquals(clubNameInputElement.getErrorMessagesTextList().get(0), emptyNameFieldErrorMsg);
        });
        softAssert.assertAll();
    }
}

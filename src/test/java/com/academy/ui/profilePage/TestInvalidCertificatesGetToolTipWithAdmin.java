package com.academy.ui.profilePage;

import com.academy.ui.pages.AdminGenerateCertificatePage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class TestInvalidCertificatesGetToolTipWithAdmin extends LoginWithAdminTestRunner {
    SoftAssert softAssert;
    AdminGenerateCertificatePage adminGenerateCertificatePage;
    String minValidValue = "1";
    String maxValidValue = "999";
    WebDriverWait wait;
    @BeforeMethod
    @Step("Open  adminGenerateCertificatePage precondition")
    public void setup(){
        softAssert = new SoftAssert();
        adminGenerateCertificatePage = homePage.header.openAdminMenu().openContentPopup().openCertificatesSubmenuPopup().clickGenerateCertificate();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    @DataProvider(name = "invalidValues")
    public Object[][] invalidValues() {
        return new Object[][] {
                {"1000", maxValidValue},
                {"1300", maxValidValue},
                {"0", minValidValue}
        };
    }

    @Test(dataProvider = "invalidValues")
    @Step("Test invalid certificates tooltip: {0} -> {1}")
    @Description("Test the behavior of the certificate tooltip with invalid values.")
    public void testInvalidCertificatesGetToolTip(String inputValue, String expectedValue){
        enterInValidValue(inputValue);
        adminGenerateCertificatePage.getStudyDurationLabel().click();
        wait.until(driver -> !adminGenerateCertificatePage.getStudyDurationInput().getAttribute("value").contains(inputValue));
        softAssert.assertTrue(adminGenerateCertificatePage.getStudyDurationInput().getAttribute("value").contains(expectedValue),
                "The duration input accepts an invalid value "+ inputValue);
        softAssert.assertAll();
    }
    @Step("Clear input field")
    private void clearInputField(WebElement inputField){
        inputField.sendKeys(Keys.COMMAND + "a");
        inputField.sendKeys(Keys.DELETE);
    }
    @Step("Enter invalid value: {0}")
    private void enterInValidValue(String value){
        WebElement studyDurationInput = adminGenerateCertificatePage.getStudyDurationInput();
        String currentValue = studyDurationInput.getAttribute("value");

        if (currentValue != null && !currentValue.isEmpty()) {
            clearInputField(studyDurationInput);
        }

        studyDurationInput.sendKeys(value);

        String updatedValue = studyDurationInput.getAttribute("value");
        softAssert.assertTrue(updatedValue != null && updatedValue.contains(value),
                "The entered value isn't present in the duration input");
    }
}

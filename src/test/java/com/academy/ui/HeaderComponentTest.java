package com.academy.ui;

import com.academy.ui.components.header.HeaderChallengesDropdown;
import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.pages.challenges.BaseChallengePage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Issue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderComponentTest extends BaseTestRunner {
    private SoftAssert softAssert;
    public static final String DEFAULT_CITY = "Київ";

    @BeforeMethod
    public void beforeTestPreconditions(){
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-23")
    @Issue("TUA-23")
    public void testVerifyKyivIsShownByDefaultCity() {
        softAssert.assertTrue(homePage.header.getLocationIcon().isDisplayed());
        softAssert.assertEquals(homePage.header.getClubsLocationButton().getText(), DEFAULT_CITY,
                "Київ must be shown as the default");
        softAssert.assertAll();
    }

    @Test(description = "TUA-311")
    public void testVerifyLocationItem() {
        softAssert.assertTrue(homePage.header.getLocationIcon().isDisplayed());
        softAssert.assertEquals(homePage.header.getClubsLocationButton().getText(), DEFAULT_CITY,
                "Київ must be shown as the default");

        homePage.header.getClubsLocationButton().click();
        softAssert.assertTrue(homePage.header.openCityMenu().isDisplayed(), "List of city doesn't display");
        softAssert.assertAll();
    }

    @Test(description = "TUA-308")
    public void testVerifyChallengeUnderline() {
        WebElement challengeButton = homePage.header.getChallengeButton();
        WebElement challengeButtonParent = challengeButton.findElement(By.xpath("./ancestor::li"));

        softAssert.assertTrue(challengeButton.isDisplayed());

        Actions actions = new Actions(driver);
        actions.moveToElement(challengeButton).perform();

        try {
            Thread.sleep(1000); // Replace with WebDriverWait for better reliability
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //check if underline-class is present
        softAssert.assertTrue(challengeButtonParent.getAttribute("class").contains("ant-menu-submenu-active"), "Challenge Button wasn't underlined");

        HeaderComponent header = homePage.getHeader();
        String oldUrl = driver.getCurrentUrl();

        HeaderChallengesDropdown challengesDropdown = header.clickChallengeButton();
        for(int i = 0; i < challengesDropdown.getChallengeDropdownItems().size(); i++){
            BaseChallengePage challengePage = challengesDropdown.clickChallengeDropdownItemByIndex(i);
            String newUrl = driver.getCurrentUrl();
            softAssert.assertNotEquals(oldUrl, newUrl);
            header = challengePage.getHeader();
            challengesDropdown = header.clickChallengeButton();
        }

        softAssert.assertAll();
    }
}

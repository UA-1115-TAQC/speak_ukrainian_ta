package com.academy.ui.clubPage.advencedSearch;

import com.academy.ui.components.AdvancedSearchSiderComponent;
import com.academy.ui.components.advancedSearchHeader.AdvancedSearchHeaderComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.runners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ClubPageWithoutLoginTest extends BaseTestRunner {
    private AdvancedSearchHeaderComponent advancedSearchHeaderComponent;

    private SoftAssert softAssert;

    @BeforeMethod
    public void clubPageSetUp() {
        advancedSearchHeaderComponent = homePage.getAdvancedSearchHeaderComponent();
        softAssert = new SoftAssert();
    }

    @Test
    @Description("Verify that 'Гурток' radiobutton set by default in 'Гурток/Центр' parameters")
    @Issue("TUA-856")
    public void verifyRadioButtonSetByDefaultOnClub_ok() {
        ClubsPage clubsPage = advancedSearchHeaderComponent.clickAdvancedSearchIcon();
        softAssert.assertTrue(driver.getCurrentUrl().contains("clubs"));
        WebElement checkedRadioButton = clubsPage.getSearchSider().getCheckedRadioButton();
        softAssert.assertTrue(checkedRadioButton.getText().equals("Гурток"));

        softAssert.assertAll();
    }
}

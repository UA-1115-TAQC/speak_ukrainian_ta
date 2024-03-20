package com.academy.ui.addClub;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddClubContactPartTest extends LoginWithAdminTestRunner {
    @Test(description = "TUA-120")
    public void checkContactSimilarityToMockUp(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.header.addClubButtonClick();
        AddClubPopUpComponent addClubPopUpComponent = new AddClubPopUpComponent(driver);

        addClubPopUpComponent.getStepOneContainer().getClubNameInputElement().setValue("Bla bla");
        addClubPopUpComponent.getStepOneContainer().selectCategory("Спортивні секції");
        addClubPopUpComponent.getStepOneContainer().setMinAgeInput("3");
        addClubPopUpComponent.getStepOneContainer().setMaxAgeInput("5");
        addClubPopUpComponent.getStepOneContainer().clickCenterDropdown();
        addClubPopUpComponent.getStepOneContainer().selectCenter("Курси програмування IT-star");
        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();

        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getClubLocationsTitle().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getAddLocationButton().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getClubAvailableOnlineTitle().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getSwitchButton().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getInfoHintIcon().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getClubWorkHoursTitle().isDisplayed());
        softAssert.assertFalse(addClubPopUpComponent.getStepTwoContainer().getWorkDaysCheckboxList().isEmpty());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getClubContactsTitle().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getTelephoneInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getFacebookInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getWhatsappInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getEmailInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getSkypeInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getSiteInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getPreviousStepButton().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getNextStepButton().isDisplayed());

        driver.manage().window().setSize(new Dimension(10, 10));
        addClubPopUpComponent = new AddClubPopUpComponent(driver);

        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getClubLocationsTitle().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getAddLocationButton().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getClubAvailableOnlineTitle().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getSwitchButton().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getInfoHintIcon().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getClubWorkHoursTitle().isDisplayed());
        softAssert.assertFalse(addClubPopUpComponent.getStepTwoContainer().getWorkDaysCheckboxList().isEmpty());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getClubContactsTitle().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getTelephoneInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getFacebookInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getWhatsappInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getEmailInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getSkypeInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.isElementPresent(addClubPopUpComponent.getStepTwoContainer().getSiteInputElement().getWebElement()));
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getPreviousStepButton().isDisplayed());
        softAssert.assertTrue(addClubPopUpComponent.getStepTwoContainer().getNextStepButton().isDisplayed());

        softAssert.assertAll();
    }
}
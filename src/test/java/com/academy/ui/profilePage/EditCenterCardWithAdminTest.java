package com.academy.ui.profilePage;

import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpStepOne;
import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpStepThree;
import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpStepTwo;
import com.academy.ui.components.AddClubPopUpComponent.LocationListElement;
import com.academy.ui.components.CenterCardWithEditComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.Dimension;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditCenterCardWithAdminTest extends LoginWithAdminTestRunner {

    private SoftAssert softAssert;

    private ProfilePage profilePage;
    private final String VALID_CENTER_NAME = "Курси програмування IT-stat";
    private final String VALID_TELEPHONE = "0977777777";
    private final String TEXT_50_SYMBOLS = "Abcd ".repeat(10);
    private AddCenterPopUpStepOne stepOne;
    private AddCenterPopUpStepTwo stepTwo;
    private AddCenterPopUpStepThree stepThree;

    @BeforeMethod
    public void addClubPopUpTestPrecondition() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openProfilePage();
    }


    @Test(description = "TUA-394")
    public void checkAdminCanEditDescriptionInCenter() {

        profilePage.clickMyClubsAndCentersOnDropdown();
        profilePage.sleep(1000);
        profilePage.clickMyCentersOnDropdown();
        CenterCardWithEditComponent centerCard = profilePage.getCenterCardByName(VALID_CENTER_NAME);
        centerCard.getMoreButton().click();

        AddCenterPopUpComponent editCenterPopUp = centerCard.clickEditCenter();
        editCenterPopUp.waitPopUpOpen(5);
        editCenterPopUp.getStepOneContainer().clickNextStepButton();
        editCenterPopUp.getStepTwoContainer()
                .getTelephoneInputElement()
                .clearInput()
                .setValue(VALID_TELEPHONE);
        editCenterPopUp.getStepTwoContainer().clickNextStepButton();

        AddCenterPopUpStepThree stepThree = editCenterPopUp.getStepThreeContainer();
        String prevDescription = stepThree.getCenterDescriptionTextarea().getAttribute("value");
        stepThree.clearDescriptionTextarea().setCenterDescriptionTextarea(TEXT_50_SYMBOLS);
        softAssert.assertEquals(stepThree
                        .getCenterDescriptionTextarea()
                        .getAttribute("value"),
                TEXT_50_SYMBOLS,
                "Description textarea should contains text " + TEXT_50_SYMBOLS);
        stepThree.clickCompleteButton();

        profilePage = new ProfilePage(driver);
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyCentersOnDropdown();
        CenterCardWithEditComponent newCenterCard = profilePage.getCenterCardByName(VALID_CENTER_NAME);
        newCenterCard.getMoreButton().click();


        AddCenterPopUpComponent newEditCenterPopUp = newCenterCard.clickEditCenter();
        newEditCenterPopUp.waitPopUpOpen(5);
        newEditCenterPopUp.getStepOneContainer().clickNextStepButton();
        newEditCenterPopUp.getStepTwoContainer()
                .getTelephoneInputElement()
                .clearInput()
                .setValue(VALID_TELEPHONE);
        newEditCenterPopUp.getStepTwoContainer().clickNextStepButton();

        AddCenterPopUpStepThree newStepThree = newEditCenterPopUp.getStepThreeContainer();
        softAssert.assertEquals(newStepThree
                        .getCenterDescriptionTextarea()
                        .getAttribute("value"),
                TEXT_50_SYMBOLS,
                "Description textarea should contains text " + TEXT_50_SYMBOLS);
        newStepThree.clearDescriptionTextarea().setCenterDescriptionTextarea(prevDescription);
        softAssert.assertEquals(newStepThree
                        .getCenterDescriptionTextarea()
                        .getAttribute("value"),
                prevDescription,
                "Description textarea should contains text " + prevDescription);
        newStepThree.clickCompleteButton();

        softAssert.assertAll();

    }

    @Test
    @Description("Verify the UI elements of the 'Редагування центру' page")
    @Issue("TUA-397")
    public void checkEditCenterUI() {
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyCentersOnDropdown();
        CenterCardWithEditComponent centerCard = profilePage.getCenterCardComponentsList().getFirst();

        centerCard.getMoreButton().click();

        AddCenterPopUpComponent addCenterPopUpComponent = centerCard.clickEditCenter();
        addCenterPopUpComponent.waitPopUpOpen(5);
        softAssert.assertTrue(addCenterPopUpComponent.getCloseButton().isDisplayed());
        softAssert.assertTrue(addCenterPopUpComponent.getCloseButton().isEnabled());
        softAssert.assertEquals(addCenterPopUpComponent.getCloseButton().getSize(),
                new Dimension(22, 22));

        stepOne = addCenterPopUpComponent.getStepOneContainer();
        softAssert.assertTrue(stepOne.getNextStepButton().isEnabled());
        softAssert.assertTrue(stepOne.getNextStepButton().isDisplayed());
        softAssert.assertTrue(stepOne.getInfoCircleHintIcon().isDisplayed());
        softAssert.assertTrue(stepOne.getAddLocationButtonInEditCenter().isDisplayed());
        softAssert.assertTrue(stepOne.getAddLocationButtonInEditCenter().isEnabled());

        softAssert.assertTrue(stepOne.getCenterTitle().getText().equals("Редагувати центр"));
        softAssert.assertEquals(stepOne.getCenterTitle()
                .getCssValue("color"), "rgba(45, 76, 104, 1)");
        softAssert.assertEquals(stepOne.getCenterTitle()
                .getCssValue("font-size"), "24px");

        softAssert.assertTrue(stepOne.getCenterInputTitle().getText().equals("Назва центру"));
        softAssert.assertEquals(stepOne.getCenterInputTitle()
                .getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepOne.getCenterInputTitle()
                .getCssValue("font-size"), "19px");
        softAssert.assertTrue(stepOne.getCenterNameInput().getAttribute("value")
                .equals("Курси програмування IT-stat "));
        softAssert.assertEquals(stepOne.getCenterNameInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepOne.getCenterNameInput()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepOne.getCenterNameInput().getSize(),
                new Dimension(474, 24));
        softAssert.assertEquals(stepOne.getInfoCircleHintIcon().getSize(),
                new Dimension(14, 14));

        softAssert.assertTrue(stepOne.getAddLocationButtonInEditCenter().getText().equals("Додати локацію"));
        softAssert.assertEquals(stepOne.getAddLocationButtonInEditCenter()
                .getCssValue("color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(stepOne.getAddLocationButtonInEditCenter()
                .getCssValue("font-size"), "16px");
        softAssert.assertEquals(stepOne.getAddLocationButtonInEditCenter().getSize(),
                new Dimension(124, 22));

        softAssert.assertTrue(stepOne.getLocationTitle().getText().equals("Локації"));
        softAssert.assertEquals(stepOne.getLocationTitle()
                .getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepOne.getLocationTitle()
                .getCssValue("font-size"), "19px");

        LocationListElement locationListElement = stepOne.getListOfLocationElements().getFirst();
        softAssert.assertTrue(locationListElement.getEditIcon().isDisplayed());
        softAssert.assertTrue(locationListElement.getEditIcon().isEnabled());
        softAssert.assertTrue(locationListElement.getDeleteIcon().isDisplayed());
        softAssert.assertTrue(locationListElement.getDeleteIcon().isEnabled());
        softAssert.assertTrue(locationListElement.getLocationItemTitle()
                .getText().equals("center_location_first....."));
        softAssert.assertEquals(locationListElement.getLocationItemTitle()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(locationListElement.getLocationItemTitle()
                .getCssValue("font-size"), "14px");
        softAssert.assertTrue(locationListElement.getDescriptionTitle().getText()
                .equals("Адреса: Запоріжжя, вул.Героїв 93ї бригади 10А"));
        softAssert.assertEquals(locationListElement.getDescriptionTitle()
                .getCssValue("color"), "rgba(0, 0, 0, 0.45)");
        softAssert.assertEquals(locationListElement.getDescriptionTitle()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(locationListElement.getEditIcon().getSize(),
                new Dimension(14, 14));
        softAssert.assertEquals(locationListElement.getDeleteIcon().getSize(),
                new Dimension(14, 14));

        softAssert.assertTrue(addCenterPopUpComponent.getSider().getFirstStepIcon().isDisplayed());
        softAssert.assertTrue(addCenterPopUpComponent.getSider().getFirstStepIcon().getText().equals("1"));
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getFirstStepIcon()
                .getCssValue("color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getFirstStepIcon()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getFirstStepIconBackground()
                .getCssValue("background-color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getFirstStepIconBackground().getSize(),
                new Dimension(32, 32));
        softAssert.assertTrue(addCenterPopUpComponent.getSider().getFirstStepTitle()
                .getText().equals("Основна інформація"));
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getFirstStepTitle()
                .getCssValue("font-size"), "16px");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getFirstStepTitle()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");

        softAssert.assertTrue(stepOne.getNextStepButton().getText().equals("Наступний крок"));
        softAssert.assertEquals(stepOne.getNextStepButton()
                .getCssValue("color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(stepOne.getNextStepButton()
                .getCssValue("font-size"), "16px");
        softAssert.assertEquals(stepOne.getNextStepButton()
                .getCssValue("background-color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(stepOne.getNextStepButton().getSize(),
                new Dimension(190, 40));

        stepOne.clickNextStepButton();

        stepTwo = addCenterPopUpComponent.getStepTwoContainer();
        softAssert.assertTrue(addCenterPopUpComponent.getSider()
                .getFirstStepSuccessIcon().getAttribute("aria-label").equals("check"));
        softAssert.assertTrue(addCenterPopUpComponent.getSider().getSecondStepIcon().isDisplayed());
        softAssert.assertTrue(addCenterPopUpComponent.getSider().getSecondStepIcon().getText().equals("2"));
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getSecondStepIcon()
                .getCssValue("color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getSecondStepIcon()
                .getCssValue("font-size"), "14px");
        softAssert.assertTrue(addCenterPopUpComponent.getSider()
                .getSecondStepTitle().getText().equals("Контакти"));
        softAssert.assertEquals(addCenterPopUpComponent.getSider()
                .getSecondStepTitle().getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider()
                .getSecondStepTitle().getCssValue("font-size"), "16px");
        softAssert.assertTrue(addCenterPopUpComponent.getSider().getSecondStepIconBackground().getText().equals("2"));
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getSecondStepIconBackground()
                .getCssValue("color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getSecondStepIconBackground()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getSecondStepIconBackground()
                .getCssValue("background-color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getSecondStepIconBackground().getSize(),
                new Dimension(32, 32));

        softAssert.assertTrue(stepTwo.getCenterInputsTitle().getText().equals("Контакти"));
        softAssert.assertTrue(stepTwo.getTelephoneInputElement()
                .getInput().getAttribute("value").equals("0977777777"));
        softAssert.assertEquals(stepTwo.getTelephoneInputElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepTwo.getTelephoneInputElement().getInput()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepTwo.getTelephoneInputElement().getInput().getSize(),
                new Dimension(435, 24));
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getStaticIcon().isDisplayed());
        softAssert.assertEquals(stepTwo.getTelephoneInputElement().getStaticIcon().getSize(),
                new Dimension(24, 24));

        softAssert.assertTrue(stepTwo.getFacebookInputElement()
                .getInput().getAttribute("value").equals("https://www.facebook.com/qwerty"));
        softAssert.assertEquals(stepTwo.getFacebookInputElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepTwo.getFacebookInputElement().getInput()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepTwo.getFacebookInputElement().getInput().getSize(),
                new Dimension(464, 24));
        softAssert.assertTrue(stepTwo.getFacebookInputElement().getStaticIcon().isDisplayed());
        softAssert.assertEquals(stepTwo.getFacebookInputElement().getStaticIcon().getSize(),
                new Dimension(24, 24));

        softAssert.assertTrue(stepTwo.getWhatsappInputElement()
                .getInput().getAttribute("value").equals("qwerty"));
        softAssert.assertEquals(stepTwo.getWhatsappInputElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepTwo.getWhatsappInputElement().getInput()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepTwo.getWhatsappInputElement().getInput().getSize(),
                new Dimension(464, 24));
        softAssert.assertTrue(stepTwo.getWhatsappInputElement().getStaticIcon().isDisplayed());
        softAssert.assertEquals(stepTwo.getWhatsappInputElement().getStaticIcon().getSize(),
                new Dimension(24, 24));

        softAssert.assertTrue(stepTwo.getEmailInputElement()
                .getInput().getAttribute("value").equals("qwerty@gmail.com"));
        softAssert.assertEquals(stepTwo.getEmailInputElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepTwo.getEmailInputElement().getInput()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepTwo.getEmailInputElement().getInput().getSize(),
                new Dimension(464, 24));
        softAssert.assertTrue(stepTwo.getEmailInputElement().getStaticIcon().isDisplayed());
        softAssert.assertEquals(stepTwo.getEmailInputElement().getStaticIcon().getSize(),
                new Dimension(24, 24));

        softAssert.assertTrue(stepTwo.getSkypeInputElement()
                .getInput().getAttribute("value").equals("qwerty"));
        softAssert.assertEquals(stepTwo.getSkypeInputElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepTwo.getSkypeInputElement().getInput()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepTwo.getSkypeInputElement().getInput().getSize(),
                new Dimension(464, 24));
        softAssert.assertTrue(stepTwo.getSkypeInputElement().getStaticIcon().isDisplayed());
        softAssert.assertEquals(stepTwo.getSkypeInputElement().getStaticIcon().getSize(),
                new Dimension(24, 24));

        softAssert.assertTrue(stepTwo.getSiteInputElement()
                .getInput().getAttribute("value").equals("https://it-stat.zp.ua/"));
        softAssert.assertEquals(stepTwo.getSiteInputElement().getInput()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepTwo.getSiteInputElement().getInput()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepTwo.getSiteInputElement().getInput().getSize(),
                new Dimension(464, 24));
        softAssert.assertTrue(stepTwo.getSiteInputElement().getStaticIcon().isDisplayed());
        softAssert.assertEquals(stepTwo.getSiteInputElement().getStaticIcon().getSize(),
                new Dimension(24, 24));

        softAssert.assertTrue(stepTwo.getPrevStepButton().getText().equals("Назад"));
        softAssert.assertTrue(stepTwo.getPrevStepButton().isDisplayed());
        softAssert.assertTrue(stepTwo.getPrevStepButton().isEnabled());
        softAssert.assertEquals(stepTwo.getPrevStepButton()
                .getCssValue("color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(stepTwo.getPrevStepButton()
                .getCssValue("font-size"), "16px");
        softAssert.assertEquals(stepTwo.getPrevStepButton()
                .getCssValue("background-color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(stepTwo.getPrevStepButton().getSize(),
                new Dimension(77, 32));

        softAssert.assertTrue(stepTwo.getNextStepButton().getText().equals("Наступний крок"));
        softAssert.assertEquals(stepTwo.getNextStepButton()
                .getCssValue("color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(stepTwo.getNextStepButton()
                .getCssValue("font-size"), "16px");
        softAssert.assertEquals(stepTwo.getNextStepButton()
                .getCssValue("background-color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(stepTwo.getNextStepButton().getSize(),
                new Dimension(190, 40));

        stepTwo.clickNextStepButton();

        stepThree = addCenterPopUpComponent.getStepThreeContainer();
        softAssert.assertTrue(addCenterPopUpComponent.getSider()
                .getSecondStepSuccessIcon().getAttribute("aria-label").equals("check"));
        softAssert.assertTrue(addCenterPopUpComponent.getSider().getThirdStepIcon().isDisplayed());
        softAssert.assertTrue(addCenterPopUpComponent.getSider().getThirdStepIcon().getText().equals("3"));
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getThirdStepIcon()
                .getCssValue("color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getThirdStepIcon()
                .getCssValue("font-size"), "14px");
        softAssert.assertTrue(addCenterPopUpComponent.getSider()
                .getThirdStepTitle().getText().equals("Опис"));
        softAssert.assertEquals(addCenterPopUpComponent.getSider()
                .getThirdStepTitle().getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider()
                .getThirdStepTitle().getCssValue("font-size"), "16px");
        softAssert.assertTrue(addCenterPopUpComponent.getSider().getThirdStepIconBackground().getText().equals("3"));
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getThirdStepIconBackground()
                .getCssValue("color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getThirdStepIconBackground()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getThirdStepIconBackground()
                .getCssValue("background-color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(addCenterPopUpComponent.getSider().getThirdStepIconBackground().getSize(),
                new Dimension(32, 32));

        softAssert.assertTrue(stepThree.getCenterLogoTitle().getText().equals("Логотип"));
        softAssert.assertEquals(stepThree.getCenterLogoTitle()
                .getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepThree.getCenterLogoTitle()
                .getCssValue("font-size"), "19px");
        stepThree.getCenterLogoDownloadInput().getText();

        softAssert.assertTrue(stepThree.getCenterLogoDownloadButton().getText().equals("Завантажити лого"));
        softAssert.assertTrue(stepThree.getCenterLogoDownloadButton().isDisplayed());
        softAssert.assertTrue(stepThree.getCenterLogoDownloadButton().isEnabled());
        softAssert.assertEquals(stepThree.getCenterLogoDownloadButton()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepThree.getCenterLogoDownloadButton()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepThree.getCenterLogoDownloadButton().getSize(),
                new Dimension(140, 19));

        softAssert.assertTrue(stepThree.getCenterPhotoTitle().getText().equals("Фото"));
        softAssert.assertEquals(stepThree.getCenterPhotoTitle()
                .getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepThree.getCenterPhotoTitle()
                .getCssValue("font-size"), "19px");
        stepThree.getCenterLogoDownloadInput().getText();

        softAssert.assertTrue(stepThree.getCenterPhotoDownloadButton().getText().equals("Завантажити фото"));
        softAssert.assertTrue(stepThree.getCenterPhotoDownloadButton().isDisplayed());
        softAssert.assertTrue(stepThree.getCenterPhotoDownloadButton().isEnabled());
        softAssert.assertEquals(stepThree.getCenterPhotoDownloadButton()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepThree.getCenterPhotoDownloadButton()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepThree.getCenterPhotoDownloadButton().getSize(),
                new Dimension(142, 19));

        softAssert.assertTrue(stepThree.getCenterDescriptionTitle().getText().equals("Опис"));
        softAssert.assertEquals(stepThree.getCenterDescriptionTitle()
                .getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepThree.getCenterDescriptionTitle()
                .getCssValue("font-size"), "19px");
        softAssert.assertTrue(stepThree.getCenterDescriptionTextarea().getText().equals(TEXT_50_SYMBOLS));
        softAssert.assertEquals(stepThree.getCenterDescriptionTextarea()
                .getCssValue("color"), "rgba(0, 0, 0, 0.88)");
        softAssert.assertEquals(stepThree.getCenterDescriptionTextarea()
                .getCssValue("font-size"), "14px");
        softAssert.assertEquals(stepThree.getCenterDescriptionTextarea().getSize(),
                new Dimension(514, 198));

        softAssert.assertTrue(stepThree.getPrevStepButton().getText().equals("Назад"));
        softAssert.assertTrue(stepThree.getPrevStepButton().isDisplayed());
        softAssert.assertTrue(stepThree.getPrevStepButton().isEnabled());
        softAssert.assertEquals(stepThree.getPrevStepButton()
                .getCssValue("color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(stepThree.getPrevStepButton()
                .getCssValue("font-size"), "16px");
        softAssert.assertEquals(stepThree.getPrevStepButton()
                .getCssValue("background-color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(stepThree.getPrevStepButton().getSize(),
                new Dimension(59, 26));

        softAssert.assertTrue(stepThree.getCompleteButton().getText().equals("Завершити"));
        softAssert.assertEquals(stepThree.getCompleteButton()
                .getCssValue("color"), "rgba(255, 255, 255, 1)");
        softAssert.assertEquals(stepThree.getCompleteButton()
                .getCssValue("font-size"), "16px");
        softAssert.assertEquals(stepThree.getCompleteButton()
                .getCssValue("background-color"), "rgba(250, 140, 22, 1)");
        softAssert.assertEquals(stepThree.getCompleteButton().getSize(),
                new Dimension(190, 40));

        softAssert.assertAll();
    }
}

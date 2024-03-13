package com.academy.ui.profilePage;

import com.academy.ui.components.AddClubPopUpComponent.*;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.components.AddLocationPopUpComponent.DropdownElement;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.pages.ClubPage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import com.academy.ui.runners.utils.ConfigProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import java.util.Objects;

public class EditClubCardWithManagerTest extends LoginWithManagerTestRunner {
    private final String CLUB_WITH_LOCATION_NAME = "Club With Location";
    private String deletedLocationName = "";
    private String deletedCity = "";
    private String deletedDistrict = "";
    private String deletedAddress = "";
    private String deletedCoordinates = "";
    private String deletedTelephone = "";
    private SoftAssert softAssert;
    private ProfilePage profilePage;
    private AddClubPopUpSider sider;


    @BeforeMethod(description = "Preconditions: Get profilePage, make softAssert object")
    public void editProfilePageWithUserTest_setUp() {
        softAssert = new SoftAssert();
        profilePage = homePage.header.openUserMenu().clickProfile();
    }

    private void addNewClubAddedWithCorrectData() {
        final String VALID_CLUB_NAME = "New CLUB NAME 333";
        final String CATEGORY = "Спортивні секції";
        final String VALID_MIN_AGE = "5";
        final String VALID_MAX_AGE = "8";
        final String VALID_TELEPHONE_NUMBER = "0987656453";
        final String TEXT_50_SYMBOLS = "Abcd ".repeat(10);

        AddClubPopUpComponent addClubPopUp = profilePage.openAddClubPopUp();
        addClubPopUp.waitPopUpOpen(5);
        AddClubPopUpStepOne stepOne = addClubPopUp.getStepOneContainer();

        stepOne.getClubNameInputElement().setValue(VALID_CLUB_NAME);
        stepOne.selectCategory(CATEGORY);
        stepOne.setMinAgeInput(VALID_MIN_AGE);
        stepOne.setMaxAgeInput(VALID_MAX_AGE);
        stepOne.clickNextStepButton();

        addClubPopUp.getStepTwoContainer()
                .getTelephoneInputElement()
                .setValue(VALID_TELEPHONE_NUMBER);
        addClubPopUp.getStepTwoContainer().clickNextStepButton();

        addClubPopUp.getStepThreeContainer().setDescriptionValue(TEXT_50_SYMBOLS);
        addClubPopUp.getStepThreeContainer().clickCompleteButton();

    }

    private String getClubName() {
        if (profilePage.getClubCardComponents().isEmpty()) {
            addNewClubAddedWithCorrectData();
            driver.navigate().refresh();
            profilePage = new ProfilePage(driver);
        }
        return profilePage
                .getClubCardComponents()
                .get(0)
                .getTitle()
                .getAttribute("innerText");
    }


    @Test(description = "User can add locations of the club")
    @Description("Verify that user can add locations of the club (for a club that is in the center)")
    @Issue("TUA-970")
    public void checkUserCanAddLocationsOfTheClub() {

        final String VALID_LOCATION_NAME_1 = "ТестЛокація1";
        final String VALID_LOCATION_NAME_2 = "ТестЛокація2";
        final String VALID_LOCATION_NAME_3 = "ТестЛокація3";
        final String VALID_LOCATION_NAME_4 = "ТестЛокація4";
        final String VALID_LOCATION_NAME_5 = "ТестЛокація5";

        String clubName = getClubName();
        ClubCardWithEditComponent clubCardByName = profilePage.getClubCardByName(clubName);
        AddClubPopUpComponent editClubPopUp = clubCardByName.clickMoreButton().clickEditClub();
        editClubPopUp.waitPopUpOpen(5);
        editClubPopUp.getStepOneContainer().clickNextStepButton();

        addLocation(editClubPopUp, VALID_LOCATION_NAME_1);
        addLocation(editClubPopUp, VALID_LOCATION_NAME_2);
        addLocation(editClubPopUp, VALID_LOCATION_NAME_3);
        addLocation(editClubPopUp, VALID_LOCATION_NAME_4);
        addLocation(editClubPopUp, VALID_LOCATION_NAME_5);

        softAssert.assertEquals(editClubPopUp.getStepTwoContainer().getListOfLocationElements().size(), 5);
        checkLocationInList(editClubPopUp, VALID_LOCATION_NAME_1);
        checkLocationInList(editClubPopUp, VALID_LOCATION_NAME_2);
        checkLocationInList(editClubPopUp, VALID_LOCATION_NAME_3);
        checkLocationInList(editClubPopUp, VALID_LOCATION_NAME_4);
        checkLocationInList(editClubPopUp, VALID_LOCATION_NAME_5);

        softAssert.assertAll();
    }

    private void checkLocationInList(AddClubPopUpComponent editClubPopUp, String name) {
        softAssert.assertTrue(editClubPopUp.getStepTwoContainer().getLocationsNameList().contains(name),
                "List of location names should have " + name);
    }

    private void addLocation(AddClubPopUpComponent editClubPopUp, String VALID_LOCATION_NAME) {

        final String VALID_CITY_NAME = "Київ";
        final String VALID_CITY_DISTRICT = "Деснянський";
        final String VALID_ADDRESS = "вул. Садова, 1а";
        final String VALID_COORDINATES = "50.4485253, 30.4735083";
        final String VALID_TELEPHONE = "0977777777";

        AddLocationPopUpComponent addLocationPopUp = editClubPopUp.getStepTwoContainer().clickAddLocationButton();
        addLocationPopUp.waitPopUpOpen(5);
        softAssert.assertTrue(addLocationPopUp.isOpen(), "Add location popup should be open");

        addLocationPopUp.getLocatioNameInputElement().setValue(VALID_LOCATION_NAME);
        softAssert.assertEquals(addLocationPopUp
                        .getLocatioNameInputElement()
                        .getInput()
                        .getAttribute("value"),
                VALID_LOCATION_NAME);

        addLocationPopUp.getLocatioCityDropdownElement().clickDropdown().selectValue(VALID_CITY_NAME);
        softAssert.assertEquals(addLocationPopUp
                        .getLocatioCityDropdownElement()
                        .getSelectedItem()
                        .getText(),
                VALID_CITY_NAME);

        addLocationPopUp.getLocationDistrictDropdownElement().clickDropdown().selectValue(VALID_CITY_DISTRICT);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationDistrictDropdownElement()
                        .getSelectedItem()
                        .getText(),
                VALID_CITY_DISTRICT);

        addLocationPopUp.getLocationAddressInputElement().setValue(VALID_ADDRESS);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationAddressInputElement()
                        .getInput()
                        .getAttribute("value"),
                VALID_ADDRESS);

        addLocationPopUp.getLocationCoordinatesInputElement().setValue(VALID_COORDINATES);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationCoordinatesInputElement()
                        .getInput()
                        .getAttribute("value"),
                VALID_COORDINATES);

        addLocationPopUp.getLocationTelephoneInputElement().setValue(VALID_TELEPHONE);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationTelephoneInputElement()
                        .getInput()
                        .getAttribute("value"),
                VALID_TELEPHONE);

        addLocationPopUp.clickAddLocationButton();
    }

    @Test(description = "User can change photo while editing club")
    @Description("Verify that user can change ‘Фото’ on the ‘Опис’ tab of the ‘Редагувати гурток’ pop-up window (for club that is in the center)")
    @Issue("TUA-82")
    public void checkUserCanChangePhotoWhileEditClub() {
        final String IMAGE_NAME_1 = "image.png";
        final String IMAGE_NAME_2 = "book.png";

        String clubName = getClubName();
        ClubCardWithEditComponent clubCardByName = profilePage.getClubCardByName(clubName);
        AddClubPopUpComponent editClubPopUp = clubCardByName.clickMoreButton().clickEditClub();
        editClubPopUp.waitPopUpOpen(5);
        editClubPopUp.getStepOneContainer().clickNextStepButton();
        editClubPopUp.getStepTwoContainer().clickNextStepButton();
        AddClubPopUpStepThree stepThree = editClubPopUp.getStepThreeContainer();
        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(IMAGE_NAME_1));
        stepThree.getUploadedCoverImg().waitImageLoad(5);
        String uploadedImage = stepThree
                .getUploadedCoverImg()
                .getImgTitle()
                .getText();
        softAssert.assertEquals(uploadedImage, IMAGE_NAME_1, "Image should be downloaded");
        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(IMAGE_NAME_2));
        stepThree.getUploadedCoverImg().waitImageChanged(uploadedImage, 5);

        softAssert.assertEquals(stepThree
                        .getUploadedCoverImg()
                        .getImgTitle()
                        .getText(),
                IMAGE_NAME_2,
                "Image should be changed");

        stepThree.clickCompleteButton();

        softAssert.assertTrue(driver.getCurrentUrl().contains("/user"),
                "Profile page should be opened");

        driver.navigate().refresh();
        profilePage = new ProfilePage(driver);
        ClubCardWithEditComponent clubCardUpdated = profilePage.getClubCardByName(clubName);

        softAssert.assertTrue(clubCardUpdated
                        .clickDetailsButton()
                        .getClubCover()
                        .getAttribute("style")
                        .contains(IMAGE_NAME_2),
                "Image should be changed to the " + IMAGE_NAME_2);

        softAssert.assertAll();
    }

    @Test
    @Description("Verify that the user can add valid photo to the 'Логотип', 'Обкладинка', and 'Галерея' categories")
    @Issue("TUA-967")
    public void checkEditCartUploadPhotos() {
        String imagePath = "harrybean.jpg";

        ClubCardWithEditComponent clubCard = profilePage.getClubCardComponents().getFirst();
        AddClubPopUpComponent addClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();
        addClubPopUpComponent.waitPopUpOpen(5);

        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();
        addClubPopUpComponent.getStepTwoContainer().clickNextStepButton();

        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clickClubLogoDownloadButton();
        stepThree.getClubLogoDownloadInput().sendKeys(configProperties.getImagePath(imagePath));
        softAssert.assertTrue(stepThree.getUploadedLogoImg().getImgTitle().getText().equals("harrybean.jpg"));

        stepThree.clickClubCoverDownloadButton();
        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(imagePath));
        softAssert.assertTrue(stepThree.getUploadedCoverImg().getImgTitle().getText().equals("harrybean.jpg"));

        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath(imagePath));
        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath(imagePath));
        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath(imagePath));
        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath(imagePath));
        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath(imagePath));

        stepThree.sleep(500);
        softAssert.assertEquals(stepThree.getClubGalleryUploadedImgs().size(), 5);

        stepThree.clickCompleteButton();

        driver.navigate().refresh();
        profilePage = new ProfilePage(driver);
        ClubCardWithEditComponent card = profilePage.getClubCardComponents().getFirst();
        ClubPage clubPage = card.clickDetailsButton();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/club"));
        softAssert.assertEquals(clubPage.getCarouselImgs().size(), 5);

        softAssert.assertAll();
    }

    @Test
    @Description("Verify that the icon of the main photo 'Обкладинка' is set by default if photo is not uploaded")
    @Issue("TUA-85")
    public void checkDefaultCoverImg() {
        String testCoverImage = "image.png";
        String defaultCoverImage = "harrybean.jpg";

        ClubCardWithEditComponent clubCard = profilePage.getClubCardComponents().getFirst();
        AddClubPopUpComponent addClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();
        addClubPopUpComponent.waitPopUpOpen(5);

        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();
        addClubPopUpComponent.getStepTwoContainer().clickNextStepButton();

        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clickClubCoverDownloadButton();
        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(testCoverImage));
        stepThree.getUploadedCoverImg().waitImageLoad(5);
        softAssert.assertTrue(stepThree.getUploadedCoverImg().getImgTitle().getText().contains(testCoverImage));
        stepThree.clickCompleteButton();

        driver.navigate().refresh();
        profilePage = new ProfilePage(driver);
        ClubCardWithEditComponent card = profilePage.getClubCardComponents().getFirst();
        ClubPage clubPage = card.clickDetailsButton();
        softAssert.assertTrue(clubPage.getClubCover().getAttribute("style").contains(testCoverImage));

        clubPage = new ClubPage(driver);
        profilePage = clubPage.getHeader().openUserMenu().clickProfile();

        clubCard = profilePage.getClubCardComponents().getFirst();
        addClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();
        addClubPopUpComponent.waitPopUpOpen(5);

        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();
        addClubPopUpComponent.getStepTwoContainer().clickNextStepButton();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clickClubCoverDownloadButton();
        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(defaultCoverImage));
        stepThree.getUploadedCoverImg().waitImageLoad(5);
        softAssert.assertTrue(stepThree.getUploadedCoverImg().getImgTitle().getText().contains(defaultCoverImage));
        stepThree.clickCompleteButton();

        driver.navigate().refresh();
        profilePage = new ProfilePage(driver);
        card = profilePage.getClubCardComponents().getFirst();
        clubPage = card.clickDetailsButton();
        softAssert.assertTrue(clubPage.getClubCover().getAttribute("style").contains(defaultCoverImage));

        softAssert.assertAll();
    }

    @Test
    @Description("Verify that user can edit ‘Опис’ field with valid data, and save changes on the "
            + "‘Опис’ tab of the ‘Редагувати гурток’ pop-up window")
    @Issue("TUA-78")
    public void checkStepThreeEditDescriptionTextArea() {
        String defaultDescription = "We'll teach you to play much better than Daniel Radcliffe."
                + " We will teach you acting better than anyone else.";
        String testDescription = "^^/!/::expecto патронум::!!/?/&&".repeat(10);

        ClubCardWithEditComponent clubCard = profilePage.getClubCardComponents().getFirst();
        AddClubPopUpComponent addClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();
        addClubPopUpComponent.waitPopUpOpen(5);

        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();
        addClubPopUpComponent.getStepTwoContainer().clickNextStepButton();

        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clearDescriptionTextarea().setDescriptionValue(testDescription);
        softAssert.assertTrue(stepThree.getNextStepButton().isEnabled());

        stepThree.clickCompleteButton();
        driver.navigate().refresh();

        profilePage = new ProfilePage(driver);
        ClubCardWithEditComponent card = profilePage.getClubCardComponents().getFirst();
        ClubPage clubPage = card.clickDetailsButton();
        softAssert.assertTrue(clubPage.getClubDescription().getText().equals(testDescription));

        clubPage = new ClubPage(driver);
        profilePage = clubPage.getHeader().openUserMenu().clickProfile();

        clubCard = profilePage.getClubCardComponents().getFirst();
        addClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();
        addClubPopUpComponent.waitPopUpOpen(5);
        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();
        addClubPopUpComponent.getStepTwoContainer().clickNextStepButton();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clearDescriptionTextarea().setDescriptionValue(defaultDescription);
        softAssert.assertTrue(stepThree.getNextStepButton().isEnabled());
        stepThree.clickCompleteButton();

        driver.navigate().refresh();
        profilePage = new ProfilePage(driver);
        card = profilePage.getClubCardComponents().getFirst();
        clubPage = card.clickDetailsButton();
        softAssert.assertTrue(clubPage.getClubDescription().getText().equals(defaultDescription));

        softAssert.assertAll();
    }

    @Test()
    @Issue("TUA-57")
    public void checkCorrectEditOfNameCategoryAge(){
        ClubCardWithEditComponent clubCard = profilePage.getClubCardComponents().getFirst();
        clubCard.sleep(1000);
        AddClubPopUpComponent editClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();
        editClubPopUpComponent.waitPopUpOpen(5);
        AddClubPopUpStepOne editClubPopUpStepOne = editClubPopUpComponent.getStepOneContainer();

        editClubPopUpStepOne.getClubNameInputElement().clearInput();
        editClubPopUpStepOne.getClubNameInputElement().setValue("Harry 123&*? Potter");
        softAssert.assertEquals(editClubPopUpStepOne.getClubNameInputElement().getInput().getAttribute("value"), "Harry 123&*? Potter");

        editClubPopUpStepOne.selectCategoryForEdit("Центр розвитку");
        softAssert.assertTrue(editClubPopUpStepOne.getCheckedCategoriesListForEdit().stream().anyMatch(category -> category.getText().equals("Центр розвитку")), "Категорія не вибрана 1");

        editClubPopUpStepOne.getMinAgeInput().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        editClubPopUpStepOne.setMinAgeInput("2");
        editClubPopUpStepOne.getMaxAgeInput().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        editClubPopUpStepOne.setMaxAgeInput("6");
        softAssert.assertEquals(editClubPopUpStepOne.getMinAgeInput().getAttribute("value"), "2");
        softAssert.assertEquals(editClubPopUpStepOne.getMaxAgeInput().getAttribute("value"), "6");

        editClubPopUpStepOne.clickNextStepButton();
        editClubPopUpComponent.getStepTwoContainer().clickNextStepButton();
        editClubPopUpComponent.getStepThreeContainer().clickCompleteButton();

        profilePage = new ProfilePage(driver);
        clubCard = profilePage.getClubCardComponents().getFirst();
        ClubPage clubPage = clubCard.clickDetailsButton();

        softAssert.assertEquals(clubPage.getClubName().getText(), "Harry 123&*? Potter");
        softAssert.assertTrue(clubPage.getCategoriesClubName().stream().anyMatch(category -> Objects.equals(category.getText(), "Центр розвитку")), "Категорія не вибрана 2");
        softAssert.assertTrue(clubPage.getAgeOfTheAudienceClub().getText().contains("2"));
        softAssert.assertTrue(clubPage.getAgeOfTheAudienceClub().getText().contains("6"));
        softAssert.assertAll();
    }

    @Test
    @Issue("TUA-958")
    public void checkChangeCoverPhoto() {
        String imageName= "book.png";
        String imageName2 = "image.png";
        String clubName = getClubName();
        ClubCardWithEditComponent clubCardByName = profilePage.getClubCardByName(clubName);
        AddClubPopUpComponent editClubPopUp = clubCardByName.clickMoreButton().clickEditClub();
        editClubPopUp.waitPopUpOpen(5);
        editClubPopUp.getStepOneContainer().clickNextStepButton();
        editClubPopUp.getStepTwoContainer().clickNextStepButton();
        AddClubPopUpStepThree stepThree = editClubPopUp.getStepThreeContainer();

        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(imageName));
        stepThree.getUploadedCoverImg().waitImageLoad(5);

        softAssert.assertEquals(stepThree.getUploadedCoverImg().getImgTitle().getText(), imageName,
                "Image should be changed");

        softAssert.assertTrue(stepThree
                .getUploadedCoverImg()
                .getImgTitle()
                .isEnabled(),
                "Cannot click on cover photo");

        stepThree.clickCompleteButton();

        driver.navigate().refresh();
        profilePage = new ProfilePage(driver);
        ClubCardWithEditComponent clubCardUpdated = profilePage.getClubCardByName(clubName);

        softAssert.assertTrue(clubCardUpdated
                        .clickDetailsButton()
                        .getClubCover()
                        .getAttribute("style")
                        .contains(imageName),
                "Image should be changed to the " + imageName);

        softAssert.assertAll();
    }

    @Test(description = "TUA-981")
    @Description("Verify that user can change center for the existing club with center")
    @Issue("TUA-981")
    public void checkChangeCenterClubWithCenter(){
        softAssert = new SoftAssert();
        String centerToSelect = "Центр творчості дітей та юнацтва";

        ClubCardWithEditComponent clubCard = profilePage.getClubCardByName("Club With Center");
        AddClubPopUpComponent edit  = clubCard.clickMoreButton().clickEditClub();
        edit.waitPopUpOpen(10);

        AddClubPopUpStepOne one = edit.getStepOneContainer();
        String oldCenter = one.getCenterSelectedTitle().getText();
        one.getCenterDropdown().clickDropdown().selectValue(centerToSelect);
        one.clickNextStepButton();
        edit.getStepTwoContainer().clickNextStepButton();
        edit.getStepThreeContainer().clickCompleteButton();

        profilePage = new ProfilePage(driver);
        clubCard = profilePage.getClubCardByName("Club With Center");
        ClubPage clubPage =  clubCard.clickDetailsButton();
        String newCenter = clubPage.getClubCenterName();

        softAssert.assertNotEquals(oldCenter, newCenter);
        softAssert.assertEquals(newCenter, centerToSelect);
        softAssert.assertAll();
    }

    @Test(description = "TUA-48")
    public void checkContactTabUI() {
        String clubName = getClubName();
        ClubCardWithEditComponent clubCardByName = profilePage.getClubCardByName(clubName);
        AddClubPopUpComponent editClubPopUp = clubCardByName.clickMoreButton().clickEditClub();
        editClubPopUp.waitPopUpOpen(5);
        editClubPopUp.getStepOneContainer().clickNextStepButton();

        sider = editClubPopUp.getSider();
        softAssert.assertEquals(sider.getFirstStepIcon().getText(), "");
        softAssert.assertEquals(sider.getFirstStepTitle().getText(), "Основна інформація");
        softAssert.assertEquals(sider.getSecondStepIcon().getText(), "2");
        softAssert.assertEquals(sider.getSecondStepTitle().getText(), "Контакти");
        softAssert.assertEquals(sider.getThirdStepIcon().getText(), "3");
        softAssert.assertEquals(sider.getThirdStepTitle().getText(), "Опис");

        AddClubPopUpStepTwo stepTwo = editClubPopUp.getStepTwoContainer();
        softAssert.assertEquals(stepTwo.getClubLocationsTitle().getText(), "Локації");
        softAssert.assertEquals(stepTwo.getClubLocationsTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepTwo.getClubLocationsTitle().getCssValue("font-size"), "19px");

        softAssert.assertEquals(stepTwo.getClubAvailableOnlineTitle().getText(), "Доступний онлайн");
        softAssert.assertEquals(stepTwo.getClubAvailableOnlineTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepTwo.getClubAvailableOnlineTitle().getCssValue("font-size"), "19px");

        softAssert.assertEquals(stepTwo.getClubWorkHoursTitle().getText(), "Години роботи");
        softAssert.assertEquals(stepTwo.getClubWorkHoursTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepTwo.getClubWorkHoursTitle().getCssValue("font-size"), "19px");

        softAssert.assertEquals(stepTwo.getClubContactsTitle().getText(), "Контакти");
        softAssert.assertEquals(stepTwo.getClubContactsTitle().getCssValue("color"), "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepTwo.getClubContactsTitle().getCssValue("font-size"), "19px");

        softAssert.assertEquals(stepTwo.getAddLocationButton().getText(), "Додати локацію");
        softAssert.assertEquals(stepTwo.getAddLocationButton().getCssValue("color"),"rgba(250, 140, 22, 1)");
        softAssert.assertEquals(stepTwo.getAddLocationButton().getCssValue("font-size"), "17px",
                "location button");

        softAssert.assertTrue(stepTwo.getSwitchButton().isSelected());

        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getErrorMessages().isEmpty());
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getStaticIcon().isDisplayed());

        softAssert.assertTrue(stepTwo.getNextStepButton().isDisplayed());
        softAssert.assertEquals(stepTwo.getNextStepButton().getText(), "Наструпний крок");
        softAssert.assertEquals(stepTwo.getNextStepButton().getCssValue("color"), "rgba(250, 140, 22, 1)");

        softAssert.assertTrue(stepTwo.getPrevStepButton().isDisplayed());
        softAssert.assertEquals(stepTwo.getPrevStepButton().getText(),"Назад");
        softAssert.assertEquals(stepTwo.getPrevStepButton().getCssValue("color"), "rgba(250, 140, 22, 1)");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getSwitchButton().equals(driver.switchTo().activeElement()),
                "Focus should be on Switch Button");
        actions.sendKeys(Keys.TAB).perform();

        softAssert.assertTrue(stepTwo.getCheckedWorkDaysList().get(0).equals(driver.switchTo().activeElement()),
                "Focus should be on Monday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getCheckedWorkDaysList().get(1).equals(driver.switchTo().activeElement()),
                "Focus should be on Tuesday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getCheckedWorkDaysList().get(2).equals(driver.switchTo().activeElement()),
                "Focus should be on Wednesday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getCheckedWorkDaysList().get(3).equals(driver.switchTo().activeElement()),
                "Focus should be on Thursday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getCheckedWorkDaysList().get(4).equals(driver.switchTo().activeElement()),
                "Focus should be on Friday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getCheckedWorkDaysList().get(5).equals(driver.switchTo().activeElement()),
                "Focus should be on Saturday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getCheckedWorkDaysList().get(6).equals(driver.switchTo().activeElement()),
                "Focus should be on Sunday checkbox");
        actions.sendKeys(Keys.TAB).perform();

        softAssert.assertTrue(stepTwo.getTelephoneInputElement().equals(driver.switchTo().activeElement()),
                "Focus should be on Telephone Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getFacebookInputElement().equals(driver.switchTo().activeElement()),
                "Focus should be on Facebook Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getWhatsappInputElement().equals(driver.switchTo().activeElement()),
                "Focus should be on Whatsapp Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getEmailInputElement().equals(driver.switchTo().activeElement()),
                "Focus should be on Email Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getSkypeInputElement().equals(driver.switchTo().activeElement()),
                "Focus should be on Skype Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getSiteInputElement().equals(driver.switchTo().activeElement()),
                "Focus should be on Site Input");
        actions.sendKeys(Keys.TAB).perform();

        softAssert.assertTrue(stepTwo.getPrevStepButton().equals(driver.switchTo().activeElement()),
                "Focus should be on Previous Step Button");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getNextStepButton().equals(driver.switchTo().activeElement()),
                "Focus should be on Next Step Button");

        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidTelephone", dataProviderClass = EditClubWithManagerDataProvider.class)
    @Description("Verify user cannot save invalid data in Telephone field on the 'Контакти' tab of the 'Редагувати гурток' pop-up window")
    @Issue("TUA-961")
    public void checkInvalidTelephoneInput(String input, String expectedErrorMsg){
        softAssert = new SoftAssert();
        ClubCardWithEditComponent clubCard = profilePage.getClubCardByName("Club With Center");
        AddClubPopUpComponent edit  = clubCard.clickMoreButton().clickEditClub();
        edit.waitPopUpOpen(10);
        edit.getStepOneContainer().clickNextStepButton();
        AddClubPopUpStepTwo two = edit.getStepTwoContainer();

        two.getTelephoneInputElement().setValue(input);
        String errorMsg = "";
        List<String> errorList= two.getTelephoneInputElement().getErrorMessagesTextList();
        for(String str : errorList){
            errorMsg = errorMsg + str + " ";
        }

        softAssert.assertEquals(errorMsg, expectedErrorMsg);
        softAssert.assertFalse(two.getNextStepButton().isEnabled());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidEmail", dataProviderClass = EditClubWithManagerDataProvider.class)
    @Description("Verify user cannot save invalid data in Email field on the 'Контакти' tab of the 'Редагувати гурток' pop-up window")
    @Issue("TUA-961")
    public void checkInvalidEmailInput(String input){
        softAssert = new SoftAssert();
        ClubCardWithEditComponent clubCard = profilePage.getClubCardByName("Club With Center");
        AddClubPopUpComponent edit  = clubCard.clickMoreButton().clickEditClub();
        edit.waitPopUpOpen(10);
        edit.getStepOneContainer().clickNextStepButton();
        AddClubPopUpStepTwo two = edit.getStepTwoContainer();

        two.getEmailInputElement().setValue(input);
        softAssert.assertEquals(
                two.getEmailInputElement().getErrorMessagesTextList().get(0),
                "Некоректний формат email"
        );
        softAssert.assertFalse(two.getNextStepButton().isEnabled());
        softAssert.assertAll();
    }

    @Test
    @Issue("TUA-79")
    public void checkUserCanEditDescriptionFieldWithInvalidData() {
        String clubName = getClubName();
        ClubCardWithEditComponent clubCardByName = profilePage.getClubCardByName(clubName);
        AddClubPopUpComponent editClubPopUp = clubCardByName.clickMoreButton().clickEditClub();
        editClubPopUp.waitPopUpOpen(5);
        editClubPopUp.getStepOneContainer().clickNextStepButton();
        editClubPopUp.getStepTwoContainer().clickNextStepButton();
        AddClubPopUpStepThree stepThree = editClubPopUp.getStepThreeContainer();

        stepThree.clearDescriptionTextarea().setDescriptionValue("You will become better with us.");

        softAssert.assertTrue(stepThree.getClubDescriptionTextarea().isDisplayed());
        softAssert.assertFalse(stepThree.getErrorMessagesTextList().isEmpty());
        softAssert.assertFalse(stepThree.getNextStepButton().isEnabled(),
                "The 'Завершити' button is enabled");

        softAssert.assertAll();
    }

    @Test(description = "TUA-955")
    public void checkValidPhoneInput() {
        final String PHONE_TEST_VALUE = "0661782312";
        final String FACEBOOK_TEST_VALUE = "facebook1user";
        final String WHATSAPP_TEST_VALUE = "username";
        final String SITE_TEST_VALUE = "exampleuser";
        final String SKYPE_TEST_VALUE = "skypeuser1";
        final String EMAIL_TEST_VALUE = "email@email.com";
        String clubName = getClubName();
        ClubCardWithEditComponent clubCardByName = profilePage.getClubCardByName(clubName);
        AddClubPopUpComponent editClubPopUp = clubCardByName.clickMoreButton().clickEditClub();
        editClubPopUp.waitPopUpOpen(2);
        editClubPopUp.getStepOneContainer().clickNextStepButton();
        AddClubPopUpStepTwo addLocationPopUp = editClubPopUp.getStepTwoContainer();

        AddClubInputElement phone = addLocationPopUp.getTelephoneInputElement();
        validateAddClubInputElement(phone, PHONE_TEST_VALUE);

        AddClubInputElement facebook = addLocationPopUp.getFacebookInputElement();
        validateAddClubInputElement(facebook, FACEBOOK_TEST_VALUE);

        AddClubInputElement whatsapp = addLocationPopUp.getWhatsappInputElement();
        validateAddClubInputElement(whatsapp, WHATSAPP_TEST_VALUE);

        AddClubInputElement email = addLocationPopUp.getEmailInputElement();
        validateAddClubInputElement(email, EMAIL_TEST_VALUE);

        AddClubInputElement skype = addLocationPopUp.getSkypeInputElement();
        validateAddClubInputElement(skype, SKYPE_TEST_VALUE);

        AddClubInputElement site = addLocationPopUp.getSiteInputElement();
        validateAddClubInputElement(site, SITE_TEST_VALUE);

        editClubPopUp.getStepTwoContainer().clickNextStepButton();

        AddClubPopUpStepThree stepThree = editClubPopUp.getStepThreeContainer();
        stepThree.clickCompleteButton();

        driver.navigate().refresh();


        String newClubName = getClubName();
        ClubCardWithEditComponent newClubCardByName = profilePage.getClubCardByName(newClubName);
        AddClubPopUpComponent newEditClubPopUp = newClubCardByName.clickMoreButton().clickEditClub();
        newEditClubPopUp.waitPopUpOpen(5);
        newEditClubPopUp.getStepOneContainer().clickNextStepButton();
        AddClubPopUpStepTwo newAddLocationPopUp = newEditClubPopUp.getStepTwoContainer();

        softAssert.assertEquals(newAddLocationPopUp.getTelephoneInputElement().getInput().getAttribute("value"), PHONE_TEST_VALUE);
        softAssert.assertEquals(newAddLocationPopUp.getFacebookInputElement().getInput().getAttribute("value"), FACEBOOK_TEST_VALUE);
        softAssert.assertEquals(newAddLocationPopUp.getWhatsappInputElement().getInput().getAttribute("value"), WHATSAPP_TEST_VALUE);
        softAssert.assertEquals(newAddLocationPopUp.getSkypeInputElement().getInput().getAttribute("value"), SKYPE_TEST_VALUE);
        softAssert.assertEquals(newAddLocationPopUp.getEmailInputElement().getInput().getAttribute("value"), EMAIL_TEST_VALUE);
        softAssert.assertEquals(newAddLocationPopUp.getSiteInputElement().getInput().getAttribute("value"), SITE_TEST_VALUE);
        softAssert.assertAll();
    }

    private void validateAddClubInputElement(AddClubInputElement element, String testValue) {
        element.clearInput();
        element.setValue(testValue);
        softAssert.assertEquals(element.getErrorMessagesTextList(), List.of());

        WebElement parentOfCircleIcon = element.getValidationCircleIcon().findElement(By.xpath(".."));
        softAssert.assertTrue(parentOfCircleIcon.getAttribute("class").contains("ant-form-item-feedback-icon-success"));
    }


    @Test(description = "TUA-58")
    public void verifyClubTitleCanNotBeChangedWithIncorrectName() {
        final String incorrectClubName = "#1 'München federală'";
        final String expectedErrorMessage = "Некоректна назва гуртка";
        final String expectedColor = "rgba(255, 77, 79, 1)";

        softAssert.assertTrue(profilePage.getClubCardComponents()
                        .stream()
                        .noneMatch(c -> c.getTitle().getText().contains(incorrectClubName)),
                "Club with incorrect club name ('%s') should not be present in manager's club list"
                        .formatted(incorrectClubName));

        AddClubPopUpStepOne stepOneContainer = profilePage.getClubCardComponents().get(0)
                .clickMoreButton()
                .clickEditClub().getStepOneContainer();
        AddClubInputElement clubNameInputElement = (AddClubInputElement) stepOneContainer.getClubNameInputElement()
                .clearInput().setValue(incorrectClubName);

        softAssert.assertEquals(clubNameInputElement.getErrorMessagesTextList().get(0),
                expectedErrorMessage, "'%s' should be present under the name input field"
                        .formatted(expectedErrorMessage));
        softAssert.assertEquals(clubNameInputElement.getErrorMessages()
                .get(0).getCssValue("color"), expectedColor);
        softAssert.assertFalse(stepOneContainer.getNextStepButton().isEnabled(),
                "Submit button should not be enabled");
        softAssert.assertAll();
    }

    @Test(description = "TUA-957")
    public void checkNewClubCardsLogoDisplayedOnProfilePage() {
        final String initialLogoImage = "image.png";
        final String newLogoImage = "book.png";

        ClubCardWithEditComponent clubCardWithEditComponent = profilePage.getClubCardComponents().get(0);
        AddClubPopUpComponent addClubPopUpComponent = clubCardWithEditComponent
                .clickMoreButton()
                .clickEditClub();
        addClubPopUpComponent.waitPopUpOpen(5);

        addClubPopUpComponent
                .getStepOneContainer()
                .clickNextStepButton()
                .clickNextStepButton();
        addClubPopUpComponent.waitPopUpOpen(5);

        AddClubPopUpStepThree stepThreeContainer = addClubPopUpComponent.getStepThreeContainer();
        stepThreeContainer
                .getClubLogoDownloadInput()
                .sendKeys(ConfigProperties.getImagePath(newLogoImage));

        UploadedImgComponent uploadedLogoImg = stepThreeContainer.getUploadedLogoImg();
        uploadedLogoImg.waitImageLoad(5);

        softAssert.assertEquals(uploadedLogoImg.getImgTitle().getAttribute("title"),
                newLogoImage, "Logo title should be '%s'".formatted(newLogoImage));

        stepThreeContainer.clickCompleteButtonWithWait();
        profilePage = new ProfilePage(driver);

        softAssert.assertTrue(profilePage.getClubCardComponents().get(0)
                .getLogo().getAttribute("src").contains(newLogoImage),
                "Club card logo should contains '%s'".formatted(newLogoImage));
        softAssert.assertAll();

        setClubCardLogoToInitialConditions(initialLogoImage);
    }

    private void setClubCardLogoToInitialConditions(String initialLogoImage) {
        AddClubPopUpComponent addClubPopUpComponent = profilePage.getClubCardComponents()
                .get(0).clickMoreButton()
                .clickEditClub();
        addClubPopUpComponent.waitPopUpOpen(5);
        addClubPopUpComponent.getStepOneContainer()
                .clickNextStepButton()
                .clickNextStepButton();
        addClubPopUpComponent.waitPopUpOpen(5);
        AddClubPopUpStepThree stepThreeContainer = addClubPopUpComponent.getStepThreeContainer();
        stepThreeContainer.getClubLogoDownloadInput()
                .sendKeys(ConfigProperties.getImagePath(initialLogoImage));
        stepThreeContainer.getUploadedLogoImg()
                .waitImageLoad(5);
        stepThreeContainer.clickCompleteButton();
    }

    @Test(description = "Center is added")
    @Description("Verify that user can add center for the existing club without center")
    @Issue("TUA-980")
    public void checkStepOneEditCenter() {
        String centerName = "Академія талановитих дітей";
        String clubName = getClubName();
        ClubCardWithEditComponent clubCardByName = profilePage.getClubCardByName(clubName);
        AddClubPopUpComponent editClubPopUp = clubCardByName.clickMoreButton().clickEditClub();
        editClubPopUp.waitPopUpOpen(5);
        editClubPopUp.getStepOneContainer().clickCenterDropdown().selectCenter(centerName);
        editClubPopUp.getStepOneContainer().clickNextStepButton();
        editClubPopUp.getStepTwoContainer().clickNextStepButton();
        editClubPopUp.getStepThreeContainer().clickCompleteButton();

        driver.navigate().refresh();
        profilePage = new ProfilePage(driver);
        ClubCardWithEditComponent clubCardUpdated = profilePage.getClubCardByName(clubName);

        softAssert.assertEquals(clubCardUpdated
                        .clickDetailsButton()
                        .getClubCenter()
                        .getText()
                        .trim(),
                centerName);

        softAssert.assertAll();
    }

    @Test(description = "TUA-87/63/")
    public void isOnlineCheckboxCheckedAutomatically(){
        softAssert = new SoftAssert();
        profilePage = new ProfilePage(driver);
        deleteLocation();
        profilePage = new ProfilePage(driver);
        ClubCardWithEditComponent club = profilePage.getClubCardByName(CLUB_WITH_LOCATION_NAME);
        softAssert.assertEquals(club.getAddress().getText(), "Онлайн");
        undoChanges();
        softAssert.assertAll();
    }

    private void deleteLocation() {
        profilePage.getClubCardByName(CLUB_WITH_LOCATION_NAME)
                .clickMoreButton().clickEditClub();
        AddClubPopUpComponent edit = new AddClubPopUpComponent(driver);
        edit.waitPopUpOpen(20);
        edit.getStepOneContainer().clickNextStepButton();

        AddClubPopUpStepTwo twoEdit = edit.getStepTwoContainer();
        LocationListElement locationElement = twoEdit.getListOfLocationElements().get(0);

        AddLocationPopUpComponent location = locationElement.clickEditIcon();
        deletedLocationName = location.getLocatioNameInputElement().getInput().getAttribute("value");
        deletedCity = location.getLocatioCityDropdownElement().getSelectedItem().getText();
        deletedDistrict = location.getLocationDistrictDropdownElement().getSelectedItem().getText();
        deletedAddress = location.getLocationAddressInputElement().getInput().getAttribute("value");
        deletedCoordinates = location.getLocationCoordinatesInputElement().getInput().getAttribute("value");
        deletedTelephone = location.getLocationTelephoneInputElement().getInput().getAttribute("value");
        location.clickAddLocationButton();

        locationElement.clickDeleteIcon();
        twoEdit.clickNextStepButton();
        edit.getStepThreeContainer().clickCompleteButton();
    }

    private void undoChanges(){
        profilePage = new ProfilePage(driver);
        AddClubPopUpComponent edit = profilePage.getClubCardByName(CLUB_WITH_LOCATION_NAME)
                .clickMoreButton().clickEditClub();
        edit.waitPopUpOpen(20);
        edit.getStepOneContainer().clickNextStepButton();
        AddClubPopUpStepTwo twoEdit = edit.getStepTwoContainer();

        AddLocationPopUpComponent location = twoEdit.clickAddLocationButton();
        location.getLocatioNameInputElement().setValue(deletedLocationName);
        location.getLocatioCityDropdownElement().clickDropdown().selectValue(deletedCity);
        location.getLocationDistrictDropdownElement().clickDropdown().selectValue(deletedDistrict);
        location.getLocationAddressInputElement().setValue(deletedAddress);
        location.getLocationCoordinatesInputElement().setValue(deletedCoordinates);
        location.getLocationTelephoneInputElement().setValue(deletedTelephone);
        location.clickAddLocationButton();

        twoEdit.clickNextStepButton();
        edit.getStepThreeContainer().clickCompleteButton();
    }

}

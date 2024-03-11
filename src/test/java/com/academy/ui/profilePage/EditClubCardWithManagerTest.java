package com.academy.ui.profilePage;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepThree;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.pages.ClubPage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditClubCardWithManagerTest extends LoginWithManagerTestRunner {
    private SoftAssert softAssert;
    private ProfilePage profilePage;


    @BeforeMethod
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


    @Test(description = "TUA-970")
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

    @Test(description = "TUA-82")
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

    @Test(description = "TUA-967")
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

    @Test(description = "TUA-85")
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

    @Test(description = "TUA-78")
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

        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(imageName2));
        stepThree.getUploadedCoverImg().waitImageLoad(5);

        softAssert.assertEquals(stepThree.getUploadedCoverImg().getImgTitle().getText(), imageName2,
                "Image should be changed");

        softAssert.assertTrue(stepThree
                .getUploadedCoverImg()
                .getImgTitle()
                .isEnabled(),
                "Cannot click on cover photo");

        stepThree.clickCompleteButton();
        ProfilePage profilePage1 = new ProfilePage(driver);
        String clubName1 = getClubName();
        ClubCardWithEditComponent clubCardByName1 = profilePage1.getClubCardByName(clubName1);
        clubCardByName1.clickDetailsButton();

        ClubPage clubPage = new ClubPage(driver);
        softAssert.assertTrue(clubPage.getCurrentTabHandle().contains("user"));
        softAssert.assertTrue(clubPage.getClubCover().getAttribute("style").contains(imageName2));

        softAssert.assertAll();
    }
}

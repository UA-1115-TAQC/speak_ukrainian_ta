package com.academy.ui.profilePage;

import com.academy.ui.components.AddCenterPopUpComponent.*;
import com.academy.ui.components.AddClubPopUpComponent.*;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.pages.ClubPage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import com.academy.ui.runners.randomvaluesgenerators.RandomAlphanumericStringGenerator;
import com.academy.ui.runners.utils.ConfigProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
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
   private   String validClubName;
    private String initialCentre;
   private String validMinAge;
    private String validMaxAge;
    private  String validPhone = "0".repeat(10);
    private  String validDescription;
    private  AddClubPopUpComponent addClubPopUpComponent;
    private String validCenterName;
    private String validLocationName;
    private String validAddress;
    private String validCoordinates ="50.56, 5.08";
    private AddCenterPopUpComponent addCenterPopUp;
    private int initialIndex;

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

    @Test(description = "TUA-89")
    @Description("Verify that locations of the previous center are removed from the club, " +
            "and locations of a new center are added automatically (for a club that is in the center)")
    @Issue("TUA-89")
    public void checkThatLocationsChangeForAclubWhenChangingCenters(){
       if(profilePage.getClubCardComponents().isEmpty()){
            addNewRandomClubAddedWithCorrectData();
           refreshProfilePage();
        }
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyCentersOnDropdown();
       if(profilePage.getCenterCardComponentsList().isEmpty() || profilePage.getCenterCardComponentsList().size() == 1){
           addARandomCenter();
           refreshProfilePage();
       }
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyClubsOnDropdown();
        String initialLocation = profilePage.getClubCardComponents().get(0).getAddressLocationName().getText();
        setANewCentreInAClub(2);
        refreshProfilePage();
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyClubsOnDropdown();
        if (!Objects.equals(initialLocation, "Онлайн") && !Objects.equals(profilePage.getClubCardComponents().get(0).getAddressLocationName().getText(), "Онлайн")){
            softAssert.assertFalse(initialLocation.equals(profilePage.getClubCardComponents().get(0).getAddressLocationName().getText()),
                    "The location wasn't modified after changing a centre");
        }
        setANewCentreInAClub(initialIndex);
        softAssert.assertAll();
    }
    @Test(description = "TUA-66")
    @Tag("YuUk")
    @Description("Verify that user can edit ‘Контакти’ fields (for a club that is not in the center) " +
            "with valid data, and save changes on the ‘Контакти’ tab of the ‘Редагувати гурток’ pop-up window")
    @Issue("TUA-66")
    public void verifyUserCanEditOptionalFieldsEditClubCardStepThree(){
        if(profilePage.getClubCardComponents().isEmpty()){
            addNewRandomClubAddedWithCorrectData();
            refreshProfilePage();
        }
        goToTheSecondStep();
        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();
        String initialPhone = getValueFromField(stepTwo.getTelephoneInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getTelephoneInputElement().getInput());

        String initialFacebook = getValueFromField(stepTwo.getFacebookInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getFacebookInputElement().getInput());

        String initialWhatsapp = getValueFromField(stepTwo.getWhatsappInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getWhatsappInputElement().getInput());

        String initialEmail = getValueFromField(stepTwo.getEmailInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getEmailInputElement().getInput());

        String initialSkype = getValueFromField(stepTwo.getSkypeInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getSkypeInputElement().getInput());

        String initialSite = getValueFromField(stepTwo.getSiteInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getSiteInputElement().getInput());
        editOptionalFieldsEditClubCardStepTwo(stepTwo,
                RandomAlphanumericStringGenerator.generateRandomString(10,10,1),
                RandomAlphanumericStringGenerator.generateRandomString(10,15,3),
                RandomAlphanumericStringGenerator.generateRandomString(10,15,3),
                (RandomAlphanumericStringGenerator.generateRandomString(5,6,3)+"@mail.com"),
                RandomAlphanumericStringGenerator.generateRandomString(10,15,3),
                RandomAlphanumericStringGenerator.generateRandomString(10,15,3)
                );
        if(initialPhone != null){
            softAssert.assertFalse(stepTwo.getTelephoneInputElement().getInput().getAttribute("value").contains(initialPhone),
                    "The phone number wasn't changed on the second step after editing it");
        }
        if(initialFacebook != null){
            softAssert.assertFalse(stepTwo.getFacebookInputElement().getInput().getAttribute("value").contains(initialFacebook),
                    "The facebook value wasn't changed on the second step after editing it");
        }
        if(initialWhatsapp != null){
            softAssert.assertFalse(stepTwo.getWhatsappInputElement().getInput().getAttribute("value").contains(initialWhatsapp),
                    "The whatsapp value wasn't changed on the second step after editing it");
        }
        if(initialEmail != null){
            softAssert.assertFalse(stepTwo.getEmailInputElement().getInput().getAttribute("value").contains(initialEmail),
                    "The email wasn't changed on the second step after editing it");
        }
        if(initialSkype != null){
            softAssert.assertFalse(stepTwo.getSkypeInputElement().getInput().getAttribute("value").contains(initialSkype),
                    "The skype value wasn't changed on the second step after editing it");
        }
        if(initialSite != null){
            softAssert.assertFalse(stepTwo.getSiteInputElement().getInput().getAttribute("value").contains(initialSite),
                    "The site wasn't changed on the second step after editing it");
        }
        stepTwo.clickNextStepButton();
        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clickCompleteButton();
        refreshProfilePage();
        goToTheSecondStep();
        stepTwo = addClubPopUpComponent.getStepTwoContainer();
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getTelephoneInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getFacebookInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getWhatsappInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getEmailInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getSkypeInputElement().getInput());
        clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getSiteInputElement().getInput());
        editOptionalFieldsEditClubCardStepTwo(stepTwo, initialPhone, initialFacebook, initialWhatsapp, initialEmail, initialSkype, initialSite);
        stepTwo.clickNextStepButton();
        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clickCompleteButton();
        softAssert.assertAll();
    }
    private String getValueFromFieldStepTwo(WebElement element) {
        String value = element.getAttribute("value");
        return value != null ? value : "";
    }
    private void clearFieldsIfNotEmptyStepTwo(AddClubPopUpStepTwo stepTwo, WebElement field){
        if(!field.getAttribute("value").isEmpty()){
            field.sendKeys(Keys.COMMAND + "a");
            field.sendKeys(Keys.DELETE);
        }
    }
    private void editOptionalFieldsEditClubCardStepTwo(AddClubPopUpStepTwo stepTwo, String phone, String facebook,
                                                       String whatsapp, String email, String skype, String site){
        stepTwo.getTelephoneInputElement()
                .setValue(phone);
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getInput().getAttribute("value").equals(phone),
                "The phone value: "+phone+" wasn't set");
        if(facebook != null){
            stepTwo.getFacebookInputElement().setValue(facebook);
            softAssert.assertTrue(stepTwo.getFacebookInputElement().getInput().getAttribute("value").equals(facebook),
                    "The facebook value: "+facebook+" wasn't set");
        }else{
            clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getFacebookInputElement().getInput());
        }
        if(whatsapp != null){
            stepTwo.getWhatsappInputElement().setValue(whatsapp);
            softAssert.assertTrue(stepTwo.getWhatsappInputElement().getInput().getAttribute("value").equals(whatsapp),
                    "The whatsapp value: "+whatsapp+" wasn't set");
        }else{
            clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getWhatsappInputElement().getInput());
        }
        if(email != null){
            stepTwo.getEmailInputElement().setValue(email);
            softAssert.assertTrue(stepTwo.getEmailInputElement().getInput().getAttribute("value").equals(email),
                    "The email value: "+email+" wasn't set");
        }else{
            clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getEmailInputElement().getInput());
        }
        if(skype != null){
            stepTwo.getSkypeInputElement().setValue(skype);
            softAssert.assertTrue(stepTwo.getSkypeInputElement().getInput().getAttribute("value").equals(skype),
                    "The skype value: "+skype+" wasn't set");
        }else{
            clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getSkypeInputElement().getInput());
        }
        if(site != null){
            stepTwo.getSiteInputElement().setValue(site);
            softAssert.assertTrue(stepTwo.getSiteInputElement().getInput().getAttribute("value").equals(site),
                    "The site value: "+site+" wasn't set");
        }else{
            clearFieldsIfNotEmptyStepTwo(stepTwo, stepTwo.getSiteInputElement().getInput());
        }
    }
    @Test(description = "TUA-956")
    @Description("Verify that ‘Доступний онлайн’ checkbox is checked automatically " +
            "if no location is checked (for a club that is in the center)")
    @Issue("TUA-956")
    public void checkClubIsOnlineWhenNoLocationsAreSelected(){
        if(profilePage.getClubCardComponents().isEmpty()){
            addNewRandomClubAddedWithCorrectData();
            refreshProfilePage();
        }
        goToTheSecondStep();
        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();
        boolean hasLocation=false;
       if(stepTwo.isSwitchButtonChecked() && stepTwo.getListOfLocationElements().isEmpty()){
           addLocation(addClubPopUpComponent, "Sample location");
           stepTwo.clickNextStepButton();
           AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
           stepThree.clickCompleteButton();
           refreshProfilePage();
           goToTheSecondStep();
       }else{
           hasLocation=true;
       }
       addClubPopUpComponent = new AddClubPopUpComponent(driver);

       //addClubPopUpComponent.sleep(1000);
        deleteTheSetLocation(addClubPopUpComponent);
        refreshProfilePage();
        softAssert.assertTrue(profilePage.getEmailUser().isDisplayed(), "The profile page wasn't displayed " +
                "after successfully editing a club");
        softAssert.assertTrue(profilePage.getClubCardComponents().get(0).getAddressLocationName().getText().contains("Онлайн"),
                "The location text on the club card wasn't automatically changed to 'Online' after deleting all locations");
        if(hasLocation){
            undoChangesLocation();
        }
        softAssert.assertAll();
    }
    private void deleteTheSetLocation(AddClubPopUpComponent edit) {
        AddClubPopUpStepTwo twoEdit = edit.getStepTwoContainer();
        softAssert.assertTrue(twoEdit.getSwitchButton().isDisplayed());
        LocationListElement locationElement = twoEdit.getListOfLocationElements().get(0);
        AddLocationPopUpComponent location = locationElement.clickEditIcon();
        deletedLocationName = location.getLocationNameInputElement().getInput().getAttribute("value");
        deletedCity = location.getLocationCityDropdownElement().getSelectedItem().getText();
        deletedDistrict = location.getLocationDistrictDropdownElement().getSelectedItem().getText();
        deletedAddress = location.getLocationAddressInputElement().getInput().getAttribute("value");
        deletedCoordinates = location.getLocationCoordinatesInputElement().getInput().getAttribute("value");
        deletedTelephone = location.getLocationTelephoneInputElement().getInput().getAttribute("value");
        location.clickAddLocationButton();
        //    Delete icon in location Element does not work
        //therefore  uncomment the try/catch construction, to see the result of the tests - soft asserts
    //try {
    locationElement.clickDeleteIcon();
    //}catch (StaleElementReferenceException e) {
    twoEdit.clickNextStepButton();
    edit.getStepThreeContainer().clickPreviousStepButton();
    softAssert.assertTrue(edit.getStepTwoContainer().getLocationList().isEmpty(),
            "The previous location wasn't deleted");
    softAssert.assertTrue(edit.getStepTwoContainer().isSwitchButtonChecked(),
            "The club isn't displayed as an online club after deleting a location");
    edit.getStepTwoContainer().clickNextStepButton();
    edit.getStepThreeContainer().clickCompleteButton();
    //}
    }

    private void undoChangesLocation(){
        profilePage = new ProfilePage(driver);
        AddClubPopUpComponent edit = profilePage.getClubCardComponents().get(0)
                .clickMoreButton().clickEditClub();
        edit.waitPopUpOpen(20);
        edit.getStepOneContainer().clickNextStepButton();
        AddClubPopUpStepTwo twoEdit = edit.getStepTwoContainer();

        AddLocationPopUpComponent location = twoEdit.clickAddLocationButton();
        location.getLocationNameInputElement().setValue(deletedLocationName);
        location.getLocationCityDropdownElement().clickDropdown().selectValue(deletedCity);
        location.getLocationDistrictDropdownElement().clickDropdown().selectValue(deletedDistrict);
        location.getLocationAddressInputElement().setValue(deletedAddress);
        location.getLocationCoordinatesInputElement().setValue(deletedCoordinates);
        location.getLocationTelephoneInputElement().setValue(deletedTelephone);
        location.clickAddLocationButton();

        twoEdit.clickNextStepButton();
        edit.getStepThreeContainer().clickCompleteButton();
    }
    @Test(description = "TUA-973")
    @Description("Verify that the icon of the main photo 'Обкладинка' is set " +
            "by default if photo is not uploaded (for a club that is in the center)")
    @Issue("TUA-973")
    public void checkDefaultClubCover(){
        String initialCover = "image.png";
        if(profilePage.getClubCardComponents().isEmpty()){
            addNewRandomClubAddedWithCorrectData();
            refreshProfilePage();
        }
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyClubsOnDropdown();
        setClubCover(initialCover);
        refreshProfilePage();
        deleteExistingCover(initialCover);
        refreshProfilePage();
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyClubsOnDropdown();
        ClubPage clubPage = profilePage.getClubCardComponents().get(0).clickDetailsButton();
        softAssert.assertFalse(clubPage.getClubCover().getAttribute("style").contains(initialCover),
                "The default photo isn't displayed");
        softAssert.assertAll();
    }
    private void setClubCover(String image){
        goToTheSecondStep();
        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();
        stepTwo.clickNextStepButton();
        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.uploadCoverImage(ConfigProperties.getImagePath(image));
        stepThree.clickCompleteButton();
    }
    private void deleteExistingCover(String cover){
        goToTheSecondStep();
        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();
        stepTwo.clickNextStepButton();
        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        softAssert.assertTrue(stepThree.getClubCoverDownloadInput().getAttribute("title").contains(cover),
                "It is impossible to delete an old cover, as it isn't displayed as uploaded");
        //todo - add delete an old cover action when it is implemented
       // softAssert.assertTrue(stepThree.getUploadedCoverImg().clickRemoveImg().isComponentVisible(),
             //   "The delete button for the old image isn't displayed, so it is impossible to delete an old cover");
        stepThree.clickCompleteButton();
    }
    private String getValueFromField(WebElement field){
        if(!field.getAttribute("value").isEmpty()){
            return field.getAttribute("value");
        }
        return null;
    }
    private void goToTheSecondStep(){
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyClubsOnDropdown();
        addClubPopUpComponent= profilePage.getClubCardComponents().get(0).clickMoreButton().clickEditClub();
        AddClubPopUpStepOne stepOne = addClubPopUpComponent.getStepOneContainer();
        stepOne.clickNextStepButton();
    }
    private void refreshProfilePage(){
        driver.navigate().refresh();
        profilePage = new ProfilePage(driver);
    }
    private void setANewCentreInAClub(int index){
        profilePage.clickMyClubsAndCentersOnDropdown().clickMyClubsOnDropdown();
        addClubPopUpComponent= profilePage.getClubCardComponents().get(0).clickMoreButton().clickEditClub();
        AddClubPopUpStepOne stepOne = addClubPopUpComponent.getStepOneContainer();
        initialCentre= stepOne.getCenterSelectedTitle().getText();
        stepOne.scrollIntoView(driver, stepOne.getCenterSelectedTitle());
        stepOne.getCenterSelectedTitle().click();
        for( int i =0; i<stepOne.getCentersList().size(); i++){
            if(stepOne.getCentersList().get(i).getText().equals(initialCentre)){
                initialIndex=i;
            }
        }
        stepOne.scrollIntoView(driver, stepOne.getCentersList().get(index));
        stepOne.getCentersList().get(index).click();
        String newCentre= stepOne.getCenterSelectedTitle().getText();
        softAssert.assertFalse(newCentre.equals(initialCentre),
                "The centre wasn't changed");
        stepOne.clickNextStepButton();
        AddClubPopUpStepTwo stepTwo = addClubPopUpComponent.getStepTwoContainer();
        stepTwo.clickNextStepButton();
        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clickCompleteButton();
    }
    private void addNewRandomClubAddedWithCorrectData() {
        validClubName = RandomAlphanumericStringGenerator.generateRandomString(8, 12,2);
        validMinAge = RandomAlphanumericStringGenerator.generateRandomString(2, 4,1);
        validMaxAge = RandomAlphanumericStringGenerator.generateRandomString(5, 17,1);
        validDescription = RandomAlphanumericStringGenerator.generateRandomString(40, 50,3);

        AddClubPopUpComponent addClubPopUp = profilePage.openAddClubPopUp();
        addClubPopUp.waitPopUpOpen(5);
        AddClubPopUpStepOne stepOne = addClubPopUp.getStepOneContainer();

        stepOne.getClubNameInputElement().setValue(validClubName);
        stepOne.getCategoriesCheckboxList().get(0).click();
        stepOne.setMinAgeInput(validMinAge);
        stepOne.setMaxAgeInput(validMaxAge);
        WebElement initialCenterElement = stepOne.clickCenterDropdown().getCentersList().get(1);
        initialCentre= initialCenterElement.getText();
        initialCenterElement.click();
        stepOne.clickNextStepButton();
        addClubPopUp.getStepTwoContainer()
                .getTelephoneInputElement()
                .setValue(validPhone);
        addClubPopUp.getStepTwoContainer().clickNextStepButton();

        addClubPopUp.getStepThreeContainer().setDescriptionValue(validDescription);
        addClubPopUp.getStepThreeContainer().clickCompleteButton();
    }
   private void addARandomCenter(){
        addCenterPopUp= profilePage.header.openUserMenu().openAddCentreForm();
        validCenterName = RandomAlphanumericStringGenerator.generateRandomString(8,12,2);
        validDescription = RandomAlphanumericStringGenerator.generateRandomString(40,50,3);
        AddCenterPopUpStepOne stepOne = addCenterPopUp.getStepOneContainer();
        stepOne.setCenterName(validCenterName);
        validLocationName =RandomAlphanumericStringGenerator.generateRandomString(8,12,2);
        AddLocationPopUpComponent addLocationPopUp = stepOne.clickAddLocationButton();
        addLocationPopUp.getLocationNameInputElement().setValue(validLocationName);
        addLocationPopUp.getLocationCityDropdownElement().clickDropdown().selectValue("Київ");
        validAddress = RandomAlphanumericStringGenerator.generateRandomString(8,15,3);
        addLocationPopUp.getLocationAddressInputElement().setValue(validAddress);
        addLocationPopUp.getLocationCoordinatesInputElement().setValue(validCoordinates);
        addLocationPopUp.getLocationTelephoneInputElement().setValue(validPhone);
        addLocationPopUp.clickAddLocationButton();
        stepOne.clickLocationCheckboxByName(validLocationName);
        stepOne.clickNextStepButton();
        AddCenterPopUpStepTwo stepTwo = addCenterPopUp.getStepTwoContainer();
        stepTwo.getTelephoneInputElement().setValue(validPhone);
        stepTwo.clickNextStepButton();
        AddCenterPopUpStepThree stepThree = addCenterPopUp.getStepThreeContainer();
        stepThree.setCenterDescriptionTextarea(validDescription);
        stepThree.clickNextStepButton();
        AddCenterPopUpStepFour stepFour = addCenterPopUp.getStepFourContainer();
        stepFour.clickFinishButton();
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

        addLocationPopUp.getLocationNameInputElement().setValue(VALID_LOCATION_NAME);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationNameInputElement()
                        .getInput()
                        .getAttribute("value"),
                VALID_LOCATION_NAME);

        addLocationPopUp.getLocationCityDropdownElement().clickDropdown().selectValue(VALID_CITY_NAME);
        softAssert.assertEquals(addLocationPopUp
                        .getLocationCityDropdownElement()
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
        stepThree.getUploadedCoverElement().waitImageLoad(5);
        String uploadedImage = stepThree
                .getUploadedCoverElement()
                .getImageTitle()
                .getText();
        softAssert.assertEquals(uploadedImage, IMAGE_NAME_1, "Image should be downloaded");
        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(IMAGE_NAME_2));
        stepThree.getUploadedCoverElement().waitImageChanged(uploadedImage, 5);

        softAssert.assertEquals(stepThree
                        .getUploadedCoverElement()
                        .getImageTitle()
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
        softAssert.assertTrue(stepThree.getUploadedLogoElement().getImageTitle().getText().equals("harrybean.jpg"));

        stepThree.clickClubCoverDownloadButton();
        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(imagePath));
        softAssert.assertTrue(stepThree.getUploadedCoverElement().getImageTitle().getText().equals("harrybean.jpg"));

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

        stepThree.waitUntilGalleryImagesUpload(5);
        softAssert.assertEquals(stepThree.getClubGalleryUploadedImgs().size(), 5);

        stepThree.clickCompleteButton();

        profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageIsCompletelyLoaded();
        ClubCardWithEditComponent card = profilePage.getClubCardComponents().getFirst();
        ClubPage clubPage = card.clickDetailsButton();
        clubPage.waitUntilPageIsCompletelyLoaded();
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
        stepThree.getUploadedCoverElement().waitImageLoad(5);
        softAssert.assertTrue(stepThree.getUploadedCoverElement().getImageTitle().getText().contains(testCoverImage));
        stepThree.clickCompleteButton();

        profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageIsCompletelyLoaded();
        ClubCardWithEditComponent card = profilePage.getClubCardComponents().getFirst();
        ClubPage clubPage = card.clickDetailsButton();
        clubPage.waitUntilPageIsCompletelyLoaded();
        clubPage = new ClubPage(driver);
        softAssert.assertTrue(clubPage.getClubCover().getAttribute("style").contains(testCoverImage));

        profilePage = clubPage.getHeader().openUserMenu().clickProfile();
        profilePage.waitUntilPageIsCompletelyLoaded();

        clubCard = profilePage.getClubCardComponents().getFirst();
        addClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();
        addClubPopUpComponent.waitPopUpOpen(5);
        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();
        addClubPopUpComponent.getStepTwoContainer().clickNextStepButton();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clickClubCoverDownloadButton();
        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(defaultCoverImage));
        stepThree.getUploadedCoverElement().waitImageLoad(5);
        softAssert.assertTrue(stepThree.getUploadedCoverElement().getImageTitle().getText().contains(defaultCoverImage));
        stepThree.clickCompleteButton();

        profilePage = new ProfilePage(driver);
        card = profilePage.getClubCardComponents().getFirst();
        clubPage = card.clickDetailsButton();
        clubPage.waitUntilPageIsCompletelyLoaded();
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

        profilePage = new ProfilePage(driver);
        softAssert.assertEquals(profilePage.getTopNoticeMessage().getText(), "Гурток успішно оновлено");
        ClubCardWithEditComponent card = profilePage.getClubCardComponents().getFirst();

        ClubPage clubPage = card.clickDetailsButton();
        clubPage.waitUntilPageIsCompletelyLoaded();
        clubPage = new ClubPage(driver);
        softAssert.assertTrue(clubPage.getClubDescription().getText().equals(testDescription));

        profilePage = clubPage.getHeader().openUserMenu().clickProfile();
        profilePage.waitUntilPageIsCompletelyLoaded();

        clubCard = profilePage.getClubCardComponents().getFirst();
        addClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();
        addClubPopUpComponent.waitPopUpOpen(5);
        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();
        addClubPopUpComponent.getStepTwoContainer().clickNextStepButton();

        stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clearDescriptionTextarea().setDescriptionValue(defaultDescription);
        softAssert.assertTrue(stepThree.getNextStepButton().isEnabled());
        stepThree.clickCompleteButton();

        profilePage = new ProfilePage(driver);
        softAssert.assertEquals(profilePage.getTopNoticeMessage().getText(), "Гурток успішно оновлено");
        card = profilePage.getClubCardComponents().getFirst();
        clubPage = card.clickDetailsButton();
        clubPage.waitUntilPageIsCompletelyLoaded();
        softAssert.assertTrue(clubPage.getClubDescription().getText().equals(defaultDescription));

        softAssert.assertAll();
    }

    @Test()
    @Issue("TUA-57")
    public void checkCorrectEditOfNameCategoryAge(){
        ClubCardWithEditComponent clubCard = profilePage.getClubCardComponents().getFirst();
        clubCard.sleep(1000);
        AddClubPopUpComponent editClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();
        editClubPopUpComponent.waitPopUpOpen(15);
        AddClubPopUpStepOne editClubPopUpStepOne = editClubPopUpComponent.getStepOneContainer();

        editClubPopUpStepOne.getClubNameInputElement().clearInput();
        editClubPopUpStepOne.getClubNameInputElement().setValue("Harry 123&*? Potter");
        softAssert.assertEquals(editClubPopUpStepOne.getClubNameInputElement().getInput().getAttribute("value"), "Harry 123&*? Potter");

        editClubPopUpStepOne.selectCategory("Центр розвитку");
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

    @Test(description = "User can change 'Фото' for the existing club")
    @Description("Verify that user can change ‘Фото’ on the ‘Опис’ tab of the ‘Редагувати гурток’ pop-up window")
    @Issue("TUA-958")
    public void checkChangeCoverPhoto() {
        String imageName= "image.png";
        String clubName = getClubName();
        ClubCardWithEditComponent clubCardByName = profilePage.getClubCardByName(clubName);
        AddClubPopUpComponent editClubPopUp = clubCardByName.clickMoreButton().clickEditClub();
        editClubPopUp.waitPopUpOpen(5);
        editClubPopUp.getStepOneContainer().clickNextStepButton();
        editClubPopUp.getStepTwoContainer().clickNextStepButton();
        AddClubPopUpStepThree stepThree = editClubPopUp.getStepThreeContainer();

        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath(imageName));
        stepThree.getUploadedCoverElement().waitImageLoad(5);

        softAssert.assertEquals(stepThree
                                        .getUploadedCoverElement()
                                        .getImageTitle()
                                        .getText(),
                                imageName,
                                "Image should be changed");

        softAssert.assertTrue(stepThree
                                .getUploadedCoverElement()
                                .getImageTitle()
                                .isEnabled(),
                              "Cannot click on cover photo");

        stepThree.clickCompleteButton();

        refreshProfilePage();
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
        AddClubPopUpComponent edit = clubCard.clickMoreButton().clickEditClub();
        edit.waitPopUpOpen(10);

        AddClubPopUpStepOne one = edit.getStepOneContainer();
        String oldCenter = one.getCenterSelectedTitle().getText();
        one.getCenterDropdownElement().clickDropdown().selectValue(centerToSelect);
        one.clickNextStepButton();
        edit.getStepTwoContainer().clickNextStepButton();
        edit.getStepThreeContainer().clickCompleteButton();

        profilePage = new ProfilePage(driver);
        clubCard = profilePage.getClubCardByName("Club With Center");
        ClubPage clubPage = clubCard.clickDetailsButton();
        String newCenter = clubPage.getClubCenterName();

        softAssert.assertNotEquals(oldCenter, newCenter);
        softAssert.assertEquals(newCenter, centerToSelect);
        softAssert.assertAll();
    }

    @Test(description = "Check the 'Контакти' tab with UI")
    @Description("Check the ‘Контакти’ tab on the ‘Редагувати гурток’ pop-up window (UI)")
    @Issue("TUA-48")
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
        softAssert.assertEquals(stepTwo.getClubLocationsTitle().getCssValue("color"),
                "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepTwo.getClubLocationsTitle().getCssValue("font-size"),
                "19px");

        softAssert.assertEquals(stepTwo.getClubAvailableOnlineTitle().getText(), "Доступний онлайн");
        softAssert.assertEquals(stepTwo.getClubAvailableOnlineTitle().getCssValue("color"),
                "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepTwo.getClubAvailableOnlineTitle().getCssValue("font-size"),
                "19px");

        softAssert.assertEquals(stepTwo.getClubWorkHoursTitle().getText(), "Години роботи");
        softAssert.assertEquals(stepTwo.getClubWorkHoursTitle().getCssValue("color"),
                "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepTwo.getClubWorkHoursTitle().getCssValue("font-size"),
                "19px");

        softAssert.assertEquals(stepTwo.getClubContactsTitle().getText(), "Контакти");
        softAssert.assertEquals(stepTwo.getClubContactsTitle().getCssValue("color"),
                "rgba(109, 109, 109, 1)");
        softAssert.assertEquals(stepTwo.getClubContactsTitle().getCssValue("font-size"),
                "19px");

        softAssert.assertEquals(stepTwo.getAddLocationButton().getText(), "Додати локацію");
        softAssert.assertEquals(stepTwo.getAddLocationButton().getCssValue("color"),
                "rgba(254, 119, 41, 1)");
        softAssert.assertEquals(stepTwo.getAddLocationButton().getCssValue("font-size"),
                "14px",
                "location button");

        softAssert.assertTrue(stepTwo.getSwitchButton().isDisplayed());

        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getErrorMessages().isEmpty());
        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getStaticIcon().isDisplayed());

        softAssert.assertTrue(stepTwo.getNextStepButton().isDisplayed());
        softAssert.assertEquals(stepTwo.getNextStepButton().getText(), "Наступний крок");
        softAssert.assertEquals(stepTwo.getNextStepButton().getCssValue("color"),
                "rgba(255, 255, 255, 1)");


        softAssert.assertTrue(stepTwo.getPreviousStepButton().isDisplayed());
        softAssert.assertEquals(stepTwo.getPreviousStepButton().getText(), "Назад");
        softAssert.assertEquals(stepTwo.getPreviousStepButton().getCssValue("color"),
                "rgba(250, 140, 22, 1)");

        Actions actions = new Actions(driver);
        WebElement startingElement = stepTwo.getSwitchButton();
        startingElement.click();
        softAssert.assertTrue(stepTwo.getSwitchButton().equals(driver.switchTo().activeElement()),
                "Focus should be on Switch Button");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getWorkDaysCheckboxList().get(0).equals(driver.switchTo().activeElement()),
                "Focus should be on Monday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getWorkDaysCheckboxList().get(1).equals(driver.switchTo().activeElement()),
                "Focus should be on Tuesday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getWorkDaysCheckboxList().get(2).equals(driver.switchTo().activeElement()),
                "Focus should be on Wednesday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getWorkDaysCheckboxList().get(3).equals(driver.switchTo().activeElement()),
                "Focus should be on Thursday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getWorkDaysCheckboxList().get(4).equals(driver.switchTo().activeElement()),
                "Focus should be on Friday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getWorkDaysCheckboxList().get(5).equals(driver.switchTo().activeElement()),
                "Focus should be on Saturday checkbox");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getWorkDaysCheckboxList().get(6).equals(driver.switchTo().activeElement()),
                "Focus should be on Sunday checkbox");
        actions.sendKeys(Keys.TAB).perform();

        softAssert.assertTrue(stepTwo.getTelephoneInputElement().getInput().equals(driver.switchTo().activeElement()),
                "Focus should be on Telephone Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getFacebookInputElement().getInput().equals(driver.switchTo().activeElement()),
                "Focus should be on Facebook Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getWhatsappInputElement().getInput().equals(driver.switchTo().activeElement()),
                "Focus should be on Whatsapp Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getEmailInputElement().getInput().equals(driver.switchTo().activeElement()),
                "Focus should be on Email Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getSkypeInputElement().getInput().equals(driver.switchTo().activeElement()),
                "Focus should be on Skype Input");
        actions.sendKeys(Keys.TAB).perform();
        softAssert.assertTrue(stepTwo.getSiteInputElement().getInput().equals(driver.switchTo().activeElement()),
                "Focus should be on Site Input");
        actions.sendKeys(Keys.TAB).perform();

        softAssert.assertTrue(stepTwo.getPreviousStepButton().equals(driver.switchTo().activeElement()),
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
        AddClubPopUpComponent edit = clubCard.clickMoreButton().clickEditClub();
        edit.waitPopUpOpen(10);
        edit.getStepOneContainer().clickNextStepButton();
        AddClubPopUpStepTwo two = edit.getStepTwoContainer();

        two.getTelephoneInputElement().clearInput().setValue(input);
        String errorMsg = "";
        List<String> errorList = two.getTelephoneInputElement().getErrorMessagesTextList();
        for (String str : errorList) {
            errorMsg = errorMsg + str + " ";
        }

        softAssert.assertEquals(errorMsg, expectedErrorMsg);
        softAssert.assertFalse(two.getNextStepButton().isEnabled());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidEmail", dataProviderClass = EditClubWithManagerDataProvider.class)
    @Description("Verify user cannot save invalid data in Email field on the 'Контакти' tab of the 'Редагувати гурток' pop-up window")
    @Issue("TUA-961")
    public void checkInvalidEmailInput(String input) {
        softAssert = new SoftAssert();
        ClubCardWithEditComponent clubCard = profilePage.getClubCardByName("Club With Center");
        AddClubPopUpComponent edit = clubCard.clickMoreButton().clickEditClub();
        edit.waitPopUpOpen(10);
        edit.getStepOneContainer().clickNextStepButton();
        AddClubPopUpStepTwo two = edit.getStepTwoContainer();

        two.getEmailInputElement().clearInput().setValue(input);
        softAssert.assertEquals(
                two.getEmailInputElement().getErrorMessagesTextList().get(0),
                "Некоректний формат email"
        );
        softAssert.assertFalse(two.getNextStepButton().isEnabled());
        softAssert.assertAll();
    }

    @Test(description = "Check the submit button is disabled after entering invalid data in the description field")
    @Description("Verify that user can edit ‘Опис’ field with invalid data, " +
            "and changes will not be saved on the ‘Опис’ tab of the ‘Редагувати гурток’ pop-up window ")
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


    @Test(description = "Verify club title can't be changed with incorrect data")
    @Description("""
            Verify invalid data in 'Назва' field can't be saved
            on the ‘Основна інформація’ tab of the ‘Редагувати гурток’ pop-up""")
    @Issue("TUA-58")
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

    @Test(description = "Verify user can change logo of the club cards")
    @Description("""
            Verify user can change ‘Лого’ on the ‘Опис’ tab
            of the ‘Редагувати гурток’ pop-up window""")
    @Issue("TUA-957")
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

        UploadedImgComponent uploadedLogoImg = stepThreeContainer.getUploadedLogoElement();
        uploadedLogoImg.waitImageLoad(5);

        softAssert.assertEquals(uploadedLogoImg.getImageTitle().getAttribute("title"),
                newLogoImage, "Logo title should be '%s'".formatted(newLogoImage));

        stepThreeContainer.clickCompleteButtonWithWait(10);
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
        stepThreeContainer.getUploadedLogoElement()
                .waitImageLoad(5);
        stepThreeContainer.clickCompleteButton();
    }

    @Test(description = "The user can add center for the existing club")
    @Description("Verify that user can add center for the existing club without center")
    @Issue("TUA-980")
    public void checkStepOneEditCenter() {
        String centerName = "Академія талановитих дітей";
        profilePage.getClubCardWithoutCenter();
        AddClubPopUpComponent addClubPopUpComponent = new AddClubPopUpComponent(driver);
        String clubName = addClubPopUpComponent
                .getStepOneContainer()
                .getClubNameInputElement()
                .getInput()
                .getAttribute("value")
                .toString();

        addClubPopUpComponent.getStepOneContainer().clickCenterDropdown().selectCenter(centerName);
        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();
        addClubPopUpComponent.getStepTwoContainer().clickNextStepButton();
        addClubPopUpComponent.getStepThreeContainer().clickCompleteButton();

        refreshProfilePage();
        ClubCardWithEditComponent clubCardUpdated = profilePage.getClubCardByName(clubName);

        softAssert.assertEquals(clubCardUpdated
                        .clickDetailsButton()
                        .getClubCenter()
                        .getText()
                        .trim(),
                centerName);

        softAssert.assertAll();
    }

//    Delete icon in location Element does not work
//    (click on it invoke popup element that is covered by Add club popup element)
    @Ignore
    @Test(description = "TUA-87/63")
    @Description("(63 duplicates)Verify that ‘Доступний онлайн’ checkbox is checked automatically if no location is checked (for a club that is not in the center)")
    @Issue("TUA-87")
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
        deletedLocationName = location.getLocationNameInputElement().getInput().getAttribute("value");
        deletedCity = location.getLocationCityDropdownElement().getSelectedItem().getText();
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
        location.getLocationNameInputElement().setValue(deletedLocationName);
        location.getLocationCityDropdownElement().clickDropdown().selectValue(deletedCity);
        location.getLocationDistrictDropdownElement().clickDropdown().selectValue(deletedDistrict);
        location.getLocationAddressInputElement().setValue(deletedAddress);
        location.getLocationCoordinatesInputElement().setValue(deletedCoordinates);
        location.getLocationTelephoneInputElement().setValue(deletedTelephone);
        location.clickAddLocationButton();

        twoEdit.clickNextStepButton();
        edit.getStepThreeContainer().clickCompleteButton();
    }

    @Test
    @Issue("TUA-965")
    public void checkEditClubButtonIsDisplayed(){
        ClubCardWithEditComponent clubCard = profilePage.getClubCardComponentsList().getFirst();
        AddClubPopUpComponent editClubPopUp = clubCard.clickMoreButton().clickEditClub();
        editClubPopUp.waitPopUpOpen(5);
        softAssert.assertTrue(editClubPopUp.isOpen());
        softAssert.assertAll();
    }
}
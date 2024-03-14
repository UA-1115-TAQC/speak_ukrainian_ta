package com.academy.ui.profilePage;

import com.academy.ui.components.AddClubPopUpComponent.*;
import com.academy.ui.components.AddLocationPopUpComponent.AddLocationPopUpComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TestAllEnteredValuesAreSavedClubPopUpWithoutCenter extends LoginWithManagerTestRunner {
   protected SoftAssert softAssert;
    protected  ProfilePage profilePage;
   protected AddClubPopUpComponent addClubPopUpComponent;
   protected AddClubPopUpStepOne addClubPopUpStepOne;
   protected AddClubPopUpStepTwo addClubPopUpStepTwo;
   protected AddClubPopUpStepThree addClubPopUpStepThree;
    protected AddClubPopUpSider addClubPopUpSider;
   protected WebDriverWait wait;
    private String validClubName="IT club";
   private String validMinAge="2";
   private String validMaxAge="18";
   private int checkboxToCheckIndex=0;
   private String phone="0100101010";
   private String facebook = "facebook";
   private String whatsapp="whatsapp";
   private String email = "email@com.com";
   private String skype ="skype";
   private String site ="site";
   private AddLocationPopUpComponent addLocationPopUpComponent;
   private String locationName="test location";
   private String locationCity="Київ";
   private String address="address 1";
   private String coordinates="50.4504, 30.524";
   private String description="test description. -09j2njnsiiu\n" +
           "test description. -09j2njnsiiu";

private String imagePath = "image.png";
private String stepOneSelectedCentre;
    @BeforeMethod
    public void precondition(){
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        profilePage = homePage.header.openUserMenu().clickProfile();
        profilePage.addButtonClick().get(0).click();
        addClubPopUpComponent = new AddClubPopUpComponent(driver);
        addClubPopUpSider = new AddClubPopUpSider(driver, addClubPopUpComponent.getSiderElement());
        addClubPopUpStepOne = addClubPopUpComponent.getStepOneContainer();
    }
    @Test(description = "TUA-127")
    @Description("Verify that all entered values are saved after clicking " +
            "'Наступний крок' button on 'Додати гурток' pop-up if a club is without a center")
    @Issue("TUA-127")
    public void verifyAllEnteredValuesAreSavedClubPopUpWithoutCenter(){
        verifyAllParametersAreEmptyByDefaultStepOne();
        fillStepOneComponentWithValidData(validClubName, validMinAge, validMaxAge, checkboxToCheckIndex);
        verifyStepOneIsFilledWithEnteredData(validClubName, validMinAge, validMaxAge, checkboxToCheckIndex, true);
        addClubPopUpStepOne.clickNextStepButton();
        addClubPopUpStepTwo = addClubPopUpComponent.getStepTwoContainer();
        verifyAllParametersAreEmptyByDefaultStepTwo();
        fillStepTwoWithValidData( phone, facebook, whatsapp, email, skype,site);
        verifyStepTwoIsFilledWithEnteredData( phone, facebook, whatsapp, email, skype,site, locationName);
        addClubPopUpStepTwo.clickNextStepButton();
        addClubPopUpStepThree = addClubPopUpComponent.getStepThreeContainer();
        verifyAllParametersAreEmptyStepThree();
        fillStepThreeWithValidData(configProperties.getImagePath(imagePath),configProperties.getImagePath(imagePath),description);
        verifyStepThreeIsFilledWithEnteredData(configProperties.getImagePath(imagePath),configProperties.getImagePath(imagePath),description);
        addClubPopUpStepThree.clickPreviousStepButton();
        addClubPopUpStepTwo = addClubPopUpComponent.getStepTwoContainer();
        verifyStepTwoIsFilledWithEnteredData( phone, facebook, whatsapp, email, skype,site, locationName);
        addClubPopUpStepTwo.clickPreviousStepButton();
        addClubPopUpStepOne = addClubPopUpComponent.getStepOneContainer();
      verifyStepOneIsFilledWithEnteredData(validClubName, validMinAge, validMaxAge, checkboxToCheckIndex,false);
        softAssert.assertAll();
    }

    private void verifyAllParametersAreEmptyByDefaultStepOne(){
        softAssert.assertTrue(addClubPopUpStepOne.getClubNameInputElement().getInput().getAttribute("value").isEmpty(),
                "The name of the club isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepOne.getCheckedCategoriesList().isEmpty(),
                "Some of the checkboxes are checked by default");
        softAssert.assertTrue(addClubPopUpStepOne.getMaxAgeInput().getAttribute("value").isEmpty(),
                "The maximum age input isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepOne.getCenterSelect().getText().isEmpty(), "The club name input isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepOne.getMinAgeInput().getAttribute("value").isEmpty(),
                "The minimum age input isn't empty by default");

    }

    private void fillStepOneComponentWithValidData(String validClubName, String validMinAge, String validMaxAge, int checkboxToCheckIndex){
        wait.until(ExpectedConditions.visibilityOf(addClubPopUpStepOne.getClubNameInputElement().getInput()));
        addClubPopUpStepOne.getClubNameInputElement().getInput().sendKeys(validClubName);
        addClubPopUpStepOne.getCategoriesCheckboxList().get(checkboxToCheckIndex).click();
        addClubPopUpStepOne.setMaxAgeInput(validMaxAge);
        addClubPopUpStepOne.setMinAgeInput(validMinAge);
        addClubPopUpStepOne.getCenterSelect().click();
        addClubPopUpStepOne.getCentersList().get(checkboxToCheckIndex).click();
        wait.until(ExpectedConditions.visibilityOf(addClubPopUpStepOne.getCenterSelectedTitle()));
    }

    private void verifyStepOneIsFilledWithEnteredData(String validClubName, String validMinAge, String validMaxAge, int checkboxToCheckIndex, boolean runForThe1stTime){
        softAssert.assertEquals(addClubPopUpStepOne.getClubNameInputElement().getInput().getAttribute("value")
                ,validClubName, "The club name input doesn't contain the entered value");
        softAssert.assertEquals(addClubPopUpStepOne.getCategoriesCheckboxList().get(checkboxToCheckIndex).getText(),
                addClubPopUpStepOne.getCheckedCategoriesList().get(checkboxToCheckIndex).getText(), "The checkbox wasn't checked");
        softAssert.assertEquals(addClubPopUpStepOne.getMinAgeInput().getAttribute("value")
                ,validMinAge, "The min age input doesn't contain the entered value");
        softAssert.assertEquals(addClubPopUpStepOne.getMaxAgeInput().getAttribute("value"),validMaxAge,
                "The max age input doesn't contain the entered value");
        stepOneSelectedCentre= addClubPopUpStepOne.getCenterSelectedTitle().getText();
        if(runForThe1stTime){
            softAssert.assertEquals(addClubPopUpStepOne.getCentersList().get(checkboxToCheckIndex).getText(),
                    addClubPopUpStepOne.getCenterSelectedTitle().getText(), "The center wasn't selected");

        }else{
            softAssert.assertEquals(stepOneSelectedCentre,  addClubPopUpStepOne.getCenterSelectedTitle().getText());
        }
    }

    private void verifyAllParametersAreEmptyByDefaultStepTwo(){
        wait.until(ExpectedConditions.visibilityOf(addClubPopUpStepTwo.getClubLocationsTitle()));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(addClubPopUpSider.getSecondStepIconBackground(),"border-color", "rgba(255, 255, 255, 1)")));
        softAssert.assertTrue(addClubPopUpStepTwo.getCheckedWorkDaysList().isEmpty(),
                "Some of the work days are checked by default");
        softAssert.assertEquals(addClubPopUpStepTwo.getSwitchButton().getAttribute("aria-checked"),"false",
                "The switch button is toggled by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getTelephoneInputElement().getInput().getAttribute("value").isEmpty(),
                "The telephone input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getFacebookInputElement().getInput().getAttribute("value").isEmpty(),
                "The facebook input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getWhatsappInputElement().getInput().getAttribute("value").isEmpty(),
                "The whatsapp input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getEmailInputElement().getInput().getAttribute("value").isEmpty(),
                "The email input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getSkypeInputElement().getInput().getAttribute("value").isEmpty(),
                "The skype input element isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepTwo.getSiteInputElement().getInput().getAttribute("value").isEmpty(),
                "The site input element isn't empty by default");
    }

    private void fillStepTwoWithValidData(String phone, String facebook, String whatsapp, String email,
                                          String skype, String site){
        wait.until(ExpectedConditions.visibilityOf(addClubPopUpStepTwo.getClubLocationsTitle()));
        addClubPopUpStepTwo.getTelephoneInputElement().setValue(phone);
        addClubPopUpStepTwo.getFacebookInputElement().setValue(facebook);
        addClubPopUpStepTwo.getWhatsappInputElement().setValue(whatsapp);
        addClubPopUpStepTwo.getEmailInputElement().setValue(email);
        addClubPopUpStepTwo.getSkypeInputElement().setValue(skype);
        addClubPopUpStepTwo.getSiteInputElement().setValue(site);
        addLocationPopUpComponent= addClubPopUpStepTwo.clickAddLocationButton();
        addLocation(locationName, locationCity, address, coordinates, phone);
    }

    private void verifyStepTwoIsFilledWithEnteredData(String phone, String facebook, String whatsapp, String email,
                                                      String skype, String site, String locationName){
        softAssert.assertTrue(addClubPopUpStepTwo.getTelephoneInputElement().getInput().getAttribute("value").contains(phone),
                "The phone input doesn't contain the entered phone");
        softAssert.assertEquals(addClubPopUpStepTwo.getFacebookInputElement().getInput().getAttribute("value"),facebook);
        softAssert.assertEquals(addClubPopUpStepTwo.getWhatsappInputElement().getInput().getAttribute("value"),whatsapp);
        softAssert.assertEquals(addClubPopUpStepTwo.getEmailInputElement().getInput().getAttribute("value"),email);
        softAssert.assertEquals(addClubPopUpStepTwo.getSkypeInputElement().getInput().getAttribute("value"),skype);
        softAssert.assertEquals(addClubPopUpStepTwo.getSiteInputElement().getInput().getAttribute("value"),site);
        softAssert.assertEquals(addClubPopUpStepTwo.getListOfLocationElements().get(checkboxToCheckIndex).getLocationItemTitle().getText(), locationName);
    }

    private void addLocation(String locationName,String locationCity, String address, String coordinates, String phone){
        addLocationPopUpComponent.getLocatioNameInputElement().setValue(locationName);
        softAssert.assertEquals(addLocationPopUpComponent.getLocatioNameInputElement().getInput().getAttribute("value"), locationName,
                "The location name input doesn't contain the entered value");
        addLocationPopUpComponent.getLocatioCityDropdownElement().clickDropdown().selectValue(locationCity);
        softAssert.assertEquals(addLocationPopUpComponent.getLocatioCityDropdownElement().getSelectedItem().getAttribute("title"), locationCity,
                "The selected value isn't displayed in the dropdown");
        addLocationPopUpComponent.getLocationAddressInputElement().setValue(address);
        softAssert.assertEquals(addLocationPopUpComponent.getLocationAddressInputElement().getInput().getAttribute("value"), address,
                "The address input doesn't contain the entered value");
        addLocationPopUpComponent.getLocationCoordinatesInputElement().setValue(coordinates);
        softAssert.assertEquals(addLocationPopUpComponent.getLocationCoordinatesInputElement().getInput().getAttribute("value"), coordinates,
                "The coordinates input doesn't contain the entered value");
        addLocationPopUpComponent.getLocationTelephoneInputElement().setValue(phone);
        softAssert.assertTrue(addLocationPopUpComponent.getLocationTelephoneInputElement().getInput().getAttribute("value").contains(phone),
                "The phone input doesn't contain the entered value");
        addLocationPopUpComponent.getAddLocationButton().click();
    }

    private void verifyAllParametersAreEmptyStepThree(){
        wait.until(ExpectedConditions.visibilityOf(addClubPopUpStepThree.getClubLogoTitle()));
        softAssert.assertTrue(addClubPopUpStepThree.getClubDescriptionTextarea().getText().isEmpty(), "The text area isn't empty by default");
        softAssert.assertTrue(addClubPopUpStepThree.getClubLogoDownloadInput().getAttribute("value").isEmpty(),
                "The logo download input isn't empty");
        softAssert.assertTrue(addClubPopUpStepThree.getClubCoverDownloadInput().getAttribute("value").isEmpty(),
                "The club cover download input isn't empty");
        softAssert.assertTrue(addClubPopUpStepThree.getClubGalleryDownloadInput().getAttribute("value").isEmpty(),
                "The club gallery download input isn't empty");
    }

    private void fillStepThreeWithValidData(String logoPath, String coverPath, String description){
        addClubPopUpStepThree.setDescriptionValue(description);
        addClubPopUpStepThree.getClubLogoDownloadInput().sendKeys(logoPath);
        addClubPopUpStepThree.getClubCoverDownloadInput().sendKeys(coverPath);
    }

    private void verifyStepThreeIsFilledWithEnteredData(String logoPath, String coverPath, String description){
        softAssert.assertEquals(addClubPopUpStepThree.getClubDescriptionTextarea().getText(), description,
                "The entered description doesn't match the description shown in the text area");
        if (!addClubPopUpStepThree.getAllUploadedElements().isEmpty()) {
            softAssert.assertFalse(addClubPopUpStepThree.getAllUploadedElements().get(0).getAttribute("title").isEmpty(),
                    "The logo wasn't uploaded");
            softAssert.assertFalse(addClubPopUpStepThree.getAllUploadedElements().get(1).getAttribute("title").isEmpty(),
                    "The cover wasn't uploaded");
        }
    }
}

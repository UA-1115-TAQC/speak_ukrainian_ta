package com.academy.ui.editClubCart;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepThree;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.pages.ClubPage;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClubCardWithEditComponentWithManagerLoginTest extends LoginWithManagerTestRunner {

    private ProfilePage profilePage;
    private SoftAssert softAssert;


    @BeforeMethod
    public void editClubCartSetUp() {
        profilePage = homePage.header.openUserMenu().clickProfile();
        softAssert = new SoftAssert();
    }

    @Test(description = "TUA-967")
    public void checkEditCartUploadPhotos() {
        String imagePath = "src\\test\\resources\\images\\harrybean.jpg";

        ClubCardWithEditComponent clubCard = profilePage.getClubCardComponents().getFirst();
        AddClubPopUpComponent addClubPopUpComponent = clubCard.clickMoreButton().clickEditClub();

        addClubPopUpComponent.getStepOneContainer().clickNextStepButton();
        addClubPopUpComponent.getStepTwoContainer().clickNextStepButton();

        AddClubPopUpStepThree stepThree = addClubPopUpComponent.getStepThreeContainer();
        stepThree.clickClubLogoDownloadButton();
        stepThree.getClubLogoDownloadInput().sendKeys(configProperties.getImagePath() + imagePath);
        softAssert.assertTrue(stepThree.getUploadedLogoImg().getImgTitle().getText().equals("harrybean.jpg"));

        stepThree.clickClubCoverDownloadButton();
        stepThree.getClubCoverDownloadInput().sendKeys(configProperties.getImagePath() + imagePath);
        softAssert.assertTrue(stepThree.getUploadedCoverImg().getImgTitle().getText().equals("harrybean.jpg"));

        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath() + imagePath);
        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath() + imagePath);
        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath() + imagePath);
        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath() + imagePath);
        stepThree.clickClubGalleryDownloadButton();
        stepThree.getClubGalleryDownloadInput().sendKeys(configProperties.getImagePath() + imagePath);

        stepThree.sleep(500);
        softAssert.assertEquals(stepThree.getClubGalleryUploadedImgs().size(), 5);

        stepThree.clickCompleteButton();

        ClubCardWithEditComponent card = profilePage.getClubCardComponents().getFirst();
        Actions actions = new Actions(driver);
        actions.moveToElement(card.getDetailsButton()).click().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("/club"));
        ClubPage clubPage = new ClubPage(driver);
        softAssert.assertEquals(clubPage.getCarouselImgs().size(), 5);

        softAssert.assertAll();
    }
}

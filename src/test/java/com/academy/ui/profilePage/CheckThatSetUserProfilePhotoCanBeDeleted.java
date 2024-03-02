package com.academy.ui.profilePage;

//import com.academy.ui.runners.LogInWithUserTestRunner;
import com.academy.ui.components.EditProfilePopUpComponent;
import com.academy.ui.pages.ProfilePage;
import com.academy.ui.runners.LoginWithUserTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckThatSetUserProfilePhotoCanBeDeleted extends LoginWithUserTestRunner {
    SoftAssert softAssert;
    WebDriverWait wait;
    EditProfilePopUpComponent editProfilePopUpComponent;
    @BeforeMethod
    public void setup(){
        softAssert= new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        homePage.header.openUserMenu().clickProfile().editButtonClick();
        editProfilePopUpComponent = new EditProfilePopUpComponent(driver,
                driver.findElement(By.xpath("./descendant::div[contains(@class, \"ant-modal css-13m256z user-edit\")]//div[@class=\"ant-modal-content\"]")));
        editProfilePopUpComponent.clickUserType();
    }
    @Test (description = "TUA-919")
    public void checkThatSetUserProfilePhotoCanBeDeleted(){
        //Everyone should add their path to our repo to config and use it
        editProfilePopUpComponent.getUploadUserPhotoInput().sendKeys(configProperties.getSampleImagePath()+"/speak_ukrainian_ta/src/test/java/com/academy/ui/profilePage/samplePhoto.png");
        wait.until(ExpectedConditions.visibilityOf(editProfilePopUpComponent.getUploadPhotoNameUserPhoto()));
        editProfilePopUpComponent.getUploadPhotoNameUserPhoto().click();
        wait.until(ExpectedConditions.elementToBeClickable(editProfilePopUpComponent.getRemoveUserPhoto()));
        editProfilePopUpComponent.getRemoveUserPhoto().click();
        editProfilePopUpComponent.clickSubmitButton();
        //+ verify the pop up The profile image was successfully changed???
        ProfilePage profilePage = new ProfilePage(driver);
        softAssert.assertFalse(profilePage.getUserAvatarImage().getAttribute("src").contains("samplePhoto.png"),"The uploaded photo wasn't deleted. It is still present on the profile page");
        softAssert.assertFalse(profilePage.header.getAvatarImage().getAttribute("src").contains("samplePhoto.png"),"The uploaded photo wasn't deleted. It is still present on the profile page");
        softAssert.assertAll();
    }
}

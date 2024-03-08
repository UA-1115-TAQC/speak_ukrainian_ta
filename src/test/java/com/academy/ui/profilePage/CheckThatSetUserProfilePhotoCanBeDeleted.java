package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.runners.LogInWithUserTestRunner;
import com.academy.ui.pages.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckThatSetUserProfilePhotoCanBeDeleted extends LogInWithUserTestRunner {
    SoftAssert softAssert;
    WebDriverWait wait;
    EditProfilePopUp editProfilePopUpComponent;
    @BeforeMethod
    public void setup(){
        softAssert= new SoftAssert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        homePage.header.openUserMenu().clickProfile().editButtonClick();
        editProfilePopUpComponent = new EditProfilePopUp(driver,
                driver.findElement(By.xpath("./descendant::div[contains(@class, \"ant-modal css-13m256z user-edit\")]//div[@class=\"ant-modal-content\"]")));
        editProfilePopUpComponent.clickUserButton();
    }
    @Test (description = "TUA-919")
    public void checkThatSetUserProfilePhotoCanBeDeleted(){
        editProfilePopUpComponent.getUploadUserPhotoInput().sendKeys(configProperties.getImagePath("image.png"));
        editProfilePopUpComponent.waitUntilElementIsVisible(editProfilePopUpComponent.getUploadPhotoNameUserPhoto());
        editProfilePopUpComponent.getUploadPhotoNameUserPhoto().click();
        editProfilePopUpComponent.waitUntilElementIsVisible(editProfilePopUpComponent.getRemoveUserPhoto());
        editProfilePopUpComponent.getRemoveUserPhoto().click();
        editProfilePopUpComponent.clickSubmitButton();
        //+ verify the pop up The profile image was successfully changed??? //todo
        ProfilePage profilePage = new ProfilePage(driver);
        softAssert.assertFalse(profilePage.getUserAvatarImage().getAttribute("src").contains("image.png"),"The uploaded photo wasn't deleted. It is still present on the profile page");
        softAssert.assertFalse(profilePage.header.getAvatarImage().getAttribute("src").contains("image.png"),"The uploaded photo wasn't deleted. It is still present on the profile page");
        softAssert.assertAll();
    }
}

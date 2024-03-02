package com.academy.ui.profilePage;

import com.academy.ui.components.EditProfilePopUpComponent;
import com.academy.ui.runners.LoginWithManagerTestRunner;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ChangePasswordValidDataForManager extends LoginWithManagerTestRunner {
    private EditProfilePopUpComponent editProfilePopUpComponent;
    SoftAssert softAssert;
    @BeforeMethod
    public void setup(){
        softAssert= new SoftAssert();
        homePage.header.openUserMenu().clickProfile().editButtonClick();
        editProfilePopUpComponent = new EditProfilePopUpComponent(driver,
                driver.findElement(By.xpath("./descendant::div[contains(@class, \"ant-modal css-13m256z user-edit\")]//div[@class=\"ant-modal-content\"]")));
        editProfilePopUpComponent.clickUserType();
        editProfilePopUpComponent.checkBoxChangePasswordClick();
    }
    @Test(description = "TUA-154")
    public void test(){
        editProfilePopUpComponent.sleep(1000);
    }
}

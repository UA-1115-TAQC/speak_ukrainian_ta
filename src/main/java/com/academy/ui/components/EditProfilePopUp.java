package com.academy.ui.components;

import com.academy.ui.components.editProfileElement.EditProfileInputElement;
import com.academy.ui.pages.ProfilePage;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class EditProfilePopUp extends BasePopUp {

    @FindBy(xpath="//input[@value='ROLE_MANAGER']/../..")
    @Getter(AccessLevel.NONE)
    protected WebElement managerTypeButton;

    @FindBy(xpath="//input[@value='ROLE_USER']/../..")
    @Getter(AccessLevel.NONE)
    protected WebElement userTypeButton;

    @FindBy(xpath = "./descendant::div[contains(@class,'user-edit-input')][1]")
    @Getter(AccessLevel.NONE)
    private WebElement lastName;

    @FindBy(xpath = "./descendant::div[contains(@class,'user-edit-input')][2]")
    @Getter(AccessLevel.NONE)
    private WebElement firstName;

    @FindBy(xpath = "./descendant::div[contains(@class,'user-edit-input')][3]")
    @Getter(AccessLevel.NONE)
    private WebElement phone;

    @FindBy(xpath = "./descendant::div[contains(@class,'user-edit-input')][4]")
    @Getter(AccessLevel.NONE)
    private WebElement email;

    @FindBy(xpath = "./descendant::div[@class=\"align-checkbox\"]//text")
    private WebElement changePasswordTitle;

    @FindBy(xpath = "./descendant::input[@name=\"checkbox\"]")
    private WebElement checkboxChangePassword;

    @FindBy(xpath = "./descendant::div[contains(@class,'user-edit-input')][6]")
    @Getter(AccessLevel.NONE)
    private WebElement currentPassword;

    @FindBy(xpath = "./descendant::div[contains(@class,'user-edit-input')][7]")
    @Getter(AccessLevel.NONE)
    private WebElement newPassword;

    @FindBy(xpath = "./descendant::div[contains(@class,'user-edit-input')][8]")
    @Getter(AccessLevel.NONE)
    private WebElement confirmPassword;

    @FindBy(xpath = "./descendant::button[@class=\"ant-btn css-13m256z ant-btn-default submit-button\"]")
    private WebElement submitButton;

    @FindBy(xpath = ".//span[@aria-label=\"question-circle\"]")
    private WebElement questionCircleForPhoto;

    @FindBy(xpath = "//label[@for=\"edit_urlLogo\"]")
    private WebElement photoLink;

    @FindBy(xpath = "//div[@class=\"ant-tooltip-inner\"]")
    private WebElement photoToolTipForm;

    @FindBy(xpath = "//span[@class=\"add-club-upload\"]")
    private WebElement uploadPhotoLink;

    @FindBy(xpath = ".//span[@class=\"ant-upload-list-item-name\"]")
    private WebElement uploadPictureTitle;

    @FindBy(xpath = ".//button[contains(@class, \"ant-upload-list-item-action\")]")
    private WebElement removeUserPhoto;

    @FindBy(xpath = ".//div[@class=\"ant-upload-icon\"]")
    private WebElement paperClipForUploadUserPhoto;

    private EditProfileInputElement lastNameElement;
    private EditProfileInputElement firstNameElement;
    private EditProfileInputElement phoneElement;
    private EditProfileInputElement currentPasswordElement;
    private EditProfileInputElement newPasswordElement;
    private EditProfileInputElement confirmPasswordElement;

    public EditProfilePopUp(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        lastNameElement = new EditProfileInputElement(driver, lastName);
        firstNameElement = new EditProfileInputElement(driver, firstName);
        phoneElement = new EditProfileInputElement(driver, phone);
        currentPasswordElement = new EditProfileInputElement(driver, currentPassword);
        newPasswordElement = new EditProfileInputElement(driver, newPassword);
        confirmPasswordElement = new EditProfileInputElement(driver, confirmPassword);
    }

    public EditProfilePopUp clickUserButton() {
        userTypeButton.click();
        return this;
    }

    public EditProfilePopUp clickManagerButton() {
        managerTypeButton.click();
        return this;
    }

    public EditProfilePopUp clickCheckBox(){
        checkboxChangePassword.click();
        return this;
    }

    public String getTooltipText() {
        Actions actions = new Actions(driver);
        actions.moveToElement(questionCircleForPhoto).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOf(photoToolTipForm));
        return tooltip.getText();
    }

    public EditProfilePopUp clickUploadPhoto() {
        uploadPhotoLink.click();
        return this;
    }

    public ProfilePage clickSubmitButton() {
        submitButton.click();
        return new ProfilePage(driver);
    }

}
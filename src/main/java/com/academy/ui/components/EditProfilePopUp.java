package com.academy.ui.components;

import com.academy.ui.components.editProfileElement.EditProfileInputElement;
import com.academy.ui.components.elements.InputWithIconElement;
import com.academy.ui.pages.ProfilePage;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class EditProfilePopUp extends BasePopUp {

    @FindBy(xpath="//input[@value='ROLE_MANAGER']/../..")
    protected WebElement managerTypeButton;

    @FindBy(xpath="//input[@value='ROLE_USER']/../..")
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

    @FindBy(xpath = "./descendant::input[@id='edit_urlLogo']")
    private WebElement uploadPhoto;

    @FindBy(xpath = "//input[@id=\"edit_urlLogo\"]")
    protected WebElement uploadUserPhotoInput;
  
    @FindBy(xpath = "./descendant::span[@class=\"ant-upload-list-item-name\"]")
    protected WebElement uploadPhotoNameUserPhoto;

    @FindBy(xpath = ".//span[@class=\"ant-upload-list-item-name\"]")
    private WebElement uploadPictureTitle;

    @FindBy(xpath = ".//button[contains(@class, \"ant-upload-list-item-action\")]")
    private WebElement removeUserPhoto;

    @FindBy(xpath = ".//div[@class=\"ant-upload-icon\"]")
    private WebElement paperClipForUploadUserPhoto;

    @FindBy(xpath = " (//div[contains(@class,\"item-control-input\")]/span[contains(@class,\"ant-input-password\") and not (contains(@class,\"login-box\"))])[1]")
    protected WebElement currentPasswordInputNode;
    @FindBy(xpath = " (//div[contains(@class,\"item-control-input\")]/span[contains(@class,\"ant-input-password\") and not (contains(@class,\"login-box\"))])[2]")
    protected WebElement newPasswordInputNode;
    @FindBy(xpath = " (//div[contains(@class,\"item-control-input\")]/span[contains(@class,\"ant-input-password\") and not (contains(@class,\"login-box\"))])[3]")
    protected WebElement confirmPasswordInputNode;
    @FindBy(xpath = "./descendant:://span[@class=\"add-club-upload\"]")
    private WebElement downloadPhotoLink;

    @FindBy(xpath = "./descendant::input[@id='edit_urlLogo']")
    private WebElement downloadPhotoInput;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][1]")
    private WebElement downloadPhotoButton;

    @FindBy(xpath = "./descendant::div[@class='edit-header']")
    private WebElement popUpHeaderTitle;

    @FindBy(xpath = "./descendant::div[@class='ellipse'][1]")
    private WebElement userIcon;

    @FindBy(xpath = "./descendant::div[@class='ellipse'][2]")
    private WebElement managerIcon;

    @FindBy(xpath = "//div[contains(@class,'ant-col')]/descendant::div[@class='ant-form-item-explain-error']")
    private List<WebElement> errorMessages;

    @FindBy(xpath = "//div[contains(@class,\"explain-error\")]")
    private List<WebElement> errorMessagesForChangingPassword;

    private EditProfileInputElement lastNameElement;
    private EditProfileInputElement firstNameElement;
    private EditProfileInputElement phoneElement;
    private EditProfileInputElement currentPasswordElement;
    private EditProfileInputElement newPasswordElement;
    private EditProfileInputElement confirmPasswordElement;
    private EditProfileInputElement emailElement;
    WebDriverWait wait;

    public EditProfilePopUp(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        lastNameElement = new EditProfileInputElement(driver, lastName);
        firstNameElement = new EditProfileInputElement(driver, firstName);
        phoneElement = new EditProfileInputElement(driver, phone);
        currentPasswordElement = new EditProfileInputElement(driver, currentPassword);
        newPasswordElement = new EditProfileInputElement(driver, newPassword);
        confirmPasswordElement = new EditProfileInputElement(driver, confirmPassword);
        emailElement = new EditProfileInputElement(driver, email);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Step("Choose current password input")
    public InputWithIconElement getCurrentPasswordInput(){
        return new InputWithIconElement(driver, getCurrentPasswordInputNode());
    }

    @Step("Choose new password input")
    public InputWithIconElement getNewPasswordInput(){
        return new InputWithIconElement(driver, getNewPasswordInputNode());
    }

    @Step("Choose confirm password input")
    public InputWithIconElement getConfirmPasswordInput(){
        return new InputWithIconElement(driver, getConfirmPasswordInputNode());
    }

    @Step("Click on User type button")
    public EditProfilePopUp clickUserButton() {
        userTypeButton.click();
        return this;
    }

    @Step("Click on Manager type button")
    public EditProfilePopUp clickManagerButton() {
        managerTypeButton.click();
        return this;
    }

    @Step("Click on the checkbox")
    public EditProfilePopUp clickCheckBox(){
        getCheckboxChangePassword().click();
        return this;
    }

    @Step("Move to question circle for photo and wait for the tooltip")
    public String getTooltipText() {
        Actions actions = new Actions(driver);
        actions.moveToElement(questionCircleForPhoto).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOf(photoToolTipForm));
        return tooltip.getText();
    }

    @Step("Click on the download photo link")
    public EditProfilePopUp clickUploadPhoto() {
        downloadPhotoInput.click();
        return this;
    }

    @Step("Click on the Submit button")
    public ProfilePage clickSubmitButton() {
        submitButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOf(getWebElement()));
        return new ProfilePage(driver);
    }
    public void waitUntilElementIsVisible(WebElement el){
        wait.until(ExpectedConditions.visibilityOf(el));
    }

    /*кнопки 'видалити аватар' немає на попапі, якщо заходити вже із встановленим аватаром(хоча має бути згідно із вимогами).
    вона з'являється тільки якщо добавляти аватар заново. Картинка на попапі видаляється, але сама аватарка залишається.*/
    @Step("delete user avatar on the edit profile pop up")
    public EditProfilePopUp deleteUserAvatar() {
        new Actions(driver)
                .moveToElement(uploadPictureTitle)
                .pause(Duration.ofSeconds(2))
                .moveToElement(removeUserPhoto)
                .click()
                .perform();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOf(uploadPictureTitle));
        return this;
    }
}

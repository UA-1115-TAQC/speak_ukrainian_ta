package com.academy.ui.components;

import com.academy.ui.components.editProfileElement.EditProfileInputElement;
import com.academy.ui.pages.ProfilePage;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

@Getter
public class EditProfilePopUpComponent extends BaseComponent {

    @FindBy(xpath = "//div[@class=\"ant-modal-content\"]/button[@class=\"ant-modal-close\"]")
    private WebElement closeButton; //Don't work

    @FindBy(xpath = "//div[@class='ant-message-notice-wrapper']/descendant::div[contains(@class, 'ant-message-error') or contains(@class, 'ant-message-success')]")
    private WebElement topNoticeMessage;

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

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item-label')]/label[contains(@for, \"Logo\")]")
    @Getter(AccessLevel.NONE)
    private WebElement photo;

    @FindBy(xpath = "./descendant::div[@class=\"ant-form-item-control-input-content\"]/span[contains(@class, \"ant-upload-wrapper\")]")
    @Getter(AccessLevel.NONE)
    private WebElement uploadPhoto;

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

    private EditProfileInputElement lastNameElement;
    private EditProfileInputElement firstNameElement;
    private EditProfileInputElement phoneElement;
    private EditProfileInputElement currentPasswordElement;
    private EditProfileInputElement newPasswordElement;
    private EditProfileInputElement confirmPasswordElement;
    private EditProfileInputElement photoWithTooltipElement;
    private EditProfileInputElement uploadPhotoElement;


    public EditProfilePopUpComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        lastNameElement = new EditProfileInputElement(driver, lastName);
        firstNameElement = new EditProfileInputElement(driver, firstName);
        phoneElement = new EditProfileInputElement(driver, phone);
        currentPasswordElement = new EditProfileInputElement(driver, currentPassword);
        newPasswordElement = new EditProfileInputElement(driver, newPassword);
        confirmPasswordElement = new EditProfileInputElement(driver, confirmPassword);
        photoWithTooltipElement = new EditProfileInputElement(driver, photo);
        uploadPhotoElement = new EditProfileInputElement(driver, uploadPhoto);
    }

    public void clickUserButton() {
        userTypeButton.click();
    }

    public void clickManagerButton() {
        managerTypeButton.click();
    }

    public void clickCheckBox(){
        checkboxChangePassword.click();
    }

    public void closeEditModalForm() {
        closeButton.click();
    }

}

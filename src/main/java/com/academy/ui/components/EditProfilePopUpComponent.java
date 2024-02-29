package com.academy.ui.components;

import com.academy.ui.components.elements.BaseInputElement;
import com.academy.ui.components.elements.InputElement;
import com.academy.ui.components.elements.InputWithIconElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Getter
public class EditProfilePopUpComponent extends BaseComponent {

    @FindBy(xpath = "./descendant::div[contains(@class, \"ant-modal css-13m256z user-edit\")]//div[@class=\"ant-modal-content\"]")
    private WebElement editUserModalForm;

    // Додати це в Profile zakotiuk ProfilePage
    //
    // public LoginPopupComponent openEditUserModalForm() {
    //        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //        wait.until(ExpectedConditions.elementToBeClickable(КНОПКА_РЕДАГУВАТИ_ПРОФІЛЬ)).click();
    //        return new EditProfilePopUpComponent(driver, editUserModalForm);
    //    }

    private final InputElement editLastNameElement;
    private final InputElement editFirstNameElement;
    private final InputElement editPhoneElement;

    private final InputWithIconElement currentPasswordInputElement;
    private final InputWithIconElement enterNewPasswordInputElement;
    private final InputWithIconElement confirmPasswordInputElement;

    @FindBy(xpath = "./descendant::label[@for=\"edit_lastName\"]")
    private WebElement lastNameTitle;

    @FindBy(xpath = "./descendant::label[@for=\"edit_firstName\"]")
    private WebElement firstNameTitle;

    @FindBy(xpath = "./descendant::label[@for=\"edit_phone\"]")
    private WebElement phoneTitle;

    @FindBy(xpath = "./descendant::label[@for=\"edit_email\"]")
    private WebElement emailTitle;

    @FindBy(xpath = "./descendant::label[@for=\"edit_urlLogo\"]")
    private WebElement photoTitle;

    @FindBy(xpath = "./descendant::div[@class=\"ant-tooltip-inner\"]")
    private WebElement photoToolTipText;

    @FindBy(xpath = "./descendant::input[@id=\"edit_lastName\"]")
    private WebElement editLastNameInput;

    @FindBy(xpath = "./descendant::input[@id=\"edit_firstName\"]")
    private WebElement editFirstNameInput;

    @FindBy(xpath = "./descendant::input[@id=\"edit_phone\"]")
    private WebElement editPhoneInput;

    @FindBy(xpath = "./descendant::span[@class='ant-input-group-addon']")
    private  WebElement countryCode;

    @FindBy(xpath = "./descendant::div[@class=\"align-checkbox\"]")
    private WebElement changePasswordTitle;

    @FindBy(xpath = "./descendant::input[@name=\"checkbox\"]")
    private WebElement checkboxChangePassword;

    @FindBy(xpath = "./descendant::span[@class=\"ant-input-affix-wrapper ant-input-password user-edit-box ant-input-affix-wrapper-has-feedback css-13m256z\"][1]")
    private WebElement currentPasswordInput;

    @FindBy(xpath = "./descendant::span[@class=\"ant-input-affix-wrapper ant-input-password user-edit-box ant-input-affix-wrapper-has-feedback css-13m256z\"][2]")
    private WebElement enterNewPasswordInput;

    @FindBy(xpath = "./descendant::span[@class=\"ant-input-affix-wrapper ant-input-password user-edit-box ant-input-affix-wrapper-has-feedback css-13m256z\"][3]")
    private WebElement confirmPasswordInput;

    //upload photo
    @FindBy(xpath = "./descendant::span[@class=\"add-club-upload\"]")
    private WebElement uploadUserPhoto;

    @FindBy(xpath = "./descendant::span[@class=\"ant-upload-list-item-name\"]")
    private WebElement uploadPhotoNameUserPhoto;

    @FindBy(xpath = "./descendant::button[@class=\"ant-btn css-13m256z ant-btn-text ant-btn-sm ant-btn-icon-only ant-upload-list-item-action\"]")
    private WebElement removeUserPhoto;

    @FindBy(xpath = "./descendant::div[@class=\"ant-upload-icon\"]")
    private WebElement paperClipForUploadUserPhoto;

    @FindBy(xpath = "./descendant::button[@class=\"ant-btn css-13m256z ant-btn-default submit-button\"]")
    private WebElement submitButton;

    //USER скопіювати з реєстрації
    @FindBy(xpath = "./descendant::label[@class=\"ant-radio-button-wrapper ant-radio-button-wrapper-checked ant-radio-button-wrapper-in-form-item css-13m256z\"]")
    private WebElement managerType;

    @FindBy(xpath = "./descendant::label[@class=\"ant-radio-button-wrapper ant-radio-button-wrapper-in-form-item css-13m256z\"]")
    private WebElement userType;

    public EditProfilePopUpComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        editLastNameElement = new InputElement(driver, editLastNameInput);
        editFirstNameElement = new InputElement(driver, editFirstNameInput);
        editPhoneElement = new InputElement(driver, editPhoneInput);

        currentPasswordInputElement = new InputWithIconElement(driver, currentPasswordInput);
        enterNewPasswordInputElement = new InputWithIconElement(driver, enterNewPasswordInput);
        confirmPasswordInputElement = new InputWithIconElement(driver, confirmPasswordInput);

    }

    public InputElement editLastName(String lastName) {
        return editLastNameElement.setValue(lastName);
    }

    public InputElement editFirstName(String firstName) {
        return editFirstNameElement.setValue(firstName);
    }

    public InputElement editPhone(String phone) {
        return editPhoneElement.setValue(phone);
    }

    public BaseInputElement enterCurrentPassword(String password) {
        return currentPasswordInputElement.setValue(password);
    }

    public BaseInputElement enterNewPassword(String password) {
        return enterNewPasswordInputElement.setValue(password);
    }

    public BaseInputElement enterConfirmPassword(String password) {
        return confirmPasswordInputElement.setValue(password);
    }

    public void checkBoxChangePasswordClick(){
        checkboxChangePassword.click();
    }

    public void clickUploadPhoto() {
        uploadUserPhoto.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickUserType() {
        userType.click();
    }

    public void clickManagerType() {
        managerType.click();
    }

}

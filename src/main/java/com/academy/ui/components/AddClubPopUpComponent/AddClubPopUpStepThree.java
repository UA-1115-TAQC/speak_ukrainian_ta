package com.academy.ui.components.AddClubPopUpComponent;

import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AddClubPopUpStepThree extends AddClubPopUpContainer {

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
    private WebElement clubLogoTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][2]")
    private WebElement clubCoverTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][3]")
    private WebElement clubGalleryTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][4]")
    private WebElement clubDescriptionTitle;

    @FindBy(xpath = "./descendant::input[@id='basic_urlLogo']")
    private WebElement clubLogoDownloadInput;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][1]")
    private WebElement clubLogoDownloadButton;

    @FindBy(xpath = "./descendant::input[@id='basic_urlBackground']")
    private WebElement clubCoverDownloadInput;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][2]")
    private WebElement clubCoverDownloadButton;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][3]")
    private WebElement clubGalleryDownloadButton;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][3]//input")
    private WebElement clubGalleryDownloadInput;

    @FindBy(xpath = ".//textarea[contains(@id,'basic_description')]")
    private WebElement clubDescriptionTextarea;

    @FindBy(xpath = ".//span[contains(@class, 'ant-form-item-feedback-icon')]")
    private WebElement clubDescriptionValidationMark;

    @FindBy(xpath = ".//div[@class='ant-form-item-control-input']/descendant::span[contains(@class,'anticon-close-circle') or contains(@class,'anticon-check-circle')]")
    private WebElement validationTextareaCircleIcon;

    @FindBy(xpath = ".//div[contains(@class,'ant-col')]/descendant::div[@class='ant-form-item-explain-error']")
    private List<WebElement> errorMessagesTextarea;

    @FindBy(xpath = "//span[contains(@class,'ant-upload-list-item-name')]")
    private List<WebElement> uploadedElementsListNode;

    @FindBy(xpath = ".//div[@class='ant-upload-list ant-upload-list-picture-card']//div[@class='ant-upload-list-item-container']")
    private List<WebElement> clubGalleryUploadedImgs;

    @FindBy(xpath = "./descendant::a[@title='Preview file']")
    private WebElement previewFile;

    @FindBy(xpath = "./descendant::span[(@role='img') and (@aria-label='eye')]")
    private WebElement previewIcon;

    @FindBy(xpath = "./descendant::div[@class='ant-upload-list ant-upload-list-text'][1]")
    @Getter(AccessLevel.NONE)
    private WebElement uploadedLogoImgContainer;

    @FindBy(xpath = "./descendant::div[@class='ant-upload-list ant-upload-list-text'][2]")
    @Getter(AccessLevel.NONE)
    private WebElement uploadedCoverImgContainer;

    @FindBy(xpath = "./descendant::span[@class='ant-select-selection-placeholder']")
    private WebElement selectPlaceholder;

    private final UploadedImgComponent uploadedLogoImg;
    private final UploadedImgComponent uploadedCoverImg;

    public AddClubPopUpStepThree(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        uploadedCoverImg = new UploadedImgComponent(driver, uploadedCoverImgContainer);
        uploadedLogoImg = new UploadedImgComponent(driver, uploadedLogoImgContainer);
    }

    @Step("Click on the button 'Завантажити лого' on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree clickClubLogoDownloadButton() {
        clubLogoDownloadButton.click();
        return this;
    }

    @Step("Click on the button 'Додати' to add image to Gallery on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree clickClubGalleryDownloadButton() {
        clubGalleryDownloadButton.click();
        return this;
    }

    @Step("Click on the button 'Завантажити обкладинку' on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree clickClubCoverDownloadButton() {
        clubCoverDownloadButton.click();
        return this;
    }

    @Step("Click on the button 'Завершити' on the third step of Add/Edit club pop-up")
    public void clickCompleteButton() {
        getNextStepButton().click();
    }

    @Step("Set club description on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree setDescriptionValue(String value) {
        clubDescriptionTextarea.sendKeys(value);
        return this;
    }

    @Step("Get list of error messages of club description on the third step of Add/Edit club pop-up")
    public List<String> getErrorMessagesTextList() {
        return errorMessagesTextarea.stream().map(elem -> elem.getAttribute("innerText")).collect(Collectors.toList());
    }

    @Step("Clear club description textarea on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree clearDescriptionTextarea() {
        Platform currentPlatform = ((RemoteWebDriver) driver).getCapabilities().getPlatformName();
        if (currentPlatform.is(Platform.MAC)) {
            clubDescriptionTextarea.sendKeys(Keys.COMMAND + "a", Keys.DELETE);
        } else {
            clubDescriptionTextarea.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        return this;
    }

    @Step("Get all uploaded items on the third step of Add/Edit club pop-up")
    public List<WebElement> getAllUploadedElements() {
        return getUploadedElementsListNode();
    }

    @Step("Get uploaded Gallery image by index {index} on the third step of Add/Edit club pop-up")
    public UploadedImgComponent getUploadedGalleryImg(int index) {
        if (index >= 0 && index < clubGalleryUploadedImgs.size()) {
            return new UploadedImgComponent(driver, clubGalleryUploadedImgs.get(index));
        } else {
            throw new RuntimeException("GalleryImg not found by index: " + index);
        }
    }
}

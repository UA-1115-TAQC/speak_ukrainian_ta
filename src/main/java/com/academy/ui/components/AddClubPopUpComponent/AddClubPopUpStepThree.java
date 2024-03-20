package com.academy.ui.components.AddClubPopUpComponent;

import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

    @FindBy(xpath = ".//input[@id='basic_urlLogo']")
    private WebElement clubLogoDownloadInput;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][1]")
    private WebElement clubLogoDownloadButton;

    @FindBy(xpath = ".//input[@id='basic_urlBackground']")
    private WebElement clubCoverDownloadInput;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][2]")
    private WebElement clubCoverDownloadButton;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][3]")
    private WebElement clubGalleryDownloadButton;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][3]//input")
    private WebElement clubGalleryDownloadInput;

    @FindBy(xpath = ".//textarea[(@id='basic_descriptionText') or (@id='basic_description')]")
    private WebElement clubDescriptionTextarea;

    @FindBy(xpath = ".//span[contains(@class, 'ant-form-item-feedback-icon')]")
    private WebElement clubDescriptionValidationMark;

    @FindBy(xpath = ".//div[@class='ant-form-item-control-input']//span[contains(@class,'anticon-close-circle') or contains(@class,'anticon-check-circle')]")
    private WebElement validationTextareaCircleIcon;

    @FindBy(xpath = ".//div[contains(@class,'ant-col')]//div[@class='ant-form-item-explain-error']")
    private List<WebElement> errorMessagesTextarea;

    @FindBy(xpath = "//span[contains(@class,'ant-upload-list-item-name')]")
    private List<WebElement> uploadedElementsListNode;

    @FindBy(xpath = ".//div[@class='ant-upload-list ant-upload-list-picture-card']//div[@class='ant-upload-list-item-container']")
    private List<WebElement> clubGalleryUploadedImgs;

    @FindBy(xpath = "./descendant::div[@class='ant-upload-list ant-upload-list-text'][1]")
    @Getter(AccessLevel.NONE)
    private WebElement uploadedLogoImgContainer;

    @FindBy(xpath = "./descendant::div[@class='ant-upload-list ant-upload-list-text'][2]")
    @Getter(AccessLevel.NONE)
    private WebElement uploadedCoverImgContainer;

    private final UploadedImgComponent uploadedLogoElement;
    private final UploadedImgComponent uploadedCoverElement;

    public AddClubPopUpStepThree(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        uploadedCoverElement = new UploadedImgComponent(driver, uploadedCoverImgContainer);
        uploadedLogoElement = new UploadedImgComponent(driver, uploadedLogoImgContainer);
    }

    @Step("Click on the button 'Завантажити лого' on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree clickClubLogoDownloadButton() {
        clubLogoDownloadButton.click();
        return this;
    }

    @Step("Upload new logo image with wait on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree uploadLogoImage(String pathToImage) {
        getClubLogoDownloadInput().sendKeys(pathToImage);
        getUploadedLogoElement().waitImageLoad(5);
        return this;
    }

    @Step("Click on the button 'Завантажити обкладинку' on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree clickClubCoverDownloadButton() {
        clubCoverDownloadButton.click();
        return this;
    }

    @Step("Upload new cover image with wait on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree uploadCoverImage(String pathToImage) {
        getClubCoverDownloadInput().sendKeys(pathToImage);
        getUploadedCoverElement().waitImageLoad(5);
        return this;
    }

    @Step("Click on the button 'Додати' to add image to Gallery on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree clickClubGalleryDownloadButton() {
        clubGalleryDownloadButton.click();
        return this;
    }

    @Step("Upload new gallery image with wait on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree uploadImgToGallery(String pathToImage) {
        int countImg = clubGalleryUploadedImgs.size();
        clubGalleryDownloadInput.sendKeys(pathToImage);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> countImg < clubGalleryUploadedImgs.size());
        return this;
    }

    @Step("Get uploaded Gallery image by index {index} on the third step of Add/Edit club pop-up")
    public UploadedImgComponent getUploadedGalleryImgByIndex(int index) {
        if (index >= 0 && index < clubGalleryUploadedImgs.size()) {
            return new UploadedImgComponent(driver, clubGalleryUploadedImgs.get(index));
        } else {
            throw new RuntimeException("GalleryImg not found by index: " + index);
        }
    }

    @Step("Click on the button 'Завершити' on the third step of Add/Edit club pop-up")
    public void clickCompleteButton() {
        getNextStepButton().click();
    }

    @Step("Click on complete button with {timeOut} seconds wait")
    public void clickCompleteButtonWithWait(long timeOut) {
        getNextStepButton().click();
        new WebDriverWait(driver, Duration.ofSeconds(timeOut))
                .until(ExpectedConditions.invisibilityOfAllElements(getNextStepButton()));
    }

    @Step("Set club description on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree setDescriptionValue(String value) {
        clubDescriptionTextarea.sendKeys(value);
        return this;
    }

    @Step("Get list of error messages of club description on the third step of Add/Edit club pop-up")
    public List<String> getErrorMessagesTextList() {
        return errorMessagesTextarea.stream()
                .map(elem -> elem.getAttribute("innerText"))
                .collect(Collectors.toList());
    }

    @Step("Wait new error of the description textarea on the third step of Add/Edit club pop-up")
    public void waitNewError(int initialErrorCount) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> errorMessagesTextarea.size() > initialErrorCount);
    }

    @Step("Clear club description textarea on the third step of Add/Edit club pop-up")
    public AddClubPopUpStepThree clearDescriptionTextarea() {
        clubDescriptionTextarea.sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.DELETE);
        clubDescriptionTextarea.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> clubDescriptionTextarea.getAttribute("value").isEmpty());
        return this;
    }

    @Step("Get all uploaded items on the third step of Add/Edit club pop-up")
    public List<WebElement> getAllUploadedElements() {
        return getUploadedElementsListNode();
    }

    @Step("Click on the previous step button")
    @Override
    public AddClubPopUpStepTwo clickPreviousStepButton() {
        getPreviousStepButton().click();
        return new AddClubPopUpStepTwo(driver, rootElement);
    }

}

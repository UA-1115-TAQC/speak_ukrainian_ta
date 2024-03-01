package com.academy.ui.components.AddClubPopUpComponent;

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
public class AddClubPopUpStepThree extends AddClubPopUpContainer{

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

    @FindBy(xpath = "./descendant::textarea[@id='basic_description']")
    private WebElement clubDescriptionTextarea;

    @FindBy(xpath = ".//div[@class='ant-form-item-control-input']/descendant::span[contains(@class,'anticon-close-circle') or contains(@class,'anticon-check-circle')]")
    private WebElement validationCircleIcon;

    @FindBy(xpath = ".//div[contains(@class,'ant-col')]/descendant::div[@class='ant-form-item-explain-error']")
    private List<WebElement> errorMessages;

    @FindBy(xpath = ".//div[@class='ant-upload-list ant-upload-list-picture-card']//div[@class='ant-upload-list-item-container']")
    private List<WebElement> clubGalleryUploadedImgs;

    @FindBy(xpath = ".//span[@class='ant-upload-list-item-name'][1]")
    private WebElement clubLogoUploadedImg;

    @FindBy(xpath = ".//span[@class='ant-upload-list-item-name'][2]")
    private WebElement clubCoverUploadedImg;

    public AddClubPopUpStepThree(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public AddClubPopUpStepThree clickClubLogoDownloadButton(){
        clubLogoDownloadButton.click();
        return this;
    }
    public AddClubPopUpStepThree clickClubGalleryDownloadButton(){
        clubGalleryDownloadButton.click();
        return this;
    }

    public AddClubPopUpStepThree clickClubCoverDownloadButton(){
        clubCoverDownloadButton.click();
        return this;
    }

    public void clickCompleteButton(){
        getNextStepButton().click();
    }

    public AddClubPopUpStepThree setDescriptionValue(String value) {
        clubDescriptionTextarea.sendKeys(value);
        return this;
    }

    public List<String> getErrorMessagesTextList() {
        return errorMessages.stream().map(elem -> elem.getAttribute("innerText")).collect(Collectors.toList());
    }

    public AddClubPopUpStepThree clearDescriptionTextarea(){
        Platform currentPlatform = ((RemoteWebDriver) driver).getCapabilities().getPlatformName();
        if (currentPlatform.is(Platform.MAC)) {
            clubDescriptionTextarea.sendKeys(Keys.COMMAND + "a", Keys.DELETE);
        } else {
            clubDescriptionTextarea.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        return this;
    }
}

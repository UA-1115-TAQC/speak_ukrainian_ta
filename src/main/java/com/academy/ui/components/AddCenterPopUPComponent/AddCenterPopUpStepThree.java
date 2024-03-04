package com.academy.ui.components.AddCenterPopUPComponent;

import com.academy.ui.components.AddClubPopUpComponent.UploadedImgComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AddCenterPopUpStepThree extends AddCenterPopUpContainer{

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
    private WebElement centerLogoTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][2]")
    private WebElement centerPhotoTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][3]")
    private WebElement centerDescriptionTitle;

    @FindBy(xpath = "./descendant::input[@id='basic_urlLogo']")
    private WebElement centerLogoDownloadInput;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][1]")
    private WebElement centerLogoDownloadButton;

    @FindBy(xpath = "./descendant::div[@class='ant-upload-list ant-upload-list-text'][1]")
    @Getter(AccessLevel.NONE)
    private WebElement uploadedLogoImgContainer;

    @FindBy(xpath = "./descendant::input[@id='basic_urlBackground']")
    private WebElement centerPhotoDownloadInput;

    @FindBy(xpath = "./descendant::span[(@class='ant-upload') and (@role='button')][2]")
    private WebElement centerPhotoDownloadButton;

    @FindBy(xpath = "./descendant::div[@class='ant-upload-list ant-upload-list-text'][2]")
    @Getter(AccessLevel.NONE)
    private WebElement uploadedCoverImgContainer;

    @FindBy(xpath = "./descendant::textarea[@id='basic_description']")
    private WebElement centerDescriptionTextarea;

    @FindBy(xpath = ".//div[@class='ant-form-item-control-input']/descendant::span[contains(@class,'anticon-close-circle') or contains(@class,'anticon-check-circle')]")
    private WebElement validationCircleIconTextarea;

    @FindBy(xpath = ".//div[contains(@class,'ant-col')]/descendant::div[@class='ant-form-item-explain-error']")
    private List<WebElement> errorMessagesTextarea;

    private UploadedImgComponent uploadedLogoImg;
    private UploadedImgComponent uploadedCoverImg;

    public AddCenterPopUpStepThree(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        uploadedCoverImg = new UploadedImgComponent(driver, uploadedCoverImgContainer);
        uploadedLogoImg = new UploadedImgComponent(driver, uploadedLogoImgContainer);
    }

}

package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class UploadedImgComponent extends BaseComponent {

    @FindBy(xpath = ".//span[(@role='img') and (@aria-label='paper-clip')]")
    private WebElement paperClipIcon;

    @FindBy(xpath = ".//span[@class='ant-upload-list-item-name']")
    public WebElement imageTitle;

    @FindBy(xpath = ".//button[(@title='Remove file') and (@type='button')]")
    private WebElement deleteImageIcon;

    @FindBy(xpath = ".//div[@class='ant-tooltip-inner']")
    private WebElement uploadError;

    @FindBy(xpath = ".//a[@title='Preview file']")
    private WebElement previewImage;

    @FindBy(xpath = ".//span[(@role='img') and (@aria-label='eye')]")
    private WebElement previewImageIcon;

    @FindBy(xpath = "//descendant::button[(@type='button') and (@aria-label='Close')][3]")
    private WebElement closeButton;

    @FindBy(xpath = ".//div[contains(@class,'ant-upload-list-item-done')]")
    private WebElement uploadDone;

    @FindBy(xpath = "//div[@class='ant-modal-title']")
    private WebElement modalPreviewImageTitle;

    public UploadedImgComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Get error message text of the loaded image on the third step of Add/Edit club pop-up")
    public String getUploadErrorMessage() {
        return uploadError.getAttribute("innerText");
    }

    @Step("Click on the Remove-icon to delete image on the third step of Add/Edit club pop-up")
    public UploadedImgComponent clickDeleteImage() {
        deleteImageIcon.click();
        return this;
    }

    @Step("Click on the Preview-icon to preview image on the third step of Add/Edit club pop-up")
    public UploadedImgComponent clickPreviewImage() {
        previewImage.click();
        return this;
    }

    @Step("Click 'X' button to close image preview on the third step of Add/Edit club pop-up")
    public UploadedImgComponent clickClosePreviewWindow() {
        closeButton.click();
        return this;
    }

    @Step("Wait for {timeout} seconds until image is loaded on the third step of Add/Edit club pop-up")
    public void waitImageLoad(long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(uploadDone));
    }

    @Step("Wait {timeout} seconds until loaded image is changed to the new one on the third step of Add/Edit club pop-up")
    public void waitImageChanged(String prevImage, long timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(e -> !imageTitle.getText().contains(prevImage));
        waitImageLoad(timeout);
    }
}

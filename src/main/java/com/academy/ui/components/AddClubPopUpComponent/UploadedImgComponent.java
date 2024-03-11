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

    @FindBy(xpath = "./descendant::span[(@role='img') and (@aria-label='paper-clip')]")
    private WebElement paperClipImg;

    @FindBy(xpath = "./descendant::span[@class='ant-upload-list-item-name']")
    public WebElement imgTitle;

    @FindBy(xpath = "./descendant::button[(@title='Remove file') and (@type='button')]")
    private WebElement removeImgButton;

    @FindBy(xpath = "./descendant::div[@class='ant-tooltip-inner']")
    private WebElement uploadError;

    @FindBy(xpath = "./descendant::a[@title='Preview file']")
    private WebElement previewFile;

    @FindBy(xpath = "./descendant::span[(@role='img') and (@aria-label='eye')]")
    private WebElement previewIcon;

    @FindBy(xpath = "./descendant::button[(@type='button') and (@aria-label='Close')][3]")
    private WebElement closeButton;

    @FindBy(xpath = ".//div[contains(@class,'ant-upload-list-item-done')]")
    private WebElement uploadDone;

    public UploadedImgComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Get error message text of the loaded image on the third step of Add/Edit club pop-up")
    public String getUploadErrorMessage() {
        return uploadError.getAttribute("innerText");
    }

    @Step("Click on the Remove-icon to delete image on the third step of Add/Edit club pop-up")
    public UploadedImgComponent clickRemoveImg() {
        removeImgButton.click();
        return this;
    }

    @Step("Click on the Preview-icon to preview image on the third step of Add/Edit club pop-up")
    public UploadedImgComponent clickPreviewFile() {
        previewFile.click();
        return this;
    }

    @Step("Click 'X' button to close image preview on the third step of Add/Edit club pop-up")
    public UploadedImgComponent clickClosePreviewWindow() {
        closeButton.click();
        return this;
    }

    @Step("Wait for {timeout} seconds until image is loaded on the third step of Add/Edit club pop-up")
    public void waitImageLoad(long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(uploadDone));
    }

    @Step("Wait {timeout} seconds until loaded image is changed to the new one on the third step of Add/Edit club pop-up")
    public void waitImageChanged(String prevImage, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(e -> !imgTitle.getText().equals(prevImage));
        wait.until(ExpectedConditions.visibilityOf(uploadDone));
    }
}

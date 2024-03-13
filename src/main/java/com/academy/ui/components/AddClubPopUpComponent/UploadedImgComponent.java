package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
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

    @FindBy(xpath = "//descendant::button[(@type='button') and (@aria-label='Close')][3]")
    private WebElement closeButton;

    @FindBy(xpath = ".//div[contains(@class,'ant-upload-list-item-done')]")
    private WebElement uploadDone;

    public UploadedImgComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getUploadErrorMessage() {
        return uploadError.getAttribute("innerText");
    }

    public UploadedImgComponent clickRemoveImg() {
        removeImgButton.click();
        return this;
    }

    public UploadedImgComponent clickPreviewFile() {
        previewFile.click();
        return this;
    }

    public UploadedImgComponent clickClosePreviewWindow() {
        closeButton.click();
        return this;
    }

    public void waitImageLoad(long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(uploadDone));
    }

    public void waitImageChanged(String prevImage, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(e -> !imgTitle.getText().equals(prevImage));
        wait.until(ExpectedConditions.visibilityOf(uploadDone));
    }
}

package com.academy.ui.components.editProfileElement;

import com.academy.ui.components.AddClubPopUpComponent.AddClubInputElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class EditProfileInputElement extends AddClubInputElement {

    @FindBy(xpath = "./descendant::span[@class='ant-input-suffix']/span[contains(@aria-label, 'eye-invisible') or contains(@aria-label, 'eye')]")
    private WebElement passwordVisibilityIcon;

    @FindBy(xpath = ".//label")
    private WebElement title;

    @FindBy(xpath = ".//span[@class='ant-input-group-addon']")
    private  WebElement phoneCountryCode;

    @FindBy(xpath = ".//span[@aria-label=\"question-circle\"]")
    private WebElement questionCircleForPhoto;

    @FindBy(xpath = "//label[@for=\"edit_urlLogo\"]")
    private WebElement photoLink;

    @FindBy(xpath = "//div[@class=\"ant-tooltip-inner\"]")
    private WebElement photoToolTipForm;

    @FindBy(xpath = "//span[@class=\"add-club-upload\"]")
    private WebElement uploadPhotoLink;

    @FindBy(xpath = ".//span[@class=\"ant-upload-list-item-name\"]")
    private WebElement uploadPictureTitle;

    @FindBy(xpath = ".//button[contains(@class, \"ant-upload-list-item-action\")]")
    private WebElement removeUserPhoto;

    @FindBy(xpath = ".//div[@class=\"ant-upload-icon\"]")
    private WebElement paperClipForUploadUserPhoto;

    public EditProfileInputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

    }

    public String getTooltipText() {
        Actions actions = new Actions(driver);
        actions.moveToElement(questionCircleForPhoto).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOf(photoToolTipForm));
        return tooltip.getText();
    }

    public EditProfileInputElement clickPasswordVisibilityIcon() {
        passwordVisibilityIcon.click();
        return this;
    }

    public String getTitle() {
        return title.getText();
    }

    public EditProfileInputElement clickUploadPhoto() {
        uploadPhotoLink.click();
        return this;
    }

}

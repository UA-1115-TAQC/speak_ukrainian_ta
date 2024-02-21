package com.academy.ui.components.AddClubPopUpComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddClubPopUpStepThree extends AddClubPopUpContainer{

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
    private WebElement clubLogoTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][2]")
    private WebElement clubCoverTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][3]")
    private WebElement clubGalleryTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
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

    public AddClubPopUpStepThree(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickCompleteButton(){
        getNextStepButton().click();
    }
}

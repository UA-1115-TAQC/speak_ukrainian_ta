package com.academy.ui.components.AddCenterPopUpComponent;

import com.academy.ui.components.AddClubPopUpComponent.AddClubInputElement;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddCenterPopUpStepTwo extends AddCenterPopUpContainer {

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')]")
    private WebElement centerInputsTitle;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][1]")
    @Getter(AccessLevel.NONE)
    private WebElement telephoneInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][2]")
    @Getter(AccessLevel.NONE)
    private WebElement facebookInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][3]")
    @Getter(AccessLevel.NONE)
    private WebElement whatsappInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][4]")
    @Getter(AccessLevel.NONE)
    private WebElement emailInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][5]")
    @Getter(AccessLevel.NONE)
    private WebElement skypeInput;

    @FindBy(xpath = ".//div[contains(@class,'add-club-contacts')]/descendant::div[contains(@class,'add-club-contact')][6]")
    @Getter(AccessLevel.NONE)
    private WebElement siteInput;

    private AddClubInputElement telephoneInputElement;
    private AddClubInputElement facebookInputElement;
    private AddClubInputElement whatsappInputElement;
    private AddClubInputElement emailInputElement;
    private AddClubInputElement skypeInputElement;
    private AddClubInputElement siteInputElement;

    public AddCenterPopUpStepTwo(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.telephoneInputElement = new AddClubInputElement(driver, telephoneInput);
        this.facebookInputElement = new AddClubInputElement(driver, facebookInput);
        this.whatsappInputElement = new AddClubInputElement(driver, whatsappInput);
        this.emailInputElement = new AddClubInputElement(driver, emailInput);
        this.skypeInputElement = new AddClubInputElement(driver, skypeInput);
        this.siteInputElement = new AddClubInputElement(driver, siteInput);
    }

}

package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddChildPopUpComponent extends BasePopUp {

    @FindBy(xpath = "//input[@id='add-child_firstName']")
    private WebElement addFirstName;

    @FindBy(xpath = "//input[@id='add-child_lastName']")
    private WebElement addLastName;

    @FindBy(xpath = "add-child_age")
    private WebElement addAge;

    @FindBy(xpath = "//input[@value='MALE']")
    private WebElement genderMale;

    @FindBy(xpath = "//input[@value='FEMALE']")
    private WebElement genderFemale;

    @FindBy(xpath = "//button[contains(@class,'submit-button')]")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id='add-child_firstName_help']")
    private WebElement hintMessageForName;

    @FindBy(xpath = "//div[@id='add-child_lastName_help']")
    private WebElement hintMessageForLastName;

    @FindBy(xpath = "//div[@id='add-child_age_help']")
    private WebElement hintMessageForAge;

    @FindBy(xpath = "//div[@id='add-child_gender_help']")
    private WebElement hintMessageForGender;

    @FindBy(xpath = "//div[@id='add-child_age_help']//div[@class='ant-form-item-explain-error']")
    private WebElement hintMessageAcceptableChildAge;


    public AddChildPopUpComponent addChildName(String name) {
        addFirstName.sendKeys(name);
        return this;
    }

    public AddChildPopUpComponent addChildLastName(String lastName) {
        addLastName.sendKeys(lastName);
        return this;
    }

    public AddChildPopUpComponent addChildAge(String age) {
        addAge.sendKeys(age);
        return this;
    }

    public AddChildPopUpComponent clickOnFemaleGender() {
        genderFemale.click();
        return this;
    }

    public AddChildPopUpComponent clickOnMaleGender() {
        genderMale.click();
        return this;
    }

    public AddChildPopUpComponent clickOnSubmitButton() {
        submitButton.click();
        return this;
    }

    public AddChildPopUpComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

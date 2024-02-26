package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddChildPopUpComponent extends BasePopUp {

    @FindBy(xpath = "./descendant::input[@id='add-child_firstName']")
    private WebElement addFirstName;

    @FindBy(xpath = "./descendant::input[@id='add-child_lastName']")
    private WebElement addLastName;

    @FindBy(xpath = "./descendant::input[@id='add-child_age']")
    private WebElement addAge;

    @FindBy(xpath = "./descendant::input[@value='MALE']")
    private WebElement genderMale;

    @FindBy(xpath = "./descendant::input[@value='FEMALE']")
    private WebElement genderFemale;

    @FindBy(xpath = "./descendant::button[contains(@class,'submit-button')]")
    private WebElement submitButton;

    @FindBy(xpath = "./descendant::div[@id='add-child_firstName_help']")
    private WebElement hintMessageForName;

    @FindBy(xpath = "./descendant::div[@id='add-child_lastName_help']")
    private WebElement hintMessageForLastName;

    @FindBy(xpath = "./descendant::div[@id='add-child_age_help']")
    private WebElement hintMessageForAge;

    @FindBy(xpath = "./descendant::div[@id='add-child_gender_help']")
    private WebElement hintMessageForGender;



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

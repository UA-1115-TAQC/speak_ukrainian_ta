package com.academy.ui.components.AddClubPopUpComponent;

import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AddClubPopUpStepOne extends AddClubPopUpContainer {

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item-row')]")
    @Getter(AccessLevel.NONE)
    private WebElement clubNameInput;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
    private WebElement clubInputTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][2]")
    private WebElement clubCategoriesTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][3]")
    private WebElement clubAgeTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][4]")
    private WebElement clubCenterTitle;

    @FindBy(xpath = "./descendant::input[@class='ant-checkbox-input']")
    private List<WebElement> categoriesCheckboxList;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-checkbox-checked')]/input[@class='ant-checkbox-input']")
    private List<WebElement> checkedCategoriesList;

    @FindBy(xpath = "./ancestor::div[@id='basic_categories_help']/div")
    private WebElement categoriesError;

    @FindBy(xpath = "./descendant::span[contains(@class,'add-club-age')]")
    private WebElement ageComponent;

    @FindBy(xpath = "./descendant::input[@id='basic_ageFrom']")
    private WebElement minAgeInput;

    @FindBy(xpath = "./descendant::span[@aria-label='Increase Value'][1]")
    private WebElement minAgeIncreaseButton;

    @FindBy(xpath = "./descendant::span[@aria-label='Decrease Value'][1]")
    private WebElement minAgeDecreaseButton;

    @FindBy(xpath = "./descendant::input[@id='basic_ageTo']")
    private WebElement maxAgeInput;

    @FindBy(xpath = "./descendant::span[@aria-label='Increase Value'][2]")
    private WebElement maxAgeIncreaseButton;

    @FindBy(xpath = "./descendant::span[@aria-label='Decrease Value'][2]")
    private WebElement maxAgeDecreaseButton;

    @FindBy(xpath = "./descendant::div[@id='basic_ageFrom_help']/div")
    private WebElement minAgeInputError;

    @FindBy(xpath = "./descendant::div[@id='basic_ageTo_help']/div")
    private WebElement maxAgeInputError;

    @FindBy(xpath = "./descendant::input[@id='basic_centerId']")
    private WebElement centerSelect;

    @FindBy(xpath = "./descendant::span[@class='ant-select-selection-item']")
    private WebElement centerSelectedTitle;

    @FindBy(xpath = "//div[@class='ant-select-item-option-content']")
    private List<WebElement> centersList;

    @FindBy(xpath = "./descendant::span[@class='ant-select-selection-placeholder']")
    private WebElement selectPlaceholder;

    private AddClubInputElement clubNameInputElement;

    public AddClubPopUpStepOne(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        clubNameInputElement = new AddClubInputElement(driver, clubNameInput);
    }

    @Step("Select club category by name on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne selectCategory(String value) {
        categoriesCheckboxList.stream()
                .filter(category -> category.getAttribute("value").equals(value))
                .forEach(WebElement::click);
        return this;
    }

    @Step("Set club minimum age on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne setMinAgeInput(String age) {
        minAgeInput.sendKeys(age);
        return this;
    }

    @Step("Set club maximum age on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne setMaxAgeInput(String age) {
        maxAgeInput.sendKeys(age);
        return this;
    }

    @Step("Select center by name on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne selectCenter(String value) {
        centersList.stream()
                .filter(center -> (center.getAttribute("innerText").equals(value)))
                .forEach(WebElement::click);
        return this;
    }

    @Step("Click on center dropdown on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne clickCenterDropdown() {
        centerSelect.click();
        return this;
    }

}

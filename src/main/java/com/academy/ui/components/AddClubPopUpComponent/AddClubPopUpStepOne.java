package com.academy.ui.components.AddClubPopUpComponent;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AddClubPopUpStepOne extends AddClubPopUpContainer {

    @FindBy(xpath = "./descendant::input[@id='basic_name']")
    private WebElement clubNameInput;

    @FindBy(xpath = "./following-sibling::span[@class='ant-input-suffix']/descendant::span[@role='img']")
    private WebElement clubNameInputIcon;

    @FindBy(xpath = "./ancestor::div[contains(@class,'ant-form-item-control')]/descendant::div[@class='ant-form-item-explain-error']")
    private WebElement clubNameInputError;

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

    @FindBy(xpath = "./ancestor::body/descendant::div[@class='ant-select-item-option-content']")
    private List<WebElement> centersList;

    @FindBy(xpath = "./descendant::button[contains(@class,'add-club-content-next')]")
    private WebElement nextStepButton;

    public AddClubPopUpStepOne(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public AddClubPopUpStepOne setClubNameInput(String name) {
        clubNameInput.sendKeys(name);
        return this;
    }

    public AddClubPopUpStepOne selectCategory(String value) {
        categoriesCheckboxList.stream()
                .filter(category -> category.getAttribute("value").equals(value))
                .forEach(WebElement::click);
        return this;
    }

    public AddClubPopUpStepOne setMinAgeInput(String age) {
        minAgeInput.sendKeys(age);
        return this;
    }

    public AddClubPopUpStepOne setMaxAgeInput(String age) {
        maxAgeInput.sendKeys(age);
        return this;
    }

    public AddClubPopUpStepOne clickToSelectCenter(String value) {
        if (centersList == null || centersList.isEmpty()) centerSelect.click();
        centersList.stream()
                .filter(center -> (center.getAttribute("innerText").equals(value)))
                .forEach(WebElement::click);
        return this;
    }

}

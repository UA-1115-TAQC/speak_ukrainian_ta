package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.AddLocationPopUpComponent.DropdownElement;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

@Getter
public class AddClubPopUpStepOne extends AddClubPopUpContainer {

    @FindBy(xpath = ".//div[contains(@class,'ant-form-item-row')]")
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

    @FindBy(xpath = ".//label[contains(@class,'ant-checkbox-wrapper')]")
    private List<WebElement> categoriesCheckboxList;

    @FindBy(xpath = ".//span[contains(@class,'ant-checkbox-checked')]/input[@class='ant-checkbox-input']")
    private List<WebElement> checkedCategoriesList;

    @FindBy(xpath = ".//span[contains(@class, 'checkbox')]/following-sibling::span")
    private List<WebElement> categoriesListForEdit;

    @FindBy(xpath = ".//span[contains(@class,'ant-checkbox-checked')]/following-sibling::span")
    private List<WebElement> checkedCategoriesListForEdit;

    @FindBy(xpath = "./ancestor::div[@id='basic_categories_help']/div")
    private WebElement categoriesError;

    @FindBy(xpath = ".//span[contains(@class,'add-club-age')]")
    private WebElement ageComponent;

    @FindBy(xpath = ".//input[contains(@id, 'ageFrom')]")
    private WebElement minAgeInput;

    @FindBy(xpath = "./descendant::span[@aria-label='Increase Value'][1]")
    private WebElement minAgeIncreaseButton;

    @FindBy(xpath = "./descendant::span[@aria-label='Decrease Value'][1]")
    private WebElement minAgeDecreaseButton;

    @FindBy(xpath = ".//input[contains(@id, 'ageTo')]")
    private WebElement maxAgeInput;

    @FindBy(xpath = "./descendant::span[@aria-label='Increase Value'][2]")
    private WebElement maxAgeIncreaseButton;

    @FindBy(xpath = "./descendant::span[@aria-label='Decrease Value'][2]")
    private WebElement maxAgeDecreaseButton;

    @FindBy(xpath = ".//div[@id='basic_ageFrom_help']/div")
    private WebElement minAgeInputError;

    @FindBy(xpath = ".//div[@id='basic_ageTo_help']/div")
    private WebElement maxAgeInputError;

    @FindBy(xpath = ".//div[contains(@class, 'add-club-select')]")
    @Getter(AccessLevel.NONE)
    private WebElement centerDropdown;

    @FindBy(xpath = ".//span[@class='ant-select-selection-item']")
    private WebElement centerSelectedTitle;

    @FindBy(xpath = "//div[contains(@class,'ant-select-item ant-select-item-option')]")
    private List<WebElement> centersList;

    @FindBy(xpath = ".//span[@class='ant-select-selection-placeholder']")
    private WebElement dropdownPlaceholder;

    @FindBy(xpath = "//div[@class='rc-virtual-list-holder']")
    private WebElement centersDropdownListContainer;

    private final AddClubInputElement clubNameInputElement;
    private final DropdownElement centerDropdownElement;

    public AddClubPopUpStepOne(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        clubNameInputElement = new AddClubInputElement(driver, clubNameInput);
        centerDropdownElement = new DropdownElement(driver, centerDropdown);
    }

    @Step("Select club category by name {name} on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne selectCategory(String name) {
        categoriesCheckboxList.stream()
                .filter(category -> category.getAttribute("innerText").contains(name))
                .forEach(WebElement::click);
        return this;
    }

    @Step("Set club minimum age {age} on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne setMinAgeInput(String age) {
        minAgeInput.sendKeys(age);
        return this;
    }

    @Step("Set club maximum age {age} on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne setMaxAgeInput(String age) {
        maxAgeInput.sendKeys(age);
        return this;
    }

    @Step("Select center by name {name} on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne selectCenter(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElements(centersDropdownListContainer));
        centersList.stream()
                .filter(center -> (center.getAttribute("title").contains(name)))
                .forEach(WebElement::click);
        return this;
    }

    @Step("Click on center dropdown on the first step of Add/Edit club pop-up")
    public AddClubPopUpStepOne clickCenterDropdown() {
        centerDropdown.click();
        return this;
    }

    @Step("Click on the next step button")
    @Override
    public AddClubPopUpStepTwo clickNextStepButton() {
        getNextStepButton().click();
        return new AddClubPopUpStepTwo(driver, rootElement);
    }

}

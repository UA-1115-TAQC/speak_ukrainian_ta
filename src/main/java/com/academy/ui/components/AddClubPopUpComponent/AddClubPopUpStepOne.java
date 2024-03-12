package com.academy.ui.components.AddClubPopUpComponent;

import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

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

    @FindBy(xpath = "./descendant::span[contains(@class, 'checkbox')]/following-sibling::span")
    private List<WebElement> categoriesListForEdit;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-checkbox-checked')]/following-sibling::span")
    private List<WebElement> checkedCategoriesListForEdit;

    @FindBy(xpath = "./ancestor::div[@id='basic_categories_help']/div")
    private WebElement categoriesError;

    @FindBy(xpath = "./descendant::span[contains(@class,'add-club-age')]")
    private WebElement ageComponent;

//    @FindBy(xpath = "./descendant::input[@id='basic_ageFrom']")
//    private WebElement minAgeInput;
    @FindBy(xpath = "./descendant::input[contains(@id, 'ageFrom')]")
    private WebElement minAgeInput;

    @FindBy(xpath = "./descendant::span[@aria-label='Increase Value'][1]")
    private WebElement minAgeIncreaseButton;

    @FindBy(xpath = "./descendant::span[@aria-label='Decrease Value'][1]")
    private WebElement minAgeDecreaseButton;

//    @FindBy(xpath = "./descendant::input[@id='basic_ageTo']")
//    private WebElement maxAgeInput;
    @FindBy(xpath = "./descendant::input[contains(@id, 'ageTo')]")
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

    public AddClubPopUpStepOne selectCategory(String value) {
        categoriesCheckboxList.stream()
                .filter(category -> category.getAttribute("value").equals(value))
                .forEach(WebElement::click);
        return this;
    }

    public AddClubPopUpStepOne selectCategoryForEdit(String value) {
        OptionalInt index = IntStream.range(0, categoriesListForEdit.size())
                .filter(i -> categoriesListForEdit.get(i).getText().equals(value))
                .findFirst();

        if (index.isPresent()) {
            int foundIndex = index.getAsInt()+1;
            categoriesCheckboxList.stream()
                    .filter(category -> category.getAttribute("value").equals(String.valueOf(foundIndex)))
                    .forEach(WebElement::click);
        } else {
            System.out.println("Елемент з текстом '" + value + "' не знайдено в списку");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(e -> categoriesCheckboxList);
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

    public AddClubPopUpStepOne selectCenter(String value) {
        centersList.stream()
                .filter(center -> (center.getAttribute("innerText").equals(value)))
                .forEach(WebElement::click);
        return this;
    }

    public AddClubPopUpStepOne clickCenterDropdown(){
        centerSelect.click();
        return this;
    }

}
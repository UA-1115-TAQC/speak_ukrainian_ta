package com.academy.ui.components.AddClubPopUpComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddClubPopUpStepOne extends AddClubPopUpContainer {
    @FindBy(xpath = "/descendant::input[@id='basic_name']")
    public WebElement clubNameInput;

    @FindBy(xpath = "./following-sibling::span[@class='ant-input-suffix']/descendant::span[@role='img']")
    public WebElement clubNameInputIcon;

    @FindBy(xpath = "./ancestor::div[contains(@class,'ant-form-item-control')]/descendant::div[@class='ant-form-item-explain-error']")
    public WebElement clubNameInputError;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][1]")
    public WebElement clubInputTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][2]")
    public WebElement clubCategoriesTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][3]")
    public WebElement clubAgeTitle;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-typography')][4]")
    public WebElement clubCenterTitle;

    @FindBy(xpath = "./descendant::input[@class='ant-checkbox-input']")
    public List<WebElement> categoriesCheckboxList;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-checkbox-checked')]/input[@class='ant-checkbox-input']")
    public List<WebElement> checkedCategoriesList;

    @FindBy(xpath = "//ancestor::div[@id='basic_categories_help']/div")
    public WebElement categoriesError;

    @FindBy(xpath = "./descendant::span[contains(@class,'add-club-age')]")
    public WebElement ageComponent;

    @FindBy(xpath = "./descendant::input[@id='basic_ageFrom']")
    public WebElement minAgeInput;

    @FindBy(xpath = "./descendant::input[@id='basic_ageTo']")
    public WebElement maxAgeInput;

    @FindBy(xpath = "./descendant::div[@id='basic_ageFrom_help']/div")
    public WebElement minAgeInputError;

    @FindBy(xpath = "./descendant::div[@id='basic_ageTo_help']/div")
    public WebElement maxAgeInputError;

    @FindBy(xpath = "//input[@id='basic_centerId']")
    public WebElement centerSelect;

    @FindBy(xpath = "//ancestor::span[@class='ant-select-selection-search']/following-sibling::span[@class='ant-select-selection-item']")
    public WebElement centerSelectedTitle;

    @FindBy(xpath = "//div[@class='ant-select-item-option-content']")
    public List<WebElement> centersList;

    @FindBy(xpath = "//button[contains(@class,'add-club-content-next')]")
    public WebElement nextStepButton;

    public AddClubPopUpStepOne(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void setClubNameInput(String name) {
        clubNameInput.sendKeys(name);
    }

    public void selectCategory(int index) {
        if (index >= 0 && index < categoriesCheckboxList.size()) {
            categoriesCheckboxList.get(index).click();
        }
    }

    public void setMinAgeInput(String age) {
        minAgeInput.sendKeys(age);
    }

    public void setMaxAgeInput(String age) {
        maxAgeInput.sendKeys(age);
    }

    public void clickCenterSelect(){
        centerSelect.click();
    }

    public void clickToSelectCenter(int index) {
        clickCenterSelect();
        if (index >= 0 && index < centersList.size()) {
            centersList.get(index).click();
        }
    }

    public void clickNextButton() {
        nextStepButton.click();
    }


}

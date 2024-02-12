package com.academy.ui.pages.popups;

import com.academy.ui.pages.BasePopUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AddClubPopUpComponent extends BasePopUp {

    protected WebElement clubTitle;
    protected WebElement clubNameInput;
    protected WebElement clubNameInputError;
    protected List<WebElement> categoriesCheckboxList;
    protected WebElement categoryCheckboxItem;
    protected List<WebElement> checkedCategoriesList;
    protected WebElement minAgeInput;
    protected WebElement maxAgeInput;
    protected WebElement centerSelect;
    protected List<WebElement> centersList;
    protected WebElement nextStepButton;
    protected static final String className = "modal-add-club";

    public AddClubPopUpComponent(WebDriver driver) {
        super(driver, className);
    }

    public WebElement getClubTitle() {
        if (clubTitle == null) {
            clubTitle = rootElement.findElement(By.xpath("//div[contains(@class,'add-club-header')]"));
        }
        return clubTitle;
    }

    public String getClubTitleText() {
        return getClubTitle().getAttribute("innerText");
    }

    public WebElement getClubNameInput() {
        if (clubNameInput == null) {
            clubNameInput = rootElement.findElement(By.xpath("//input[@id='basic_name']"));
        }
        return clubNameInput;
    }

    public void setClubNameInput(String name) {
        getClubNameInput().sendKeys(name);
    }

    public String getClubNameValue() {
        return getClubNameInput().getAttribute("value");
    }

    public WebElement getClubNameInputError() {
        if (clubNameInputError == null) {
            clubNameInputError = getClubNameInput().findElement(By.xpath("//ancestor::div[contains(@class,'ant-form-item-control')]/descendant::div[@class='ant-form-item-explain-error']"));
        }
        return clubNameInputError;
    }

    public String getClubNameErrorText() {
        return getClubNameInputError().getAttribute("innerText");
    }

    public List<WebElement> getCategoriesCheckboxList() {
        if (categoriesCheckboxList == null) {
            categoriesCheckboxList = rootElement.findElements(By.xpath("//input[@class='ant-checkbox-input']"));
        }
        return categoriesCheckboxList;
    }

    public List<String> getCategoriesListValues() {
        List<String> list = new ArrayList<>();
        getCategoriesCheckboxList().forEach(elem -> list.add(elem.getAttribute("value")));
        return list;
    }

    public WebElement getCategoryItem(String category) {
        if (categoryCheckboxItem == null || !categoryCheckboxItem.getAttribute("value").equals(category)) {
            categoryCheckboxItem = rootElement.findElement(By.xpath("//input[@value='" + category + "']"));
        }
        return categoryCheckboxItem;
    }

    public void clickCategoriesListItem(String category) {
        getCategoryItem(category).click();
    }

    public List<WebElement> getCheckedCategoriesList() {
        if (checkedCategoriesList == null) {
            checkedCategoriesList = rootElement.findElements(By.xpath("//span[contains(@class,'ant-checkbox-checked')]/input[@class='ant-checkbox-input']"));
        }
        return checkedCategoriesList;
    }

    public List<String> getCheckedCategoriesListValues() {
        List<String> list = new ArrayList<>();
        getCheckedCategoriesList().forEach(elem -> list.add(elem.getAttribute("value")));
        return list;
    }

    public String getCategoriesErrorText() {
        return rootElement.findElement(By.xpath("//ancestor::div[@id='basic_categories_help']/div")).getAttribute("innerText");
    }

    public WebElement getMinAgeInput() {
        if (minAgeInput == null) {
            minAgeInput = rootElement.findElement(By.xpath("//input[@id='basic_ageFrom']"));
        }
        return minAgeInput;
    }

    public void setMinAgeInput(String age) {
        getMinAgeInput().sendKeys(age);
    }

    public String getMinAgeValue() {
        return getMinAgeInput().getAttribute("value");
    }

    public WebElement getMaxAgeInput() {
        if (maxAgeInput == null) {
            maxAgeInput = rootElement.findElement(By.xpath("//input[@id='basic_ageTo']"));
        }
        return maxAgeInput;
    }

    public void setMaxAgeInput(String age) {
        getMaxAgeInput().sendKeys(age);
    }

    public String getMaxAgeValue() {
        return getMaxAgeInput().getAttribute("value");
    }

    public String getMinAgeErrorText() {
        return rootElement.findElement(By.xpath("//div[@id='basic_ageFrom_help']/div")).getAttribute("innerText");
    }

    public String getMaxAgeErrorText() {
        return rootElement.findElement(By.xpath("//div[@id='basic_ageTo_help']/div")).getAttribute("innerText");
    }

    public WebElement getCenterSelect() {
        if (centerSelect == null) {
            centerSelect = rootElement.findElement(By.xpath("//input[@id='basic_centerId']"));
        }
        return centerSelect;
    }

    public void clickCenterSelect() {
        getCenterSelect().click();
    }

    public String getCenterSelectedTitle() {
        return getCenterSelect().findElement(By.xpath("//ancestor::span[@class='ant-select-selection-search']/following-sibling::span[@class='ant-select-selection-item']")).getText();
    }

    public List<WebElement> getCentersList() {
        if (centersList == null) {
            centersList = rootElement.findElements(By.xpath("//div[@class='ant-select-item-option-content']"));
        }
        return centersList;
    }

    public List<String> getCentersListTitles() {
        List<String> list = new ArrayList<>();
        getCentersList().forEach(elem -> list.add(elem.getAttribute("innerText")));
        return list;
    }

    public void selectCenter(String name) {
        rootElement.findElement(By.xpath("//div[contains(@class,'ant-select-item-option') and @title='" + name + "']")).click();
    }

    public WebElement getNextStepButton() {
        if (nextStepButton == null) {
            nextStepButton = rootElement.findElement(By.xpath("//button[contains(@class,'add-club-content-next')]"));
        }
        return nextStepButton;
    }

    public void clickNextStepButton() {
        getNextStepButton().click();
    }

}

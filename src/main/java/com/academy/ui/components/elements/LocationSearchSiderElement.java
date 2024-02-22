package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Getter
public class LocationSearchSiderElement extends BaseComponent{
    @FindBy(xpath = ".//span[contains(@class,'ant-select-clear')]")
    protected WebElement selectClear;

    @FindBy(xpath = ".//span[contains(@class,'ant-select-selection-placeholder') or contains(@class, 'ant-select-selection-item')]")
    protected WebElement inputContent;

    @FindBy(xpath = ".//input[@type='search']")
    @Getter(AccessLevel.NONE)private WebElement inputBox;

    protected LocationSearchDropDownElement dropDownElement;

    public LocationSearchSiderElement(WebDriver driver, WebElement root) {
        super(driver, root);
    }

    public LocationSearchDropDownElement getDropDownElement(){
        if(dropDownElement == null){
            String listId = inputBox.getAttribute("aria-owns");
            WebElement dropDownRoot = driver.findElement(By.xpath(
                    "//div[@id='" + listId + "']/following-sibling::div"));
            dropDownElement = new LocationSearchDropDownElement(driver, dropDownRoot);
        }
        return dropDownElement;
    }

    public LocationSearchSiderElement clickClear(){
       getSelectClear().click();
       return this;
    }

    public LocationSearchSiderElement clickDropDown(){
        rootElement.click();
        return this;
    }

    public LocationSearchSiderElement selectItem(String itemName){
        getDropDownElement().selectItem(itemName);
        return this;
    }

}

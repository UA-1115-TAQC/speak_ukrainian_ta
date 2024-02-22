package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class SearchClubDropDownElement extends BaseComponent{
    @FindBy(xpath = ".//span[contains(@class,'ant-select-clear')]")
    protected WebElement selectClear;

    @FindBy(xpath = ".//span[contains(@class,'ant-select-selection-placeholder') or contains(@class, 'ant-select-selection-item')]")
    protected WebElement inputContent;

    protected WebElement dropDown = null;
    protected List<WebElement> dropDownList = null;
    protected WebElement scrollbar = null;

    public SearchClubDropDownElement(WebDriver driver, WebElement root) {
        super(driver, root);
    }

    public WebElement getDropDown(){
        if(dropDownList == null){
            String listId = rootElement.findElement(By.xpath(
                    ".//input[@type='search']"))
                    .getAttribute("aria-owns");
            dropDown = driver.findElement(By.xpath(
                    "//div[@id='" + listId + "']/following-sibling::div"));
        }
        return dropDown;
    }

    public WebElement getScrollbar(){
        if(scrollbar == null){
            scrollbar = getDropDown().findElement(By.xpath(
                            ".//div[@class='rc-virtual-list-scrollbar-thumb']"));
        }
        return scrollbar;
    }

    public SearchClubDropDownElement clickClear(){
       getSelectClear().click();
       return this;
    }

    public SearchClubDropDownElement clickDropDown(){
        rootElement.click();
        return this;
    }

    public SearchClubDropDownElement scrollDown(){
//        TODO
        return this;
    }

    public SearchClubDropDownElement scrollUp(){
//        TODO
        return this;
    }

    public SearchClubDropDownElement selectItem(){
//        TODO
        return this;
    }

}

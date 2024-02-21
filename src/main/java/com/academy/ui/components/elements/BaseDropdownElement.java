package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class BaseDropdownElement extends BaseComponent {

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-select-in-form-item')]")
    private WebElement dropdown;

    @FindBy(xpath = "./descendant::span[contains(@class,'ant-select-arrow')]/span[@aria-label='down']")
    private WebElement dropdownArrowIcon;

    @FindBy(xpath = "./descendant::span[@class='ant-select-selection-item']")
    private WebElement selectedItem;

    @FindBy(xpath = "//div[@class='rc-virtual-list-holder']")
    private WebElement dropdownBox;

    @FindBy(xpath = "//div[@class='rc-virtual-list']/descendant::div[contains(@class,'ant-select-item ant-select-item-option')]")
    private List<WebElement> dropdownOptionsList;

    public BaseDropdownElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public BaseDropdownElement clickDropdown() {
        dropdown.click();
        return this;
    }

    public Set<String> getTextDropdownOptionsList() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = 0", dropdownBox);
        Set<String> set = new HashSet<>();
        while (true) {
            int listSize = set.size();
            dropdownOptionsList.forEach(item -> set.add(item.getAttribute("title")));
            if (listSize == set.size()) break;
            js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].offsetHeight;", dropdownBox);
            sleep(500);
        }
        return set;
    }

    public BaseDropdownElement selectValue(String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = 0", dropdownBox);
        while (true) {
            List<String> list = new ArrayList<>();
            for (WebElement item : dropdownOptionsList) {
                list.add(item.getAttribute("title"));
                if (item.getAttribute("title").equals(value)) {
                    js.executeScript("arguments[0].scrollIntoView(); arguments[0].click();", item);
                    return this;
                }
            }
            js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[0].offsetHeight;", dropdownBox);
            sleep(500);
            List<String> newList = new ArrayList<>();
            dropdownOptionsList.forEach(item -> newList.add(item.getAttribute("title")));
            if (list.equals(newList)) break;
        }
        return this;
    }
}
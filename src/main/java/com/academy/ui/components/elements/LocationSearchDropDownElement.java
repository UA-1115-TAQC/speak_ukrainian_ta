package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class LocationSearchDropDownElement extends BaseComponent {
    @FindBy(xpath = ".//div[@class='rc-virtual-list-scrollbar-thumb']")
    protected WebElement scrollbar;
    protected List<WebElement> itemsList = null;

    public LocationSearchDropDownElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public LocationSearchDropDownElement scrollDown(){
//        TODO
        return this;
    }

    public LocationSearchDropDownElement scrollUp(){
//        TODO
        return this;
    }

    public LocationSearchDropDownElement selectItem(){
//        TODO
        return this;
    }
}

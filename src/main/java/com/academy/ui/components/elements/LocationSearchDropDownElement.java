package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class LocationSearchDropDownElement extends BaseComponent {
    @FindBy(xpath = ".//div[@class ='rc-virtual-list-holder-inner']/div[contains(@class, 'ant-select-item')]")
    protected List<WebElement> itemsList;

    public LocationSearchDropDownElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    private WebElement findItemInList(String itemName){
        for(WebElement e : itemsList){
            String title = e.getAttribute("title");
            if(title.equals(itemName)){
                return e;
            }
        }
        return null;
    }

    private void goTop(){
        while(true){
            WebElement currentFirstElement = itemsList.get(0);
            new Actions(driver).moveToElement(currentFirstElement).perform();
            WebElement newFirstElement = itemsList.get(0);
            if(currentFirstElement.equals(newFirstElement)){
                return;
            }
        }
    }

    private WebElement findItem(String itemName){
        goTop();
        while(true){
            WebElement goalItem = findItemInList(itemName);
            if(goalItem != null){
                return goalItem;
            }
            WebElement currentLastElement = itemsList.get(itemsList.size() - 1);
            new Actions(driver).moveToElement(currentLastElement).perform();
            WebElement newLastElement = itemsList.get(itemsList.size() - 1);
            if(currentLastElement.equals(newLastElement)){
                break;
            }
        }
        return null;
    }

    public LocationSearchDropDownElement selectItem(String itemName){
        WebElement item = findItem(itemName);
        if(item != null){
            new Actions(driver).moveToElement(item).perform();
            item.click();
        }
        return this;
    }

}

package com.academy.ui.components;

import com.academy.ui.pages.ClubsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


@Getter
public class ClubListControlComponent extends BaseComponent{
    @FindBy(xpath = ".//span[contains(@class,'control-sort-label')]")
    protected WebElement sortLabel;

    @FindBy(xpath = ".//span[contains(@class, 'control-sort-option')]")
    protected List<WebElement> sortOptions;

    @FindBy(xpath = ".//span[contains(@aria-label, 'arrow-up')]")
    protected WebElement arrowUp;

    @FindBy(xpath = ".//span[contains(@aria-label, 'arrow-down')]")
    protected WebElement arrowDown;

    @FindBy(xpath = ".//label[contains(@class, 'ant-radio-button-wrapper')]")
    protected List<WebElement> wrappers;

    public ClubListControlComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public ClubsPage clickSortAlphabet(){
        for(WebElement e : getSortOptions()){
            if(e.getText().equals("за алфавітом")){
                e.click();
            }
        }
        return new ClubsPage(driver);
    }

    public ClubsPage clickSortRating(){
        for(WebElement e : getSortOptions()){
            if(e.getText().equals("за рейтингом")){
                e.click();
            }
        }
        return new ClubsPage(driver);
    }

    public ClubsPage clickArrowUp(){
        getArrowUp().click();
        return new ClubsPage(driver);
    }

    public ClubsPage clickArrowDown(){
        getArrowDown().click();
        return new ClubsPage(driver);
    }

    public ClubsPage clickWrapperList(){
        for(WebElement e : getWrappers()) {
            WebElement input = e.findElement(By.xpath(
                    ".//input[@class='ant-radio-button-input']"));
            if(input.getDomAttribute("value").equals("LIST")){
                e.click();
            }
        }
        return new ClubsPage(driver);
    }

    public ClubsPage clickWrapperBlock(){
        for(WebElement e : getWrappers()) {
            WebElement input = e.findElement(By.xpath(
                    ".//input[@class='ant-radio-button-input']"));
            if(input.getDomAttribute("value").equals("BLOCK")){
                e.click();
            }
        }
        return new ClubsPage(driver);
    }

}

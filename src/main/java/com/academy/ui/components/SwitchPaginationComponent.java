package com.academy.ui.components;


import com.academy.ui.pages.ClubsPage;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

@Getter
public class SwitchPaginationComponent extends BaseComponent {

    @FindBy(xpath = ".//li[contains(@class,'ant-pagination-prev')]")
    protected WebElement previousItem;

    @FindBy(xpath = ".//li[contains(@class,'ant-pagination-next')]")
    protected WebElement nextItem;

    @FindBy(xpath = ".//li[contains(@class, 'ant-pagination-item') or contains(@class, 'ant-pagination-jump-')]")
    protected List<WebElement> paginationItems;


    public SwitchPaginationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getPagItemByTitle(String pageNum) {
        for (WebElement pagItem : paginationItems) {
            String str = pagItem.getDomAttribute("title");
            if (str.equals(pageNum)) {
                return pagItem;
            }
        }
        return null;
    }

    public boolean isPagItemActive(String pageNum) {
        WebElement pagItem = getPagItemByTitle(pageNum);
        if (pagItem != null) {
            String str = pagItem.getDomAttribute("class");
            return str.contains("ant-pagination-item-active");
        }
        return false;
    }

    public boolean isPreviousDisabled(){
        String disabled = previousItem.getAttribute("aria-disabled");
        if(disabled.equals("true")){
            return true;
        }
        return false;
    }

    public boolean isNextDisabled(){
        String disabled = nextItem.getAttribute("aria-disabled");
        if(disabled.equals("true")){
            return true;
        }
        return false;
    }

    public ClubsPage clickPrevious() {
        previousItem.click();
        return new ClubsPage(driver);
    }


    public ClubsPage clickNext() {
        nextItem.click();
        return new ClubsPage(driver);
    }

    public ClubsPage clickPagItemByNum(String pageNum) {
        WebElement pagItem = getPagItemByTitle(pageNum);
        if (pagItem != null) {
            pagItem.click();
        }
        return new ClubsPage(driver);
    }

    public ClubsPage clickPrevFivePages() {
        WebElement pagItem = getPagItemByTitle("Previous 5 Pages");
        if (pagItem != null) {
            pagItem.click();
        }
        return new ClubsPage(driver);
    }

    public ClubsPage clickNextFivePages() {
        WebElement pagItem = getPagItemByTitle("Next 5 Pages");
        if (pagItem != null) {
            pagItem.click();
        }
        return new ClubsPage(driver);
    }

    public ClubsPage getLastPage(){
        while (!isNextDisabled()){
            clickNext();
        }
        return new ClubsPage(driver);
    }

    public boolean isPaginationPresent() {
        return !paginationItems.isEmpty();
    }

}

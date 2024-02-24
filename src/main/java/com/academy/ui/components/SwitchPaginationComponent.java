package com.academy.ui.components;


import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void clickPrevious() {
        WebElement previousButton = previousItem.findElement(By.xpath(".//button"));
        if (previousButton.isEnabled()) {
            previousButton.click();
        }
    }

    public void clickNext() {
        WebElement nextButton = nextItem.findElement(By.xpath(".//button"));
        if (nextButton.isEnabled()) {
            nextButton.click();
        }
    }

    public void clickPagItemByNum(String pageNum) {
        WebElement pagItem = getPagItemByTitle(pageNum);
        if (pagItem != null) {
            pagItem.click();
        }
    }

    public void clickPrevFivePages() {
        WebElement pagItem = getPagItemByTitle("Previous 5 Pages");
        if (pagItem != null) {
            pagItem.click();
        }
    }

    public void clickNextFivePages() {
        WebElement pagItem = getPagItemByTitle("Next 5 Pages");
        if (pagItem != null) {
            pagItem.click();
        }
    }

}

package com.academy.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SwitchPaginationComponent extends BaseComponent {
    protected WebElement previousItem;
    protected WebElement nextItem;
    protected List<WebElement> paginationItems;

    public SwitchPaginationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getPrevious() {
        if (previousItem == null) {
            previousItem = rootElement.findElement(By.xpath(
                    ".//li[contains(@class,'ant-pagination-prev')]"));
        }
        return previousItem;
    }

    public WebElement getNext() {
        if (nextItem == null) {
            nextItem = rootElement.findElement(By.xpath(
                    ".//li[contains(@class,'ant-pagination-next')]"));
        }
        return nextItem;
    }

    private void createPagItemsList() {
        paginationItems = rootElement.findElements(By.xpath(
                ".//li[contains(@class, 'ant-pagination-item') or contains(@class, 'ant-pagination-jump-')]"));
    }

    public List<WebElement> getPagItems() {
        if (paginationItems == null) {
            createPagItemsList();
        }
        return paginationItems;
    }

    public WebElement getPagItemByTitle(String pageNum) {
        getPagItems();
        for (WebElement pagItem : paginationItems) {
            String str = pagItem.getDomAttribute("title");
            if (str.equals(pageNum)) {
                return pagItem;
            }
        }
        return null;
    }

    public boolean isPagItemActive(String pageNum) {
        getPagItems();
        WebElement pagItem = getPagItemByTitle(pageNum);
        if (pagItem != null) {
            String str = pagItem.getDomAttribute("class");
            return str.contains("ant-pagination-item-active");
        }
        return false;
    }

    public void clickPrevious() {
        getPrevious();
        WebElement previousButton = previousItem.findElement(By.xpath(
                ".//button"));
        if (previousButton.isEnabled()) {
            previousButton.click();
        }
    }

    public void clickNext() {
        getNext();
        WebElement nextButton = nextItem.findElement(By.xpath(
                ".//button"));
        if (nextButton.isEnabled()) {
            nextButton.click();
        }
    }

    public void clickPagItem(WebElement item) {
        item.click();
        createPagItemsList();
    }

    public void clickPagItemByNum(String pageNum) {
        getPagItems();
        WebElement pagItem = getPagItemByTitle(pageNum);
        if (pagItem != null) {
            clickPagItem(pagItem);
        }
    }

    public void clickPrevFivePages() {
        getPagItems();
        WebElement pagItem = getPagItemByTitle("Previous 5 Pages");
        if (pagItem != null) {
            clickPagItem(pagItem);
        }
    }

    public void clickNextFivePages() {
        getPagItems();
        WebElement pagItem = getPagItemByTitle("Next 5 Pages");
        if (pagItem != null) {
            clickPagItem(pagItem);
        }
    }

}

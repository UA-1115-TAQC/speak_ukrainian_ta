package com.academy.ui.pages.component;

import com.academy.ui.pages.Base;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

//this class is common for AllNewsPage and ClubsPage
public class PaginationComponent extends Base {
    private List<WebElement> pagePagination;
    private WebElement previousPageArrow;
    private WebElement nextPageArrow;
    public PaginationComponent(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getPagePagination() {
        return driver
                .findElements(xpath("//li[contains(@class, 'ant-pagination-item') or contains(@class, 'ant-pagination-jump')]"));
    }

    public WebElement getPreviousPageArrow() {
        return driver.findElement(xpath("//li[contains(@title, 'Previous Page')]"));
    }

    public WebElement getNextPageArrow() {
        return driver.findElement(xpath("//li[contains(@title, 'Next Page')]"));
    }

    public Optional<WebElement> getPageByNumber(int pageNumber) {
        return pageNumber > 0 && pageNumber < getPagePagination().size() ?
                Optional.of(getPagePagination().get(pageNumber - 1)) : Optional.empty();
    }

    public Optional<WebElement> getThreeDotPagination() {
        return getPagePagination()
                .stream()
                .filter(webElement -> webElement.getAttribute("class").contains("jump-next ant-pagination"))
                .findFirst();
    }
}

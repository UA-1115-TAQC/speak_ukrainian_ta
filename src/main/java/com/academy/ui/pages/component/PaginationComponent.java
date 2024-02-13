package com.academy.ui.pages.component;

import com.academy.ui.pages.Base;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

//this class is common for AllNewsPage and ClubsPage
public class PaginationComponent extends Base {
    private final WebElement root;
    private List<WebElement> pagePagination;
    private WebElement previousPageArrow;
    private WebElement nextPageArrow;
    public PaginationComponent(WebDriver driver) {
        super(driver);
        root = driver.findElement(xpath("//ul[contains(@class, 'ant-pagination')]"));
    }

    public List<WebElement> getPagePagination() {
        return root
                .findElements(xpath(".//li[contains(@class, 'ant-pagination-item') or contains(@class, 'ant-pagination-jump')]"));
    }

    public WebElement getPreviousPageArrow() {
        return root.findElement(xpath(".//li[contains(@title, 'Previous Page')]"));
    }

    public WebElement getNextPageArrow() {
        return root.findElement(xpath(".//li[contains(@title, 'Next Page')]"));
    }

    //this is just example. Because there may be a situation when there are two ... (one for the previous page and one for the next one)
    public void clickThreeDotPagination() {
        getThreeDotPagination()
                .ifPresent(WebElement::click);
    }

    public void goToPage(int pageNumber) {
        if (pageNumber > 0 && pageNumber < getPagePagination().size()) {
            getPagePagination().get(pageNumber - 1).click();
        }
    }

    private Optional<WebElement> getThreeDotPagination() {
        return getPagePagination()
                .stream()
                .filter(webElement -> webElement.getAttribute("class").contains("jump-next ant-pagination"))
                .findFirst();
    }}

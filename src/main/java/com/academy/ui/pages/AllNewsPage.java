package com.academy.ui.pages;

import com.academy.ui.pages.component.ClubCardLineComponent;
import com.academy.ui.pages.component.NewsCardComponent;
import com.academy.ui.pages.component.PaginationComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class AllNewsPage extends BasePage {
    private List<NewsCardComponent> newsCardComponents;
    private List<ClubCardLineComponent> clubCardLineComponents;
    private PaginationComponent paginationComponent;

    public AllNewsPage(WebDriver driver) {
        super(driver);
    }

    public List<NewsCardComponent> getNewsCardComponents() {
        return newsCardComponents = driver.findElements(xpath("//div[@id = 'newsContainer']"))
                .stream()
                .map(webElement -> new NewsCardComponent(driver, webElement))
                .collect(Collectors.toList());
    }

    public List<ClubCardLineComponent> getClubCardLineComponents() {
        return clubCardLineComponents = driver.findElements(xpath("//div[contains(@class, 'ant-card')]"))
                .stream()
                .map(webElement -> new ClubCardLineComponent(driver, webElement))
                .collect(Collectors.toList());
    }

    public PaginationComponent getPaginationComponent() {
        if (paginationComponent == null) {
            paginationComponent = new PaginationComponent(driver);
        }
        return paginationComponent;
    }

    public void goToPage(int pageNumber) {
        getPaginationComponent().getPageByNumber(pageNumber)
                .ifPresent(WebElement::click);
    }

    //this is just example. Because there may be a situation when there are two ... (one for the previous page and one for the next one)
    public void clickThreeDotPagination() {
        getPaginationComponent().getThreeDotPagination()
                .ifPresent(WebElement::click);
    }

    public void clickPreviousPage() {
        getPaginationComponent().getPreviousPageArrow().click();
    }

    public void clickNextPage() {
        getPaginationComponent().getNextPageArrow().click();
    }
}

package com.academy.ui.pages;

import com.academy.ui.pages.component.ClubCardLineComponent;
import com.academy.ui.pages.component.NewsCardComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.xpath;

public class AllNewsPage extends BasePage {
    private List<NewsCardComponent> newsCardComponents;
    private List<ClubCardLineComponent> clubCardLineComponents;
    private List<WebElement> newsPages;
    private WebElement previousPageArrow;
    private WebElement nextPageArrow;

    public AllNewsPage(WebDriver driver) {
        super(driver);
    }

    public List<NewsCardComponent> getNewsCardComponents() {
        newsCardComponents = driver.findElements(xpath("//div[@id = 'newsContainer']"))
                .stream()
                .map(webElement -> new NewsCardComponent(driver, webElement))
                .collect(Collectors.toList());
        return newsCardComponents;
    }

    public List<ClubCardLineComponent> getClubCardLineComponents() {
        clubCardLineComponents = driver.findElements(xpath("//div[contains(@class, 'ant-card')]"))
                .stream()
                .map(webElement -> new ClubCardLineComponent(driver, webElement))
                .collect(Collectors.toList());
        return clubCardLineComponents;
    }

    public List<WebElement> getNewsPages() {
        if (newsPages == null) {
            newsPages = driver.findElements(xpath(".//li[contains(@class, 'ant-pagination-item-')]"));
        }
        return newsPages;
    }

    public WebElement getPreviousPageArrow() {
        if (previousPageArrow == null) {
            previousPageArrow = driver.findElement(xpath("//li[contains(@title, 'Previous')]"));
        }
        return previousPageArrow;
    }

    public WebElement getNextPageArrow() {
        if (nextPageArrow == null) {
            nextPageArrow = driver.findElement(xpath("//li[contains(@title, 'Next')]"));
        }
        return nextPageArrow;
    }
}

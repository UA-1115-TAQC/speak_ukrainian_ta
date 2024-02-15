package com.academy.ui.pages;

import com.academy.ui.components.ClubCardLineComponent;
import com.academy.ui.components.NewsCardComponent;
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

    public List<WebElement> getNewsPages() {
        return newsPages = driver.findElements(xpath(".//li[contains(@class, 'ant-pagination-item-')]"));
    }

    public WebElement getPreviousPageArrow() {
        return previousPageArrow = driver.findElement(xpath("//li[contains(@title, 'Previous')]"));
    }

    public WebElement getNextPageArrow() {
        return nextPageArrow = driver.findElement(xpath("//li[contains(@title, 'Next')]"));
    }
}

package com.academy.ui.pages;

import com.academy.ui.pages.component.ClubCardLineComponent;
import com.academy.ui.pages.component.NewsCardComponent;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class AllNewsPage extends BasePage {
    private NewsCardComponent newsCardComponent;
    private ClubCardLineComponent clubCardLineComponent;
    private List<WebElement> newsPages;
    private WebElement previousPageArrow;
    private WebElement nextPageArrow;

    public AllNewsPage(WebDriver driver) {
        super(driver);
    }

    public NewsCardComponent getNewsCardComponent() {
        if (newsCardComponent == null) {
            WebElement node = driver.findElement(xpath("(//div[@id = 'newsContainer'])[1]"));
            newsCardComponent = new NewsCardComponent(driver, node);
        }
        return newsCardComponent;
    }

    public ClubCardLineComponent getClubCardLineComponent() {
        if (clubCardLineComponent == null) {
            WebElement node = driver.findElement(xpath("(//div[contains(@class, 'ant-card')])[1]"));
            clubCardLineComponent = new ClubCardLineComponent(driver, node);
        }
        return clubCardLineComponent;
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

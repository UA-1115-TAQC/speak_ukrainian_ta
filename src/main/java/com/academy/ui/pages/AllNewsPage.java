package com.academy.ui.pages;

import com.academy.ui.pages.component.ClubCardLineComponent;
import com.academy.ui.pages.component.NewsCardComponent;
import com.academy.ui.pages.component.PaginationComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;

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
}

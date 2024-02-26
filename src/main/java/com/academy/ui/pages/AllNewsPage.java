package com.academy.ui.pages;

import com.academy.ui.components.ClubCardLineComponent;
import com.academy.ui.components.NewsCardComponent;
import com.academy.ui.components.SwitchPaginationComponent;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AllNewsPage extends BasePageWithAdvancedSearch {
    private List<NewsCardComponent> newsCards;

    private List<ClubCardLineComponent> clubCards;

    private SwitchPaginationComponent pagination;

    @Getter(AccessLevel.NONE)
    @FindBy(xpath = "//div[contains(@id, 'newsContainer')]")
    private List<WebElement> newsCardComponents;

    @Getter(AccessLevel.NONE)
    @FindBy(xpath = "//div[contains(@class, 'ant-card')]")
    private List<WebElement> clubCardComponents;

    @Getter(AccessLevel.NONE)
    @FindBy(xpath = "//ul[contains(@class, 'ant-pagination')]")
    private WebElement paginationRoot;

    @Getter(AccessLevel.NONE)
    @FindBy(xpath = "//div[@class = 'club-sider']//h2")
    private WebElement clubSiderTitle;

    public AllNewsPage(WebDriver driver) {
        super(driver);
        newsCards = initNewsCardComponents();
        clubCards = initClubCardLinesComponents();
        pagination = new SwitchPaginationComponent(driver, paginationRoot);
    }

    public AllNewsPage clickNextPage() {
        pagination.clickNext();
        return this;
    }

    public AllNewsPage clickPreviousPage() {
        pagination.clickPrevious();
        return this;
    }

    public String getClubSiderTitle() {
        return clubSiderTitle.getText();
    }

    private List<NewsCardComponent> initNewsCardComponents() {
        return newsCardComponents.stream()
                .map(webElement -> new NewsCardComponent(driver, webElement))
                .collect(Collectors.toList());
    }

    private List<ClubCardLineComponent> initClubCardLinesComponents() {
        return clubCardComponents
                .stream()
                .map(webElement -> new ClubCardLineComponent(driver, webElement))
                .collect(Collectors.toList());
    }
}

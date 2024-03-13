package com.academy.ui.components;

import com.academy.ui.pages.NewsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsCardComponent extends BaseComponent {
    @FindBy(id = "newsImage")
    private WebElement newsCardImage;

    @FindBy(id = "newsDate")
    private WebElement newsCardDate;

    @FindBy(id = "newsTitle")
    private WebElement newsCardTitle;

    @FindBy(xpath = ".//a[contains(@href, '/news/')]")
    private WebElement newsCardLink;

    public NewsCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Click on 'Детальніше' button and redirect to the News Page")
    public NewsPage newsCardLinkClick() {
        newsCardLink.click();
        return new NewsPage(driver);
    }

    @Step("Get News Card date value")
    public String getNewsDate() {
        return newsCardDate.getText();
    }

    @Step("Get News Card title")
    public String getNewsTitle() {
        return newsCardTitle.getText();
    }

    @Step("Check that news card is displayed on the News Card")
    public boolean newsTitleVisible() {
        return newsCardTitle.isDisplayed();
    }
}

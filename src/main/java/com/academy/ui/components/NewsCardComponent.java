package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
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

    //when NewsPage will be implemented
    /*public NewsPage toNewsPage() {
        getNewsCardLink().click();
        return new NewsPage(driver);
    }
    */

    public String getNewsDate() {
        return newsCardDate.getText();
    }

    public String getNewsTitle() {
        return newsCardTitle.getText();
    }
}

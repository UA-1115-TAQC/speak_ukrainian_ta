package com.academy.ui.components;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class NewsCardComponent extends BaseComponent {
    private WebElement newsCardImage;
    private WebElement newsCardData;
    private WebElement newsCardTitle;
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

    public WebElement getNewsCardImage() {
        if (newsCardImage == null) {
            newsCardImage = rootElement.findElement(xpath(".//div[@id = 'newsImage']"));
        }
        return newsCardImage;
    }

    public WebElement getNewsCardData() {
        if (newsCardData == null) {
            newsCardData = rootElement.findElement(xpath(".//div[@id = 'newsDate']"));
        }
        return newsCardData;
    }

    public WebElement getNewsCardTitle() {
        if (newsCardTitle == null) {
            newsCardTitle = rootElement.findElement(xpath(".//div[@id = 'newsTitle']"));
        }
        return newsCardTitle;
    }

    public WebElement getNewsCardLink() {
        if (newsCardLink == null) {
            newsCardLink = rootElement.findElement(xpath(".//a[contains(@href, '/news/')]"));
        }
        return newsCardLink;
    }
}

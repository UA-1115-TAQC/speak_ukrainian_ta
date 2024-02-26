package com.academy.ui.pages;

import com.academy.ui.SocialInfoComponent;
import com.academy.ui.components.carousel.NewsPageCarouselComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class NewsPage extends BasePage {
    private SocialInfoComponent socialInfoComponent;

    public NewsPageCarouselComponent newsPageCarousel;

    @FindBy(xpath = "//div[@class = 'image']")
    private WebElement newsImage;

    @FindBy(xpath = "//div[@id= 'date']")
    private WebElement newsDate;

    @FindBy(xpath = "//div[@id = 'description']")
    private WebElement newsText;

    @FindBy(xpath = "//div[@class = 'social-info']/descendant::button")
    private WebElement helpProjectButton;

    @FindBy(xpath = "//div[@class = 'other-news']/div[@class = 'title']")
    private WebElement newsCarouselTitle;

    public NewsPage(WebDriver driver) {
        super(driver);
        socialInfoComponent = new SocialInfoComponent(driver,
                driver.findElement(By.xpath("//div[@class='social-info']")));
        newsPageCarousel = new NewsPageCarouselComponent(driver,
                driver.findElement(By.xpath("//div[@class = 'news-carousel-block']")));
    }

    public void clickHelpProjectButton() {
        helpProjectButton.click();
    }
}

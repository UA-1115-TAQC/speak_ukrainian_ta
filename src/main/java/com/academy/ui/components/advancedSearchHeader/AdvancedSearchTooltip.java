package com.academy.ui.components.advancedSearchHeader;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.pages.ClubsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

@Getter
public class AdvancedSearchTooltip extends BaseComponent {
    @FindBy(xpath = "//div[contains(@title,\"Категорії\")]")
    protected WebElement categoryNameCategories;
    @FindBy(xpath = "//div[contains(@title,\"Гуртки\")]")
    protected WebElement categoryNameClubs;

    protected HashMap<String, WebElement> categories;
    protected HashMap<String, WebElement> clubs;


    public AdvancedSearchTooltip(WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
    }

    public WebElement getCategoryByTitle(String title) {
        if (categories == null) {
            this.getCategories();
        }
        return categories.get(title);
    }
    public WebElement getClubByTitle(String title) {
        if (clubs == null) {
            this.getClubs();
        }
        return clubs.get(title);
    }

    public ClubsPage clickCategoryByTitle(String title) {
        this.getCategoryByTitle(title).click();
        return new ClubsPage(driver);
    }

    public ClubsPage clickClubByTitle(String title) {
        this.getClubByTitle(title).click();
        return new ClubsPage(driver);
    }

    public HashMap<String, WebElement> getCategories() {
        if (categories == null) {
            categories = new HashMap<>();
            List<WebElement> categoryElements = rootElement.findElements(By.xpath("//div[@type='category']"));

            for (WebElement categoryElement : categoryElements) {
                String title = categoryElement.getAttribute("title");
                categories.put(title, categoryElement);
            }
        }
        return categories;
    }

    public HashMap<String, WebElement> getClubs() {
        if (clubs == null) {
            clubs = new HashMap<>();
            List<WebElement> categoryElements = rootElement.findElements(By.xpath("//div[@type='club']"));
            for (WebElement categoryElement : categoryElements) {
                String title = categoryElement.getAttribute("title");
                clubs.put(title, categoryElement);
            }
        }
        return clubs;
    }
}


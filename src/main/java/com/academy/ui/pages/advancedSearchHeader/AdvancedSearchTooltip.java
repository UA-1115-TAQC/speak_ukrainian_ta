package com.academy.ui.pages.advancedSearchHeader;

import com.academy.ui.pages.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class AdvancedSearchTooltip extends BaseComponent {
    protected WebElement categoryNameCategories;
    protected WebElement categoryNameClubs;

    protected HashMap<String, WebElement> categories;
    protected HashMap<String, WebElement>  clubs;


    public AdvancedSearchTooltip(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

    }
    public WebElement getCategoryNameCategories(){
        if (categoryNameCategories == null) {
            categoryNameCategories = rootElement.findElement(By.xpath("//div[contains(@title,\"Категорії\")]"));
        }
        return categoryNameCategories;
    }

    public WebElement getCategoryNameClubs(){
        if (categoryNameClubs == null) {
            categoryNameClubs = rootElement.findElement(By.xpath("//div[contains(@title,\"Гуртки\")]"));
        }
        return categoryNameClubs;
    }

    public WebElement getCategoryByTitle(String title){
        if(categories == null){
            this.getCategories();
        }
       return categories.get(title);
    }


    public WebElement getClubByTitle(String title){
        if(clubs == null){
            this.getClubs();
        }
        return clubs.get(title);
    }

    public void clickCategoryByTitle(String title){
        this.getCategoryByTitle(title).click();
    }

    public void clickClubByTitle(String title){
        this.getClubByTitle(title).click();
    }
    public HashMap<String,WebElement> getCategories(){
        if(categories == null){
            categories = new HashMap<>();
            List<WebElement> categoryElements = rootElement.findElements(By.xpath("//div[@type='category']"));

            for (WebElement categoryElement : categoryElements) {
                String title = categoryElement.getAttribute("title");
                categories.put(title, categoryElement);
            }
        }
        return categories;
    }

    public HashMap<String,WebElement> getClubs(){
        if(clubs == null){
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


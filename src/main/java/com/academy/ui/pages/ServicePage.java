package com.academy.ui.pages;


import com.academy.ui.SocialInfoComponent;
import com.academy.ui.components.CollapseComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ServicePage extends BasePageWithAdvancedSearch {

    @FindBy(xpath = ".//div[@class='title']")
    private WebElement banner;

    @FindBy(xpath = ".//div[@class='title']/span[@class='text']")
    private WebElement bannerText;

    @FindBy(xpath = ".//div[@class='social-info']")
    private WebElement socialInfoXpath;

    @FindBy(xpath = ".//div[@class ='social-info']/descendant::button")
    private WebElement helpProjectButton;

    @FindBy(xpath = ".//div[@class ='content-title']")
    private WebElement contentTitle;

    @FindBy(xpath = ".//div[@class ='content-text']")
    private WebElement contentText;

    @FindBy(xpath = ".//div[@class ='faq-title']")
    private WebElement faqTitle;

    @Getter(AccessLevel.NONE)
    @FindBy(xpath = ".//div[contains(@class,'ant-collapse-icon-position-start')]")
    private List<WebElement> collapseElementList;

    private SocialInfoComponent socialInfoComponent;

    @Getter(AccessLevel.NONE)
    private List<CollapseComponent> listOfCollapseComponents;

    public ServicePage(WebDriver driver) {
        super(driver);
        this.socialInfoComponent = new SocialInfoComponent(driver, socialInfoXpath);
    }

    public void clickHelpProjectButton() {
        helpProjectButton.click();
    }

    public List<CollapseComponent> getListOfCollapseComponents() {
        listOfCollapseComponents = new ArrayList<>();
        if (!collapseElementList.isEmpty()) {
            collapseElementList.forEach(item -> listOfCollapseComponents.add(new CollapseComponent(driver, item)));
        }
        return listOfCollapseComponents;
    }

    public ServicePage clickOnCollapseElementByIndex(int number) {
        collapseElementList.get(number).click();
        return this;
    }

}

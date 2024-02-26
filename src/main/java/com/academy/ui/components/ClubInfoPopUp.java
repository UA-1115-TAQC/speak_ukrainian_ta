package com.academy.ui.components;

import com.academy.ui.components.elements.ContactElement;
import com.academy.ui.pages.ClubPage;

import java.util.ArrayList;
import java.util.List;

import com.academy.ui.pages.DirectionTagComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ClubInfoPopUp extends BasePopUp {
    @FindBy(xpath = ".//div[contains(@class, 'title')]")
    private WebElement title;

    @FindBy(xpath = ".//div[@class = 'address']")
    private WebElement clubAddress;

    @FindBy(xpath = ".//div[@class = 'age']//span[contains(@class, 'sider-label')]")
    private WebElement clubAgeSiderLabel;

    @FindBy(xpath = ".//span[@class = 'years']")
    private WebElement clubAgeYears;

    @FindBy(xpath = ".//button[contains(@class,'more-button')]")
    private WebElement clubButton;

    @FindBy(xpath = ".//div[contains(@class, 'about')]//span[@class = 'title']")
    private WebElement aboutClubTitle;

    @FindBy(xpath = ".//div[contains(@class, 'about')]//div[@class = 'description']")
    private WebElement aboutClubDescription;

    @FindBy(xpath = ".//ul[contains(@class,'ant-rate')]")
    protected WebElement rating;

    @FindBy(xpath = ".//span[contains(@class,'feedback')]")
    protected WebElement feedback;

    @FindBy(xpath = ".//button[contains(@class,'download-button')]")
    protected WebElement downloadButton;

    @FindBy(xpath = ".//span[contains(@class,'ant-tag')]")
    @Getter(AccessLevel.NONE)private List<WebElement> directionTags;

    @FindBy(xpath = ".//div[contains(@class,'contact')]")
    @Getter(AccessLevel.NONE)private List<WebElement> contactTags;

    protected List<DirectionTagComponent> directions;
    protected List<ContactElement> socialMedia;

    public ClubInfoPopUp(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<DirectionTagComponent> getDirectionTags() {
        if (directions == null) {
            directions = new ArrayList<>();
            for (WebElement tag : directionTags) {
                directions.add(new DirectionTagComponent(driver, tag));
            }
        }
        return directions;
    }

    public List<ContactElement> getSocialMedia() {
        if (socialMedia == null) {
            socialMedia = new ArrayList<>();
            for (WebElement tag : contactTags) {
                socialMedia.add(new ContactElement(driver, tag));
            }
        }
        return socialMedia;
    }

    public ClubPage clubButtonClick() {
        clubButton.click();
        return new ClubPage(driver);
    }

    public void clickFeedback(){
        getFeedback().click();
    }

    public void clickDownloadButton(){
        getDownloadButton().click();
    }

}

package com.academy.ui.components;

import com.academy.ui.components.elements.ContactElement;
import com.academy.ui.pages.ClubPage;

import java.util.ArrayList;
import java.util.List;

import com.academy.ui.pages.DirectionTagComponent;
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

    protected List<DirectionTagComponent> directionTags;
    protected List<ContactElement> socialMedia;

    public ClubInfoPopUp(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<DirectionTagComponent> getDirectionTags() {
        if (directionTags == null) {
            directionTags = new ArrayList<>();
            List<WebElement> tags = rootElement.findElements(By.xpath(
                    ".//span[contains(@class,'ant-tag')]"));
            for (WebElement tag : tags) {
                directionTags.add(new DirectionTagComponent(driver, tag));
            }
        }
        return directionTags;
    }

    public List<ContactElement> getSocialMedia() {
        if (socialMedia == null) {
            socialMedia = new ArrayList<>();
            List<WebElement> tags = rootElement.findElements(By.xpath(
                    ".//div[contains(@class,'contact')]"));
            for (WebElement tag : tags) {
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

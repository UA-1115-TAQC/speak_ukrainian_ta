package com.academy.ui.components;

import com.academy.ui.components.elements.ContactElement;
import com.academy.ui.pages.ClubPage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.academy.ui.pages.DirectionTagComponent;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.xpath;

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
    private WebElement description;

    @FindBy(xpath = ".//ul[contains(@class,'ant-rate')]")
    protected WebElement rating;

    @FindBy(xpath = ".//span[contains(@class,'feedback')]")
    protected WebElement feedback;

    @FindBy(xpath = ".//button[contains(@class,'download-button')]")
    protected WebElement downloadButton;

    @FindBy(xpath = ".//span[contains(@class,'ant-tag')]")
    @Getter(AccessLevel.NONE)
    private List<WebElement> directionTags;

    @FindBy(xpath = ".//div[contains(@class,'contact')]")
    @Getter(AccessLevel.NONE)
    private List<WebElement> contactTags;

    protected List<DirectionTagComponent> directions;
    protected List<ContactElement> socialMedia;

    public ClubInfoPopUp(WebDriver driver) {
        super(driver, driver.findElement(xpath("//div[contains(@class,'clubInfo')]")));
    }

    @Step("Get list of the direction components on the club info popup")
    public List<DirectionTagComponent> getDirections() {
        if (directions == null) {
            directions = new ArrayList<>();
            for (WebElement tag : directionTags) {
                directions.add(new DirectionTagComponent(driver, tag));
            }
        }
        return directions;
    }

    @Step("Check if the direction components of the club info popup contains {text}")
    public boolean directionsContains(String text){
        getDirections();
        for (DirectionTagComponent direction : directions) {
            if(direction.getNameText().toLowerCase().contains(text.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    @Step("Check if the direction components of the club info popup contains {text}")
    public boolean descriptionContains(String text){
        return getDescription().getText().toLowerCase().contains(text.toLowerCase());
    }

    @Step("Get list of the contact elements on the club info popup")
    public List<ContactElement> getSocialMedia() {
        if (socialMedia == null) {
            socialMedia = new ArrayList<>();
            for (WebElement tag : contactTags) {
                socialMedia.add(new ContactElement(driver, tag));
            }
        }
        return socialMedia;
    }

    @Step("Click on the 'Більше про гурток' button on the club info popup")
    public ClubPage clubButtonClick() {
        clubButton.click();
        return new ClubPage(driver);
    }

    @Step("Click on the 'відгуків' link on the club info popup")
    public void clickFeedback() {
        getFeedback().click();
    }

    @Step("Click on the 'Завантажити' button on the club info popup")
    public void clickDownloadButton() {
        getDownloadButton().click();
    }

    @Step("Get list of age on the club info popup")
    public List<Integer> getClubsAgeList() {
        List<Integer> years = new ArrayList<>();
        Pattern pattern = Pattern.compile("від\\s+(\\d+)\\s+до\\s+(\\d+)\\s+років");
        Matcher matcher = pattern.matcher(clubAgeYears.getAttribute("innerText"));
        if (matcher.find()) {
            years.add(Integer.parseInt(matcher.group(1)));
            years.add(Integer.parseInt(matcher.group(2)));
        }
        return years;
    }

}

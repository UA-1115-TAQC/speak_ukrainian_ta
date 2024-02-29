package com.academy.ui.pages;

import com.academy.ui.components.*;
import com.academy.ui.components.advancedSearchHeader.AdvancedSearchClubHeaderComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ClubsPage extends BasePage {
    @FindBy(xpath="//div[contains(@class, 'lower-header-box')]")
    @Getter(AccessLevel.NONE) private WebElement searchClubHeaderWebElement;

    @FindBy(xpath="//ul[contains(@class,'ant-pagination') and contains(@class,'pagination')]")
    @Getter(AccessLevel.NONE) private WebElement switchPaginationWebElement;

    @FindBy(xpath="//div[contains(@class, 'club-list-control')]")
    @Getter(AccessLevel.NONE) private WebElement listControlWebElement;

    @FindBy(xpath="//div[contains(@class, '')]")
    @Getter(AccessLevel.NONE) private WebElement searchSiderWebElement;

    @FindBy(xpath="//div[contains(@class,'content-clubs-list')]/child::div")
    @Getter(AccessLevel.NONE) private List<WebElement> clubCardsWebElement;

    @FindBy(xpath="//div[contains(@class,'content-center-list')]/child::div")
    @Getter(AccessLevel.NONE) private List<WebElement> centerCardsWebElement;

    protected AdvancedSearchClubHeaderComponent advancedSearchClubHeader;
    protected ClubsPaginationComponent switchPagination;
    protected ClubListControlComponent listControl;
    protected AdvancedSearchSiderComponent searchSider;
    protected List<ClubCardComponent> clubCards;
    protected List<CenterCardComponent> centerCards;

    public ClubsPage(WebDriver driver) {
        super(driver);

        advancedSearchClubHeader = new AdvancedSearchClubHeaderComponent(this.driver, searchClubHeaderWebElement);
        switchPagination = new ClubsPaginationComponent(this.driver, switchPaginationWebElement);
        listControl = new ClubListControlComponent(this.driver, listControlWebElement);
        searchSider = new AdvancedSearchSiderComponent(this.driver, searchSiderWebElement);

        clubCards = createClubComponents();
        centerCards = createCenterComponents();
    }

    private List<ClubCardComponent> createClubComponents() {
        List<ClubCardComponent> clubs = new ArrayList<>();
        for (WebElement element : clubCardsWebElement) {
            clubs.add(new ClubCardComponent(driver, element));
        }
        return clubs;
    }

    private List<CenterCardComponent> createCenterComponents() {
        List<CenterCardComponent> centers = new ArrayList<>();
        for (WebElement element : clubCardsWebElement) {
            centers.add(new CenterCardComponent(driver, element));
        }
        return centers;
    }

    public ClubsPage waitUntilClubsPageIsLoaded(int seconds){
        if(seconds > 0 ) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.urlContains("clubs"));
            wait.until(ExpectedConditions.visibilityOf(getAdvancedSearchClubHeader().getShowOnMapButton()));
            return this;
        }
        throw new Error("The number of seconds must be greater than 0 and an integer number");
    }

    public ClubsPage setTextHeaderSearch(String input) {
        advancedSearchClubHeader.setTextSelectionSearchInputField(input);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1500000));
        sleep(1000);
        clubCards = createClubComponents();
        return this;
    }

}

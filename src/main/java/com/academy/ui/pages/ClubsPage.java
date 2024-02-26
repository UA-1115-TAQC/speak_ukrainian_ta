package com.academy.ui.pages;

import com.academy.ui.components.advancedSearchHeader.AdvancedSearchClubHeaderComponent;
import com.academy.ui.components.ClubListControlComponent;
import com.academy.ui.components.SwitchPaginationComponent;
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

    @FindBy(xpath="//div[contains(@class,'content-clubs-list')]/child::div")
    @Getter(AccessLevel.NONE) private List<WebElement> clubCardsWebElement;

    protected AdvancedSearchClubHeaderComponent advancedSearchClubHeader;
    protected SwitchPaginationComponent switchPagination;
    protected ClubListControlComponent listControl;
    protected List<ClubCardComponent> clubCards;

    public ClubsPage(WebDriver driver) {
        super(driver);

        advancedSearchClubHeader = new AdvancedSearchClubHeaderComponent(this.driver, searchClubHeaderWebElement);
        switchPagination = new SwitchPaginationComponent(this.driver, switchPaginationWebElement);
        listControl = new ClubListControlComponent(this.driver, listControlWebElement);

        clubCards = createClubComponents();
    }

    private List<ClubCardComponent> createClubComponents() {
        List<ClubCardComponent> clubs = new ArrayList<>();
        for (WebElement element : clubCardsWebElement) {
            clubs.add(new ClubCardComponent(driver, element));
        }
        return clubs;
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

    public void clickNext(){

    }

}

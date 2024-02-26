package com.academy.ui.pages;

import com.academy.ui.components.advancedSearchHeader.AdvancedSearchClubHeaderComponent;
import com.academy.ui.components.ClubListControlComponent;
import com.academy.ui.components.SwitchPaginationComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
        listControl = new ClubListControlComponent(driver, listControlWebElement);

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


    public static void main(String[] args){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
        HomePage hp = new HomePage(driver);

        ClubsPage cp1 = hp.getAdvancedSearchHeaderComponent().clickAdvancedSearchIcon();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

//        List<ClubCardComponent> cards1 = null;
//        List<String> clubNames1 = new ArrayList<>();
//        while(true){
//            cards1 = cp1.getClubCards();
//            for(ClubCardComponent c : cards1){
//                clubNames1.add(c.getClubName());
//            }
//            if(cp1.getSwitchPagination().isNextDisabled()){
//                break;
//            }
//            cp1 = cp1.getSwitchPagination().clickNext();
//        }
//
//        for(String str : clubNames1){
//            System.out.println( str + ", ");
//        }
//        System.out.println("******");

        ClubsPage cp2 = cp1.getListControl().clickArrowUp();
        List<ClubCardComponent> cards2 = cp2.getClubCards();
        List<String> clubNames2 = new ArrayList<>();
        for(ClubCardComponent c : cards2){
            clubNames2.add(c.getClubName());
        }
        for(String str : clubNames2){
            System.out.println( str + ", ");
        }


    }
}

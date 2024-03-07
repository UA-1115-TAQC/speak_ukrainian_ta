package com.academy.ui.pages;

import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.CenterCardWithEditComponent;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.components.ClubsPaginationComponent;
import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.components.LeftSideProfileComponent;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProfilePage extends BasePage {
    public LeftSideProfileComponent leftSideProfileComponent;
    @FindBy(xpath = ".//div[@class='content-title']")
    private WebElement myProfileTitle;

    @FindBy(xpath = ".//span[contains(@class, 'user-avatar')]")
    private WebElement userAvatar;

    @FindBy(xpath = ".//div[@class='user-name']")
    private WebElement userName;

    @FindBy(xpath = ".//div[@class='user-role']")
    private WebElement userRole;

    @FindBy(xpath = "./descendant::div[@class='user-phone-data']")
    private WebElement phoneUser;

    @FindBy(xpath = "./descendant::div[@class='user-email-data']")
    private WebElement emailUser;

    @FindBy(xpath = "./descendant::span[text()='Редагувати профіль']")
    private WebElement editProfileButton;

    @FindBy(xpath = ".//div[contains(@class, 'ant-select-selector')]")
    private WebElement myLessonsOrCentersDropDown;

    @FindBy(xpath = "//div[contains(@class, 'select-item')]//span[text()='гуртки']")
    private WebElement myLessonsDropDown;

    @FindBy(xpath = "//div[contains(@class, 'select-item')]//span[text()='центри']")
    private WebElement myCentersDropDown;

    @FindBy(xpath = ".//div[contains(@class, 'add-club-dropdown')]//button")
    private WebElement addButton;

    @FindBy(xpath = "//div[contains(@class,'ant-dropdown')]/child::*[1]//div[text()='Додати гурток']")
    private WebElement addClubButton;

    @FindBy(xpath = "./descendant::div[contains(@class, 'ant-modal css-13m256z user-edit')]//div[@class='ant-modal-content']")
    private WebElement editUserModalForm;

    @FindBy(xpath = "//div[contains(@class,'ant-dropdown')]/child::*[1]//div[text()='Додати центр']")
    private WebElement addCenterButton;

    @FindBy(xpath = ".//div[contains(@class, 'menu-component')]")
    private WebElement leftSideRoot;

    @FindBy(xpath = ".//div[contains(@class,'ant-card-body')]")
    @Getter(AccessLevel.NONE)
    private List<WebElement> clubCardsListWebElements;

    @FindBy(xpath = ".//ul[contains(@class,'ant-pagination') and contains(@class,'pagination')]")
    @Getter(AccessLevel.NONE)
    private WebElement switchPaginationWebElement;

    @FindBy(xpath = ".//div[contains(@class,'center-card')]")
    @Getter(AccessLevel.NONE)
    private List<WebElement> centerCardsListWebElements;

    protected WebElement addButtonAddClubOption;
    protected WebElement addButtonAddCenterOption;
    protected List<ClubCardWithEditComponent> clubCardComponentsList;
    protected ClubsPaginationComponent switchPagination;
    protected List<CenterCardWithEditComponent> centerCardComponentsList;

    public ProfilePage(WebDriver driver) {
        super(driver);
        leftSideProfileComponent = new LeftSideProfileComponent(driver, leftSideRoot);
        switchPagination = new ClubsPaginationComponent(this.driver, switchPaginationWebElement);
        selectWhatCardsToShow();
    }

    public void dropDownClick() {
        myLessonsOrCentersDropDown.click();
    }

    public void editButtonClick() {
        editProfileButton.click();
    }

    public void centersDropDownClick() {
        myCentersDropDown.click();
    }

    public List<WebElement> addButtonClick(){
        addButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getAddButtonOptionStringPath("club"))));
        List<WebElement> addButtonDropdown = new ArrayList<>();
        addButtonDropdown.add(driver.findElement(By.xpath(getAddButtonOptionStringPath("club"))));
        addButtonDropdown.add(driver.findElement(By.xpath(getAddButtonOptionStringPath("center"))));
        return addButtonDropdown;
    }

    private String getAddButtonOptionStringPath(String name) {
        return "//li[contains(@data-menu-id,\"add_" + name + "_admin\")]";

    }

    public void hoverAddButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(addButton).perform();
    }

    public AddClubPopUpComponent lessonsDropDownClick() {
        addClubButton.click();
        return new AddClubPopUpComponent(driver);
    }

    public AddCenterPopUpComponent centerDropDownClick() {
        addCenterButton.click();
        return new AddCenterPopUpComponent(driver);
    }

    public EditProfilePopUp openEditUserProfile() {
        editProfileButton.click();
        return new EditProfilePopUp(driver, editUserModalForm);
    }

    public List<ClubCardWithEditComponent> getClubCardComponents() {
        clubCardComponentsList = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(clubCardsListWebElements));
        for (WebElement card : clubCardsListWebElements) {
            clubCardComponentsList.add(new ClubCardWithEditComponent(driver, card));
        }
        return clubCardComponentsList;
    }

    public ClubCardWithEditComponent getClubCardByName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (ClubCardWithEditComponent card : clubCardComponentsList) {
            wait.until(e -> card.getWebElement().isDisplayed());
            if (card.getTitle().getText().equals(name)) {
                return card;
            }
        }
        return null;
    }

    public AddClubPopUpComponent openAddClubPopUp() {
        addButtonClick().get(0).click();
        return new AddClubPopUpComponent(driver);
    }

    public List<CenterCardWithEditComponent> getCenterCardComponents() {
        centerCardComponentsList = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(centerCardsListWebElements));
        for (WebElement card : centerCardsListWebElements) {
            centerCardComponentsList.add(new CenterCardWithEditComponent(driver, card));
        }
        return centerCardComponentsList;
    }

    public ProfilePage clickMyClubsAndCentersOnDropdown() {
        myLessonsOrCentersDropDown.click();
        return this;
    }

    public void clickMyCentersOnDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(myCentersDropDown)).click();
        getCenterCardComponents();
    }

    public void clickMyClubsOnDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(myLessonsDropDown)).click();
        getClubCardComponents();
    }

    public CenterCardWithEditComponent getCenterCardByName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (CenterCardWithEditComponent card : centerCardComponentsList) {
            wait.until(e -> card.getWebElement().isDisplayed());
            if (card.getCenterName().getText().equals(name)) {
                return card;
            }
        }
        return null;
    }

    private void selectWhatCardsToShow() {
        if (myLessonsOrCentersDropDown.getAttribute("innerText").equals("гуртки")) {
            getClubCardComponents();
        } else {
            getCenterCardComponents();
        }
    }
}



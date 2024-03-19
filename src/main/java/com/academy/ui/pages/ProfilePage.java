package com.academy.ui.pages;

import com.academy.ui.components.AddCenterPopUpComponent.AddCenterPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpStepOne;
import com.academy.ui.components.CenterCardWithEditComponent;
import com.academy.ui.components.ClubCardWithEditComponent;
import com.academy.ui.components.ClubsPaginationComponent;
import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.components.LeftSideProfileComponent;
import io.qameta.allure.Step;
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
import java.util.stream.Collectors;

@Getter
public class ProfilePage extends BasePage {
    public LeftSideProfileComponent leftSideProfileComponent;

    @FindBy(xpath = ".//div[@class='content-title']")
    private WebElement myProfileTitle;

    @FindBy(xpath = ".//span[contains(@class, 'user-avatar')]")
    private WebElement userAvatar;

    @FindBy(xpath="//span[contains(@class, 'user-avatar')]/img")
    protected WebElement userAvatarImage;

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

    @FindBy(xpath = "//div[contains(@class, 'user-club-content')]//div[contains(@class, 'space-item')]")
    private List<WebElement> myClubsList;

    @FindBy(xpath = "//div[contains(@class, 'user-club-content')]")
    private WebElement clubsSpace;

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
    public List<ClubCardComponent> getClubsElements(){
        return myClubsList.stream().map(el -> new ClubCardComponent(driver, el)).collect(Collectors.toList());
    }

    public ProfilePage(WebDriver driver) {
        super(driver);
        leftSideProfileComponent = new LeftSideProfileComponent(driver, leftSideRoot);
        switchPagination = new ClubsPaginationComponent(this.driver, switchPaginationWebElement);
        selectWhatCardsToShow();
    }

    public void dropDownClick() {
        myLessonsOrCentersDropDown.click();
    }

    @Step("Click edit button 'Додати' on the Profile page")
    public void editButtonClick() {
        editProfileButton.click();
    }

    @Step("Click on My Centers on drop down")
    public void centersDropDownClick() {
        myCentersDropDown.click();
    }

    @Step("Click add button 'Додати' on the Profile page")
    public List<WebElement> addButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getAddButtonOptionStringPath("club"))));
        List<WebElement> addButtonDropdown = new ArrayList<>();
        addButtonDropdown.add(driver.findElement(By.xpath(getAddButtonOptionStringPath("club"))));
        addButtonDropdown.add(driver.findElement(By.xpath(getAddButtonOptionStringPath("center"))));
        return addButtonDropdown;
    }

    @Step("Get xpath by name {name} for add button 'Додати' dropdown options on the Profile page")
    private String getAddButtonOptionStringPath(String name) {
        return "//li[contains(@data-menu-id,\"add_" + name + "_admin\")]";
    }

    @Step("Hover add button 'Додати' on the Profile page")
    public void hoverAddButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(addButton).perform();
    }

    @Step("Click on add club button on drop down")
    public AddClubPopUpComponent clubDropDownClick() {
        addClubButton.click();
        return new AddClubPopUpComponent(driver);
    }

    @Step("Click edit button 'Додати' on the Profile page")
    public AddCenterPopUpComponent centerDropDownClick() {
        addCenterButton.click();
        return new AddCenterPopUpComponent(driver);
    }

    @Step("Click edit button 'Редагувати профіль' on the Profile page")
    public EditProfilePopUp openEditUserProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(editProfileButton)).click();
        return new EditProfilePopUp(driver, editUserModalForm);
    }

    @Step("Get list of club cards on the Profile page")
    public List<ClubCardWithEditComponent> getClubCardComponents() {
        clubCardComponentsList = new ArrayList<>();
        if (!clubCardsListWebElements.isEmpty()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfAllElements(clubCardsListWebElements));
            for (WebElement card : clubCardsListWebElements) {
                clubCardComponentsList.add(new ClubCardWithEditComponent(driver, card));
            }
        }
        return clubCardComponentsList;
    }

    @Step("Get clubs card by name {name} on the Profile page")
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

    @Step("Click add club button 'Додати гурток' on the Profile page")
    public AddClubPopUpComponent openAddClubPopUp() {
        addButtonClick().get(0).click();
        return new AddClubPopUpComponent(driver);
    }

    @Step("Click add center button 'Додати центр' on the Profile page")
    public AddCenterPopUpComponent openAddCenterPopUp() {
        addButtonClick().get(1).click();
        return new AddCenterPopUpComponent(driver);
    }

    @Step("Get list of center cards on the Profile page")
    public List<CenterCardWithEditComponent> getCenterCardComponents() {
        centerCardComponentsList = new ArrayList<>();
        if (!centerCardsListWebElements.isEmpty()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfAllElements(centerCardsListWebElements));
            for (WebElement card : centerCardsListWebElements) {
                centerCardComponentsList.add(new CenterCardWithEditComponent(driver, card));
            }
        }
        return centerCardComponentsList;
    }

    @Step("Click on dropdown 'Мої гуртки, центри' on the Profile page")
    public ProfilePage clickMyClubsAndCentersOnDropdown() {
        myLessonsOrCentersDropDown.click();
        return this;
    }

    @Step("Click 'Мої центри' option on dropdown on the Profile page")
    public void clickMyCentersOnDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(myCentersDropDown)).click();
        getCenterCardComponents();
    }

    @Step("Click 'Мої гуртки' option on dropdown on the Profile page")
    public void clickMyClubsOnDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(myLessonsDropDown)).click();
        getClubCardComponents();
    }

    @Step("Get center card by name {name} on the Profile page")
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

    @Step("Select center or club cards to show on the Profile page")
    private void selectWhatCardsToShow() {
        if (myLessonsOrCentersDropDown.getAttribute("innerText").equals("гуртки")) {
            getClubCardComponents();
        } else {
            getCenterCardComponents();
        }
    }

    //може бути декілька меседжів одночасно: наприклад, коли змінюєш пароль: Профіль змінено успішно та Пароль змінено успішно.
    //Збираю ці меседжи колектором у стрінгу із сепаратором ';', щоб потім assertTrue("msg".contains(expectedMessage))
    public String getSuccessEditMessage() {
        return driver.findElements(By
                        .xpath("//div[contains(@class, 'notice-wrapper')]//span[contains(., 'змінено успішно')]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.joining(";"));
    }

    public ClubCardWithEditComponent getClubCardWithoutCenter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (ClubCardWithEditComponent card : clubCardComponentsList) {
            wait.until(e -> card.getWebElement().isDisplayed());
            AddClubPopUpComponent chooseCard = card.clickMoreButton().clickEditClub();
            String center = chooseCard.getStepOneContainer().getCenterSelectedTitle().getAttribute("title").toString();
            if (center.equals("- центр не вказано")) {
                return card;
            } else {
                chooseCard.close();
            }
        }
        return null;
    }

    public ClubCardWithEditComponent getClubCardWithCenter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        int index = 0;
        while (index < clubCardComponentsList.size()) {
            ClubCardWithEditComponent card = clubCardComponentsList.get(index);
            wait.until(e -> card.getWebElement().isDisplayed());
            AddClubPopUpComponent chooseCard = card.clickMoreButton().clickEditClub();
            String center = chooseCard
                    .getStepOneContainer()
                    .getCenterSelectedTitle()
                    .getAttribute("title")
                    .toString();
            if (!center.equals("- центр не вказано")) {
                chooseCard.close();
                return card;
            } else {
                chooseCard.close();
                index++;
            }
        }
        return null;
    }

}



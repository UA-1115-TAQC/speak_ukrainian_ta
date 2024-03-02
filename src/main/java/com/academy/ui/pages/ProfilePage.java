package com.academy.ui.pages;

import com.academy.ui.components.LeftSideProfileComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends BasePage{
    public LeftSideProfileComponent leftSideProfileComponent;
    public ProfilePage(WebDriver driver) {
        super(driver);
        this.leftSideProfileComponent = getLeftSideProfileComponent();
    }

    //ЗАЛИШИЛОСЬ ЛИШЕ : карточка з гуртком і робота з нею

    @Getter
    @FindBy(xpath = "//div[@class='content-title']")
    private WebElement myProfileTitle;

    @Getter
    @FindBy(xpath = "//span[contains(@class, 'user-avatar')]")
    private WebElement userAvatar;

    @Getter
    @FindBy(xpath = "//div[@class='user-name']")
    private WebElement userName;

    @Getter
    @FindBy(xpath = "//div[@class='user-role']")
    private WebElement userRole;

    @Getter
    @FindBy(xpath = "./descendant::div[@class='user-phone-data']")
    private WebElement phoneUser;

    @Getter
    @FindBy(xpath = "./descendant::div[@class='user-email-data']")
    private WebElement emailUser;

    @FindBy(xpath = "./descendant::span[text()='Редагувати профіль']")
    private WebElement editProfileButton;
    @FindBy(xpath = "//div[contains(@class, 'ant-select-selector')]")
    private WebElement myLessonsOrCentersDropDown;
    @FindBy(xpath = "//div[contains(@class, 'select-item')]//span[text()='гуртки']")
    private WebElement myLessonsDropDown;
    @FindBy(xpath = "//div[contains(@class, 'select-item')]//span[text()='центри']")
    private WebElement myCentersDropDown;
    @FindBy(xpath = "//div[contains(@class, 'add-club-dropdown')]//button")
    private WebElement addButton;
    protected WebElement addButtonAddClubOption;
    protected WebElement addButtonAddCenterOption;

    public void dropDownClick(){
        myLessonsOrCentersDropDown.click();
    }
    public void editButtonClick() { editProfileButton.click(); }
    public void lessonsDropDownClick(){
        myLessonsDropDown.click();
    }
    public void centersDropDownClick(){
        myCentersDropDown.click();
    }
    public List<WebElement> addButtonClick(){
        addButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getAddButtonOptionStringPath("club"))));
        List<WebElement> addButtonDropdown = new ArrayList<>();
        addButtonDropdown.add(driver.findElement(By.xpath(getAddButtonOptionStringPath("club"))));
        addButtonDropdown.add(driver.findElement(By.xpath(getAddButtonOptionStringPath("center"))));
        return addButtonDropdown;
    }
    private String getAddButtonOptionStringPath(String name){
        return "//li[contains(@data-menu-id,\"add_"+name+"_admin\")]/span";
    }

    public LeftSideProfileComponent getLeftSideProfileComponent(){
        if(leftSideProfileComponent == null) {
            WebElement leftSideRoot = this.driver.findElement(By.xpath("//div[contains(@class, 'menu-component')]"));
            leftSideProfileComponent = new LeftSideProfileComponent(driver, leftSideRoot);
        }
        return leftSideProfileComponent;
    }

}

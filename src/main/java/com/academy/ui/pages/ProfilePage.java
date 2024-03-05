package com.academy.ui.pages;

import com.academy.ui.components.AddClubPopUpComponent.AddClubPopUpComponent;
import com.academy.ui.components.EditProfilePopUp;
import com.academy.ui.components.LeftSideProfileComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

@Getter
public class ProfilePage extends BasePage {
    public LeftSideProfileComponent leftSideProfileComponent;
    @FindBy(xpath = "//div[@class='content-title']")
    private WebElement myProfileTitle;

    //ЗАЛИШИЛОСЬ ЛИШЕ : карточка з гуртком і робота з нею
    // Залишилось ще плюс додати дроп давнт карточки
    @FindBy(xpath = "//span[contains(@class, 'user-avatar')]")
    private WebElement userAvatar;
    @FindBy(xpath = "//div[@class='user-name']")
    private WebElement userName;
    @FindBy(xpath = "//div[@class='user-role']")
    private WebElement userRole;
    @FindBy(xpath = "./descendant::div[@class='user-phone-data']")
    private WebElement phoneUser;
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
    @FindBy(xpath = "//div[contains(@class,'ant-dropdown')]/child::*[1]//div[text()='Додати гурток']")
    private WebElement addClubButton;
    @FindBy(xpath = "./descendant::div[contains(@class, \"ant-modal css-13m256z user-edit\")]//div[@class=\"ant-modal-content\"]")
    private WebElement editUserModalForm;
    @FindBy(xpath = "//div[contains(@class,'ant-dropdown')]/child::*[1]//div[text()='Додати центр']")
    private WebElement addCenterButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.leftSideProfileComponent = getLeftSideProfileComponent();
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

    public void hoverAddButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(addButton).perform();
    }

    public AddClubPopUpComponent lessonsDropDownClick() {
        addClubButton.click();
        return new AddClubPopUpComponent(driver);
    }

//    public AddCenterPopUpComponent centerDropDownClick(){
//        Дописати схожий код сюди, коли буде готовий AddCenterPopUpComponent :
//        addCenterButton.click();
//        return new AddCenterPopUpComponent(driver);
//    }

    public LeftSideProfileComponent getLeftSideProfileComponent() {
        if (leftSideProfileComponent == null) {
            WebElement leftSideRoot = this.driver.findElement(By.xpath("//div[contains(@class, 'menu-component')]"));
            leftSideProfileComponent = new LeftSideProfileComponent(driver, leftSideRoot);
        }
        return leftSideProfileComponent;
    }

    public EditProfilePopUp openEditUserProfile() {
        editProfileButton.click();
        return new EditProfilePopUp(driver, editUserModalForm);
    }
}

package com.academy.ui.components;

import com.academy.ui.components.AddCenterPopUPComponent.AddCenterPopUpComponent;
import com.academy.ui.pages.ProfilePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class CenterCardWithEditComponent extends BaseComponent{

    @FindBy(xpath = ".//img[@class='center-icon']")
    protected WebElement centerLogo;

    @FindBy(xpath = ".//div[@class='center-title']")
    protected WebElement centerTitle;

    @FindBy(xpath = ".//div[@class='center-name']")
    protected WebElement centerName;

    @FindBy(xpath = ".//div[@class='center-address']/span[@role='img']")
    protected WebElement centerAddressIcon;

    @FindBy(xpath = ".//div[@class='center-address']/span[@class='text']")
    protected WebElement centerAddress;

    @FindBy(xpath = ".//div[@class='center-edit-button']")
    protected WebElement moreButton;

    @FindBy(xpath = "//ul[contains(@class,'update-menu')]")
    protected WebElement moreButtonMenu;

    @FindBy(xpath = "//ul[contains(@class,'update-menu')]/li[1]")
    protected WebElement editCenter;

    @FindBy(xpath = "//ul[contains(@class,'update-menu')]/li[2]")
    protected WebElement deleteCenter;


    public CenterCardWithEditComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public AddCenterPopUpComponent clickEditCenter(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(editCenter)).click();
        return new AddCenterPopUpComponent(driver);
    }

    public void clickDeleteCenter(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(editCenter)).click();
    }
}

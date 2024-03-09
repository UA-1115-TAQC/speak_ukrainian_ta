package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class DayTimeCheckboxElement extends BaseComponent {

    @FindBy(xpath = "./descendant::input[@class='ant-checkbox-input']")
    private WebElement checkbox;

    @FindBy(xpath = "./descendant::label[contains(@class,'ant-checkbox-wrapper')]/span[2]/div")
    private WebElement checkboxTitle;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item')]/descendant::input[@placeholder='HH:mm'][1]")
    private WebElement timeFromInput;

    @FindBy(xpath = "./descendant::div[contains(@class,'ant-form-item')]/descendant::input[@placeholder='HH:mm'][2]")
    private WebElement timeToInput;

    @FindBy(xpath = "//div[@class='ant-picker-panel-container']")
    private WebElement timePicker;

    @FindBy(xpath = "//div[@class='ant-picker-panel-container']//button")
    private WebElement timePickerButton;

    @FindBy(xpath = "//div[contains(@class,'ant-picker-dropdown')]/descendant::ul[contains(@class,'ant-picker-time-panel-column')][1]//div[@class='ant-picker-time-panel-cell-inner']")
    private List<WebElement> timeFromPickerList;

    @FindBy(xpath = "//div[contains(@class,'ant-picker-dropdown')]/descendant::ul[contains(@class,'ant-picker-time-panel-column')][2]//div[@class='ant-picker-time-panel-cell-inner']")
    private List<WebElement> timeToPickerList;

    @FindBy(xpath = "//div[contains(@class,'ant-picker-dropdown')]/descendant::span[@class='ant-picker-suffix']/span[@aria-label='clock-circle']")
    private WebElement clockIcon;

    public DayTimeCheckboxElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Set 'From' club time on the second step of Add club pop-up")
    public DayTimeCheckboxElement setTimeFromInput(String time) {
        timeFromInput.sendKeys(time);
        return this;
    }

    @Step("Set 'To' club time on the second step of Add club pop-up")
    public DayTimeCheckboxElement setTimeToInput(String time) {
        timeToInput.sendKeys(time);
        return this;
    }

    @Step("Click on 'OK' button on the time picker on the second step of Add club pop-up")
    public DayTimeCheckboxElement clickOkTimePickerButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(timePickerButton)).click();
        return this;
    }
}

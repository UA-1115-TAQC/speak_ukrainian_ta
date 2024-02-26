package com.academy.ui.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

@Getter
public class LeaveCommentPopUpComponent extends BasePopUp {

    @FindBy(xpath = "//textarea[@id='comment-edit_commentText']")
    private WebElement addComment;

    @FindBy(xpath = "//button[contains(@class,' do-comment-button')]")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id='rc-tabs-0-tab-2']")
    private WebElement complaintTab;

    @FindBy(xpath = "//div[@id='rc-tabs-0-tab-1']")
    private WebElement commentTab;

    @FindBy(xpath = "//div[@class='ant-tooltip-inner']")
    private WebElement tooltipMassage;


    public LeaveCommentPopUpComponent hoverOverSubmitButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(submitButton).perform();
        return this;
    }

    public LeaveCommentPopUpComponent addComment(String comment) {
        addComment.sendKeys(comment);
        return this;
    }

    public LeaveCommentPopUpComponent submitButton() {
        submitButton.click();
        return this;
    }

    public LeaveCommentPopUpComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

package com.academy.ui.components;

import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class SignUpToClubPopUpComponent extends BasePopUp{

    @FindBy(xpath = "//div[contains(@class,'SignUpForClub_customCheckbox')]")
    private WebElement selfSignUpForClubCheckBox;

    @FindBy(xpath = "//textarea[@id='registration-to-club_comment']")
    private WebElement addComment;

    @FindBy(xpath = "//button[contains(@class,'SignUpForClub_formButton')]")
    private WebElement signUpButton;

    @FindBy(xpath = "//button[contains(@class,'add-children')]")
    private WebElement addChildrenButton;

    @FindBy(xpath = "//div[contains(@class,'add-child-modal')]")
    @Getter(AccessLevel.NONE)
    private WebElement addChildrenWebElement;


    public SignUpToClubPopUpComponent clickOnSelfSignUpForClubCheckBox(){
        selfSignUpForClubCheckBox.click();
        return this;
    }

    public SignUpToClubPopUpComponent addComment(String text){
        addComment.sendKeys(text);
        return this;
    }

    public SignUpToClubPopUpComponent clickOnSubmitButton(){
        signUpButton.click();
        return this;
    }

    public AddChildPopUpComponent openAddChildWindow(){
        addChildrenButton.click();
        return new AddChildPopUpComponent(driver, addChildrenWebElement);
    }

    public SignUpToClubPopUpComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

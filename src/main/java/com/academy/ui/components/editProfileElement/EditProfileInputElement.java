package com.academy.ui.components.editProfileElement;

import com.academy.ui.components.AddClubPopUpComponent.AddClubInputElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Getter
public class EditProfileInputElement extends AddClubInputElement {

    @FindBy(xpath = "./descendant::span[@class='ant-input-suffix']/span[contains(@aria-label, 'eye-invisible') or contains(@aria-label, 'eye')]")
    private WebElement passwordVisibilityIcon;

    @FindBy(xpath = ".//label")
    private WebElement title;

    @FindBy(xpath = ".//span[@class='ant-input-group-addon']")
    private  WebElement phoneCountryCode;


    public EditProfileInputElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

    }

    @Step("Click on the password visibility icon")
    public EditProfileInputElement clickPasswordVisibilityIcon() {
        passwordVisibilityIcon.click();
        return this;
    }

    @Step("Get title text for the element")
    public String getTitleText() {
        return title.getText();
    }

}

package com.academy.ui.components.AddClubPopUpComponent;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LocationListElement extends BaseComponent {

    @FindBy(xpath = "./descendant::h4[@class='ant-list-item-meta-title']")
    private WebElement locationTitle;

    @FindBy(xpath = "./descendant::div[@class='ant-list-item-meta-description']")
    private WebElement descriptionTitle;

    @FindBy(xpath = "./descendant::ul[@class='ant-list-item-action']//span[@aria-label='edit']")
    private WebElement editIcon;

    @FindBy(xpath = "./descendant::ul[@class='ant-list-item-action']//span[@aria-label='delete']")
    private WebElement deleteIcon;

    public LocationListElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

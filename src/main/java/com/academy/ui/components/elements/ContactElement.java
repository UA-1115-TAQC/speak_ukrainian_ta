package com.academy.ui.components.elements;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ContactElement extends BaseComponent {
    @FindBy(xpath =".//span[contains(@class,'contact-name')]" )
    protected WebElement contactName;

    @FindBy(xpath = ".//div[contains(@class,'icon')]")
    protected WebElement icon;

    public ContactElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickContact(){
        WebElement contact = getContactName();
        List<WebElement> hrefs = contact.findElements(By.xpath(".//a"));
        if(hrefs.size() > 0){
            hrefs.get(0).click();
        }
    }
}

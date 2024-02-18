package com.academy.ui.pages;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class DirectionTagComponent extends BaseComponent {
    @FindBy(xpath =".//span[contains(@class,'name')]" )
    protected WebElement name;
    @FindBy(xpath = ".//div[contains(@class,'icon')]")
    protected WebElement icon;

    public DirectionTagComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

//    public WebElement getName(){
//        if (name == null) {
//            name = rootElement.findElement(By.xpath(
//                    ".//descendant::span[contains(@class,'name')]"));
//        }
//        return name;
//    }
//
//    public WebElement getIcon(){
//        if (icon == null) {
//            icon = rootElement.findElement(By.xpath(
//                    ".//descendant::div[contains(@class,'icon')]"));
//        }
//        return icon;
//    }
}

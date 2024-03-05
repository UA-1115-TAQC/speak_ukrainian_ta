package com.academy.ui.components.AddCenterPopUPComponent;

import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AddCenterPopUpStepFour extends AddCenterPopUpContainer {

    @FindBy(xpath = ".//div[@class='checkbox-item']")
    private List<WebElement> clubWebElementsList;

    @Getter(AccessLevel.NONE)
    List<ClubCheckboxLogoNameComponent> clubCheckboxLogoNameComponents;

    public AddCenterPopUpStepFour(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        getClubCheckboxLogoNameComponents();

    }

    public void getClubCheckboxLogoNameComponents() {
        clubCheckboxLogoNameComponents = new ArrayList<>();
        for (WebElement club : clubWebElementsList) {
            clubCheckboxLogoNameComponents.add(new ClubCheckboxLogoNameComponent(driver, club));
        }
    }

    public void clickOnClubCheckBoxByName(String name) {
        clubCheckboxLogoNameComponents.stream()
                .filter(club -> club.getClubName().getAttribute("innerText").equals(name))
                .forEach(club -> club.getClubCheckbox().click());
    }

    public List<ClubCheckboxLogoNameComponent> getListOfCheckedClubComponents() {
        return clubCheckboxLogoNameComponents.stream()
                .filter(club -> club.getClubCheckbox().isSelected())
                .collect(Collectors.toList());
    }


}

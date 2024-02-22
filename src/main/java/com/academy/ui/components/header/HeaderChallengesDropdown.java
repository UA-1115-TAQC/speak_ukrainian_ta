package com.academy.ui.pages.header;

import com.academy.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
@Getter
public class HeaderChallengesDropdown <T extends HeaderChallengesDropdown<T>> extends BaseComponent {
    @FindBy(xpath = "./li[@role=\"menuitem\"]")
    protected List<WebElement> challengeDropdownItems;
  public  HeaderChallengesDropdown(WebDriver driver, WebElement root){
      super(driver, root);
  }
  public WebElement getChallengeDropdownItemByIndex(int index){
      if(index >=0 && index < getChallengeDropdownItems().size()){
         return getChallengeDropdownItems().get(index);
      }
      throw new Error("The index must be between 0 and " + (getChallengeDropdownItems().size()-1)+", inclusive.");
  }
  public void clickChallengeDropdownItemByIndex(int index){
      String oldUrl = driver.getCurrentUrl();
      getChallengeDropdownItemByIndex(index).click();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
      wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
  }
  public WebElement getSelectedDropdownItem(){
      for (WebElement item:getChallengeDropdownItems()){
          if(item.getAttribute("class").contains("selected")){
            return item;
          }
      }
      throw new Error("You haven't selected any items");
  }
    public boolean checkThatDropdownItemIsSelectedByIndex(int index){
      return getChallengeDropdownItemByIndex(index).equals(getSelectedDropdownItem());
    }
}

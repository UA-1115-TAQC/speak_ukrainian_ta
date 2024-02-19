package com.academy.ui.components;


//import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class SwitchPaginationComponent extends BaseComponent {

    @FindBy(xpath = ".//li[contains(@class,'ant-pagination-prev')]")
    protected WebElement previousItem;

    @FindBy(xpath = ".//li[contains(@class,'ant-pagination-next')]")
    protected WebElement nextItem;

    @FindBy(xpath = ".//li[contains(@class, 'ant-pagination-item') or contains(@class, 'ant-pagination-jump-')]")
    protected List<WebElement> paginationItems;

    public SwitchPaginationComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

//    private void createPagItemsList() {
//        paginationItems = rootElement.findElements(By.xpath(
//                ".//li[contains(@class, 'ant-pagination-item') or contains(@class, 'ant-pagination-jump-')]"));
//    }

//    public List<WebElement> getPaginationItems() {
//        if (paginationItems == null) {
//            createPagItemsList();
//        }
//        return paginationItems;
//    }

    public WebElement getPagItemByTitle(String pageNum) {
        getPaginationItems();
        for (WebElement pagItem : paginationItems) {
            String str = pagItem.getDomAttribute("title");
            if (str.equals(pageNum)) {
                return pagItem;
            }
        }
        return null;
    }

    public boolean isPagItemActive(String pageNum) {
        getPaginationItems();
        WebElement pagItem = getPagItemByTitle(pageNum);
        if (pagItem != null) {
            String str = pagItem.getDomAttribute("class");
            return str.contains("ant-pagination-item-active");
        }
        return false;
    }

    public void clickPrevious() {
//        WebElement p = this.getPreviousItem();
        WebElement previousButton = previousItem.findElement(By.xpath(
                ".//button"));
        if (previousButton.isEnabled()) {
            previousButton.click();
        }
    }

    public void clickNext() {
//        getNext();
        WebElement nextButton = nextItem.findElement(By.xpath(
                ".//button"));
        if (nextButton.isEnabled()) {
            nextButton.click();
        }
    }

    public void clickPagItem(WebElement item) {
        item.click();
//        createPagItemsList();
        getPaginationItems();
    }

    public void clickPagItemByNum(String pageNum) {
        getPaginationItems();
        WebElement pagItem = getPagItemByTitle(pageNum);
        if (pagItem != null) {
            clickPagItem(pagItem);
        }
    }

    public void clickPrevFivePages() {
        getPaginationItems();
        WebElement pagItem = getPagItemByTitle("Previous 5 Pages");
        if (pagItem != null) {
            clickPagItem(pagItem);
        }
    }

    public void clickNextFivePages() {
        getPaginationItems();
        WebElement pagItem = getPagItemByTitle("Next 5 Pages");
        if (pagItem != null) {
            clickPagItem(pagItem);
        }
    }

//    TODO DELETE
    public static void main(String[] strs){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/clubs");
        WebElement root = driver.findElement(By.xpath(
                "//ul[contains(@class,'ant-pagination') and contains(@class,'pagination')]"));
        SwitchPaginationComponent c = new SwitchPaginationComponent(driver, root);
        c.clickNext();

//        List<WebElement> l = c.getPaginationItems();
//        for(WebElement e : l){
//            System.out.print(e.getText() + ", ");
//        }
//        System.out.println("******");
//
//        c.clickPagItemByNum("5");
//        l = c.getPaginationItems();
//        for(WebElement e : l){
//            System.out.print(e.getText() + ", ");
//        }
//        System.out.println("******");
//
//        c.clickPagItemByNum("7");
//        l = c.getPaginationItems();
//        for(WebElement e : l){
//            System.out.print(e.getText() + ", ");
//        }
//        System.out.println("******");
    }

}

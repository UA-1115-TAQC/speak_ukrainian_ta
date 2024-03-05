package com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

public class EditQuizSubmenuPopup extends BaseComponent {
    @FindBy(xpath = "//li[contains(@data-menu-id, 'edit_questions')]")
    private WebElement editQuestions;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'edit_question_categories')]")
    private WebElement editQuestionCategories;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'edit_question_types')]")
    private WebElement editQuestionTypes;

    public EditQuizSubmenuPopup(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickEditQuestions() {
        clickElement(driver, editQuestions); // todo
    }

    public void clickEditQuestionCategories() {
        clickElement(driver, editQuestionCategories);// todo
    }

    public void clickEditQuestionTypes() {
        clickElement(driver, editQuestionTypes); // todo
    }
}

package com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

public class QuizSubmenuPopup extends BaseComponent {
    @FindBy(xpath = "//li[contains(@data-menu-id, 'questions')]")
    private WebElement questions;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'import_questions')]")
    private WebElement importQuestions;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'generate_questions')]")
    private WebElement generateQuestions;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'export_questions')]")
    private WebElement exportQuestions;
    @FindBy(xpath = "//div[contains(@aria-controls, 'edit-popup')]")
    private WebElement edit;

    public QuizSubmenuPopup(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickQuestions() {
        clickElement(driver, questions); // todo
    }

    public void clickImportQuestions() {
        clickElement(driver, importQuestions); // todo
    }

    public void clickExportQuestions() {
        clickElement(driver, exportQuestions); // todo
    }

    public void clickGenerateQuestions() {
        clickElement(driver, generateQuestions); // todo
    }

    public EditQuizSubmenuPopup openEditQuizSubmenu() {
        clickElement(driver, edit); // todo
        return new EditQuizSubmenuPopup(driver, edit);
    }
}

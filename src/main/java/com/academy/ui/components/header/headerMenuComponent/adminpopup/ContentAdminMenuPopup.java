package com.academy.ui.components.header.headerMenuComponent.adminpopup;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu.CertificatesSubmenuPopup;
import com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu.ChallengesSubmenuPopup;
import com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu.FilesSubmenuPopup;
import com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu.QuizSubmenuPopup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.academy.ui.components.header.HeaderUtil.clickElement;


public class ContentAdminMenuPopup extends BaseComponent {

    @FindBy(xpath = "//div[contains(@aria-controls, 'challenges-submenu-popup')]")
    private WebElement challengesSubmenuPopup;
    @FindBy(xpath = "//div[contains(@aria-controls, 'certificates-popup')]")
    private WebElement certificatesSubmenuPopup;
    @FindBy(xpath = "//div[contains(@aria-controls, 'quiz-submenu-popup')]")
    private WebElement quizSubmenuPopup;
    @FindBy(xpath = "//div[contains(@data-menu-id, 'files-popup')]")
    private WebElement filesSubmenuPopup;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'logs')]")
    private WebElement logs;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'metrics')]")
    private WebElement metrics;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'users')]")
    private WebElement users;

    public ContentAdminMenuPopup(WebDriver driver, WebElement contentPopup) {
        super(driver, contentPopup);
    }

    public ChallengesSubmenuPopup openChallengesSubmenuPopup() {
        clickElement(driver, challengesSubmenuPopup);
        return new ChallengesSubmenuPopup(driver, challengesSubmenuPopup);
    }

    public CertificatesSubmenuPopup openCertificatesSubmenuPopup() {
        clickElement(driver, certificatesSubmenuPopup);
        return new CertificatesSubmenuPopup(driver, challengesSubmenuPopup);
    }

    public QuizSubmenuPopup openQuizSubmenuPopup() {
        clickElement(driver, quizSubmenuPopup);
        return new QuizSubmenuPopup(driver, challengesSubmenuPopup);
    }

    public FilesSubmenuPopup openFilesSubmenuPopup() {
        clickElement(driver, filesSubmenuPopup);
        return new FilesSubmenuPopup(driver, challengesSubmenuPopup);
    }

    public void clickLogs() {
        clickElement(driver, logs); // todo
    }

    public void clickMetrics() {
        clickElement(driver, metrics); // todo
    }

    public void clickUsers() {
        clickElement(driver, users); // todo
    }
}

package com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu;

import com.academy.ui.components.BaseComponent;
import com.academy.ui.pages.AdminGenerateCertificatePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.academy.ui.components.header.HeaderUtil.clickElement;

public class CertificatesSubmenuPopup extends BaseComponent {
    @FindBy(xpath = "//li[contains(@data-menu-id, 'all_certificates')]")
    private WebElement allCertificates;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'all_certificate_types')]")
    private WebElement allCertificateTypes;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'all_templates')]")
    private WebElement allTemplates;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'generate_certificate')]")
    private WebElement generateCertificate;
    @FindBy(xpath = "//li[contains(@data-menu-id, 'generate_certificate_by_template')]")
    private WebElement generateCertificateByTemplate;
    private WebDriverWait wait;
    public CertificatesSubmenuPopup(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickAllCertificates() {
        clickElement(driver, allCertificates);
    }

    public void clickAllCertificateTypes() {
        clickElement(driver, allCertificateTypes); // todo
    }

    public void clickAllTemplates() {
        clickElement(driver, allTemplates); // todo
    }

    public AdminGenerateCertificatePage clickGenerateCertificate() {
        clickElement(driver, generateCertificate); // todo
        AdminGenerateCertificatePage adminGenerateCertificatePage = new AdminGenerateCertificatePage(driver);
        wait.until(ExpectedConditions.visibilityOf(adminGenerateCertificatePage.getStudyDurationLabel()));
        return adminGenerateCertificatePage;

    }

    public void clickGenerateCertificateByTemplate() {
        clickElement(driver, generateCertificateByTemplate); // todo
    }
}
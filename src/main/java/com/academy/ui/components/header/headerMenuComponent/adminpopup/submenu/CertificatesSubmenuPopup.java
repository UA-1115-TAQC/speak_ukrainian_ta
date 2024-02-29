package com.academy.ui.components.header.headerMenuComponent.adminpopup.submenu;

import com.academy.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public CertificatesSubmenuPopup(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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

    public void clickGenerateCertificate() {
        clickElement(driver, generateCertificate); // todo
    }

    public void clickGenerateCertificateByTemplate() {
        clickElement(driver, generateCertificateByTemplate); // todo
    }
}
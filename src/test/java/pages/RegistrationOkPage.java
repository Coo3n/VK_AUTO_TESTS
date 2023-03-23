package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RegistrationOkPage {
    private static final By FIELD_EMAIL = By.id("field_email");
    private static final By FIELD_PASSWORD = By.id("field_password");
    private static final By FIELD_ERROR_LOG_IN = By.xpath("//div/div[@class='input-e login_error']");
    private static final By BUTTON_CHANGE_LANGUAGE = By.xpath("//div/button[@class='anon-tb-menu-btn portal-headline__menulink']");
    private static final By FIELD_ENGLISH_LANGUAGE = By.xpath("//a[@class='anon-tb-projects-link']");
    private static final By BUTTON_BOTTOM_CHANGE_LANGUAGE = By.xpath("//li//a//div[@class='tico']");
    private WebDriver webDriver;

    public RegistrationOkPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement getFieldEmail() {
        return webDriver.findElement(FIELD_EMAIL);
    }

    public WebElement getFieldPassword() {
        return webDriver.findElement(FIELD_PASSWORD);
    }

    public WebElement getLogInErrorInfo() {
        return webDriver.findElement(FIELD_ERROR_LOG_IN);
    }

    public WebElement getButtonChangeLanguage() {
        return webDriver.findElement(BUTTON_CHANGE_LANGUAGE);
    }

    public WebElement getFieldEnglishLanguage() {
        return webDriver.findElement(FIELD_ENGLISH_LANGUAGE);
    }

    public WebElement getButtonBottomChangeLanguage() {
        return webDriver.findElement(BUTTON_BOTTOM_CHANGE_LANGUAGE);
    }
}

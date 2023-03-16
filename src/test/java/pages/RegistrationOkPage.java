package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RegistrationOkPage {
    private static final By FIELD_EMAIL = By.id("field_email");
    private static final By FIELD_PASSWORD = By.id("field_password");
    private static final By FIELD_ERROR_LOG_IN = By.xpath("//div[@id='tabpanel-login-9058087262']/form/div[3]/div");

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
}

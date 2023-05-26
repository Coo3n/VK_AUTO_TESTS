package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public record LoginOkPage(WebDriver webDriver) {
    private static final By FIELD_EMAIL = By.id("field_email");
    private static final By FIELD_PASSWORD = By.id("field_password");

    public void login(String email, String password) {
        WebElement elementFieldEmail = getFieldEmail();
        WebElement elementFieldPassword = getFieldPassword();
        Assertions.assertAll(
                () -> Assertions.assertTrue(elementFieldEmail.isDisplayed()),
                () -> Assertions.assertTrue(elementFieldPassword.isDisplayed())
        );
        elementFieldEmail.sendKeys(email);
        elementFieldPassword.sendKeys(password);
        elementFieldPassword.sendKeys(Keys.ENTER);
    }

    private WebElement getFieldEmail() {
        return webDriver.findElement(FIELD_EMAIL);
    }

    private WebElement getFieldPassword() {
        return webDriver.findElement(FIELD_PASSWORD);
    }
}

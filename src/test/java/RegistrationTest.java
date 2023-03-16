import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import pages.RegistrationOkPage;

public class RegistrationTest {
    private static WebDriver webDriver;
    private static final String TARGET_URL = "https://ok.ru/";
    public static final String LOGIN = "89211899434";
    public static final String PASSWORD = "bkmz8520";
    public static final String WRONG_PASSWORD = "b";

    @BeforeAll
    public static void initWebDriver() {
        webDriver = new EdgeDriver();
    }

    @Test
    public void testCorrectLogIn() {
        webDriver.get(TARGET_URL);
        RegistrationOkPage registrationOkPage = new RegistrationOkPage(webDriver);

        WebElement elementFieldEmail = registrationOkPage.getFieldEmail();
        WebElement elementFieldPassword = registrationOkPage.getFieldPassword();

        elementFieldEmail.sendKeys(LOGIN);
        elementFieldPassword.sendKeys(PASSWORD);
        elementFieldPassword.sendKeys(Keys.ENTER);

        String currentURL = webDriver.getCurrentUrl();
        Assertions.assertEquals(currentURL, TARGET_URL);
    }

    @Test
    public void testWrongLogIn() {
        webDriver.get(TARGET_URL);

        RegistrationOkPage registrationOkPage = new RegistrationOkPage(webDriver);

        WebElement elementFieldEmail = registrationOkPage.getFieldEmail();
        WebElement elementFieldPassword = registrationOkPage.getFieldPassword();

        elementFieldEmail.sendKeys(LOGIN);
        elementFieldPassword.sendKeys(WRONG_PASSWORD);
        elementFieldPassword.sendKeys(Keys.ENTER);

        String errorInfoText = registrationOkPage.getLogInErrorInfo().getText();
        Assertions.assertEquals(errorInfoText, "Неправильно указан логин и/или пароль\n");
    }

    @AfterAll
    public static void quit() {
        webDriver.quit();
    }
}

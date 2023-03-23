import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import pages.RegistrationOkPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;

public class RegistrationTest {
    private static WebDriver webDriver;
    private static final String TARGET_URL = "https://ok.ru/";
    private static final String LOGIN = "botS23AT24";
    private static final String PASSWORD = "autotests2023";
    private static final String WRONG_PASSWORD = "b";
    private static final String ENGLISH_URL_PAGE = "https://ok.ru/dk?cmd=lang&st.lang=en&st.cmd=anonymMain";

    @BeforeAll
    public static void initWebDriver() {
        webDriver = new EdgeDriver();
    }

    @Test
    public void checkEnglishUrlLanguage() throws InterruptedException {
        webDriver.get(TARGET_URL);
        RegistrationOkPage registrationOkPage = new RegistrationOkPage(webDriver);
        WebElement buttonChangeLanguage = registrationOkPage.getButtonChangeLanguage();
        buttonChangeLanguage.click();

        WebElement fieldEnglishLanguage = registrationOkPage.getFieldEnglishLanguage();
        String newPageURL = fieldEnglishLanguage.getAttribute("href");

        assertThat(newPageURL, hasToString(ENGLISH_URL_PAGE));
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
        registrationOkPage.getLogInErrorInfo().getText();
    }

    @AfterAll
    public static void quit() {
        webDriver.quit();
    }
}

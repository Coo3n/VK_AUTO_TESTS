import model.AuthorizationData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import pages.LoginOkPage;

public class BaseTest {
    private static WebDriver webDriver;
    private static final String REGISTRATION_TARGET_URL = "https://ok.ru/";

    @BeforeAll
    public static void beforeAll() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new EdgeDriver(edgeOptions);
        webDriver.get(REGISTRATION_TARGET_URL);

        LoginOkPage loginOkPage = new LoginOkPage(webDriver);
        AuthorizationData auth = AuthorizationData.getInstance();
        loginOkPage.login(auth.getEmail(), auth.getPassword());
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @AfterAll
    public static void afterAll() {
        webDriver.quit();
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainOkPage;

public class InvisibleModeTest extends BaseTest {
    @Test
    @Disabled("Пример отключенного теста")
    public void disabledTest() {
        Assertions.assertEquals(0, 0);
    }

    @DisplayName("Проверка клика на кнопку Невидимка")
    @Test
    public void checkClickInvisibleModeButton() {
        MainOkPage mainOkPage = new MainOkPage(getWebDriver());
        mainOkPage.clickOnSwitchInvisibleModeButton()
                .checkIsVisibleOkAds();
    }
}

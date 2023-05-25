import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pages.MainOkPage;

public class ToolBarIconTest extends BaseTest {
    @DisplayName("Проверка видимости наличия иконки")
    @Test
    public void isVisibleMailIcon() {
        MainOkPage mainOkPage = new MainOkPage(getWebDriver());
        mainOkPage.checkVisibleMailIcon();
    }

    @Nested
    @DisplayName("Вложенный тест на проверку уведомления")
    class CheckNotificationTest {
        @Test
        void checkAvailabilityNotification() {
            MainOkPage mainOkPage = new MainOkPage(getWebDriver());
            mainOkPage.checkAvailabilityNotification();
        }
    }
}
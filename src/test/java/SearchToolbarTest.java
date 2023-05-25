import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainOkPage;

public class SearchToolbarTest extends BaseTest {
    @DisplayName("Проверка развернут ли тулбар")
    @Test
    public void checkIsExpandedToolbar() {
        MainOkPage mainOkPage = new MainOkPage(getWebDriver());
        mainOkPage.checkIsExpandedToolbar();
    }

    @ParameterizedTest(name = "Value {0}")
    @ValueSource(ints = {-1, -4})
    @DisplayName("Проверка тулбара")
    public void checkSearchToolbar(int exceptedValue) {
        MainOkPage mainOkPage = new MainOkPage(getWebDriver());
        mainOkPage.clickOnSearchToolbar(exceptedValue)
                .checkInputFieldOnMainSearchBar();
    }
}

package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public record MainOkPage(WebDriver webDriver) {
    private static final By SWITCH_INVISIBLE_MODE_BUTTON = By.xpath("//div[@class='checkable tumbler']/input");
    private static final By SEARCH_TOOLBAR = By.xpath("//label[@class='label__mofy2 label-input__on39s']/input");
    private static final By SEARCH_MAIN_MENU = By.xpath(
            "//label[@class='label__t53m6 __radius__t53m6 input-w-btn__t53m6 label-input__w4hf7']/input"
    );
    private static final By HIDE_SEARCH_TOOLBAR_BUTTON = By.xpath("//div[@class ='toolbar_search__hb']/span[1]");
    private static final By MAIL_ICON = By.xpath("//div[@id='msg_toolbar_button']/div[@class ='toolbar_nav_i_ic']");
    private static final By MAIL_ICON_NOTIFICATION = By.xpath("//div[@id='counter_ToolbarMessages']/div");

    private static final String EXCEPTED_TITLE_ADS = "Невидимка";
    private static Integer EXCEPTED_BALANCE_ADS = 0;

    public MainOkPage clickOnSwitchInvisibleModeButton() {
        WebElement switchButton = getSwitchInvisibleModeButton();
        switchButton.click();
        return this;
    }

    public MainOkPage clickOnSearchToolbar(int exceptedValue) {
        WebElement search = getSearchToolbar();
        EXCEPTED_BALANCE_ADS = exceptedValue;

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(search)).click();
        search.sendKeys(String.valueOf(exceptedValue), Keys.ENTER);
        return this;
    }

    public void checkIsExpandedToolbar() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(getSearchToolbar())).click();
        WebElement expandedButton = getHideSearchBarButton();
        String hideText = expandedButton.getText();
        Assertions.assertTrue(expandedButton.isDisplayed(), "Кнопка скрыть тулбар не отображена");
        assertThat(hideText, containsString("Свернуть поиск"));
    }

    public void checkIsVisibleOkAds() {
        AdsOkPage adsOkPage = switchToAdsOkPage();
        int balanceOkAds = adsOkPage.getBalance();
        String titleAds = adsOkPage.getTitle();
        adsOkPage.switchToMainOkPage();

        Assertions.assertAll(
                () -> Assertions.assertEquals(EXCEPTED_TITLE_ADS, titleAds, "Заголовок рекламы некорректный"),
                () -> Assertions.assertEquals(EXCEPTED_BALANCE_ADS, balanceOkAds, "Баланс неккоректный")
        );
    }

    public void checkInputFieldOnMainSearchBar() {
        WebElement searchField = getSearchMainMenu();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(searchField, "Поле поиска на главной странице не найдено"),
                () -> Assertions.assertTrue(searchField.isDisplayed(), "Поле поиска на главной странице не отображается"),
                () -> Assertions.assertEquals(
                        Integer.parseInt(searchField.getAttribute("value"))
                        , EXCEPTED_BALANCE_ADS,
                        "Баланс неккоректный"
                )
        );
    }

    public void checkVisibleMailIcon() {
        WebElement mailIcon = getMailIcon();
        Assertions.assertTrue(mailIcon.isDisplayed());
    }

    public void checkAvailabilityNotification() {
        WebElement notification = getMailIconNotification();
        Assertions.assertEquals("1", notification.getText());
    }

    private AdsOkPage switchToAdsOkPage() {
        return new AdsOkPage(webDriver);
    }

    private WebElement getSwitchInvisibleModeButton() {
        return getWebElementWithWait(SWITCH_INVISIBLE_MODE_BUTTON);
    }

    private WebElement getSearchToolbar() {
        return getWebElementWithWait(SEARCH_TOOLBAR);
    }

    private WebElement getSearchMainMenu() {
        return getWebElementWithWait(SEARCH_MAIN_MENU);
    }

    private WebElement getHideSearchBarButton() {
        return getWebElementWithWait(HIDE_SEARCH_TOOLBAR_BUTTON);
    }

    private WebElement getMailIcon() {
        return getWebElementWithWait(MAIL_ICON);
    }

    private WebElement getMailIconNotification() {
        return getWebElementWithWait(MAIL_ICON_NOTIFICATION);
    }

    private WebElement getWebElementWithWait(By pathElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pathElement));
    }
}

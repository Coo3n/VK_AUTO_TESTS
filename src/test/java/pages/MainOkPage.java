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
    private static final By HIDE_SEARCH_TOOLBAR_BUTTON = By.xpath(
            "/html/body/div[9]/div[4]/div/div[1]/div/div[5]/div/span[1]"
    );
    private static final By IFRAME_ADS = By.xpath(
            "//div[@class='h-mod modal-new_payment-frame-wrap']/iframe"
    );
    private static final By MAIL_ICON = By.xpath("//div[@id='msg_toolbar_button']/div[@class ='toolbar_nav_i_ic']");
    private static final By MAIL_ICON_NOTIFICATION = By.xpath("//div[@id='counter_ToolbarMessages']/div");
    private static final By TITLE_ADS = By.xpath("//div[@class = 'service-intro_text-wrapper']/div[@class='service-intro_title']");
    private static final By BALANCE_OK_ADS = By.xpath("//div[@class = 'pf_info_tx']/span");

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
        webDriver.switchTo().frame(getIframeAds());
        int balanceOkAds = Integer.parseInt(getBalanceOkAds().getText());
        String titleAds = getTitleAds().getText();
        webDriver.switchTo().defaultContent();

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

    private WebElement getSwitchInvisibleModeButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SWITCH_INVISIBLE_MODE_BUTTON));
    }

    private WebElement getIframeAds() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(IFRAME_ADS));
    }

    private WebElement getTitleAds() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ADS));
    }

    private WebElement getBalanceOkAds() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(BALANCE_OK_ADS));
    }

    private WebElement getSearchToolbar() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_TOOLBAR));
    }

    private WebElement getSearchMainMenu() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_MAIN_MENU));
    }

    private WebElement getHideSearchBarButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(HIDE_SEARCH_TOOLBAR_BUTTON));
    }

    private WebElement getMailIcon() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MAIL_ICON));
    }

    private WebElement getMailIconNotification() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MAIL_ICON_NOTIFICATION));
    }
}

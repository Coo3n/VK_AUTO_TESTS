package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdsOkPage {
    private final WebDriver webDriver;
    private static final By IFRAME_ADS = By.xpath("//div[@class='h-mod modal-new_payment-frame-wrap']/iframe");
    private static final By TITLE_ADS = By.xpath("//div[@class = 'service-intro_text-wrapper']/div[@class='service-intro_title']");
    private static final By BALANCE_OK_ADS = By.xpath("//div[@class = 'pf_info_tx']/span");

    public AdsOkPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriver.switchTo().frame(getIframeAds());
    }

    public MainOkPage switchToMainOkPage() {
        webDriver.switchTo().defaultContent();
        return new MainOkPage(webDriver);
    }

    public int getBalance() {
        return Integer.parseInt(getBalanceOkAds().getText());
    }

    public String getTitle() {
        return getTitleAds().getText();
    }

    private WebElement getIframeAds() {
        return getWebElementWithWait(IFRAME_ADS);
    }

    private WebElement getTitleAds() {
        return getWebElementWithWait(TITLE_ADS);
    }

    private WebElement getBalanceOkAds() {
        return getWebElementWithWait(BALANCE_OK_ADS);
    }

    private WebElement getWebElementWithWait(By pathElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pathElement));
    }
}

package page.def;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageEx {

    private final WebDriver driver;
    private String baseUrl;
    private int durationBase = 10;

    public PageEx(WebDriver driver, String url) {
        this.driver = driver;
        setUrl(url);
    }

    public String getText(String xPath) {
        return element(xPath).getText();
    }

    public boolean getTextTimeOut(String xPath, String exp) {
        return new WebDriverWait(driver, Duration.ofSeconds(durationBase)).until(
                ExpectedConditions.textToBe(By.xpath(xPath), exp)
        );
    }

    public boolean getTextContainsTimeOut(String xPath, String exp) {
        return new WebDriverWait(driver, Duration.ofSeconds(durationBase)).until(
                ExpectedConditions.textToBePresentInElement(element(xPath), exp)
        );
    }

    public String getAttr(String xPath, String attrName) {
        return element(xPath).getAttribute(attrName);
    }

    public boolean isElementEmpty(String xpath) {
        return driver.findElements(By.xpath(xpath)).isEmpty();
    }

    public void setUrl(String url) {
        this.baseUrl = url;
        driver.get(url);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void quit() {
        driver.quit();
    }

    public WebElement element(int duration, String xPath) {
        return new WebDriverWait(driver, Duration.ofSeconds(duration))
                .until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath(xPath))
                );
    }

    public WebElement element(String xPath) {
        int duration = 10;
        return element(duration, xPath);
    }

    public void switchFrame(String xPath) {
        element(xPath);
        driver.switchTo().frame(driver.findElement(By.xpath(xPath)));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void elementClickInvisibleLocated(String xPath) {
        element(xPath).click();
        new WebDriverWait(driver, Duration.ofSeconds(durationBase)).until(
                ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath))
        );
    }

    public void elementSendKeys(String xpath, String text) {
        var elem = element(xpath);
        elem.click();
        elem.sendKeys(text);
    }
}

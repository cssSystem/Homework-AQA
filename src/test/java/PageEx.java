import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageEx {
    protected WebDriver driver;
    protected String url;

    public PageEx(WebDriver driver, String url) {
        this.driver = driver;
        setUrl(url);
    }

    public String getText(String xPath) {
        return element(xPath).getText();
    }

    public String getAttr(String xPath, String attrName) {
        return element(xPath).getAttribute(attrName);
    }

    public boolean isElementEmpty(String xpath) {
        return driver.findElements(By.xpath(xpath)).isEmpty();
    }

    public WebElement linkElement(String text) {
        return driver.findElement(By.linkText(text));
    }


    public void setUrl(String url) {
        this.url = url;
        driver.get(url);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void quit() {
        driver.quit();
    }

    public void element(int duration, String xPath) {
        new WebDriverWait(driver, Duration.ofSeconds(duration))
                .until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath(xPath))
                );

    }
    public WebElement element(String xPath) {
        int duration = 10;
        element(duration, xPath);
        return driver.findElement(By.xpath(xPath));
    }
}

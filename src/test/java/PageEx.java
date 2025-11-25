import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageEx {
    protected WebDriver driver;
    protected String url;
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    public PageEx(WebDriver driver, String url) {
        this.driver = driver;
        setUrl(url);
    }

    public PageEx(WebDriver driver) {
        this.driver = driver;
    }

    public String getText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
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

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void quit() {
        driver.quit();
    }
}

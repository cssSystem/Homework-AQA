package page.pageOnlineReplenishmentBlock;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.def.PageEx;

import java.time.Duration;
import java.util.List;

public class PageOnlineReplenishmentBlock extends PageEx {
    public PageOnlineReplenishmentBlock(WebDriver driver, String url) {
        super(driver, url);
    }



    public String linkClickToUrl(String linkText) {
        if (linkText == null || linkText.isEmpty()) {
            return null;
        }
        linkElement(linkText).click();
        var url = this.getUrl();
        setUrl(this.url);
        return url;
    }

    public void cookieOk() {
        List<WebElement> link = driver.findElements(By.xpath("//*[@id='cookie-agree']"));
        if (!link.isEmpty() && link.get(0).isDisplayed()) {
            link.get(0).click();
        }
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='cookie-agree']"))
        );
    }

    public void formCommServ(String nomberTel, String sum) {
        var xPhone = "//*[@id='connection-phone']";
        var xSum = "//*[@id='connection-sum']";
        var xButton = "//*[@id='pay-connection']/button";

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul"))
        );

        new Actions(driver)
                .click(driver.findElement(By.xpath(xPhone)))
                .sendKeys(nomberTel)
                .click(driver.findElement(By.xpath(xSum)))
                .sendKeys(sum)
                .click(driver.findElement(By.xpath(xButton)))
                .perform();

    }


    public void click(String xPath) {
       element(xPath).click();
    }

    public void switchFrame(String xPath) {
        element(xPath);

        driver.switchTo().frame(driver.findElement(By.xpath(xPath)));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }

    public void exitFrame() {
        driver.switchTo().defaultContent();
    }
}
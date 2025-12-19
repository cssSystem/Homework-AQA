package page.pageOnlineReplenishmentBlock;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.def.PageEx;

import java.time.Duration;

public class PageOnlineReplenishmentBlock extends PageEx {

    public PageOnlineReplenishmentBlock(WebDriver driver, String url) {
        super(driver, url);
    }

    public String linkClickToUrl(String xPatch) {
        if (xPatch == null || xPatch.isEmpty()) {
            return null;
        }
        element(xPatch).click();
        var url = this.getUrl();
        setUrl(this.getBaseUrl());
        return url;
    }

    public void clickChooseService(int i) {
        String baseXpath = "//section[contains(@class, 'pay')]//*[contains(@class, 'select__wrapper')]/";
        element(baseXpath + "button/span[2]").click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(
                By.cssSelector("ul.select__list"), "style", "height: auto")
        );
        element(baseXpath + "ul/li[" + i + "]").click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ul.select__list")));
    }

    public void formCommServ(String numberTel, String sum) {
        var xPhone = "//*[@id='connection-phone']";
        var xSum = "//*[@id='connection-sum']";
        var xButton = "//*[@id='pay-connection']/button";

        clickChooseService(1);
        elementSendKeys(xPhone, numberTel);
        elementSendKeys(xSum, sum);
        element(xButton).click();
    }

    public void cookieOk() {
        var xPathCookie = "//*[@id='cookie-agree']";
        elementClickInvisibleLocated(xPathCookie);
    }
}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class PageOnlineReplenishmentBlock extends PageEx {
    public PageOnlineReplenishmentBlock(WebDriver driver, String url) {
        super(driver, url);
    }

    public PageOnlineReplenishmentBlock(WebDriver driver) {
        super(driver);
    }
    public String linkClickToUrl(String linkText){
        if(linkText==null|| linkText.isEmpty()){
            return null;
        }
        Actions actions = new Actions(driver);
        actions.moveToElement(linkElement(linkText))
                .click()
                .perform();
        String url=driver.getCurrentUrl();
        setUrl(this.url);
        return url;
    }
}

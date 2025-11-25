import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class Selenium {
    static PageOnlineReplenishmentBlock page=null;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
        page = new PageOnlineReplenishmentBlock(new ChromeDriver(), "http://mts.by/");

    }

    @BeforeEach
    void setup() {
//            if(!Objects.equals(driver.getCurrentUrl(), url)) {
//                driver.get(url);
//                cookieOk();
//            }

    }

    @AfterAll
    public static void tearDown() {
        if (page != null) {
            page.quit();
            page=null;
        }
    }

    @DisplayName("Название блока")
    @Test
    public void testBlockText() {
        if (page != null) {
            String expected = "Онлайн пополнение\n" +
                    "без комиссии";
            String text = page.getText("//*[@id='pay-section']/div/div/div[2]/section/div/h2");
            assertEquals(expected, text);
        }
    }

    @DisplayName("Наличие логотипов платёжных систем")
    @Test
    public void testLogoPaySystems() {
        if (page != null) {
            assertFalse(page.isElementEmpty("//img[@alt='Visa']"));
            assertFalse(page.isElementEmpty("//img[@alt='Verified By Visa']"));
            assertFalse(page.isElementEmpty("//img[@alt='MasterCard']"));
            assertFalse(page.isElementEmpty("//img[@alt='MasterCard Secure Code']"));
            assertFalse(page.isElementEmpty("//img[@alt='Белкарт']"));
        }
    }

    @DisplayName("Ссылка «Подробнее о сервисе»")
    @Test
    public void testLinkClick() {
        if (page != null) {
            var exp=page.linkElement("Подробнее о сервисе").getAttribute("href");
            var act =page.linkClickToUrl("Подробнее о сервисе");
            assertEquals(exp, act);
        }
    }

//    @DisplayName("Заполнение полей и проверка кнопки «Продолжить»")
//    @Test
//    public void testClickButton() {
//        if (page != null) {
//            String nomberTel = "297777777";
//            String sum = "100";
//            WebElement input = driver.findElement(By.xpath("//*[@id='connection-phone']"));
//            input.click();
//            input.sendKeys(nomberTel);
//            input = driver.findElement(By.xpath("//*[@id='connection-sum']"));
//            input.click();
//            input.sendKeys(sum);
//            WebElement link = driver.findElement(By.xpath("//*[@id='pay-connection']/button"));
//            link.click();
//
//
//            assertDoesNotThrow(() -> wait.until((d) -> driver.findElement(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']"))));
//        }
//    }

//    public static void cookieOk() {
//        List<WebElement> link = driver.findElements(By.xpath("//*[@id='cookie-agree']"));
//        if (!link.isEmpty()&&link.get(0).isDisplayed()) {
//            link.get(0).click();
//        }
//    }


}

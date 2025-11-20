import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class Selenium {
    static WebDriver driver;
    static String url = "https://www.mts.by/";
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    void setup() {
            if(!Objects.equals(driver.getCurrentUrl(), url)) {
                driver.get(url);
                cookieOk();
            }

    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DisplayName("Название блока")
    @Test
    public void testBlockText() {
        if (driver != null) {
            String expected = "Онлайн пополнение\n" +
                    "без комиссии";
            String text = driver.findElement(By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/h2")).getText();
            assertEquals(expected, text);
        }
    }

    @DisplayName("Наличие логотипов платёжных систем")
    @Test
    public void testLogoPaySystems() {
        if (driver != null) {
            assertFalse(driver.findElements(By.xpath("//img[@alt='Visa']")).isEmpty());
            assertFalse(driver.findElements(By.xpath("//img[@alt='Verified By Visa']")).isEmpty());
            assertFalse(driver.findElements(By.xpath("//img[@alt='MasterCard']")).isEmpty());
            assertFalse(driver.findElements(By.xpath("//img[@alt='MasterCard Secure Code']")).isEmpty());
            assertFalse(driver.findElements(By.xpath("//img[@alt='Белкарт']")).isEmpty());
        }
    }

    @DisplayName("Ссылка «Подробнее о сервисе»")
    @Test
    public void testLinkClick() {
        if (driver != null) {
            WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
            String expected = link.getAttribute("href");
            link.click();
            assertEquals(expected, driver.getCurrentUrl());
        }
    }

    @DisplayName("Заполнение полей и проверка кнопки «Продолжить»")
    @Test
    public void testClickButton() {
        if (driver != null) {
            String nomberTel = "297777777";
            String sum = "100";
            WebElement input = driver.findElement(By.xpath("//*[@id='connection-phone']"));
            input.click();
            input.sendKeys(nomberTel);
            input = driver.findElement(By.xpath("//*[@id='connection-sum']"));
            input.click();
            input.sendKeys(sum);
            WebElement link = driver.findElement(By.xpath("//*[@id='pay-connection']/button"));
            link.click();


            assertDoesNotThrow(() -> wait.until((d) -> driver.findElement(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']"))));
        }
    }

    public static void cookieOk() {
        List<WebElement> link = driver.findElements(By.xpath("//*[@id='cookie-agree']"));
        if (!link.isEmpty()&&link.get(0).isDisplayed()) {
            link.get(0).click();
        }
    }


}

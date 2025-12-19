package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.chrome.ChromeDriver;
import page.pageOnlineReplenishmentBlock.PageOnlineReplenishmentBlock;

import static allure.ScreenshotAllure.takeScreenshotAllure;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Owner("junior")
@Feature("Блок <<Онлайн пополнение без комиссии>> (Виджет оплаты)")
@Epic("www.mts.by")
@DisplayName("Блок <<Онлайн пополнение без комиссии>> (Виджет оплаты)")
public class TestPaymentWidget {

    static PageOnlineReplenishmentBlock page = null;
    static String numberTel = "297777777";
    static String sum = "100.00";
    String cur = "BYN";
    static String srcIframe = "//iframe[contains(@src,'checkout.bepaid.by/widget_v2/index.html')]";

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
        String url = "http://mts.by/";
        page = new PageOnlineReplenishmentBlock(new ChromeDriver(), url);
        page.maximize();
        page.cookieOk();
        page.formCommServ(numberTel, sum);
        page.switchFrame(srcIframe);
    }

    @AfterAll
    public static void tearDown() {
        if (page != null) {
            page.quit();
            page = null;
        }
    }

    @AfterEach
    public void tearDownEach(TestInfo testInfo) {
        takeScreenshotAllure(page.getDriver(), testInfo.getDisplayName());
    }

    @Description("Проверка суммы пополнения в footer с суммой пополнения {sum} и валютой {cur} в блоке «Онлайн пополнение без комиссии» (Виджет оплаты)")
    @DisplayName("Проверка суммы пополнения в footer")
    @Story("Позитивный тест")
    @Test
    public void testPaymentCorrectSumFooter() {
        var xPatch = "//app-payment-container//div[contains(@class, 'pay-description__cost')]/span";
        var separator = " ";
        var exp = sum + separator + cur;
        var act = page.getTextTimeOut(xPatch, exp);
        assertTrue(act, "Текст не совпадает. Ожидалось: \"" + exp + "\" Получено: \"" + page.getText(xPatch) + "\"");
    }

    @Description("Проверка суммы пополнения в кнопке «Оплатить» c суммой пополнения {sum} и валютой {cur} в блоке «Онлайн пополнение без комиссии» (Виджет оплаты)")
    @DisplayName("Проверка суммы пополнения в кнопке «Оплатить»")
    @Story("Позитивный тест")
    @Test
    public void testPaymentCorrectSumBtn() {
        var xPatch = "//app-payment-container//app-card-page//div[contains(@class, 'card-page__card')]/button/span";
        var separator = " ";
        var exp = sum + separator + cur;
        var act = page.getTextContainsTimeOut(xPatch, exp);
        assertTrue(act, "Текст не совпадает. Ожидалось в содержании: \"" + exp + "\" Получено: \"" + page.getText(xPatch) + "\"");
    }

    @Description("Проверка надписей в незаполненном поле {0} в блоке «Онлайн пополнение без комиссии» (Виджет оплаты)")
    @DisplayName("Проверка надписей в незаполненных полях")
    @Story("Позитивный тест")
    @ParameterizedTest(name = "Поле: {0}")
    @CsvSource({"Номер карты, //*[@id='cc-number']/../label"
            , "Срок действия, //input[@formcontrolname='expirationDate']/../label"
            , "CVC, //input[@formcontrolname='cvc']/../label"
            , "Имя и фамилия на карте, //input[@formcontrolname='holder']/../label"
    })
    public void testPaymentCorrectEmptyField(String exp, String xPath) {
        var act = page.getTextTimeOut(xPath, exp);
        assertTrue(act, "Текст не совпадает. Ожидалось: \"" + exp + "\" Получено: \"" + page.getText(xPath) + "\"");
    }

    @Description("Проверка наличия иконки {0} в блоке «Онлайн пополнение без комиссии» (Виджет оплаты)")
    @DisplayName("Проверка наличия иконок")
    @Story("Позитивный тест")
    @ParameterizedTest(name = "Иконка: {0}")
    @CsvSource({"visa-system.svg", "mastercard-system.svg", "belkart-system.svg", "maestro-system.svg", "mir-system-ru.svg"})
    public void testPaymentCorrectIcn(String nameIcon) {
        String baseXPatchImg = "//app-card-input//div[contains(@class, 'icons-container')]//img[contains(@src, '";
        String endXPatchImg = "')]";
        assertFalse(page.isElementEmpty(baseXPatchImg + nameIcon + endXPatchImg), "Иконка отсутствует");
    }
}
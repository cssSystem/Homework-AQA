package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import page.pageOnlineReplenishmentBlock.PageOnlineReplenishmentBlock;

import static allure.ScreenshotAllure.takeScreenshotAllure;
import static org.junit.jupiter.api.Assertions.*;

@Owner("junior")
@Feature("Блок <<Онлайн пополнение без комиссии>>")
@Epic("www.mts.by")
@DisplayName("Блок <<Онлайн пополнение без комиссии>>'")
public class TestOnlineReplenishmentBlock {

    static PageOnlineReplenishmentBlock page = null;
    String baseBlockXPath = "//*[@id='pay-section']";

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
        String url = "http://mts.by/";
        page = new PageOnlineReplenishmentBlock(new ChromeDriver(), url);
        page.maximize();
        page.cookieOk();
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
        String xPathFocusElement = baseBlockXPath + "//section[contains(@class, 'pay')]";

        new Actions(page.getDriver()).scrollToElement(page.element(xPathFocusElement)).perform();
        takeScreenshotAllure(page.getDriver(), testInfo.getDisplayName());
    }

    @Description("Проверка названия блока «Онлайн пополнение без комиссии»")
    @DisplayName("Название блока формы")
    @Story("Позитивный тест")
    @Test
    public void testBlockText() {
        String expected = "Онлайн пополнение\nбез комиссии";
        String xPath = baseBlockXPath + "//section//h2";
        String actual = page.getText(xPath);

        assertEquals(expected, actual);
    }

    @Description("Проверика наличия логотипов платёжных систем")
    @DisplayName("Логотипы платёжных систем")
    @Story("Позитивный тест")
    @ParameterizedTest(name = "Логотип {0}")
    @CsvSource({"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"})
    public void testLogoPaySystems(String altText) {
        String xpath = "//*[@id='pay-section']//img[@alt='" + altText + "']";
        var act = page.isElementEmpty(xpath);

        assertFalse(act);
    }

    @Description("Проверка работы ссылки «Подробнее о сервисе»")
    @DisplayName("Ссылка «Подробнее о сервисе»")
    @Story("Позитивный тест")
    @Test
    public void testLinkClick() {
        var elementXPath = baseBlockXPath + "//section[contains(@class, 'pay')]//a";
        var exp = page.element(elementXPath).getAttribute("href");
        var act = page.linkClickToUrl(elementXPath);

        assertEquals(exp, act);
    }

    @Description("Проверка надписей в незаполненных полях каждого варианта оплаты услуг: " +
            "услуги связи, домашний интернет, рассрочка, задолженность")
    @DisplayName("Поля формы оплаты: ")
    @Story("Позитивный тест")
    @ParameterizedTest(name = "{1}")
    @CsvSource({
            "1, Услуги связи ,Номер телефона, //*[@id='connection-phone'], //*[@id='connection-sum'], //*[@id='connection-email']"
            , "2, Домашний интернет, Номер абонента, //*[@id='internet-phone'], //*[@id='internet-sum'], //*[@id='internet-email']"
            , "3, Рассрочка, Номер счета на 44, //*[@id='score-instalment'], //*[@id='instalment-sum'], //*[@id='instalment-email']"
            , "4, Задолженность, Номер счета на 2073, //*[@id='score-arrears'], //*[@id='arrears-sum'], //*[@id='arrears-email']"
    })
    public void testFormTextLabelInput(int i, String testName, String exp, String xpathOne, String xpathTwo, String xpathThree) {
        String expSum = "Сумма";
        String expEmail = "E-mail для отправки чека";
        String attrName = "placeholder";

        page.clickChooseService(i);
        Allure.step("Проверка поля: " + exp);
        var act = page.getAttr(xpathOne, attrName);
        assertEquals(exp, act, "Ошибка в поле: Ожидалось: \"" + exp + "\" Получено: \"" + act + "\"");
        Allure.step("Проверка поля: " + expSum);
        act = page.getAttr(xpathTwo, attrName);
        assertEquals(expSum, act, "Ошибка в поле: Ожидалось: \"" + expSum + "\" Получено: \"" + act + "\"");
        Allure.step("Проверка поля: " + expEmail);
        act = page.getAttr(xpathThree, attrName);
        assertEquals(expEmail, act, "Ошибка в поле: Ожидалось: \"" + expEmail + "\" Получено: \"" + act + "\"");
    }

    @Description("Заполниение полей формы оплаты услуг и проверка кнопки <<Оплатить>>")
    @DisplayName("Проверка кнопки <<Оплатить>> ")
    @Story("Позитивный тест")
    @Test
    public void testButtonPay() {
        String sum = "100.00";
        String numberTel = "297777777";
        var srcIframe = "//iframe[contains(@src,'checkout.bepaid.by/widget_v2/index.html')]";

        page.formCommServ(numberTel, sum);
        assertTrue(page.element(srcIframe).isEnabled(), "Кнопка не активна");
    }
}
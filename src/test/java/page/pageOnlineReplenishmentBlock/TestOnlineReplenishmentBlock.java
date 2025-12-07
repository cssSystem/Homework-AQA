package page.pageOnlineReplenishmentBlock;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.chrome.ChromeDriver;

import static allure.screenshotAllure.takeScreenshotAllure;
import static org.junit.jupiter.api.Assertions.*;

@Owner("junior")
@Story("Позитивный сценарий")
@DisplayName("Проверка блока «Онлайн пополнение без комиссии»")
public class TestOnlineReplenishmentBlock {
    static PageOnlineReplenishmentBlock page = null;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
        page = new PageOnlineReplenishmentBlock(new ChromeDriver(), "http://mts.by/");
        page.maximize();
    }

    @BeforeEach
    void setup() {
        page.cookieOk();
    }

    @AfterAll
    public static void tearDown() {
        if (page != null) {
            page.quit();
            page = null;
        }
    }

    @Description("Проверка названия блока «Онлайн пополнение без комиссии»")
    @DisplayName("Название блока формы")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testBlockText() {
        String expected = "Онлайн пополнение\n" +
                "без комиссии";
        String text = page.getText("//*[@id='pay-section']/div/div/div[2]/section/div/h2");
        assertEquals(expected, text);
        takeScreenshotAllure(page.getDriver(), "testBlockText");
    }

    @Description("Проверика наличия логотипов платёжных систем")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testLogoPaySystems() {
        assertFalse(page.isElementEmpty("//img[@alt='Visa']"));
        assertFalse(page.isElementEmpty("//img[@alt='Verified By Visa']"));
        assertFalse(page.isElementEmpty("//img[@alt='MasterCard']"));
        assertFalse(page.isElementEmpty("//img[@alt='MasterCard Secure Code']"));
        assertFalse(page.isElementEmpty("//img[@alt='Белкарт']"));
    }

    @Description("Проверка работы ссылки «Подробнее о сервисе»")
    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void testLinkClick() {
        var exp = page.linkElement("Подробнее о сервисе").getAttribute("href");
        var act = page.linkClickToUrl("Подробнее о сервисе");
        assertEquals(exp, act);
    }

    @Description("Заполнение полей и проверка кнопки «Продолжить»." +
            "Проверить корректность отображения суммы (в том числе на кнопке), номера телефона, " +
            "а также надписей в незаполненных полях для ввода реквизитов карты, наличие иконок платёжных систем")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testClickButton() {
        String baseXpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/";
        String numberTel = "297777777";
        String sum = "100.00";
        String cur = "BYN";
        String exp = sum + " " + cur;
        String srcIfrm = "//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']";

        page.click(baseXpath + "button/span[2]");
        page.click(baseXpath + "ul/li[1]");

        page.formCommServ(numberTel, sum);

        page.switchFrame(srcIfrm);


        assertEquals(exp, page.getText("//app-payment-container/section/div/div/div[1]/div[1]/span"));
        assertTrue(page.getText("//app-payment-container/section/div/app-card-page/div/div[1]/button/span").contains(exp));

        //input
        assertEquals("Номер карты", page.getText("//app-card-input/form/div[1]/div[1]/app-input/div/div/div[1]/label"));
        assertEquals("Срок действия", page.getText("//app-card-input/form/div[1]/div[2]/div[1]/app-input/div/div/div[1]/label"));
        assertEquals("CVC", page.getText("//app-card-input/form/div[1]/div[2]/div[3]/app-input/div/div/div[1]/label"));
        assertEquals("Имя и фамилия на карте", page.getText("//app-card-input/form/div[1]/div[3]/app-input/div/div/div[1]/label"));

        //img
        assertFalse(page.isElementEmpty("//app-card-input//img[contains(@src, 'assets/images/payment-icons/card-types/visa-system.svg')]"));
        assertFalse(page.isElementEmpty("//app-card-input//img[contains(@src, 'assets/images/payment-icons/card-types/mastercard-system.svg')]"));
        assertFalse(page.isElementEmpty("//app-card-input//img[contains(@src, 'assets/images/payment-icons/card-types/belkart-system.svg')]"));
        assertFalse(page.isElementEmpty("//app-card-input//img[contains(@src, 'assets/images/payment-icons/card-types/maestro-system.svg')]"));
        assertFalse(page.isElementEmpty("//app-card-input//img[contains(@src, 'assets/images/payment-icons/card-types/mir-system-ru.svg')]"));


        page.click("//app-back-navigation/div/div/svg-icon");
        page.exitFrame();
    }

    @Description("Проверка надписей в незаполненных полях каждого варианта оплаты услуг: " +
            "услуги связи, домашний интернет, рассрочка, задолженность")
    @DisplayName("Поля формы оплаты: ")
    @Severity(SeverityLevel.MINOR)
    @ParameterizedTest(name = "{1}")
    @CsvSource({
            "1, Услуги связи ,Номер телефона, //*[@id='connection-phone'], //*[@id='connection-sum'], //*[@id='connection-email']"
            , "2, Домашний интернет, Номер абонента, //*[@id='internet-phone'], //*[@id='internet-sum'], //*[@id='internet-email']"
            , "3, Рассрочка, Номер счета на 44, //*[@id='score-instalment'], //*[@id='instalment-sum'], //*[@id='instalment-email']"
            , "4, Задолженность, Номер счета на 2073, //*[@id='score-arrears'], //*[@id='arrears-sum'], //*[@id='arrears-email']"
    })
    public void testFormTextLabelInput(int i, String testName, String exp, String xpathOne, String xpathTwo, String xpathThree) {
        String baseXpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/";
        String expSum = "Сумма";
        String expEmail = "E-mail для отправки чека";

        page.click(baseXpath + "button/span[2]");
        page.click(baseXpath + "ul/li[" + i + "]");

        assertEquals(exp, page.getAttr(xpathOne, "placeholder"));
        assertEquals(expSum, page.getAttr(xpathTwo, "placeholder"));
        assertEquals(expEmail, page.getAttr(xpathThree, "placeholder"));
        takeScreenshotAllure(page.getDriver(), testName);
    }


}

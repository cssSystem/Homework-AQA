import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.pageOnlineReplenishmentBlock.PageOnlineReplenishmentBlock;

import java.time.Duration;

import static allure.ScreenshotAllure.takeScreenshotAllure;
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
        page.cookieOk();
    }

    @BeforeEach
    void setup() {
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
        new Actions(page.getDriver()).scrollToElement(page.element("//*[@id='pay-section']//section[contains(@class, 'pay')]")).perform();
        takeScreenshotAllure(page.getDriver(), testInfo.getDisplayName());
    }

    @Description("Проверка названия блока «Онлайн пополнение без комиссии»")
    @DisplayName("Название блока формы")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testBlockText() {
        String expected = "Онлайн пополнение\nбез комиссии";
        String actual = page.getText("//*[@id='pay-section']/div/div/div[2]/section/div/h2");
        assertEquals(expected, actual);
    }

    @Description("Проверика наличия логотипов платёжных систем")
    @DisplayName("Логотипы платёжных систем")
    @Severity(SeverityLevel.MINOR)
    @ParameterizedTest(name = "Логотип: {0}")
    @CsvSource({
            "Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"
    })
    public void testLogoPaySystems(String altText) {
        String xpath = "//img[@alt='" + altText + "']";
        assertFalse(page.isElementEmpty(xpath));
    }

    @Description("Проверка работы ссылки «Подробнее о сервисе»")
    @DisplayName("Ссылка «Подробнее о сервисе»")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testLinkClick() {
        var elementXPath = "//*[@id='pay-section']/div/div/div[2]/section/div/a";
        var exp = page.element(elementXPath).getAttribute("href");
        var act = page.linkClickToUrl(elementXPath);
        assertEquals(exp, act);
    }

    @Description("Проверка корректности отображения суммы (в том числе на кнопке), номера телефона, " +
            "а также надписей в незаполненных полях для ввода реквизитов карты, наличие иконок платёжных систем")
    @DisplayName("Заполнение полей и проверка кнопки «Продолжить»")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testClickButton(TestInfo testInfo) {
        String numberTel = "297777777";
        String sum = "100.00";
        String cur = "BYN";
        String exp = sum + " " + cur;
        String srcIfrm = "//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']";
        int indexService = 1;

        page.clickChooseService(indexService);
        page.formCommServ(numberTel, sum);

        try {
            page.switchFrame(srcIfrm);

            assertEquals(exp, page.getText("//app-payment-container/section/div/div/div[1]/div[1]/span"));
            assertTrue(page.getText("//app-payment-container/section/div/app-card-page/div/div[1]/button/span").contains(exp));

            //input
            assertEquals("Номер карты", page.getText("//app-card-input/form/div[1]/div[1]/app-input/div/div/div[1]/label"));
            assertEquals("Срок действия", page.getText("//app-card-input/form/div[1]/div[2]/div[1]/app-input/div/div/div[1]/label"));
            assertEquals("CVC", page.getText("//app-card-input/form/div[1]/div[2]/div[3]/app-input/div/div/div[1]/label"));
            assertEquals("Имя и фамилия на карте", page.getText("//app-card-input/form/div[1]/div[3]/app-input/div/div/div[1]/label"));

            //img
            String baseXPatchImg = "//app-card-input//img[contains(@src, 'assets/images/payment-icons/card-types/";
            String endXPatchImg = "')]";
            String[] arr = {"visa-system.svg", "mastercard-system.svg", "belkart-system.svg", "maestro-system.svg", "mir-system-ru.svg"};
            for (String s : arr) {
                assertFalse(page.isElementEmpty(baseXPatchImg + s + endXPatchImg));
            }
            //Делаем скриншот пока открыт iframe
            takeScreenshotAllure(page.getDriver(), testInfo.getDisplayName()+" iframe");

            page.click("//app-back-navigation/div/div/svg-icon");
        } finally {
            page.exitFrame();
        }
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
        String expSum = "Сумма";
        String expEmail = "E-mail для отправки чека";

        page.clickChooseService(i);

        assertEquals(exp, page.getAttr(xpathOne, "placeholder"));
        assertEquals(expSum, page.getAttr(xpathTwo, "placeholder"));
        assertEquals(expEmail, page.getAttr(xpathThree, "placeholder"));
    }
}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class Selenium {
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
            var exp = page.linkElement("Подробнее о сервисе").getAttribute("href");
            var act = page.linkClickToUrl("Подробнее о сервисе");
            assertEquals(exp, act);
        }
    }

    @DisplayName("Заполнение полей и проверка фрейма страницы")
    @Test
    public void testClickButton() {
        if (page != null) {
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
    }

    @DisplayName("Проверка полей формы")
    @Test
    public void testFormTextLabelInput() {
        String baseXpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/";
        String expSum = "Сумма";
        String expEmail = "E-mail для отправки чека";

        //Услуги связи
        page.click(baseXpath + "button/span[2]");
        page.click(baseXpath + "ul/li[1]");

        assertEquals("Номер телефона", page.getAttr("//*[@id='connection-phone']", "placeholder"));
        assertEquals(expSum, page.getAttr("//*[@id='connection-sum']", "placeholder"));
        assertEquals(expEmail, page.getAttr("//*[@id='connection-email']", "placeholder"));

        //Домашний интернет
        page.click(baseXpath + "button/span[2]");
        page.click(baseXpath + "ul/li[2]");

        assertEquals("Номер абонента", page.getAttr("//*[@id='internet-phone']", "placeholder"));
        assertEquals(expSum, page.getAttr("//*[@id='internet-sum']", "placeholder"));
        assertEquals(expEmail, page.getAttr("//*[@id='internet-email']", "placeholder"));

        //Рассрочка
        page.click(baseXpath + "button/span[2]");
        page.click(baseXpath + "ul/li[3]");

        assertEquals("Номер счета на 44", page.getAttr("//*[@id='score-instalment']", "placeholder"));
        assertEquals(expSum, page.getAttr("//*[@id='instalment-sum']", "placeholder"));
        assertEquals(expEmail, page.getAttr("//*[@id='instalment-email']", "placeholder"));

        //Задолженность
        page.click(baseXpath + "button/span[2]");
        page.click(baseXpath + "ul/li[4]");

        assertEquals("Номер счета на 2073", page.getAttr("//*[@id='score-arrears']", "placeholder"));
        assertEquals(expSum, page.getAttr("//*[@id='arrears-sum']", "placeholder"));
        assertEquals(expEmail, page.getAttr("//*[@id='arrears-email']", "placeholder"));
    }


}

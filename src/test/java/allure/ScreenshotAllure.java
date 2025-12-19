package allure;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotAllure {

    public static void takeScreenshotAllure(WebDriver driver, String name) {
        String imgType = "image/png";
        String fileExt = ".png";

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, imgType, new ByteArrayInputStream(screenshot), fileExt);
    }
}

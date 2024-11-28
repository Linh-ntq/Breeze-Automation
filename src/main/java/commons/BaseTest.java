package commons;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BaseTest {
    Duration timeoutExWait = Duration.ofSeconds(15);
    public static WebDriverWait waitDriverApp;
    public static AppiumDriver driver;
    public BaseTest(AppiumDriver driver){
        BaseTest.driver = driver;
        waitDriverApp = new WebDriverWait(driver, timeoutExWait);
    }
    public BaseTest(){

    }
}

package commons;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BaseTest {
    Duration timeoutExWait = Duration.ofSeconds(20);
    public long scrollTimeOut = 20000;
    public static WebDriverWait waitDriverApp;
    public static AndroidDriver driver;
    public static ClassDeclaration classDecl;

    public BaseTest(){

    }
}

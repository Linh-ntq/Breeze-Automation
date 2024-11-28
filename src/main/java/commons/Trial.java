package commons;

import datas.Datas;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Trial{
    static AppiumDriver driver;
    public static Datas datas;
    public static void main (String[] args) {
        datas = new Datas();
        openAppWithDeviceInfo(datas.deviceName, datas.udid, datas.platformName, datas.platformVersion, datas.automationName, datas.appPackage, datas.appActivity);

    }

        public static void openAppWithDeviceInfo(String deviceName, String udid, String platformName, String platformVersion, String automationName, String appPackage, String appActivity) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", deviceName);
        desiredCapabilities.setCapability("appium:udid", udid);
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("appium:platformVersion", platformVersion);
        desiredCapabilities.setCapability("appium:automationName", automationName);
        desiredCapabilities.setCapability("appium:appPackage", appPackage);
        desiredCapabilities.setCapability("appium:appActivity", appActivity);
        desiredCapabilities.setCapability("appium:autoGrantPermissions", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 300); //set timeout to avoid app close automatically if no actions are performed for a long period
        desiredCapabilities.setCapability("appium:appWaitDuration", 50000);
        System.out.println("Application started");
        try {
            URL url = new URL("http://127.0.0.1:4723/");
            driver = new AppiumDriver(url, desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println("Hiiii" + e.getCause());
        }
    }
}

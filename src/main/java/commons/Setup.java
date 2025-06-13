package commons;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Setup extends BaseTest{
    private AppiumDriverLocalService service;

    @BeforeClass
    public void initializeReportGeneration() {
        classDecl = new ClassDeclaration();
        classDecl.extentReport.initiateExtentReport();
    }

    @BeforeMethod
    public void openBreezeApp(){
        startAppiumServer(classDecl.datas.pathAppiumJS);
        openAppWithDeviceInfo(classDecl.datas.deviceName, classDecl.datas.udid, classDecl.datas.platformName, classDecl.datas.platformVersion, classDecl.datas.automationName, classDecl.datas.appPackage, classDecl.datas.appActivity);

    }

    public void startAppiumServer(String pathJS) {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(pathJS))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (service != null && service.isRunning()) {
                service.stop();
                System.out.println("Appium server stopped from shutdown hook.");
            }
        }));

        if (service.isRunning()) {
            System.out.println("Appium server started successfully.");
        } else {
            System.out.println("Failed to start Appium server.");
        }

    }

    public void stopAppiumServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium server stopped successfully.");
        } else {
            System.out.println("Appium server is not running or already stopped.");
        }

    }

    public AppiumDriver openAppWithDeviceInfo(String deviceName, String udid, String platformName, String platformVersion, String automationName, String appPackage, String appActivity) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", deviceName);
        desiredCapabilities.setCapability("appium:udid", udid);
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("appium:platformVersion", platformVersion);
        desiredCapabilities.setCapability("appium:automationName", automationName);
        desiredCapabilities.setCapability("appium:appPackage", appPackage);
        desiredCapabilities.setCapability("appium:appActivity", appActivity);
//        desiredCapabilities.setCapability("appium:autoGrantPermissions", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 300); //set timeout to avoid app close automatically if no actions are performed for a long period
        desiredCapabilities.setCapability("appium:appWaitDuration", 50000);
        System.out.println("Application started");
        try {
            URL url = new URL("http://127.0.0.1:4723/");
            driver = new AndroidDriver(url, desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(timeoutExWait);
            waitDriverApp = new WebDriverWait(driver, timeoutExWait);

            // grant access location
            classDecl.commonKeyword.clickElement("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]");
            // deny send location
            classDecl.commonKeyword.clickElement("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]");

        } catch (IOException e) {
            System.out.println("Exception Error Log: " + e.getMessage());
        }
        return driver;
    }

    @AfterMethod
    public void closeBreezeApp(){
        driver.quit();
        stopAppiumServer();
    }

    @AfterClass
    public void finalizeReportGeneration (){
        classDecl.extentReport.extentReports.flush();
    }
}

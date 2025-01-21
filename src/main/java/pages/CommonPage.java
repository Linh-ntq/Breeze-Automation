package pages;

import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;

public class CommonPage extends BaseTest {
    public String lblMenu = "//android.widget.TextView[@text=\"%s\"]";

    public CommonPage(AndroidDriver driver){
        super(driver);
    }

    // bottom menu/more menu/breeze setting menu/preferences/profile
    public void tabOnMenu(String menuName){
        classDecl.commonKeyword.clickElement(lblMenu, menuName);
    }
}

package pages;

import commons.BaseTest;

public class CommonPage extends BaseTest {
    public String lblMenu = "//android.widget.TextView[@text=\"%s\"]";

    // bottom menu/more menu/breeze setting menu/preferences/profile
    public void tabOnMenu(String menuName){
        classDecl.commonKeyword.clickElement(lblMenu, menuName);
    }
}

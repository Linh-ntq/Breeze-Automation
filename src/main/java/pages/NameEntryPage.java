package pages;

import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;

public class NameEntryPage extends BaseTest {
    public String txtBxName = "//android.widget.EditText";
    public String btnNext = "//android.widget.TextView[@text=\"Next\"]";

    public NameEntryPage(AndroidDriver driver){
        super(driver);
    }

    public void enterName(String username) {
        classDecl.commonKeyword.sendKey(txtBxName, username);
    }

    public void clickNextBtn() {
        classDecl.commonKeyword.clickElement(btnNext);
    }
}

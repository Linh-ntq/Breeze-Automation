package pages;

import commons.BaseTest;
import commons.CommonKeyword;
import io.appium.java_client.AppiumDriver;

public class NameEntryPage extends BaseTest {
    CommonKeyword commonKeyword = new CommonKeyword();
    public String txtBxName = "//android.widget.EditText";
    public String btnNext = "//android.widget.TextView[@text=\"Next\"]";

    public NameEntryPage(AppiumDriver driver){
        super(driver);
    }

    public void enterName(String username) {
        commonKeyword.sendKey(txtBxName, username);
    }

    public void clickNextBtn() {
        commonKeyword.clickElement(btnNext);
    }
}

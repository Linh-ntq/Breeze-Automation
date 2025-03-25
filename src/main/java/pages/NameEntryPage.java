package pages;

import commons.BaseTest;

public class NameEntryPage extends BaseTest {
    public String txtBxName = "//android.widget.EditText";
    public String btnNext = "//android.widget.TextView[@text=\"Next\"]";


    public void enterName(String username) {
        classDecl.commonKeyword.sendKey(txtBxName, username);
    }

    public void clickNextBtn() {
        classDecl.commonKeyword.clickElement(btnNext);
    }
}

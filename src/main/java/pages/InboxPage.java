package pages;

import commons.BaseTest;

public class InboxPage extends BaseTest {
    public String lblInbMsg = "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.TextView[@text=\"%s\"]";
    public String txtMobileNo = "//android.widget.TextView[@text=\"Date of birth\"]/preceding-sibling::android.widget.EditText";
    public String txtDateOfBirth = "//android.widget.TextView[@text=\"Date of birth\"]/following-sibling::android.view.ViewGroup/android.widget.TextView[not(contains(@text, 'Confirm'))]";
    public String txtNRIC = "//android.widget.TextView[@text=\"Date of birth\"]/following-sibling::android.widget.EditText";
    public String btnConfirm = "//android.widget.TextView[@text=\"Confirm\"]";

    public void tapOnInbMsg(String title, String desc){
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.commonKeyword.clickElement(lblInbMsg, title, desc);
    }

    public void enterMobileNo(String mobileNo){
        classDecl.commonKeyword.sendKey(txtMobileNo, mobileNo);
        classDecl.commonKeyword.tapOnVirtualDoneBtn();
    }

    public void enterNRIC(String nric){
        classDecl.commonKeyword.sendKey(txtNRIC, nric);
        classDecl.commonKeyword.tapOnVirtualDoneBtn();
    }

    public void tapOnConfirmBtn(){
        classDecl.commonKeyword.clickElement(btnConfirm);
    }

}

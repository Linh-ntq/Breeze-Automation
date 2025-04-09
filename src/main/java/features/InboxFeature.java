package features;

import commons.BaseTest;

public class InboxFeature extends BaseTest {
    public void enterNTUCDetails(String mobileNo, String NRIC){
        classDecl.extentReport.startTest("Enter NTUC details");
        classDecl.inboxPage.enterMobileNo(mobileNo);
        classDecl.commonKeyword.pause(20); //pause to select date of birth
        classDecl.inboxPage.enterNRIC(NRIC);
        classDecl.extentReport.attachScreenshotToReport("NTUC details");
        classDecl.inboxPage.tapOnConfirmBtn();
    }
}

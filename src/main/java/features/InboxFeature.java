package features;

import commons.BaseTest;

public class InboxFeature extends BaseTest {
    public void enterNTUCDetails(String mobileNo, String NRIC){
        classDecl.inboxPage.enterMobileNo(mobileNo);
        classDecl.commonKeyword.pause(20); //pause to select date of birth
        classDecl.inboxPage.enterNRIC(NRIC);
        classDecl.inboxPage.tapOnConfirmBtn();
    }
}

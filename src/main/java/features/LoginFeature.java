package features;

import commons.BaseTest;

public class LoginFeature extends BaseTest {

    public void swipeToLeftAndVerifyCardUI(){
        classDecl.onbCardPage.verifyFirstCard();
        classDecl.extentReport.attachScreenshotToReport("1st card");
        classDecl.onbCardPage.swipeToLeft();
        classDecl.onbCardPage.verify2ndCard();
        classDecl.extentReport.attachScreenshotToReport("2nd card");
        classDecl.onbCardPage.swipeToLeft();
        classDecl.onbCardPage.verify3rdCard();
        classDecl.extentReport.attachScreenshotToReport("3rd card");
        classDecl.onbCardPage.swipeToLeft();
        classDecl.onbCardPage.verify4thCard();
        classDecl.extentReport.attachScreenshotToReport("4th card");
        classDecl.onbCardPage.swipeToLeft();
    }

    public void swipeToRightAndVerifyCardUI(){
        classDecl.onbCardPage.swipeToRight();
        classDecl.onbCardPage.verify4thCard();
        classDecl.extentReport.attachScreenshotToReport("4th card");
        classDecl.onbCardPage.swipeToRight();
        classDecl.onbCardPage.verify3rdCard();
        classDecl.extentReport.attachScreenshotToReport("3rd card");
        classDecl.onbCardPage.swipeToRight();
        classDecl.onbCardPage.verify2ndCard();
        classDecl.extentReport.attachScreenshotToReport("2nd card");
        classDecl.onbCardPage.swipeToRight();
        classDecl.onbCardPage.verifyFirstCard();
        classDecl.extentReport.attachScreenshotToReport("1st card");

    }

    public void goToLoginPageBySkipOnboarding() {
        classDecl.onbCardPage.clickSkipBtn();
        verifyLoginPageUI();
    }

    public void verifyLoginPageUI(){
        classDecl.loginPage.verifyLoginTitle();
        classDecl.loginPage.verifyLoginOptions();
    }

    public void goToLandingPageByGuest(String username){
        classDecl.onbCardPage.clickSkipBtn();
        classDecl.loginPage.clickSkipForNowBtn();
        classDecl.nameEntryPage.enterName(username);
        classDecl.commonKeyword.closeKeyboard();
        classDecl.nameEntryPage.clickNextBtn();
        classDecl.vehicleSettingPage.clickSkipForNowBtn();
        classDecl.landingFeature.verifySearchBarText(username);
    }

    public void goToLandingPageByGG(String username){
        classDecl.onbCardPage.clickSkipBtn();
        classDecl.loginPage.clickLoginWithGGBtn();
        classDecl.nameEntryPage.enterName(username);
        classDecl.commonKeyword.closeKeyboard();
        classDecl.nameEntryPage.clickNextBtn();

    }

    public void goToOnbVehicleSetting(String username){
        classDecl.onbCardPage.clickSkipBtn();
        classDecl.loginPage.clickSkipForNowBtn();
        classDecl.nameEntryPage.enterName(username);
        classDecl.commonKeyword.closeKeyboard();
        classDecl.nameEntryPage.clickNextBtn();
    }

}

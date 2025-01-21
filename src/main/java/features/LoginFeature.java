package features;

import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;

public class LoginFeature extends BaseTest {

    public LoginFeature(AndroidDriver driver){
        super(driver);
    }

    public void swipeToLeftAndVerifyCardUI(){
        classDecl.onbCardPage.verifyFirstCard();
        classDecl.onbCardPage.swipeToLeft();
        classDecl.onbCardPage.verify2ndCard();
        classDecl.onbCardPage.swipeToLeft();
        classDecl.onbCardPage.verify3rdCard();
        classDecl.onbCardPage.swipeToLeft();
        classDecl.onbCardPage.verify4thCard();
        classDecl.onbCardPage.swipeToLeft();
    }

    public void swipeToRightAndVerifyCardUI(){
        classDecl.onbCardPage.swipeToRight();
        classDecl.onbCardPage.verify4thCard();
        classDecl.onbCardPage.swipeToRight();
        classDecl.onbCardPage.verify3rdCard();
        classDecl.onbCardPage.swipeToRight();
        classDecl.onbCardPage.verify2ndCard();
        classDecl.onbCardPage.swipeToRight();
        classDecl.onbCardPage.verifyFirstCard();

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
        classDecl.onbVehiclePage.clickSkipForNowBtn();
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

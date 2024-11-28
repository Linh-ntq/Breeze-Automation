package features;

import commons.BaseTest;
import commons.CommonKeyword;
import io.appium.java_client.AppiumDriver;
import pages.LoginPage;
import pages.NameEntryPage;
import pages.OnbCardPage;
import pages.OnbVehiclePage;

public class LoginFeature extends BaseTest {
    OnbCardPage onbCardPage;
    LoginPage loginPage;
    NameEntryPage nameEntryPage;
    CommonKeyword commonKeyword;
    OnbVehiclePage onbVehiclePage;
    public LoginFeature(AppiumDriver driver){
        super(driver);
        onbCardPage = new OnbCardPage(driver);
        loginPage = new LoginPage(driver);
        nameEntryPage = new NameEntryPage(driver);
        commonKeyword = new CommonKeyword();
        onbVehiclePage = new OnbVehiclePage(driver);
    }

    public void swipeToLeftAndVerifyCardUI(){
        onbCardPage.verifyFirstCard();
        onbCardPage.swipeToLeft();
        onbCardPage.verify2ndCard();
        onbCardPage.swipeToLeft();
        onbCardPage.verify3rdCard();
        onbCardPage.swipeToLeft();
        onbCardPage.verify4thCard();
        onbCardPage.swipeToLeft();
    }

    public void swipeToRightAndVerifyCardUI(){
        onbCardPage.swipeToRight();
        onbCardPage.verify4thCard();
        onbCardPage.swipeToRight();
        onbCardPage.verify3rdCard();
        onbCardPage.swipeToRight();
        onbCardPage.verify2ndCard();
        onbCardPage.swipeToRight();
        onbCardPage.verifyFirstCard();

    }

    public void goToLoginPageBySkipOnboarding() {
        onbCardPage.clickSkipBtn();
        verifyLoginPageUI();
    }

    public void verifyLoginPageUI(){
        loginPage.verifyLoginTitle();
        loginPage.verifyLoginOptions();
    }

    public void goToLandingPageByGuest(String username){
        onbCardPage.clickSkipBtn();
        loginPage.clickSkipForNowBtn();
        nameEntryPage.enterName(username);
        commonKeyword.closeKeyboard();
        nameEntryPage.clickNextBtn();
        onbVehiclePage.clickSkipForNowBtn();
    }

    public void goToLandingPageByGG(String username){
        onbCardPage.clickSkipBtn();
        loginPage.clickLoginWithGGBtn();
        nameEntryPage.enterName(username);
        commonKeyword.closeKeyboard();
        nameEntryPage.clickNextBtn();
//        onbVehiclePage.clickSkipForNowBtn();

    }
}

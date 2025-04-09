package tests;

import commons.Setup;
import org.testng.annotations.Test;


public class LoginTest extends Setup {

    @Test(priority = 0)
    public void swipe_onboarding_card_and_verify_UI(){
        openBreezeApp();
        classDecl.extentReport.startTest("Swipe to left and verify onboarding card");
        classDecl.loginFeature.swipeToLeftAndVerifyCardUI();
        classDecl.extentReport.startTest("Verify Login page");
        classDecl.loginFeature.verifyLoginPageUI();
        classDecl.extentReport.attachScreenshotToReport("Login screen");
        classDecl.extentReport.startTest("Swipe to right and verify onboarding card");
        classDecl.loginFeature.swipeToRightAndVerifyCardUI();
        closeBreezeApp();
    }

    @Test(priority = 1)
    public void skip_onboarding_and_go_to_login_page() {
        openBreezeApp();
        classDecl.extentReport.startTest("Skip onboarding card and go to Login page");
        classDecl.loginFeature.goToLoginPageBySkipOnboarding();
        classDecl.extentReport.attachScreenshotToReport("Login screen");
        closeBreezeApp();
    }

    @Test(priority = 2)
    public void login_to_breeze_app_by_guest(){
        openBreezeApp();
        classDecl.extentReport.startTest("Go to Landing screen by guest");
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        classDecl.extentReport.attachScreenshotToReport("Landing screen");
        closeBreezeApp();
    }

    @Test(priority = 3)
    public void login_to_breeze_app_by_existing_GG_account(){
        openBreezeApp();
        classDecl.extentReport.startTest("Go to Landing screen by google account");
        classDecl.loginFeature.goToLandingPageByGG("GG");
        classDecl.extentReport.attachScreenshotToReport("Landing screen");
        closeBreezeApp();

    }
}

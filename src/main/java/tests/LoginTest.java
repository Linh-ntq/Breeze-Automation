package tests;

import commons.Setup;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends Setup {

    @Test(priority = 0)
    public void swipe_onboarding_card_and_verify_UI(){
        startTest("Hello 1");
        classDecl.loginFeature.swipeToLeftAndVerifyCardUI();
        startTest("Hello 2");
        classDecl.loginFeature.verifyLoginPageUI();
        startTest("Hello 3");
        classDecl.loginFeature.swipeToRightAndVerifyCardUI();
        startTest("Hello 4");
    }

    @Test(priority = 1)
    public void skip_onboarding_and_go_to_login_page() {
        startTest("Skip onboarding card and go to Login page");
        classDecl.loginFeature.goToLoginPageBySkipOnboarding();
        attachScreenshotToReport("Login page");
    }

    @Test(priority = 2)
    public void login_to_breeze_app_by_guest(){
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
    }

    @Test(priority = 3)
    public void login_to_breeze_app_by_existing_GG_account(){
        classDecl.loginFeature.goToLandingPageByGG("GG");

    }
}

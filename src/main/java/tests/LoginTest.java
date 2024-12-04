package tests;

import commons.Setup;
import org.testng.annotations.Test;

public class LoginTest extends Setup {

    @Test(priority = 0)
    public void swipe_onboarding_card_and_verify_UI(){
        loginFeature.swipeToLeftAndVerifyCardUI();
        loginFeature.verifyLoginPageUI();
        loginFeature.swipeToRightAndVerifyCardUI();
    }

    @Test(priority = 1)
    public void skip_onboarding_and_go_to_login_page(){
        loginFeature.goToLoginPageBySkipOnboarding();
    }

    @Test(priority = 2)
    public void login_to_breeze_app_by_guest(){
        loginFeature.goToLandingPageByGuest("Guest");
    }

    @Test(priority = 3)
    public void login_to_breeze_app_by_existing_GG_account(){
        loginFeature.goToLandingPageByGG("GG");

    }
}

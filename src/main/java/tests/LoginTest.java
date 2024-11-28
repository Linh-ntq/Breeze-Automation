package tests;

import commons.Setup;
import org.testng.annotations.Test;

public class LoginTest extends Setup {

    @Test(priority = 0)
    public void swipeCardAndVerifyUI(){
        loginFeature.swipeToLeftAndVerifyCardUI();
        loginFeature.verifyLoginPageUI();
        loginFeature.swipeToRightAndVerifyCardUI();
    }

    @Test(priority = 1)
    public void skipOnboardingAndGoToLoginPage(){
        loginFeature.goToLoginPageBySkipOnboarding();
    }

    @Test(priority = 2)
    public void loginToBreezeAppByGuest(){
        loginFeature.goToLandingPageByGuest("Guest");
    }

    @Test(priority = 3)
    public void loginToBreezeAppByGGAccount(){
        loginFeature.goToLandingPageByGG("GG");
    }
}

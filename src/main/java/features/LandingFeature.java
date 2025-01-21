package features;

import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;

public class LandingFeature extends BaseTest {

    public LandingFeature(AndroidDriver driver){
        super(driver);

    }

    public void verifySearchBarText(String userName){
        classDecl.landingPage.verifySearchBarText(userName);
    }

    public void verifyFocusedTab(String headerTab){
        classDecl.landingPage.expandBottomSheet();
        classDecl.landingPage.verifyFocusedTab(headerTab);
    }
}

package pages;

import commons.BaseTest;
import commons.CommonKeyword;
import io.appium.java_client.AppiumDriver;

public class LoginPage extends BaseTest {
    CommonKeyword commonKeyword = new CommonKeyword();
    public String lblTitle = "//android.widget.TextView[normalize-space(@text)='Enjoy smoother, safer journeys with']";
    public String btnLoginWithGG = "//android.widget.TextView[@text=\"Continue with Google\"]";
    public String btnSkipForNow = "//android.widget.TextView[@text=\"Skip for now\"]";

    public LoginPage(AppiumDriver driver){
        super(driver);
    }

    public void verifyLoginTitle(){
        commonKeyword.waitForElementVisible(lblTitle);
    }

    public void verifyLoginOptions(){
        commonKeyword.waitForElementVisible(btnLoginWithGG);
        commonKeyword.waitForElementVisible(btnSkipForNow);
    }

    public void clickLoginWithGGBtn() {
        commonKeyword.clickElement(btnLoginWithGG);

    }

    public void clickSkipForNowBtn() {
        commonKeyword.clickElement(btnSkipForNow);

    }

}

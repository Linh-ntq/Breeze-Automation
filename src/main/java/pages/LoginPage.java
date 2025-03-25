package pages;

import commons.BaseTest;

public class LoginPage extends BaseTest {
    public String lblTitle = "//android.widget.TextView[normalize-space(@text)='Enjoy smoother, safer journeys with']";
    public String btnLoginWithGG = "//android.widget.TextView[@text=\"Continue with Google\"]";
    public String btnSkipForNow = "//android.widget.TextView[@text=\"Skip for now\"]";


    public void verifyLoginTitle(){
        classDecl.commonKeyword.waitForElementVisible(lblTitle);
    }

    public void verifyLoginOptions(){
        classDecl.commonKeyword.waitForElementVisible(btnLoginWithGG);
        classDecl.commonKeyword.waitForElementVisible(btnSkipForNow);
    }

    public void clickLoginWithGGBtn() {
        classDecl.commonKeyword.clickElement(btnLoginWithGG);

    }

    public void clickSkipForNowBtn() {
        classDecl.commonKeyword.clickElement(btnSkipForNow);

    }

}

package pages;

import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;

public class OnbCardPage extends BaseTest {
    public String btnSkip = "//android.widget.TextView[@text=\"Skip \"]";
    public String lblTitleCard1 = "//android.widget.TextView[normalize-space(@text)='Real-time carpark availability']";
    public String lblTitleCard2 = "//android.widget.TextView[normalize-space(@text)='Live ERP and traffic updates']";
    public String lblTitleCard3 = "//android.widget.TextView[normalize-space(@text)='Pair with ERP2.0 On-Board Unit (OBU)']";
    public String lblTitleCard4 = "//android.widget.TextView[normalize-space(@text)='OBU notifications on mobile and in-car display']";

    public OnbCardPage(AndroidDriver driver){
        super(driver);
    }

    public void verifyFirstCard(){
        classDecl.commonKeyword.waitForElementVisible(lblTitleCard1);
    }

    public void verify2ndCard(){
        classDecl.commonKeyword.waitForElementVisible(lblTitleCard2);
    }

    public void verify3rdCard(){
        classDecl.commonKeyword.waitForElementVisible(lblTitleCard3);
    }

    public void verify4thCard(){
        classDecl.commonKeyword.waitForElementVisible(lblTitleCard4);
    }

    public void clickSkipBtn() {
        classDecl.commonKeyword.clickElement(btnSkip);

    }

    public void swipeToLeft() {
        classDecl.commonKeyword.scroll("LEFT", 0.5);
    }

    public void swipeToRight() {
        classDecl.commonKeyword.scroll("RIGHT", 0.5);
    }
}

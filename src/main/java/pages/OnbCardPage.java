package pages;

import commons.BaseTest;
import commons.CommonKeyword;
import io.appium.java_client.AppiumDriver;

public class OnbCardPage extends BaseTest {
    CommonKeyword commonKeyword = new CommonKeyword();
    public String btnSkip = "//android.widget.TextView[@text=\"Skip \"]";
    public String lblTitleCard1 = "//android.widget.TextView[normalize-space(@text)='Real-time carpark availability']";
    public String lblTitleCard2 = "//android.widget.TextView[normalize-space(@text)='Live ERP and traffic updates']";
    public String lblTitleCard3 = "//android.widget.TextView[normalize-space(@text)='Pair with ERP2.0 On-Board Unit (OBU)']";
    public String lblTitleCard4 = "//android.widget.TextView[normalize-space(@text)='OBU notifications on mobile and in-car display']";

    public OnbCardPage(AppiumDriver driver){
        super(driver);
    }

    public void verifyFirstCard(){
        commonKeyword.waitForElementVisible(lblTitleCard1);
        commonKeyword.clickElement(lblTitleCard1);
    }

    public void verify2ndCard(){
        commonKeyword.waitForElementVisible(lblTitleCard2);
        commonKeyword.clickElement(lblTitleCard2);
    }

    public void verify3rdCard(){
        commonKeyword.waitForElementVisible(lblTitleCard3);
        commonKeyword.clickElement(lblTitleCard3);
    }

    public void verify4thCard(){
        commonKeyword.waitForElementVisible(lblTitleCard4);
        commonKeyword.clickElement(lblTitleCard4);
    }

    public void clickSkipBtn() {
        commonKeyword.clickElement(btnSkip);

    }

    public void swipeToLeft() {
        commonKeyword.scroll("LEFT", 0.5);
    }

    public void swipeToRight() {
        commonKeyword.scroll("RIGHT", 0.5);
    }
}

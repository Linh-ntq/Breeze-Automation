package pages;

import commons.BaseTest;
import commons.CommonKeyword;
import io.appium.java_client.AppiumDriver;

public class OnbVehiclePage extends BaseTest {
    CommonKeyword commonKeyword = new CommonKeyword();
    public String btnSkipForNow = "//android.widget.TextView[@text=\"Skip for now\"]";

    public OnbVehiclePage(AppiumDriver driver){
        super(driver);
    }

    public void clickSkipForNowBtn() {
        commonKeyword.clickElement(btnSkipForNow);

    }
}

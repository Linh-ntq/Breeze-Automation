package pages;

import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import java.util.List;

public class LandingPage extends BaseTest {
    public String imgOrangeForce = "//android.widget.HorizontalScrollView//android.view.ViewGroup[1]/android.widget.ImageView";
    public String lblParkingCal = "//android.widget.TextView[@text=\"Parking Calculator\"]";
    public String lblOBUOnBreeze = "//android.widget.TextView[@text=\"OBU On Breeze\"]";
    public String lblERPChecker = "//android.widget.TextView[@text=\"ERP Checker\"]";
    public String lblLiveTraffic = "//android.widget.TextView[@text=\"Live Traffic\"]";
    public String btnHeaderArrow = "//android.widget.HorizontalScrollView/following-sibling::android.view.ViewGroup/android.widget.ImageView";
    public String btnCloseDialogue = "//android.widget.ImageView[@resource-id=\"com.ncs.breeze.demo:id/imgCloseDialog\"]";
    public String lblSearchBar = "//android.widget.TextView[@text=\"%s\"]";
    public String lstHeaderTab = "//android.widget.HorizontalScrollView//android.widget.TextView";
    public String seekBar = "//android.widget.SeekBar[contains(@content-desc, 'Bottom Sheet handle')]";
    public String lblOrangeForceTxt = "//android.widget.TextView[@text=\"Accident?\nBreeze can help.\"]";
    public String lblParkingCalTxt = "//android.widget.TextView[contains(@text,\"Start Parking\")]";
    public String lblOBUTxt = "//android.widget.TextView[contains(@text,\"Pair your OBU with Breeze\")]";
    public String lblERPCheckerTxt = "//android.widget.TextView[contains(@text,\"Check ERP along your route\")]";
    public String lblLiveTrafficTxt = "//android.widget.TextView[@text=\"CTE\"]";

    public LandingPage(AndroidDriver driver){
        super(driver);
    }

    public void verifyHeaderList(List<String> expectedHeaderOrder){
        List<String> actualHeaderOrder = classDecl.commonKeyword.getListText(lstHeaderTab);
        Assert.assertEquals(actualHeaderOrder, expectedHeaderOrder);
    }

    public void closeInAppAlerts(){
        classDecl.commonKeyword.clickElement(btnCloseDialogue);
    }

    public void verifySearchBarText(String userName){
        String formattedText = String.format("Hi %s, where to?", userName);
        classDecl.commonKeyword.waitForElementVisible(lblSearchBar, formattedText);
    }

    public void expandBottomSheet(){
        classDecl.commonKeyword.scroll("DOWN", 0.5);
    }

    public void tabOnNextTabBtn() {
        classDecl.commonKeyword.clickElement(btnHeaderArrow);
    }

    public void tapOnHeader(String headerTab){
        if (headerTab == classDecl.datas.incomeOrangeForce) {
            classDecl.commonKeyword.clickElement(imgOrangeForce);
        } else if (headerTab == classDecl.datas.parkingCalculator) {
            classDecl.commonKeyword.clickElement(lblParkingCal);
        } else if (headerTab == classDecl.datas.obuOnBreeze) {
            classDecl.commonKeyword.clickElement(lblOBUOnBreeze);
        } else if (headerTab == classDecl.datas.erpChecker) {
            classDecl.commonKeyword.clickElement(lblERPChecker);
        } else if (headerTab == classDecl.datas.liveTraffic) {
            classDecl.commonKeyword.clickElement(lblLiveTraffic);
        } else {
            classDecl.commonKeyword.clickElement(imgOrangeForce);
            System.out.println("Focus on the first tab");
        }
    }

    public void verifyFocusedTab(String headerTab){
        if (headerTab == classDecl.datas.incomeOrangeForce) {
            classDecl.commonKeyword.waitForElementVisible(lblOrangeForceTxt);
        } else if (headerTab == classDecl.datas.parkingCalculator) {
            classDecl.commonKeyword.waitForElementVisible(lblParkingCalTxt);
        } else if (headerTab == classDecl.datas.obuOnBreeze) {
            classDecl.commonKeyword.waitForElementVisible(lblOBUTxt);
        } else if (headerTab == classDecl.datas.erpChecker) {
            classDecl.commonKeyword.waitForElementVisible(lblERPCheckerTxt);
        } else if (headerTab == classDecl.datas.liveTraffic) {
            classDecl.commonKeyword.waitForElementVisible(lblLiveTrafficTxt);
        } else {
            classDecl.commonKeyword.waitForElementVisible(lblOrangeForceTxt);
            System.out.println("Focus on the first tab");
        }
    }

}






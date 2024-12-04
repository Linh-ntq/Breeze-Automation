package features;

import commons.BaseTest;
import commons.CommonKeyword;
import io.appium.java_client.AppiumDriver;
import pages.LoginPage;
import pages.NameEntryPage;
import pages.OnbCardPage;
import pages.OnbVehiclePage;

public class VehicleSettingFeature extends BaseTest {
    OnbCardPage onbCardPage;
    LoginPage loginPage;
    NameEntryPage nameEntryPage;
    CommonKeyword commonKeyword;
    OnbVehiclePage onbVehiclePage;
    public VehicleSettingFeature(AppiumDriver driver){
        super(driver);
        onbCardPage = new OnbCardPage(driver);
        loginPage = new LoginPage(driver);
        nameEntryPage = new NameEntryPage(driver);
        commonKeyword = new CommonKeyword();
        onbVehiclePage = new OnbVehiclePage(driver);
    }

    public void verifyScreenDescription(){
        onbVehiclePage.verifyScreenTitle();
        onbVehiclePage.verifyScreenDescription();
    }

    public void verifyOnbVehicleDefaultValue(){
        onbVehiclePage.verifyVehicleTypeField("Car");
        onbVehiclePage.verifyVehicleLicencePlateNoField("empty");
        onbVehiclePage.verifyVehicleBrandField("empty");
        onbVehiclePage.verifyOBUInstallationField();
        onbVehiclePage.verifyIUNumberField("empty");
        onbVehiclePage.verifyGuidanceField("collapse");
        onbVehiclePage.verifyEnergyTypeField();
    }

    public void verifyGuidanceDescription(){
        onbVehiclePage.clickOnGuidanceArrowBtn();
        onbVehiclePage.verifyGuidanceField("expand");
        onbVehiclePage.clickOnGuidanceArrowBtn();
        onbVehiclePage.verifyGuidanceField("collapse");
    }

    public void verifySaveButtonIsDisabled(){
        onbVehiclePage.clickSaveBtn();
        onbVehiclePage.verifyVehicleDetailDialog("not visible");
    }

    public void verifySaveButtonIsEnabled(){
        onbVehiclePage.clickSaveBtn();
        onbVehiclePage.verifyVehicleDetailDialog("visible");
    }

    public void verifySkipButtonIsEnabled(){
        onbVehiclePage.clickSkipForNowBtn();
        //verify Landing
    }
}

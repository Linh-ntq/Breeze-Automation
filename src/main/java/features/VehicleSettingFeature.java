package features;

import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;


public class VehicleSettingFeature extends BaseTest {

    public VehicleSettingFeature(AndroidDriver driver){
        super(driver);
    }

    public void verifyScreenDescription(){
        classDecl.onbVehiclePage.verifyScreenTitle();
        classDecl.onbVehiclePage.verifyScreenDescription();
    }

    public void verifyOnbVehicleValue(String vehicleType, String vehicleNo, String vehicleBrand, String uiNumber){
        classDecl.onbVehiclePage.verifyVehicleTypeField(vehicleType);
        classDecl.onbVehiclePage.verifyVehicleLicencePlateNoField(vehicleNo);
        classDecl.onbVehiclePage.verifyVehicleBrandField(vehicleBrand);
        classDecl.onbVehiclePage.verifyOBUInstallationField();
        classDecl.onbVehiclePage.verifyIUNumberField(uiNumber);
        classDecl.onbVehiclePage.verifyEnergyTypeField();
    }

    public void verifyGuidanceDescription(){
        classDecl.onbVehiclePage.clickOnGuidanceArrowBtn();
        classDecl.onbVehiclePage.verifyGuidanceField("expand");
        classDecl.onbVehiclePage.clickOnGuidanceArrowBtn();
        classDecl.onbVehiclePage.verifyGuidanceField("collapse");
    }

    public void verifySaveButtonStatus(String status){
        classDecl.onbVehiclePage.clickSaveBtn();
        if (status.equals("not visible")) {
            classDecl.onbVehiclePage.verifyVehicleDetailDialog("not visible");
        } else {
            classDecl.onbVehiclePage.verifyVehicleDetailDialog("visible");
        }
    }

    public void confirmVehicleDetail(){
        classDecl.onbVehiclePage.clickSaveBtn();
        classDecl.onbVehiclePage.clickConfirmBtn();
    }

    public void verifySkipButtonIsEnabled(){
        classDecl.onbVehiclePage.clickSkipForNowBtn();
    }

    public void verifySkipButtonIsNotVisible(){
        classDecl.commonKeyword.elementNotVisible(classDecl.onbVehiclePage.btnSkipForNow);
    }

    public void inputVehicleInfo(String vehicleType, String vehicleNo, String vehicleBrand, String installOBU, String uiNumber, String energyType){
        if (vehicleType != null && !vehicleType.isEmpty()){
            classDecl.onbVehiclePage.selectVehicleType(vehicleType);
        }

        if (vehicleNo != null && !vehicleNo.isEmpty()){
            classDecl.onbVehiclePage.enterVehicleLicenceNo(vehicleNo);
            classDecl.commonKeyword.closeKeyboard();
        }

        if (vehicleBrand != null && !vehicleBrand.isEmpty()){
            classDecl.onbVehiclePage.selectVehicleBrand(vehicleBrand);
            classDecl.commonKeyword.closeKeyboard();
        }

        if (installOBU != null && !installOBU.isEmpty()){
            classDecl.onbVehiclePage.selectOBUOpt(installOBU);
        }

        if (uiNumber != null && !uiNumber.isEmpty()){
            classDecl.onbVehiclePage.enterIUNumber(uiNumber);
            classDecl.commonKeyword.closeKeyboard();
        }

        if (energyType != null && !energyType.isEmpty()){
            classDecl.onbVehiclePage.selectEnergyOpt(energyType);
        }
    }

    public void goToVehicleSetting(){
        classDecl.commonPage.tabOnMenu("More");
        classDecl.commonPage.tabOnMenu("Breeze\nSettings");
        classDecl.commonPage.tabOnMenu("Profile");
        classDecl.commonPage.tabOnMenu("Vehicle Profile");
    }

}

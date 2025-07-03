package features;

import commons.BaseTest;

public class VehicleSettingFeature extends BaseTest {

    public void verifyScreenDescription(){
        classDecl.vehicleSettingPage.verifyScreenTitle();
        classDecl.vehicleSettingPage.verifyScreenDescription();
    }

    public void verifyVehicleValue(String vehicleType, String vehicleNo, String vehicleBrand, String uiNumber){
        classDecl.vehicleSettingPage.verifyVehicleTypeField(vehicleType);
        classDecl.vehicleSettingPage.verifyVehicleLicencePlateNoField(vehicleNo);
        classDecl.vehicleSettingPage.verifyVehicleBrandField(vehicleBrand);
        classDecl.vehicleSettingPage.verifyOBUInstallationField();
        classDecl.vehicleSettingPage.verifyIUNumberField(uiNumber);
        classDecl.vehicleSettingPage.verifyEnergyTypeField();
    }

    public void verifyGuidanceDescription(){
        classDecl.vehicleSettingPage.clickOnGuidanceArrowBtn();
        classDecl.vehicleSettingPage.verifyGuidanceField("expand");
        classDecl.vehicleSettingPage.clickOnGuidanceArrowBtn();
        classDecl.vehicleSettingPage.verifyGuidanceField("collapse");
    }

    public void verifySaveButtonStatus(String status){
        classDecl.vehicleSettingPage.clickSaveBtn();
        if (status.equals("not visible")) {
            classDecl.vehicleSettingPage.verifyVehicleDetailDialog("not visible");
        } else {
            classDecl.vehicleSettingPage.verifyVehicleDetailDialog("visible");
        }
    }

    public void confirmVehicleDetail(){
        classDecl.vehicleSettingPage.clickSaveBtn();
        classDecl.vehicleSettingPage.clickConfirmBtn();
    }

    public void verifySkipButtonIsEnabled(){
        classDecl.vehicleSettingPage.clickSkipForNowBtn();
    }

    public void verifySkipButtonIsNotVisible(){
        classDecl.commonKeyword.verifyElementNotVisible(classDecl.vehicleSettingPage.btnSkipForNow);
    }

    public void inputVehicleInfo(String vehicleType, String vehicleNo, String vehicleBrand, String installOBU, String uiNumber, String energyType){
        if (vehicleType != null && !vehicleType.isEmpty()){
            classDecl.vehicleSettingPage.selectVehicleType(vehicleType);
        }

        if (vehicleNo != null && !vehicleNo.isEmpty()){
            classDecl.vehicleSettingPage.enterVehicleLicenceNo(vehicleNo);
            classDecl.commonKeyword.closeKeyboard();
        }

        if (vehicleBrand != null && !vehicleBrand.isEmpty()){
            classDecl.vehicleSettingPage.enterVehicleBrand(vehicleBrand);
            classDecl.commonKeyword.closeKeyboard();
        }

        if (installOBU != null && !installOBU.isEmpty()){
            classDecl.vehicleSettingPage.selectOBUOpt(installOBU);
        }

        if (uiNumber != null && !uiNumber.isEmpty()){
            classDecl.vehicleSettingPage.enterIUNumber(uiNumber);
            classDecl.commonKeyword.closeKeyboard();
        }

        if (energyType != null && !energyType.isEmpty()){
            classDecl.vehicleSettingPage.selectEnergyOpt(energyType);
        }
    }

    public void goToVehicleSetting(){
        classDecl.commonPage.tabOnMenu("More");
        classDecl.commonPage.tabOnMenu("Breeze\nSettings");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.commonPage.tabOnMenu("Profile");
        classDecl.commonPage.tabOnMenu("Vehicle Profile");
    }

}

package tests;

import commons.Setup;
import org.testng.annotations.Test;

public class VehicleSettingTest extends Setup {

    @Test()
    public void verify_vehicle_default_value_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.verifyScreenDescription();
        classDecl.vehicleSettingFeature.verifyOnbVehicleValue(
                "Car",
                "empty",
                "empty",
                "empty"
        );
        classDecl.vehicleSettingFeature.verifyGuidanceDescription();
        classDecl.vehicleSettingFeature.verifySaveButtonStatus("not visible");
        classDecl.vehicleSettingFeature.verifySkipButtonIsEnabled();
        classDecl.landingFeature.verifySearchBarText("Guest");
    }

    @Test()
    public void verify_orange_force_tab_is_focused_after_entering_insure_income_number_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                "LLU8471T",
                "ADIVA",
                "No",
                "0123456780",
                "EV");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.landingFeature.verifySearchBarText("Guest");
        classDecl.landingFeature.verifyFocusedTab(classDecl.datas.incomeOrangeForce);
    }

    // Bug: BREEZE2-5391
    @Test()
    public void verify_OBU_On_Breeze_tab_is_focused_after_select_Yes_option_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                "LLU8471t",
                "KIA",
                "Yes",
                "0123456780",
                "EV");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.landingFeature.verifySearchBarText("Guest");
        classDecl.landingFeature.verifyFocusedTab(classDecl.datas.obuOnBreeze);
    }

    @Test()
    public void verify_Parking_Calculator_tab_is_focused_as_default_when_inputting_detail_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                "LLU8471t",
                "MAZDA",
                "No",
                "0123456780",
                "Petrol/Diesel");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.landingFeature.verifySearchBarText("Guest");
        classDecl.landingFeature.verifyFocusedTab(classDecl.datas.parkingCalculator);
    }

    @Test()
    public void verify_Parking_Calculator_tab_is_focused_as_default_when_not_inputting_detail_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.onbVehiclePage.clickSkipForNowBtn();
        classDecl.landingFeature.verifySearchBarText("Guest");
        classDecl.landingFeature.verifyFocusedTab(classDecl.datas.parkingCalculator);
    }

    //Fail in IU number step due to element not visible - will ask dev help to add this
    @Test()
    public void verify_vehicle_default_value_in_Breeze_setting(){
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        classDecl.vehicleSettingFeature.goToVehicleSetting();
        classDecl.vehicleSettingFeature.verifyScreenDescription();
        classDecl.vehicleSettingFeature.verifyOnbVehicleValue(
                "Car",
                "empty",
                "empty",
                "empty"
        );
        classDecl.vehicleSettingFeature.verifyGuidanceDescription();
        classDecl.vehicleSettingFeature.verifySaveButtonStatus("not visible");
        classDecl.vehicleSettingFeature.verifySkipButtonIsNotVisible();
    }

    //Fail in IU number step due to element not visible - will ask dev help to add this
    @Test()
    public void verify_vehicle_detail_in_Breeze_setting_will_be_displayed_same_with_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                "Sba1234g",
                "MAZDA",
                "No",
                "0123458067",
                "Petrol/Diesel");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.vehicleSettingFeature.goToVehicleSetting();
        classDecl.vehicleSettingFeature.verifyOnbVehicleValue(
                "Car",
                "Sba1234g",
                "MAZDA",
                "0123458067"
        );
        classDecl.vehicleSettingFeature.verifySaveButtonStatus("not visible");
        classDecl.vehicleSettingFeature.verifySkipButtonIsNotVisible();

    }

}

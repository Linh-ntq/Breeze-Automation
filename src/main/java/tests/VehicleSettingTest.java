package tests;

import commons.Setup;
import org.testng.annotations.Test;

public class VehicleSettingTest extends Setup {

    // Fail in step verify guidance description
    @Test(priority = 0)
    public void verify_vehicle_default_value_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.verifyScreenDescription();
        classDecl.vehicleSettingFeature.verifyOnbVehicleValue(
                "Car",
                "",
                "",
                ""
        );
        classDecl.vehicleSettingFeature.verifyGuidanceDescription();
        classDecl.vehicleSettingFeature.verifySaveButtonStatus("not visible");
        classDecl.vehicleSettingFeature.verifySkipButtonIsEnabled();
        classDecl.landingFeature.verifySearchBarText("Guest");
    }

    @Test(priority = 1)
    public void verify_orange_force_tab_is_focused_after_entering_income_insured_number_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                classDecl.datas.incomeInsuredNo,
                classDecl.datas.vehicleBrand,
                "No",
                classDecl.datas.UINumber,
                "EV");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.landingFeature.verifySearchBarText("Guest");
        classDecl.landingFeature.verifyFocusedTab(classDecl.datas.incomeOrangeForce);
    }

    @Test(priority = 2)
    public void verify_OBU_On_Breeze_tab_is_focused_after_selecting_Yes_option_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                classDecl.datas.incomeInsuredNo,
                classDecl.datas.vehicleBrand,
                "Yes",
                classDecl.datas.UINumber,
                "EV");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.landingFeature.verifySearchBarText("Guest");
        classDecl.landingFeature.verifyFocusedTab(classDecl.datas.obuOnBreeze);
    }

    @Test(priority = 3)
    public void verify_Parking_Calculator_tab_is_focused_as_default_when_inputting_detail_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                classDecl.datas.nonIncomeInsuredNo,
                classDecl.datas.vehicleBrand,
                "No",
                classDecl.datas.UINumber,
                "Petrol/Diesel");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.landingFeature.verifySearchBarText("Guest");
        classDecl.landingFeature.verifyFocusedTab(classDecl.datas.parkingCalculator);
    }

    @Test(priority = 4)
    public void verify_Parking_Calculator_tab_is_focused_as_default_when_not_inputting_detail_in_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingPage.clickSkipForNowBtn();
        classDecl.landingFeature.verifySearchBarText("Guest");
        classDecl.landingFeature.verifyFocusedTab(classDecl.datas.parkingCalculator);
    }

    //Fail in IU number step due to element not visible - will ask dev help to add this
    @Test(priority = 5)
    public void verify_vehicle_default_value_in_Breeze_setting(){
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        classDecl.vehicleSettingFeature.goToVehicleSetting();
        classDecl.vehicleSettingFeature.verifyScreenDescription();
        classDecl.vehicleSettingFeature.verifyOnbVehicleValue(
                "Car",
                "",
                "",
                ""
        );
        classDecl.vehicleSettingFeature.verifyGuidanceDescription();
        classDecl.vehicleSettingFeature.verifySaveButtonStatus("not visible");
        classDecl.vehicleSettingFeature.verifySkipButtonIsNotVisible();
    }

    //Fail in IU number step due to element not visible - will ask dev help to add this
    @Test(priority = 6)
    public void verify_vehicle_detail_in_Breeze_setting_will_be_displayed_same_with_onboarding(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                classDecl.datas.nonIncomeInsuredNo,
                classDecl.datas.vehicleBrand,
                "No",
                classDecl.datas.UINumber,
                "Petrol/Diesel");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.vehicleSettingFeature.goToVehicleSetting();
        classDecl.vehicleSettingFeature.verifyOnbVehicleValue(
                "Car",
                classDecl.datas.nonIncomeInsuredNo,
                classDecl.datas.vehicleBrand,
                classDecl.datas.UINumber
        );
        classDecl.vehicleSettingFeature.verifySaveButtonStatus("not visible");
        classDecl.vehicleSettingFeature.verifySkipButtonIsNotVisible();

    }

    //Fail in IU number step due to element not visible - will ask dev help to add this
    @Test(priority = 7)
    public void verify_orange_force_tab_is_focused_after_updating_income_insured_number_in_setting(){
        classDecl.loginFeature.goToOnbVehicleSetting("Guest");
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                classDecl.datas.nonIncomeInsuredNo,
                classDecl.datas.vehicleBrand,
                "No",
                classDecl.datas.UINumber,
                "Petrol/Diesel");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.vehicleSettingFeature.goToVehicleSetting();
        classDecl.vehicleSettingFeature.inputVehicleInfo(
                "",
                classDecl.datas.incomeInsuredNo,
                "",
                "",
                "",
                "");
        classDecl.vehicleSettingFeature.confirmVehicleDetail();
        classDecl.landingFeature.verifyFocusedTab(classDecl.datas.incomeOrangeForce);
    }

    // verify focused tab from breeze setting: income update, obu new, parking new - update - check
    // verify can input value from breeze setting
    // verify ui number error

}

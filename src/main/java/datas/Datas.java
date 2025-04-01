package datas;

import commons.CommonKeyword;

public class Datas {
    CommonKeyword commonKeyword = new CommonKeyword();

//    // Virtual device
//    public String deviceName = "sdk_gphone64_x86_64";
//    public String udid = "emulator-5554";
//    public String platformName = "Android";
//    public String platformVersion = "14";
//    public String automationName = "UiAutomator2";
//    public String appPackage = "com.ncs.breeze.demo";
//    public String appActivity = "com.ncs.breeze.ui.splash.SplashActivity";

    // Real device
    public String deviceName = "Galaxy A72";
    public String udid = "R58R362PFEW";
    public String platformName = "Android";
    public String platformVersion = "14";
    public String automationName = "UiAutomator2";
    public String appPackage = "com.ncs.breeze.demo";
    public String appActivity = "com.ncs.breeze.ui.splash.SplashActivity";

    // Path
    public String pathAppiumJS = "C:\\Users\\linh.nguyen39\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    public String pathVoucherData = "C:/Users/linh.nguyen39/IdeaProjects/voucher_v25_FIR_27031620.xlsx";

    // Landing tabs
    public String incomeOrangeForce = "Income Orange Force";
    public String obuOnBreeze = "OBU On Breeze";
    public String parkingCalculator = "Parking Calculator";
    public String erpChecker = "ERP Checker";
    public String liveTraffic = "Live Traffic";

    // Vehicle setting
    public String incomeInsuredNo = "LLU8471T";
    public String nonIncomeInsuredNo = "Sba1234G";
    public String vehicleBrand = "MAZDA";
    public String vehicleBrand2 = "ALPINE";
    public String energy_PetrolDiesel = "Petrol/Diesel";
    public String energy_EV = "EV";
    public String UINumber = commonKeyword.getRandom10Digits();

}


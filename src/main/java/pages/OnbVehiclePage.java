package pages;

import commons.BaseTest;
import commons.CommonKeyword;
import io.appium.java_client.AppiumDriver;

public class OnbVehiclePage extends BaseTest {
    CommonKeyword commonKeyword = new CommonKeyword();
    public String lblPageTitle = "//android.widget.TextView[@text=\"Enter vehicle details\"]";
    public String lblPageDesc = "//android.widget.TextView[@text=\"Complete the following to access insurance, vouchers, and track ERP and parking costs per trip. Your data will be stored only on your device.\"]";
    public String lblVehicleType = "//android.widget.TextView[@text=\"Vehicle Type\"]";
    public String ddlVehicleType = "//android.widget.TextView[@text=\"%s\"]";
    public String lblVehicleNo = "//android.widget.TextView[@text=\"Vehicle licence plate no.\"]";
    public String txtVehicleNoEmpty = "//android.widget.EditText";
    public String ddlVehicleNo = "//android.widget.TextView[@text=\"%s\"]";
    public String lblVehicleBrand = "//android.widget.TextView[@text=\"Vehicle Brand\"]";
    public String ddlVehicleBrandEmpty = "//android.widget.TextView[@text=\"Vehicle Brand\"]/following-sibling::android.view.ViewGroup/android.widget.EditText";
    public String ddlVehicleBrand = "//android.widget.TextView[@text=\"%s\"]";
    public String lblOBU = "//android.widget.TextView[@text=\"I have installed my OBU\"]";
    public String chkYes = "//android.widget.TextView[@text=\"Yes\"]/preceding-sibling::android.view.ViewGroup";
    public String chkNo = "//android.widget.TextView[@text=\"No\"]/preceding-sibling::android.view.ViewGroup";
    public String lblUINumber = "//android.widget.TextView[@text=\"IU number (Optional)\"]";
    public String txtUINumber = "//android.widget.TextView[@text=\"%s\"]";
    public String txtUINumberEmpty = "//android.widget.TextView[@text=\"IU number (Optional)\"]/following-sibling::android.view.ViewGroup/android.widget.EditText";
    public String lblGuideTitle = "//android.widget.TextView[@text=\"Where to get your vehicle IU/ OBU no.?\"]";
    public String btnArrow = "//android.widget.TextView[@text=\"Where to get your vehicle IU/ OBU no.?\"]/following-sibling::\tandroid.widget.ImageView";
    public String lblPointOneTitle = "//android.widget.TextView[@text=\"1. Via Singpass App\"]";
    public String lblPointOneDesc = "//android.widget.TextView[@text=\"Open Singpass app on your mobile > Vehicle & Driving Licence > Vehicle Asset\"]";
    public String lblPointTwoTitle = "//android.widget.TextView[@text=\"2. In-Vehicle Unit\"]";
    public String lblPointTwoDesc = "//android.widget.TextView[@text=\"10-digit number located on a sticker on your in-vehicle unit.\"]";
    public String lblPointThreeTitle = "//android.widget.TextView[@text=\"3. Via OneMotoring Website\"]";
    public String lblPointThreeDesc = "//android.widget.TextView[@text=\"Log on to OneMotoring website and access your IU/ OBU number via your vehicle's log card.\"]";
    public String lblPointFourTitle = "//android.widget.TextView[@text=\"4. On-Board Unit (via OBU display)\"]";
    public String lblPointFourDesc = "//android.widget.TextView[@text=\"Tap Menu button > Settings > About OBU > My OBU\"]";
    public String lblEnergyType = "//android.widget.TextView[@text=\"Energy type\"]";
    public String chkPetrolDiesel = "//android.widget.TextView[@text=\"Petrol/Diesel\"]/preceding-sibling::android.view.ViewGroup";
    public String chkEV = "//android.widget.TextView[@text=\"EV\"]/preceding-sibling::android.view.ViewGroup";
    public String btnSave = "//android.widget.TextView[@text=\"Save\"]";
    public String btnSkipForNow = "//android.widget.TextView[@text=\"Skip for now\"]";
    public String lblConfirmMsg = "//android.widget.TextView[@text=\"Please ensure the details you have entered are correct.\"]";
    public String btnCancel = "//android.widget.TextView[@text=\"Cancel\"]";
    public String btnConfirm = "//android.widget.TextView[@text=\"Confirm\"]";


    public OnbVehiclePage(AppiumDriver driver){
        super(driver);
    }

    public void verifyScreenTitle() {
        commonKeyword.waitForElementVisible(lblPageTitle);
    }
    public void verifyScreenDescription() {
        commonKeyword.waitForElementVisible(lblPageDesc);
    }

    public void verifyVehicleTypeField(String vehicleType) {
        commonKeyword.waitForElementVisible(lblVehicleType);
        commonKeyword.waitForElementVisible(ddlVehicleType, vehicleType);
    }

    public void verifyVehicleLicencePlateNoField(String vehicleNo) {
        commonKeyword.waitForElementVisible(lblVehicleNo);
        if (vehicleNo.equals("empty")) {
            commonKeyword.waitForElementVisible(txtVehicleNoEmpty);
        } else {
            commonKeyword.waitForElementVisible(ddlVehicleNo, vehicleNo);
        }

    }

    public void verifyVehicleBrandField(String vehicleBrand) {
        commonKeyword.waitForElementVisible(lblVehicleBrand);
        if (vehicleBrand.equals("empty")) {
            commonKeyword.waitForElementVisible(ddlVehicleBrandEmpty);
        } else {
            commonKeyword.waitForElementVisible(ddlVehicleBrand, vehicleBrand);
        }
    }

    public void verifyOBUInstallationField() {
        commonKeyword.waitForElementVisible(lblOBU);
        commonKeyword.waitForElementVisible(chkYes);
        commonKeyword.waitForElementVisible(chkNo);
    }

    public void verifyIUNumberField(String vehicleIUNumber) {
        commonKeyword.waitForElementVisible(lblUINumber);
        if (vehicleIUNumber.equals("empty")) {
            commonKeyword.waitForElementVisible(txtUINumberEmpty);
        } else {
            commonKeyword.waitForElementVisible(txtUINumber, vehicleIUNumber);
        }
    }

    public void clickOnGuidanceArrowBtn() {
        commonKeyword.clickElement(btnArrow);
    }

    public void verifyGuidanceField(String status) {
        commonKeyword.waitForElementVisible(lblGuideTitle);
        commonKeyword.waitForElementVisible(btnArrow);

        if (status == "collapse") {
            commonKeyword.elementNotVisible(lblPointOneTitle);
            commonKeyword.elementNotVisible(lblPointTwoTitle);
            commonKeyword.elementNotVisible(lblPointThreeTitle);
            commonKeyword.elementNotVisible(lblPointFourTitle);
        } else {
            commonKeyword.waitForElementVisible(lblPointOneTitle);
            commonKeyword.waitForElementVisible(lblPointOneDesc);
            commonKeyword.waitForElementVisible(lblPointTwoTitle);
            commonKeyword.waitForElementVisible(lblPointTwoDesc);
            commonKeyword.waitForElementVisible(lblPointThreeTitle);
            commonKeyword.waitForElementVisible(lblPointThreeDesc);
            commonKeyword.waitForElementVisible(lblPointFourTitle);
            commonKeyword.waitForElementVisible(lblPointFourDesc);
        }

    }

    public void verifyEnergyTypeField() {
        commonKeyword.waitForElementVisible(lblEnergyType);
        commonKeyword.waitForElementVisible(chkPetrolDiesel);
        commonKeyword.waitForElementVisible(chkEV);
    }

    public void clickSaveBtn() {
        commonKeyword.clickElement(btnSave);
    }

    public void clickSkipForNowBtn() {
        commonKeyword.clickElement(btnSkipForNow);
    }

    public void verifyVehicleDetailDialog(String status) {
        if (status == "not visible") {
            commonKeyword.elementNotVisible(lblConfirmMsg);
            commonKeyword.elementNotVisible(btnCancel);
            commonKeyword.elementNotVisible(btnConfirm);
        } else {
            commonKeyword.waitForElementVisible(lblConfirmMsg);
            commonKeyword.waitForElementVisible(btnCancel);
            commonKeyword.waitForElementVisible(btnConfirm);
        }

    }

    public void selectVehicleType(String vehicleType) {

    }

    public void enterVehicleLicenceNo(String vehicleNo) {
        commonKeyword.sendKey(txtVehicleNoEmpty, vehicleNo);
    }

    public void selectVehicleBrand(String vehicleBrand) {

    }

    public void selectOBUOpt(String opt) {
        if (opt.equals("Yes")) {
            commonKeyword.clickElement(chkYes);
        } else if (opt.equals("No")) {
            commonKeyword.clickElement(chkNo);
        } else {
            System.out.println("keep default option");
        }
    }

    public void enterIUNumber(String vehicleIUNumber) {
        commonKeyword.sendKey(txtUINumberEmpty, vehicleIUNumber);
    }

    public void selectEnergyOpt(String opt) {
        if (opt.equals("Petrol/Diesel")) {
            commonKeyword.clickElement(chkPetrolDiesel);
        } else if (opt.equals("EV")) {
            commonKeyword.clickElement(chkEV);
        } else {
            System.out.println("keep default option");
        }
    }

}
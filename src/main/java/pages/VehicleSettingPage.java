package pages;

import commons.BaseTest;

public class VehicleSettingPage extends BaseTest {
    public String lblPageTitle = "//android.widget.TextView[@text=\"Enter vehicle details\"]";
    public String lblPageDesc = "//android.widget.TextView[@text=\"Complete the following to access insurance, vouchers, and track ERP and parking costs per trip.\n\nYour data will be stored only on your device.\"]";
    public String lblVehicleType = "//android.widget.TextView[@text=\"Vehicle Type\"]";
    public String ddlVehicleType = "//android.widget.TextView[@text=\"%s\"]";
    public String btnArrowVehicleType = "//android.widget.TextView[@text=\"Vehicle Type\"]/following-sibling::android.view.ViewGroup/android.widget.ImageView";
    public String lblVehicleNo = "//android.widget.TextView[@text=\"Vehicle licence plate no.\"]";
    public String txtVehicleNo = "//android.widget.TextView[@text=\"Vehicle licence plate no.\"]/following-sibling::android.view.ViewGroup[1]/android.widget.EditText";
    public String lblVehicleBrand = "//android.widget.TextView[@text=\"Vehicle Brand\"]";
    public String txtVehicleBrand = "//android.widget.TextView[@text=\"Vehicle Brand\"]/following-sibling::android.view.ViewGroup/android.widget.EditText";
    public String lblOBU = "//android.widget.TextView[@text=\"I have installed my OBU\"]";
    public String chkYes = "//android.widget.TextView[@text=\"Yes\"]/preceding-sibling::android.view.ViewGroup";
    public String chkNo = "//android.widget.TextView[@text=\"No\"]/preceding-sibling::android.view.ViewGroup";
    public String lblUINumber = "//android.widget.TextView[@text=\"IU number (Optional)\"]";
    public String txtUINumber = "//android.widget.TextView[@text=\"IU number (Optional)\"]/following-sibling::android.view.ViewGroup[1]/android.widget.EditText";
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


    public void verifyScreenTitle() {
        classDecl.commonKeyword.waitForElementVisible(lblPageTitle);
    }
    public void verifyScreenDescription() {
        classDecl.commonKeyword.waitForElementVisible(lblPageDesc);
    }

    public void verifyVehicleTypeField(String vehicleType) {
        classDecl.commonKeyword.waitForElementVisible(lblVehicleType);
        classDecl.commonKeyword.waitForElementVisible(ddlVehicleType, vehicleType);
    }

    public void verifyVehicleLicencePlateNoField(String vehicleNo) {
        classDecl.commonKeyword.waitForElementVisible(lblVehicleNo);
        classDecl.commonKeyword.verifyText(txtVehicleNo, vehicleNo);

    }

    public void verifyVehicleBrandField(String vehicleBrand) {
        classDecl.commonKeyword.scrollToElementByXPath(lblVehicleBrand);
        classDecl.commonKeyword.waitForElementVisible(lblVehicleBrand);
        classDecl.commonKeyword.verifyText(txtVehicleBrand, vehicleBrand);
    }

    public void verifyOBUInstallationField() {
        classDecl.commonKeyword.scrollToElementByXPath(lblUINumber);
        classDecl.commonKeyword.clickElement(lblOBU); //To close vehicle brand list
        classDecl.commonKeyword.waitForElementVisible(chkYes);
        classDecl.commonKeyword.waitForElementVisible(chkNo);
    }

    public void verifyIUNumberField(String vehicleIUNumber) {
        classDecl.commonKeyword.scrollToElementByXPath(lblUINumber);
        classDecl.commonKeyword.verifyText(txtUINumber, vehicleIUNumber);
    }

    public void clickOnGuidanceArrowBtn() {
        classDecl.commonKeyword.clickElement(btnArrow);
    }

    public void verifyGuidanceField(String status) {
        classDecl.commonKeyword.waitForElementVisible(lblGuideTitle);
        classDecl.commonKeyword.waitForElementVisible(btnArrow);

        if (status.equals("collapse")) {
            classDecl.commonKeyword.verifyElementNotVisible(lblPointOneTitle);
            classDecl.commonKeyword.verifyElementNotVisible(lblPointTwoTitle);
            classDecl.commonKeyword.verifyElementNotVisible(lblPointThreeTitle);
            classDecl.commonKeyword.verifyElementNotVisible(lblPointFourTitle);
        } else {
            classDecl.voucherDetailPage.scrollUntilElementVisible(lblPointFourTitle);
            classDecl.commonKeyword.waitForElementVisible(lblPointOneTitle);
            classDecl.commonKeyword.waitForElementVisible(lblPointOneDesc);
            classDecl.commonKeyword.waitForElementVisible(lblPointTwoTitle);
            classDecl.commonKeyword.waitForElementVisible(lblPointTwoDesc);
            classDecl.commonKeyword.waitForElementVisible(lblPointThreeTitle);
            classDecl.commonKeyword.waitForElementVisible(lblPointThreeDesc);
            classDecl.commonKeyword.waitForElementVisible(lblPointFourTitle);
            classDecl.commonKeyword.scrollUntilElementVisible(lblPointFourDesc);
        }

    }

    public void verifyEnergyTypeField() {
        classDecl.commonKeyword.scrollToElementByXPath(lblEnergyType);
        classDecl.commonKeyword.waitForElementVisible(chkPetrolDiesel);
        classDecl.commonKeyword.waitForElementVisible(chkEV);
    }

    public void clickSaveBtn() {
        classDecl.commonKeyword.clickElement(btnSave);
    }

    public void clickSkipForNowBtn() {
        classDecl.commonKeyword.clickElement(btnSkipForNow);
    }

    public void verifyVehicleDetailDialog(String status) {
        if (status.equals("not visible")) {
            classDecl.commonKeyword.verifyElementNotVisible(lblConfirmMsg);
            classDecl.commonKeyword.verifyElementNotVisible(btnCancel);
            classDecl.commonKeyword.verifyElementNotVisible(btnConfirm);
        } else {
            classDecl.commonKeyword.waitForElementVisible(lblConfirmMsg);
            classDecl.commonKeyword.waitForElementVisible(btnCancel);
            classDecl.commonKeyword.waitForElementVisible(btnConfirm);
        }

    }

    public void clickConfirmBtn(){
        classDecl.commonKeyword.clickElement(btnConfirm);
    }

    public void selectVehicleType(String vehicleType) {
        //implement later
    }

    public void enterVehicleLicenceNo(String vehicleNo) {
        classDecl.commonKeyword.sendKey(txtVehicleNo, vehicleNo);
    }

    public void enterVehicleBrand(String vehicleBrand) {
        classDecl.commonKeyword.scrollToElementByXPath(txtVehicleBrand);
        classDecl.commonKeyword.sendKey(txtVehicleBrand, vehicleBrand);
    }

    public void selectOBUOpt(String opt) {
        classDecl.commonKeyword.scrollToElementByXPath(chkYes);
        if (opt.equals("Yes")) {
            classDecl.commonKeyword.clickElement(chkYes);
        } else if (opt.equals("No")) {
            classDecl.commonKeyword.clickElement(chkNo);
        } else {
            System.out.println("keep default option");
        }
    }

    public void enterIUNumber(String vehicleIUNumber) {
        classDecl.commonKeyword.scrollToElementByXPath(txtUINumber);
        classDecl.commonKeyword.sendKey(txtUINumber, vehicleIUNumber);
    }

    public void selectEnergyOpt(String opt) {
        classDecl.commonKeyword.scrollToElementByXPath(chkPetrolDiesel);
        if (opt.equals("Petrol/Diesel")) {
            classDecl.commonKeyword.clickElement(chkPetrolDiesel);
        } else if (opt.equals("EV")) {
            classDecl.commonKeyword.clickElement(chkEV);
        } else {
            System.out.println("keep default option");
        }
    }

}
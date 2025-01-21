package pages;

import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;

public class OnbVehiclePage extends BaseTest {
    public String lblPageTitle = "//android.widget.TextView[@text=\"Enter vehicle details\"]";
    public String lblPageDesc = "//android.widget.TextView[@text=\"Complete the following to access insurance, vouchers, and track ERP and parking costs per trip.\n\nYour data will be stored only on your device.\"]";
    public String lblVehicleType = "//android.widget.TextView[@text=\"Vehicle Type\"]";
    public String ddlVehicleType = "//android.widget.TextView[@text=\"%s\"]";
    public String btnArrowVehicleType = "//android.widget.TextView[@text=\"Vehicle Type\"]/following-sibling::android.view.ViewGroup/android.widget.ImageView";
    public String lblVehicleNo = "//android.widget.TextView[@text=\"Vehicle licence plate no.\"]";
    public String txtVehicleNoEmpty = "//android.widget.EditText";
    public String ddlVehicleNo = "//android.widget.EditText[@text=\"%s\"]";
    public String lblVehicleBrand = "//android.widget.TextView[@text=\"Vehicle Brand\"]";
    public String ddlVehicleBrandEmpty = "//android.widget.TextView[@text=\"Vehicle Brand\"]/following-sibling::android.view.ViewGroup/android.widget.EditText";
    public String ddlVehicleBrand = "//android.widget.EditText[@text=\"%s\"]";
    public String lblOBU = "//android.widget.TextView[@text=\"I have installed my OBU\"]";
    public String chkYes = "//android.widget.TextView[@text=\"Yes\"]/preceding-sibling::android.view.ViewGroup";
    public String chkNo = "//android.widget.TextView[@text=\"No\"]/preceding-sibling::android.view.ViewGroup";
    public String lblUINumber = "//android.widget.TextView[@text=\"IU number (Optional)\"]";
    public String txtUINumber = "//android.widget.EditText[@text=\"%s\"]";
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


    public OnbVehiclePage(AndroidDriver driver){
        super(driver);
    }

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
        if (vehicleNo.equals("empty")) {
            classDecl.commonKeyword.waitForElementVisible(txtVehicleNoEmpty);
        } else {
            classDecl.commonKeyword.waitForElementVisible(ddlVehicleNo, vehicleNo);
        }

    }

    public void verifyVehicleBrandField(String vehicleBrand) {
        classDecl.commonKeyword.scrollToElementByXPath(lblOBU);
        classDecl.commonKeyword.waitForElementVisible(lblVehicleBrand);
        if (vehicleBrand.equals("empty")) {
            classDecl.commonKeyword.waitForElementVisible(ddlVehicleBrandEmpty);
        } else {
            classDecl.commonKeyword.waitForElementVisible(ddlVehicleBrand, vehicleBrand);
        }
    }

    public void verifyOBUInstallationField() {
        classDecl.commonKeyword.waitForElementVisible(lblOBU);
        classDecl.commonKeyword.waitForElementVisible(chkYes);
        classDecl.commonKeyword.waitForElementVisible(chkNo);
    }

    public void verifyIUNumberField(String vehicleIUNumber) {
        classDecl.commonKeyword.scrollToElementByXPath(lblUINumber);
        if (vehicleIUNumber.equals("empty")) {
            classDecl.commonKeyword.waitForElementVisible(txtUINumberEmpty);
        } else {
            classDecl.commonKeyword.waitForElementVisible(txtUINumber, vehicleIUNumber);
        }
    }

    public void clickOnGuidanceArrowBtn() {
        classDecl.commonKeyword.clickElement(btnArrow);
    }

    public void verifyGuidanceField(String status) {
        classDecl.commonKeyword.waitForElementVisible(lblGuideTitle);
        classDecl.commonKeyword.waitForElementVisible(btnArrow);

        if (status.equals("collapse")) {
            classDecl.commonKeyword.elementNotVisible(lblPointOneTitle);
            classDecl.commonKeyword.elementNotVisible(lblPointTwoTitle);
            classDecl.commonKeyword.elementNotVisible(lblPointThreeTitle);
            classDecl.commonKeyword.elementNotVisible(lblPointFourTitle);
        } else {
            classDecl.commonKeyword.scrollToElementByXPath(lblPointFourTitle);
            classDecl.commonKeyword.waitForElementVisible(lblPointOneTitle);
            classDecl.commonKeyword.waitForElementVisible(lblPointOneDesc);
            classDecl.commonKeyword.waitForElementVisible(lblPointTwoTitle);
            classDecl.commonKeyword.waitForElementVisible(lblPointTwoDesc);
            classDecl.commonKeyword.waitForElementVisible(lblPointThreeTitle);
            classDecl.commonKeyword.waitForElementVisible(lblPointThreeDesc);
            classDecl.commonKeyword.waitForElementVisible(lblPointFourTitle);
            classDecl.commonKeyword.waitForElementVisible(lblPointFourDesc);
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
            classDecl.commonKeyword.elementNotVisible(lblConfirmMsg);
            classDecl.commonKeyword.elementNotVisible(btnCancel);
            classDecl.commonKeyword.elementNotVisible(btnConfirm);
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
        classDecl.commonKeyword.sendKey(txtVehicleNoEmpty, vehicleNo);
    }

    public void selectVehicleBrand(String vehicleBrand) {
        classDecl.commonKeyword.scrollToElementByXPath(ddlVehicleBrandEmpty);
        classDecl.commonKeyword.sendKey(ddlVehicleBrandEmpty, vehicleBrand);
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
        classDecl.commonKeyword.scrollToElementByXPath(txtUINumberEmpty);
        classDecl.commonKeyword.sendKey(txtUINumberEmpty, vehicleIUNumber);
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
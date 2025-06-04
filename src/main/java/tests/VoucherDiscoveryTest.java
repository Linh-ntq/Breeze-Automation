package tests;

import commons.Setup;
import org.testng.annotations.Test;

import java.util.List;

public class VoucherDiscoveryTest extends Setup {
    @Test(priority = 1)
    public void verify_voucher_destination_search() {
        String pathVoucherData = "C:/Users/linh.nguyen39/IdeaProjects/Breeze Data/Voucher_detail_file/NTUC@Breeze - Singtel Phone Plan.xlsx";
        String sheetName = "VoucherData";
        String rowName = "Singtel";
        String voucherStartDate = classDecl.excelReader.getVoucherData(pathVoucherData, sheetName, rowName, "Redemption start date");
        String voucherEndDate = classDecl.excelReader.getVoucherData(pathVoucherData, sheetName, rowName, "Redemption end date");

        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails("89912121", "119Z");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.landingPage.clickOnSearchBar();
        classDecl.extentReport.startTest("Verify vouchers in the destination search");
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(classDecl.datas.pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

    @Test(priority = 1)
    public void verify_searching_by_postal_code_in_voucher_module() {
        String pathVoucherData = "C:/Users/linh.nguyen39/IdeaProjects/Breeze Data/Voucher_detail_file/NTUC@Breeze - Singtel Phone Plan.xlsx";
        String sheetName = "VoucherData";
        String rowName = "Singtel";
        String voucherStartDate = classDecl.excelReader.getVoucherData(pathVoucherData, sheetName, rowName, "Redemption start date");
        String voucherEndDate = classDecl.excelReader.getVoucherData(pathVoucherData, sheetName, rowName, "Redemption end date");

        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails("89912121", "119Z");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();

        classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
        classDecl.voucherDiscoveryFeature.verifySearchingByPostalCodeInVoucherModule(classDecl.datas.pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

    @Test(priority = 1)
    public void verify_searching_by_building_name_in_voucher_module() {
        String pathVoucherData = "C:/Users/linh.nguyen39/IdeaProjects/Breeze Data/Voucher_detail_file/NTUC@Breeze - Singtel Phone Plan.xlsx";
        String sheetName = "VoucherData";
        String rowName = "Singtel";
        String voucherStartDate = classDecl.excelReader.getVoucherData(pathVoucherData, sheetName, rowName, "Redemption start date");
        String voucherEndDate = classDecl.excelReader.getVoucherData(pathVoucherData, sheetName, rowName, "Redemption end date");

        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails("89912121", "119Z");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();

        classDecl.voucherDiscoveryFeature.verifySearchingByBuildingNameInVoucherModule(classDecl.datas.pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

    @Test(priority = 4)
    public void verify_voucher_detail_in_voucher_module() {
        String sheetName = "VoucherData";
        String rowName = "Global Art";
        String voucherStartDate = classDecl.excelReader.getVoucherData(classDecl.datas.pathToVoucherFile, sheetName, rowName, "Redemption start date");
        String voucherEndDate = classDecl.excelReader.getVoucherData(classDecl.datas.pathToVoucherFile, sheetName, rowName, "Redemption end date");
        String voucherDescription = classDecl.excelReader.getVoucherData(classDecl.datas.pathToVoucherFile, sheetName, rowName, "Voucher card details");
        List<String> aboutVoucherSection = classDecl.excelReader.getVoucherDataList(classDecl.datas.pathToVoucherFile, sheetName, rowName, "About voucher");
        List<String> howToUseSectionBeforeClaim = classDecl.excelReader.getVoucherDataList(classDecl.datas.pathToVoucherFile, sheetName, rowName, "How to use voucher (before claim)");
        List<String> howToUseSectionAfterClaim = classDecl.excelReader.getVoucherDataList(classDecl.datas.pathToVoucherFile, sheetName, rowName, "How to use voucher (after claim)");
        List<String> termConditionSection = classDecl.excelReader.getVoucherDataList(classDecl.datas.pathToVoucherFile, sheetName, rowName, "T& C");

        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails("89912121", "119Z");
        classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
        classDecl.voucherDiscoveryFeature.verifyVoucherCard(rowName, voucherStartDate, voucherEndDate, "Voucher list page");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.myVoucherPage.clickViewBtn(voucherDescription);

        //verify voucher detail in Unclaimed tab
        classDecl.voucherDiscoveryFeature.verifyVoucherDetail("Before claim", rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionBeforeClaim, termConditionSection);

        //verify voucher detail in Claimed tab
        classDecl.voucherDiscoveryFeature.verifyVoucherDetail("After claim", rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionAfterClaim, termConditionSection);

    }

}

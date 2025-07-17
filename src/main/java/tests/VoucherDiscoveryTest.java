package tests;

import commons.Setup;
import org.testng.annotations.Test;
import java.util.List;

public class VoucherDiscoveryTest extends Setup {
    String ntucMobile = "89912121";
    String ntucNRIC = "119z";
    String sheetName = "Sheet1";
    String rowName = "Singtel";
    String pathToVoucherFile = "C:/Users/linh.nguyen39/IdeaProjects/Breeze Data/Voucher_detail_file/NTUC@Breeze - Singtel Phone Plan.xlsx";
    String voucherStartDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption start date");
    String voucherEndDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption end date");
    String voucherDescription = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Voucher card details");
    String voucherCategory = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Voucher category");
    List<String> aboutVoucherSection = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "About voucher");
    List<String> howToUseSectionBeforeClaim = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "How to use voucher (before claim)");
    List<String> howToUseSectionAfterClaim = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "How to use voucher (after claim)");
    List<String> termConditionSection = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "T& C");

    @Test(priority = 1)
    public void verify_voucher_card_and_detail_in_voucher_module() {
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails(ntucMobile, ntucNRIC);
        classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
        classDecl.commonKeyword.closeInAppAlertsIfVisible();

        // use try - finally to make sure attachScreenRecordingToReport() run even when test failed
        try {
            // Verify voucher card in Unclaimed tab
            classDecl.extentReport.startTest("Verify voucher card - Before claim");
            classDecl.extentReport.startRecordingScreen();
            classDecl.voucherDiscoveryFeature.selectVoucherCategory(voucherCategory);
            classDecl.voucherDiscoveryFeature.verifyVoucherCard(pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate, "Unclaimed tab");

            // Verify voucher detail in Unclaimed tab
            classDecl.extentReport.startTest("Verify voucher detail - Before claim");
            classDecl.myVoucherPage.clickViewBtn(voucherDescription);
            classDecl.voucherDiscoveryFeature.verifyVoucherDetail("Unclaimed", pathToVoucherFile, sheetName, rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionBeforeClaim, termConditionSection);

            // Verify voucher detail in Claimed tab
            classDecl.extentReport.startTest("Verify voucher detail - After claim");
            classDecl.voucherDiscoveryFeature.verifyVoucherDetail("Claimed", pathToVoucherFile, sheetName, rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionAfterClaim, termConditionSection);

            // Verify voucher card in Claimed tab
            classDecl.extentReport.startTest("Verify voucher card - After claim");
            classDecl.voucherDiscoveryFeature.verifyVoucherCard(pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate, "Claimed tab");

            classDecl.myVoucherPage.clickViewBtn(voucherDescription);
            classDecl.voucherDiscoveryFeature.confirmUseVoucher(rowName, voucherDescription);

            // Verify voucher card in History tab
            classDecl.extentReport.startTest("Verify voucher card - After utilization");
            classDecl.voucherDiscoveryFeature.verifyVoucherCard(pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate, "History tab");

            // Verify voucher detail in History tab
            classDecl.extentReport.startTest("Verify voucher detail - After utilization");
            classDecl.voucherDiscoveryFeature.verifyVoucherDetail("History", pathToVoucherFile, sheetName, rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionAfterClaim, termConditionSection);

        } finally {
            classDecl.extentReport.attachScreenRecordingToReport(rowName + " - Voucher detail");

        }
    }

    @Test(priority = 2)
    public void verify_voucher_destination_search() {
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails(ntucMobile, ntucNRIC);
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.landingPage.clickOnSearchBar();
        classDecl.extentReport.startTest("Verify vouchers in the destination search");
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch("Postal code", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch("Building name", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch("Full address", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch("One map address", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch("GG map address", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

    @Test(priority = 3)
    public void verify_voucher_module_search() {
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails(ntucMobile, ntucNRIC);
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
        classDecl.extentReport.startTest("Verify vouchers in Voucher module by searching with postal code");
        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch("Postal code", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch("Building name", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch("Full address", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch("One map address", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch("GG map address", pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

}

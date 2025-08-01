package tests;

import commons.Setup;
import org.testng.annotations.Test;

import java.util.List;

public class SRVoucherDiscoveryTest extends Setup {
    String voucherType = "Singtel Rewards";
    String sheetName = "Sheet1";
    String rowName = "Takagi Ramen";
    String pathToVoucherFile = "C:/Users/linh.nguyen39/IdeaProjects/Breeze Data/Voucher_detail_file/Breeze - Voucher details - Singtel Rewards - Takagi Ramen - 1-for-1 Ramen (QA 250719).xlsx";
    String voucherStartDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption start date");
    String voucherEndDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption end date");
    String voucherDescription = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Voucher card details");
    String voucherCategory = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Voucher category");
    List<String> aboutVoucherSection = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "About voucher");
    List<String> howToUseSectionBeforeClaim = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "How to use voucher (before claim)");
    List<String> howToUseSectionAfterClaim = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "How to use voucher (after claim)");
    List<String> termConditionSection = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "T& C");

    @Test(priority = 1)
    public void verify_Singtel_Rewards_voucher_in_voucher_module() {
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        classDecl.commonKeyword.pause(20); // Pause to active SR
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
            classDecl.voucherDiscoveryFeature.verifyVoucherDetail("Unclaimed", voucherType, pathToVoucherFile, sheetName, rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionBeforeClaim, termConditionSection);
            classDecl.extentReport.attachScreenRecordingToReport(rowName + " - Unclaimed");

            // Verify voucher detail in Claimed tab
            classDecl.extentReport.startRecordingScreen();
            classDecl.extentReport.startTest("Verify voucher detail - After claim");
            classDecl.voucherDiscoveryFeature.verifyVoucherDetail("Claimed", voucherType, pathToVoucherFile, sheetName, rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionAfterClaim, termConditionSection);

            // Verify voucher card in Claimed tab
            classDecl.extentReport.startTest("Verify voucher card - After claim");
            classDecl.voucherDiscoveryFeature.verifyVoucherCard(pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate, "Claimed tab");

            classDecl.myVoucherPage.clickViewBtn(voucherDescription);
            classDecl.voucherDiscoveryFeature.confirmUseVoucher(rowName, voucherDescription);
            classDecl.extentReport.attachScreenRecordingToReport(rowName + " - Claimed");

            // Verify voucher card in History tab
            classDecl.extentReport.startRecordingScreen();
            classDecl.extentReport.startTest("Verify voucher card - After utilization");
            classDecl.voucherDiscoveryFeature.verifyVoucherCard(pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate, "History tab");

            // Verify voucher detail in History tab
            classDecl.extentReport.startTest("Verify voucher detail - After utilization");
            classDecl.voucherDiscoveryFeature.verifyVoucherDetail("History", voucherType, pathToVoucherFile, sheetName, rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionAfterClaim, termConditionSection);

        } finally {
            classDecl.extentReport.attachScreenRecordingToReport(rowName + " - History");

        }
    }

    @Test(priority = 2)
    public void verify_Singtel_Rewards_voucher_destination_search() {
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        classDecl.commonKeyword.pause(20); // Pause to active SR
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.landingPage.clickOnSearchBar();
        classDecl.extentReport.startTest("Verify vouchers in the destination search");
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(classDecl.datas.postalCode, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(classDecl.datas.buildingN_n_FullA, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
//        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(classDecl.datas.buildingName, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
//        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(classDecl.datas.fullAddress, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(classDecl.datas.oneMapAddress, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(classDecl.datas.ggMapAddress, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

    @Test(priority = 3)
    public void verify_Singtel_Rewards_voucher_module_search() {
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        classDecl.commonKeyword.pause(20); // Pause to active SR
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
        classDecl.extentReport.startTest("Verify vouchers in Voucher module");
        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch(classDecl.datas.postalCode, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch(classDecl.datas.buildingN_n_FullA, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
//        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch(classDecl.datas.buildingName, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
//        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch(classDecl.datas.fullAddress, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch(classDecl.datas.oneMapAddress, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDiscoveryFeature.verifyVoucherModuleSearch(classDecl.datas.ggMapAddress, pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }
}

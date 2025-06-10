package tests;

import commons.Setup;
import org.testng.annotations.Test;

import java.util.List;

public class VoucherDiscoveryTest extends Setup {
    String sheetName = "VoucherData";
    String rowName = "McDonald's";
    String pathToVoucherFile = "C:/Users/linh.nguyen39/IdeaProjects/Breeze Data/Voucher_detail_file/Breeze - Voucher details form - McDonald's - Free Sundae #2 (250521) - Final.xlsx";
    String voucherStartDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption start date");
    String voucherEndDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption end date");
    String voucherDescription = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Voucher card details");
    String voucherCategory = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Voucher category");
    List<String> aboutVoucherSection = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "About voucher");
    List<String> howToUseSectionBeforeClaim = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "How to use voucher (before claim)");
    List<String> howToUseSectionAfterClaim = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "How to use voucher (after claim)");
    List<String> termConditionSection = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "T& C");

    @Test(priority = 1)
    public void verify_voucher_destination_search() {
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
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

    @Test(priority = 1)
    public void verify_searching_by_postal_code_in_voucher_module() {
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails("89912121", "119Z");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();

        classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
        classDecl.extentReport.startTest("Verify vouchers in Voucher module by searching with postal code");
        classDecl.voucherDiscoveryFeature.verifySearchingByPostalCodeInVoucherModule(pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

    @Test(priority = 1)
    public void verify_searching_by_building_name_in_voucher_module() {
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails("89912121", "119Z");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.extentReport.startTest("Verify vouchers in Voucher module by searching with building name");
        classDecl.voucherDiscoveryFeature.verifySearchingByBuildingNameInVoucherModule(pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

    @Test(priority = 4)
    public void verify_voucher_detail_in_voucher_module() {
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        // Pause to scan QR invitation
        classDecl.commonKeyword.pause(35);
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.inboxFeature.enterNTUCDetails("89912121", "119Z");
        classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.voucherDiscoveryFeature.selectVoucherCategory(voucherCategory);
        classDecl.voucherDiscoveryFeature.verifyVoucherCard(pathToVoucherFile, rowName, voucherStartDate, voucherEndDate, "Voucher list page");
        classDecl.myVoucherPage.clickViewBtn(voucherDescription);

        //verify voucher detail in Unclaimed tab
        classDecl.voucherDiscoveryFeature.verifyVoucherDetail("Before claim", pathToVoucherFile, rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionBeforeClaim, termConditionSection);

        //verify voucher detail in Claimed tab
        classDecl.voucherDiscoveryFeature.verifyVoucherDetail("After claim", pathToVoucherFile, rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionAfterClaim, termConditionSection);

    }

}

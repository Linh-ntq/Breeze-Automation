package tests;

import commons.Setup;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class VoucherDiscoveryTest extends Setup {
    @Test(priority = 0)
    public void verify_voucher_destination_search() throws IOException {
        String pathToVoucherFile = classDecl.datas.pathVoucherData;
        String sheetName = "VoucherData";
        String rowName = "Global Art-1";
        String colName = "Merchant locations";
        List<String> voucherDescription = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "Voucher card details");
        String voucherStartDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption start date");
        String voucherEndDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption end date");

        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        classDecl.extentReport.startTest("Go to landing page by guest");
        classDecl.commonPage.tabOnMenu("Inbox");
        classDecl.extentReport.startTest("Tap on Inbox menu");
        classDecl.inboxPage.tapOnInbMsg(classDecl.datas.discoveryNTUCTitle, classDecl.datas.discoveryNTUCDesc);
        classDecl.extentReport.startTest("Tap on NTUC inbox message");
        classDecl.inboxFeature.enterNTUCDetails("89912121", "119Z");
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.landingPage.clickOnSearchBar();
        classDecl.extentReport.startTest("Verify vouchers in the destination search");
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(
                pathToVoucherFile,
                sheetName,
                rowName,
                colName,
                voucherStartDate,
                voucherEndDate,
                voucherDescription);

    }

    @Test(priority = 1)
    public void verify_voucher_detail_in_voucher_module() throws Exception {
        String pathToVoucherFile = classDecl.datas.pathVoucherData;
        String sheetName = "VoucherData";
        String rowName = "McDonald’s";
        String voucherStartDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption start date");
        String voucherEndDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption end date");
        String voucherDescription = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Voucher card details");
        List<String> aboutVoucherSection = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "About voucher");
        List<String> howToUseSectionBeforeClaim = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "How to use voucher (before claim)");
        List<String> howToUseSectionAfterClaim = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "How to use voucher (after claim)");
        List<String> termConditionSection = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "T& C");

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
        classDecl.voucherDetailPage.clickClaimBtn();
        classDecl.voucherDiscoveryFeature.verifySuccessfullyClaimedPopup(rowName, voucherStartDate, voucherEndDate);
        classDecl.voucherDetailPage.clickViewVoucherDetailsBtn();
        classDecl.voucherDiscoveryFeature.verifyVoucherDetail("After claim", rowName, voucherDescription, voucherStartDate, voucherEndDate, aboutVoucherSection, howToUseSectionAfterClaim, termConditionSection);


    }

    @Test(priority = 2)
    public void verify_voucher_destination_search2() throws IOException {
        String pathToVoucherFile = classDecl.datas.pathVoucherData;
        String sheetName = "VoucherData";
        String rowName = "McDonald’s";
        String voucherStartDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption start date");
        String voucherEndDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption end date");

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
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch2(pathToVoucherFile, sheetName, rowName, voucherStartDate, voucherEndDate);

    }

}

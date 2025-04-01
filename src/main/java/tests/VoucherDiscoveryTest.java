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
        String rowName = "LifeSpa";
        String colName = "Address";

        List<String> voucherDescription = classDecl.excelReader.getVoucherDataList(pathToVoucherFile, sheetName, rowName, "Voucher card details");
        String voucherStartDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption start date");
        String voucherEndDate = classDecl.excelReader.getVoucherData(pathToVoucherFile, sheetName, rowName, "Redemption end date");
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        classDecl.commonKeyword.pause(60);
        classDecl.landingPage.clickOnSearchBar();
        classDecl.voucherDiscoveryFeature.verifyVoucherDestinationSearch(
                pathToVoucherFile,
                sheetName,
                rowName,
                colName,
                voucherStartDate,
                voucherEndDate,
                voucherDescription);

    }
}

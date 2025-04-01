package tests;

import commons.Setup;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class VoucherDiscoveryTest extends Setup {
    @Test(priority = 0)
    public void verify_vouchers_in_destination_search() throws IOException {
        List<String> voucherList = classDecl.excelReader.getVoucherDataList(
                classDecl.datas.pathVoucherData,
                "VoucherData",
                "LifeSpa",
                "Voucher card details");
        classDecl.loginFeature.goToLandingPageByGuest("Guest");
        classDecl.commonKeyword.pause(60);
        classDecl.landingPage.clickOnSearchBar();
        classDecl.searchDestinationPage.searchVoucherDestination(classDecl.datas.pathVoucherData,
                "VoucherData",
                "LifeSpa",
                "Address", voucherList);

    }
}

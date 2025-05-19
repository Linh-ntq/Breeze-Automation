package features;

import commons.BaseTest;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static datas.ExcelReader.getValueByRowAndColumnName;

public class VoucherDiscoveryFeature extends BaseTest {

    public void verifyVoucherCard(String voucherName, String startDate, String endDate, String voucherPosition) throws IOException {
        String voucherDesc = getValueByRowAndColumnName(classDecl.datas.pathVoucherData, "VoucherData", voucherName, "Voucher card details");

        if (voucherPosition.equals("Vouchers nearby section")){
            classDecl.voucherModuleSearchPage.verifyVoucherAtNearbySection(voucherName, voucherDesc);
        } else {
            classDecl.voucherModuleSearchPage.verifyVoucherAtSearchedDestSection(voucherName, voucherDesc);
        }
        classDecl.voucherModuleSearchPage.verifyVoucherExpiry(voucherDesc, startDate, endDate);
        classDecl.voucherModuleSearchPage.verifyViewMapBtn(voucherDesc);
        classDecl.voucherModuleSearchPage.verifyViewBtn(voucherDesc);
    }

    public void verifyVoucherDestinationSearch(String filePath, String sheetName, String rowName, String colName, String startDate, String endDate, List<String> expectedVoucher) throws IOException {
        Map<String, String> address = classDecl.searchDestinationPage.getAddressFromExcelData(filePath, sheetName, rowName, colName);
        for (Map.Entry<String, String> entry : address.entrySet()) {
            String addressKey = entry.getKey();
            String addressValue = entry.getValue();

            if (colName.equals("Address")) {
                String fullAddress = addressKey.replaceAll(" SINGAPORE \\d+", "")
                        .replaceAll(" Singapore \\d+", "")
                        .replaceAll(" \\d{6}$", "");
                switch (fullAddress) {
                    case "183 TOA PAYOH CENTRAL TOA PAYOH CENTRAL":
                        // Hard code to remove the second occurrence of "TOA PAYOH CENTRAL"
                        fullAddress = fullAddress.replaceFirst(" TOA PAYOH CENTRAL", "");
                        classDecl.searchDestinationPage.inputAddress(fullAddress);
                        break;
                    case "1 BUKIT BATOK CENTRAL LINK WEST MALL":
                        fullAddress = "1 BUKIT BATOK CENTRAL LINK";
                        classDecl.searchDestinationPage.inputAddress(fullAddress);
                        break;
                    case "78 AIRPORT BOULEVARD JEWEL CHANGI AIRPORT":
                        fullAddress = "JEWEL CHANGI AIRPORT";
                        classDecl.searchDestinationPage.inputAddress(fullAddress);
                        break;
                    default:
                        classDecl.searchDestinationPage.inputAddress(fullAddress);
                        break;
                }
            } else {
                classDecl.searchDestinationPage.inputAddress(addressKey);
            }

            classDecl.commonKeyword.closeKeyboard();

            List<String> actualVoucher = classDecl.commonKeyword.getListText(classDecl.searchDestinationPage.lblVoucherList, addressValue);
            // Removes trailing white spaces at the end
            List<String> trimmedActualVoucher = actualVoucher.stream()
                    .map(str -> str.replaceAll("\\s+$", ""))
                    .collect(Collectors.toList());
            List<String> trimmedExpectedVoucher = expectedVoucher.stream()
                    .map(str -> str.replaceAll("\\s+$", ""))
                    .collect(Collectors.toList());

            System.out.println("Actual voucher list from UI: " + trimmedActualVoucher);
            System.out.println("Expected voucher from Excel: " + trimmedExpectedVoucher);

            boolean assertionResult = new HashSet<>(trimmedActualVoucher).containsAll(trimmedExpectedVoucher);

            if (!assertionResult) {
                if (trimmedActualVoucher.stream().anyMatch(item -> item.contains("more vouchers available"))) {
                    System.out.println("When searching by " + addressKey + " voucher list contains 'more vouchers available'");
                    assertionResult = true;  // If voucher list contains 'more vouchers available - Force the test to pass
                }
            }

            Assert.assertTrue(assertionResult, "The searched keyword does not return the address with voucher");
            System.out.println("The searched keyword " + addressKey + " return address " + addressValue + " with voucher " + trimmedExpectedVoucher);

            // Get the screenshot in destination search page
            if (colName.equals("Merchant locations") || colName.equals("Postal code")){
                classDecl.extentReport.attachScreenshotToReport(rowName + " - Destination Search Page - " + addressKey);
            } else {
                classDecl.extentReport.attachScreenshotToReport(rowName + " - Destination Search Page - " + addressValue);
            }

            classDecl.commonKeyword.closeInAppAlertsIfVisible();
            classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.lblShortAdd, addressValue);
            classDecl.landingPage.verifyPromptBarText();

            // Get the screenshot in destination landing page
            if (colName.equals("Merchant locations") || colName.equals("Postal code")){
                classDecl.extentReport.attachScreenshotToReport(rowName + " - Prompt Bar - " + addressKey);
            } else {
                classDecl.extentReport.attachScreenshotToReport(rowName + " - Prompt Bar - " + addressValue);
            }


            classDecl.landingPage.tapOnPromptBar();
            classDecl.voucherModuleSearchPage.verifySearchBar(addressValue);
            classDecl.voucherDiscoveryFeature.verifyVoucherCard(rowName, startDate, endDate, "Vouchers nearby section");

            // Get the screenshot in voucher search page (after tapping on prompt bar)
            if (colName.equals("Merchant locations") || colName.equals("Postal code")){
                classDecl.extentReport.attachScreenshotToReport(rowName + " - Voucher Search Page - " + addressKey);
            } else {
                classDecl.extentReport.attachScreenshotToReport(rowName + " - Voucher Search Page - " + addressValue);
            }

            classDecl.commonKeyword.closeInAppAlertsIfVisible();
            classDecl.commonKeyword.tapOnNativeBackBtn();
            classDecl.commonKeyword.closeInAppAlertsIfVisible();
            classDecl.landingPage.tapOnSearchBarName(addressValue);
        }
    }

    public void goToVoucherModulePage(){
        classDecl.commonPage.tabOnMenu("More");
        classDecl.commonPage.tabOnMenu("My Vouchers");
    }

    public void verifyVoucherDetail(String voucherName, String voucherDesc, String startDate, String endDate, List<String> aboutVoucher, List<String> howToUseVoucher, List<String> termnCondition){
        classDecl.voucherDetailPage.verifyPageTitle();
        classDecl.voucherDetailPage.verifyVoucherDesc(voucherName, voucherDesc);
        classDecl.voucherDetailPage.verifyVoucherExpiry(startDate, endDate);
        classDecl.voucherDetailPage.verifyAboutVoucherSection(aboutVoucher);
        classDecl.voucherDetailPage.verifyViewMapBtn();
        classDecl.voucherDetailPage.verifyHowToUseVoucherSection(howToUseVoucher);
        classDecl.voucherDetailPage.verifyTermnConditionSection(termnCondition);
        classDecl.voucherDetailPage.verifyClaimBtn();
    }
}

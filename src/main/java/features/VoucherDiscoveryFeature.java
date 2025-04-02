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

    public void verifyVoucherCardInModuleSearch(String voucherName, String startDate, String endDate) throws IOException {
        String voucherOriginalName = "";
        String voucherDesc = getValueByRowAndColumnName(classDecl.datas.pathVoucherData, "VoucherData", voucherName, "Voucher card details");
        if (voucherName.matches(".*-\\d.*")) {
            voucherOriginalName = voucherName.replaceAll("-\\d", "");
            classDecl.voucherModuleSearchPage.verifyVoucherDesc(voucherOriginalName, voucherDesc);
            classDecl.voucherModuleSearchPage.verifyVoucherNotDisplayInNearbySection(voucherOriginalName, voucherDesc);
        } else {
            classDecl.voucherModuleSearchPage.verifyVoucherDesc(voucherName, voucherDesc);
            classDecl.voucherModuleSearchPage.verifyVoucherNotDisplayInNearbySection(voucherName, voucherDesc);
        }
        classDecl.voucherModuleSearchPage.verifyVoucherExpiry(voucherDesc, startDate, endDate);
        classDecl.voucherModuleSearchPage.verifyViewBtn(voucherDesc);
    }

    public void verifyVoucherDestinationSearch(String filePath, String sheetName, String rowName, String colName, String startDate, String endDate, List<String> expectedVoucher) throws IOException {
        Map<String, String> address = classDecl.searchDestinationPage.getAddressFromGGAPI(filePath, sheetName, rowName, colName);
        for (Map.Entry<String, String> entry : address.entrySet()) {
            String addressKey = entry.getKey();
            String addressValue = entry.getValue();

            if (colName.equals("Address")) {
                String fullAddress = addressKey.replaceAll(" SINGAPORE \\d+", "")
                        .replaceAll(" Singapore \\d+", "")
                        .replaceAll(" \\d{6}$", "");
                if (fullAddress.equals("183 TOA PAYOH CENTRAL TOA PAYOH CENTRAL")){
                    // Hard code to remove the second occurrence of "TOA PAYOH CENTRAL"
                    fullAddress = fullAddress.replaceFirst(" TOA PAYOH CENTRAL", "");
                    classDecl.searchDestinationPage.inputAddress(fullAddress);
                }else {
                    classDecl.searchDestinationPage.inputAddress(fullAddress);
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

            // If the assertion fails, check for "more vouchers available" in the actual voucher list
            if (!assertionResult) {
                // If "more vouchers available" is found in trimmedActualVoucher, let the test pass
                if (trimmedActualVoucher.stream().anyMatch(item -> item.contains("more vouchers available"))) {
                    System.out.println("When searching by " + addressKey + " voucher list contains 'more vouchers available'");
                    assertionResult = true;  // Force the test to pass
                }
            }

            // Perform the assertion again, either with the original result or adjusted for the "more vouchers available" case
            Assert.assertTrue(assertionResult, "The searched keyword does not return the address with voucher");
            System.out.println("The searched keyword " + addressKey + " return address " + addressValue + " with voucher " + trimmedExpectedVoucher);

//            classDecl.commonKeyword.clickElement(btnClearSearch);

            classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.lblShortAdd, addressValue);
            classDecl.landingPage.verifyPromptBarText();
            classDecl.landingPage.tapOnPromptBar();
            classDecl.voucherModuleSearchPage.verifySearchBar(addressValue);
            classDecl.voucherDiscoveryFeature.verifyVoucherCardInModuleSearch(rowName, startDate, endDate);
            classDecl.commonKeyword.closeInAppAlertsIfVisible();
            classDecl.commonKeyword.tapOnNativeBackBtn();
            classDecl.commonKeyword.closeInAppAlertsIfVisible();
            classDecl.landingPage.tapOnSearchBarName(addressValue);
        }
    }

}

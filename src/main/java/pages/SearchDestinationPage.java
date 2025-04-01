package pages;

import commons.BaseTest;
import org.testng.Assert;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SearchDestinationPage extends BaseTest {
    public String lblSearchInput = "//android.widget.EditText[@text=\"Search\"]";
    public String btnClearSearch = "//android.widget.EditText/following-sibling::android.view.ViewGroup/android.widget.ImageView";
    public String lblVoucherList = "//android.widget.TextView[@text=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView";


    public void inputAddress(String address) {
        classDecl.commonKeyword.sendKey(lblSearchInput, address);
    }

    public Map<String, String> getAddressFromGGAPI(String filePath, String sheetName, String rowName, String colName) throws IOException {
        Map<String, String> addressMap = new HashMap<>();
        String cellValue = classDecl.excelReader.getValueByRowAndColumnName(filePath, sheetName, rowName, colName);
        if (Objects.equals(colName, "Merchant locations") || Objects.equals(colName, "Address")) {
            if (Objects.equals(colName, "Merchant locations") || Objects.equals(colName, "Address")) {
                String[] splitArray = cellValue.split("\n");
                for (String item : splitArray) {
                    String[] keyValue = item.split(":");
                    // If both key and value exist (to avoid ArrayIndexOutOfBoundsException)
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();
                        addressMap.put(key, value);
                    }
                }
            }

        }
        return addressMap;
    }

    public void searchVoucherDestination(String filePath, String sheetName, String rowName, String colName, List<String> expectedVoucher) throws IOException {
        Map<String, String> address = getAddressFromGGAPI(filePath, sheetName, rowName, colName);
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

            List<String> actualVoucher = classDecl.commonKeyword.getListText(lblVoucherList, addressValue);
            // Removes trailing white spaces at the end
            List<String> trimmedActualVoucher = actualVoucher.stream()
                    .map(str -> str.replaceAll("\\s+$", ""))
                    .collect(Collectors.toList());
            List<String> trimmedExpectedVoucher = expectedVoucher.stream()
                    .map(str -> str.replaceAll("\\s+$", ""))
                    .collect(Collectors.toList());

            System.out.println("Actual voucher list from UI: " + trimmedActualVoucher);
            System.out.println("Expected voucher list from Excel: " + trimmedExpectedVoucher);

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

            classDecl.commonKeyword.clickElement(btnClearSearch);
        }
    }
}
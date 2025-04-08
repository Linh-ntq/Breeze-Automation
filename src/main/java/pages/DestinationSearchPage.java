package pages;

import commons.BaseTest;
import java.io.IOException;
import java.util.*;

public class DestinationSearchPage extends BaseTest {
    public String lblSearchInput = "//android.widget.EditText[@text=\"Search\"]";
    public String btnClearSearch = "//android.widget.EditText/following-sibling::android.view.ViewGroup/android.widget.ImageView";
    public String lblVoucherList = "//android.widget.TextView[@text=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView";
    public String lblShortAdd = "//android.widget.TextView[@text=\"%s\"]";

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
}
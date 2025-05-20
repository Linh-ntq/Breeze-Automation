package pages;

import commons.BaseTest;
import org.testng.Assert;

public class DestinationSearchPage extends BaseTest {
    public String lblSearchInput = "//android.widget.EditText[@text=\"Search\"]";
    public String btnClearSearch = "//android.widget.EditText/following-sibling::android.view.ViewGroup/android.widget.ImageView";
    public String lblVoucherList = "//android.widget.TextView[@text=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView";
    public String lblShortAdd = "//android.widget.TextView[@text=\"%s\"]";
    public String lblVoucherAddress = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.widget.TextView[1]";
    public String xpath1 = "//android.widget.TextView[@text=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[@text=\"%s\"]"; //short address - voucher desc
    public String xpath2 = "//android.widget.TextView[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='%s']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[@text=\"%s\"]"; //short address - voucher desc
    public String lblVoucher = xpath1 + " | " + xpath2;
    public String xpath3 = "//android.widget.TextView[@text=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,\"more voucher available\")]"; //short address
    public String xpath4 = "//android.widget.TextView[@text=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,\"more vouchers available\")]"; //short address
    public String lblhiddenVoucher = xpath3 + " | " + xpath4;
    public void inputAddress(String address) {
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        classDecl.commonKeyword.sendKey(lblSearchInput, address);
    }

    public String getVoucherAddressText(String filePath, String sheetName, String rowName) {
        String voucherDesc = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Voucher card details").get(0);
        String addressVoucher = "";
        String moreVoucherXpath = "//android.widget.TextView[contains(@text, 'more voucher']/ancestor::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.widget.TextView[1]";

        try {
            addressVoucher = classDecl.commonKeyword.getText(lblVoucherAddress, voucherDesc);
        } catch (Exception e) {
            if (classDecl.commonKeyword.elementIsVisible(moreVoucherXpath)){
                addressVoucher = classDecl.commonKeyword.getText(lblVoucherAddress, moreVoucherXpath);
            }else {
                Assert.fail("Element not found");
            }
        }
        return addressVoucher;
    }
}
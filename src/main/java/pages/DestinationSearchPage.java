package pages;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class DestinationSearchPage extends BaseTest {
    public String lblSearchInput = "//android.widget.EditText[@text=\"Search\"]";
    public String btnClearSearch = "//android.widget.EditText/following-sibling::android.view.ViewGroup/android.widget.ImageView";
    public String lblVoucherList = "//android.widget.TextView[@text=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView";
    public String lblVoucherAddress = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.widget.TextView[1]";
    public String xpath1 = "//android.widget.TextView[@text=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[@text=\"%s\"]"; //short address - voucher desc
    public String xpath2 = "//android.widget.TextView[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[@text=\"%s\"]"; //short address - voucher desc
    public String lblVoucher = xpath1 + " | " + xpath2;
    public String xpath3 = "//android.widget.TextView[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,\"more voucher available\")]"; //short address
    public String xpath4 = "//android.widget.TextView[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')=\"%s\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.TextView[contains(@text,\"more vouchers available\")]"; //short address
    public String lblhiddenVoucher = xpath3 + " | " + xpath4;
    public String btnBack = "//android.widget.EditText[@text=\"Search\"]/ancestor::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.widget.ImageView";
    public String lblSingleVoucher = "//android.widget.TextView[@text=\"%s\"]";
    public String xpath5 = "//android.widget.TextView[contains(@text,\"more voucher available\")]";
    public String xpath6 = "//android.widget.TextView[contains(@text,\"more vouchers available\")]";
    public String lblSingleHiddenVoucher = xpath5 + " | " + xpath6;


    public void inputAddress(String address) {
        classDecl.commonKeyword.sendKey(lblSearchInput, address);
    }

    public String getVoucherAddressText(String filePath, String sheetName, String rowName) {
        String voucherDesc = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Voucher card details").get(0);
        if (voucherDesc.contains("TM")) {
            voucherDesc = voucherDesc.replaceAll("TM", "™");
        }
        if (voucherDesc.contains("'")) {
            voucherDesc = voucherDesc.replaceAll("'", "’");
        }

        String addressVoucher = "";
        String moreVoucherXpath = "//android.widget.TextView[contains(@text, 'more voucher')]/ancestor::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.widget.TextView[1]";

        if (voucherDesc.contains("%")) {
            String lblAddress = lblVoucherAddress.replace("%s", voucherDesc);
            System.out.println("The address after replacing: " + lblAddress);
            classDecl.commonKeyword.scrollUntilEitherElementVisible(lblAddress, moreVoucherXpath);

            if (classDecl.commonKeyword.elementIsVisible(lblAddress)) {
                addressVoucher = classDecl.commonKeyword.getText(lblAddress);
            } else {
                addressVoucher = classDecl.commonKeyword.getText(moreVoucherXpath);
            }

        } else {
            classDecl.commonKeyword.scrollUntilEitherElementVisible(lblVoucherAddress, moreVoucherXpath, voucherDesc);
            if (classDecl.commonKeyword.elementIsVisible(lblVoucherAddress, voucherDesc)) {
                addressVoucher = classDecl.commonKeyword.getText(lblVoucherAddress, voucherDesc);
            } else {
                addressVoucher = classDecl.commonKeyword.getText(moreVoucherXpath);
            }
        }
        return addressVoucher != null ? addressVoucher : "";
    }

    public boolean voucherIsVisible(String filePath, String sheetName, String rowName, String moreAvailVouchers) {
        String voucherDesc = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Voucher card details").get(0);
        if (voucherDesc.contains("TM")) {
            voucherDesc = voucherDesc.replaceAll("TM", "™");
        }
        if (voucherDesc.contains("'")) {
            voucherDesc = voucherDesc.replaceAll("'", "’");
        }
        List<WebElement> elements;
        WebElement firstElement;

        try {
            if (moreAvailVouchers.equals("display")) {
                elements = driver.findElements(By.xpath(lblSingleHiddenVoucher));
            } else {
                String voucher = String.format(lblSingleVoucher, voucherDesc);
                elements = driver.findElements(By.xpath(voucher));
            }
            firstElement = elements.get(0);
            waitDriverApp.until(ExpectedConditions.visibilityOf(firstElement));
            return true;

        } catch (TimeoutException e) {
            return false;
        } catch (Exception e) {
            System.out.println("Error in elementIsVisible: " + e.getMessage());
            return false;
        }
    }
}
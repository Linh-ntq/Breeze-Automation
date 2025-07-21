package pages;

import commons.BaseTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoucherModuleSearchPage extends BaseTest {
    public String lblVoucherDesc = "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.TextView[@text=\"%s\"]"; // voucher name - voucher detail
    public String lblVoucherExpiry = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"%s\"]"; // voucher detail - voucher date
    public String btnView = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"View\"]"; // voucher detail
    public String btnViewMap = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"View map\"]"; // voucher detail
    public String lblVoucherNameAtNearby1 = "//android.widget.TextView[@text=\"Vouchers nearby\"]/following-sibling::android.widget.ScrollView//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.TextView[@text=\"%s\"]"; // voucher name - voucher detail (tap on prompt bar)
    public String lblVoucherNameAtNearby2 = "//android.widget.TextView[@text=\"Vouchers nearby\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.TextView[@text=\"%s\"]"; // voucher name - voucher detail (voucher module search)
    public String lblSearchBarName = "//android.widget.EditText[@text=\"%s\"]";
    public String lblSearchBar = "//android.widget.EditText[@text=\"Search\"]";
    public String btnClearSearch = "//android.widget.EditText/following-sibling::android.view.ViewGroup/android.widget.ImageView";
    public String lblNoVoucherFound = "//android.widget.TextView[@text=\"No vouchers found.\"]";
    public String lblVouchersNearby = "//android.widget.TextView[@text=\"Vouchers nearby\"]";

    public void verifyVoucherExpiry(String voucherDesc, String startDate, String endDate, String ... carNo) {
        if (voucherDesc.contains("TM")){
            voucherDesc = voucherDesc.replaceAll("TM",  "™");
        }

        Logger logger = Logger.getLogger(getClass().getName());

        Date currentDate = new Date();
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

        try {
            Date startD = inputFormat.parse(startDate);
            Date endD = inputFormat.parse(endDate);

            String formattedStart = outputFormat.format(startD);
            String formattedEnd = outputFormat.format(endD);

            if (carNo != null && carNo.length > 0) {
                if (startD.before(currentDate)) {
                        classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherExpiry, voucherDesc, "Valid until " + formattedEnd + " (" + carNo[0] + ")");
                    } else {
                        String xpath = "//android.view.ViewGroup/android.widget.TextView[contains(@text, 'Valid for use from')]";
                        if (classDecl.commonKeyword.elementIsVisible(xpath)){
                            classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherExpiry, voucherDesc, "Valid for use from " + formattedStart + " to " + formattedEnd + " (" + carNo[0] + ")");
                        } else {
                            classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherExpiry, voucherDesc, "Valid from " + formattedStart + " to " + formattedEnd + " (" + carNo[0] + ")");
                        }
                    }
            } else {
                if (startD.before(currentDate)) {
                    classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherExpiry, voucherDesc, "Valid until " + formattedEnd);
                } else {
                    String xpath = "//android.view.ViewGroup/android.widget.TextView[contains(@text, 'Valid for use from')]";
                    if (classDecl.commonKeyword.elementIsVisible(xpath)){
                        classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherExpiry, voucherDesc, "Valid for use from " + formattedStart + " to " + formattedEnd);
                    } else {
                        classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherExpiry, voucherDesc, "Valid from " + formattedStart + " to " + formattedEnd);
                    }
                }
            }

        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Error parsing voucher expiry dates", e);
        }
    }

    public void verifyViewBtn(String voucherDesc) {
        if (voucherDesc.contains("TM")){
            voucherDesc = voucherDesc.replaceAll("TM",  "™");
        }
        classDecl.commonKeyword.scrollUntilElementVisible(btnView, voucherDesc);
    }

    public void verifyViewMapBtn(String voucherDesc) {
        if (voucherDesc.contains("TM")){
            voucherDesc = voucherDesc.replaceAll("TM",  "™");
        }
        classDecl.commonKeyword.scrollUntilElementVisible(btnViewMap, voucherDesc);
    }

    public void verifyVoucherAtSearchedDestSection(String voucherName, String voucherDesc) {
        if (voucherName.contains("'")){
            voucherName = voucherName.replaceAll("'", "’");
        }

        if (voucherDesc.contains("TM")){
            voucherDesc = voucherDesc.replaceAll("TM",  "™");
        }
        classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherDesc, voucherName, voucherDesc);
        classDecl.commonKeyword.verifyElementNotVisible(lblVoucherNameAtNearby1, voucherName, voucherDesc);
        classDecl.commonKeyword.verifyElementNotVisible(lblVoucherNameAtNearby2, voucherName, voucherDesc);
    }

    public void verifyVoucherAtNearbySection(String voucherName, String voucherDesc) {
        if (voucherName.contains("'")){
            voucherName = voucherName.replaceAll("'", "’");
        }

        if (voucherDesc.contains("TM")){
            voucherDesc = voucherDesc.replaceAll("TM",  "™");
        }
        classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherNameAtNearby1, voucherName, voucherDesc);
    }

    public void verifySearchBar(String voucherShortAdd) {
        classDecl.commonKeyword.waitForElementVisible(lblSearchBarName, voucherShortAdd);
    }

    public void inputAddress(String address) {
        classDecl.commonKeyword.sendKey(lblSearchBar, address);
    }

}
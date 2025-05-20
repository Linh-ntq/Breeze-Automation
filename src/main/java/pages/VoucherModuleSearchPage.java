package pages;

import commons.BaseTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    public void verifyVoucherExpiry(String voucherDesc, String startDate, String endDate) {
        Logger logger = Logger.getLogger(getClass().getName());

        Date currentDate = new Date();
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

        try {
            Date startD = inputFormat.parse(startDate);
            Date endD = inputFormat.parse(endDate);

            String formattedStart = outputFormat.format(startD);
            String formattedEnd = outputFormat.format(endD);

            if (startD.before(currentDate)) {
                classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherExpiry, voucherDesc, "Valid until " + formattedEnd);
            } else {
                classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherExpiry, voucherDesc, "Valid from " + formattedStart + " to " + formattedEnd);
            }
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Error parsing voucher expiry dates", e);
        }
    }

    public void verifyViewBtn(String voucherDesc) {
        classDecl.commonKeyword.scrollUntilElementVisible(btnView, voucherDesc);
    }

    public void verifyViewMapBtn(String voucherDesc) {
        classDecl.commonKeyword.scrollUntilElementVisible(btnViewMap, voucherDesc);
    }

    public void verifyVoucherAtSearchedDestSection(String voucherName, String voucherDesc) {
        String voucherOriginalName = "";
        if (voucherName.matches(".*-\\d.*")) {
            voucherOriginalName = voucherName.replaceAll("-\\d", "");
            classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherDesc, voucherOriginalName, voucherDesc);
            classDecl.commonKeyword.elementNotVisible(lblVoucherNameAtNearby1, voucherOriginalName, voucherDesc);
            classDecl.commonKeyword.elementNotVisible(lblVoucherNameAtNearby2, voucherOriginalName, voucherDesc);
        } else {
            classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherDesc, voucherName, voucherDesc);
            classDecl.commonKeyword.elementNotVisible(lblVoucherNameAtNearby1, voucherName, voucherDesc);
            classDecl.commonKeyword.elementNotVisible(lblVoucherNameAtNearby2, voucherName, voucherDesc);
        }
    }

    public void verifyVoucherAtNearbySection(String voucherName, String voucherDesc) {
        String voucherOriginalName;
        if (voucherName.matches(".*-\\d.*")) {
            voucherOriginalName = voucherName.replaceAll("-\\d", "");
            classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherNameAtNearby1, voucherOriginalName, voucherDesc);
        } else {
            classDecl.commonKeyword.scrollUntilElementVisible(lblVoucherNameAtNearby1, voucherName, voucherDesc);
        }
    }

    public void verifySearchBar(String voucherShortAdd) {
        classDecl.commonKeyword.waitForElementVisible(lblSearchBarName, voucherShortAdd);
    }

}
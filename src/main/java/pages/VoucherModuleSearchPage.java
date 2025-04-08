package pages;

import commons.BaseTest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VoucherModuleSearchPage extends BaseTest {
    public String lblVoucherDesc = "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.TextView[@text=\"%s\"]"; // voucher name - voucher detail
    public String lblVoucherExpiry = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"%s\"]"; // voucher detail - voucher date
    public String btnView = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"View\"]"; // voucher detail
    public String btnViewMap = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"View map\"]"; // voucher detail
    public String lblVoucherNameAtNearby1 = "//android.widget.TextView[@text=\"Vouchers nearby\"]/following-sibling::android.widget.ScrollView//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.TextView[@text=\"%s\"]"; // voucher name - voucher detail (tap on prompt bar)
    public String lblVoucherNameAtNearby2 = "//android.widget.TextView[@text=\"Vouchers nearby\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"%s\"]/following-sibling::android.widget.TextView[@text=\"%s\"]"; // voucher name - voucher detail (voucher module search)
    public String lblSearchBarName = "//android.widget.EditText[@text=\"%s\"]";


    public void verifyVoucherExpiry(String voucherDesc, String startDate, String endDate) {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        try {
            Date startD = sdf.parse(startDate);
            Date endD = sdf.parse(endDate);

            if (startD.before(currentDate)) {
                classDecl.commonKeyword.waitForElementVisible(lblVoucherExpiry, voucherDesc, "Valid from " + startD + " to " + endD);
            } else {
                classDecl.commonKeyword.waitForElementVisible(lblVoucherExpiry, voucherDesc, "Valid until " + endD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyViewBtn(String voucherDesc) {
        classDecl.commonKeyword.waitForElementVisible(btnView, voucherDesc);
    }

    public void verifyViewMapBtn(String voucherDesc) {
        classDecl.commonKeyword.waitForElementVisible(btnViewMap, voucherDesc);
    }

    public void verifyVoucherAtSearchedDestSection(String voucherName, String voucherDesc) {
        try {
            classDecl.commonKeyword.waitForElementVisible(lblVoucherDesc, voucherName, voucherDesc);
        } catch (Exception e) {
            System.out.println("The voucher not found. Attempting to scroll...");
            classDecl.commonKeyword.scroll("DOWN", 0.8);
            try {
                classDecl.commonKeyword.waitForElementVisible(lblVoucherDesc, voucherName, voucherDesc);
            } catch (Exception retryException) {
                System.out.println("Voucher still not found after scrolling.");
                throw new Error("Voucher with name '" + voucherName + "' and description '" + voucherDesc + "' was not found.");
            }
        }
        classDecl.commonKeyword.elementNotVisible(lblVoucherNameAtNearby1, voucherName, voucherDesc);
        classDecl.commonKeyword.elementNotVisible(lblVoucherNameAtNearby2, voucherName, voucherDesc);
    }

    public void verifyVoucherAtNearbySection(String voucherName, String voucherDesc) {
        try {
            classDecl.commonKeyword.waitForElementVisible(lblVoucherNameAtNearby1, voucherName, voucherDesc);
        } catch (Exception e) {
            System.out.println("The voucher not found. Attempting to scroll...");
            classDecl.commonKeyword.scroll("DOWN", 0.5);
            try {
                classDecl.commonKeyword.waitForElementVisible(lblVoucherNameAtNearby1, voucherName, voucherDesc);
            } catch (Exception retryException) {
                System.out.println("Voucher still not found after scrolling.");
                throw new Error("Voucher with name '" + voucherName + "' and description '" + voucherDesc + "' was not found.");
            }
        }

    }

    public void verifySearchBar(String voucherShortAdd) {
        classDecl.commonKeyword.waitForElementVisible(lblSearchBarName, voucherShortAdd);
    }

}

package pages;

import commons.BaseTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VoucherModuleSearchPage extends BaseTest {
    public String lblVoucherDesc = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.widget.TextView[@text=\"%s\"]"; // voucher name - voucher detail
    public String lblVoucherExpiry = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"%s\"]"; // voucher detail - voucher date
    public String btnView = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"View\"]"; // voucher detail
    public String lblVoucherNameUnderNearby = "//android.widget.TextView[@text=\"%s\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"%s\"]"; // voucher name - voucher detail
    public String lblSearchBarName = "//android.widget.EditText[@text=\"%s\"]";

    public void verifyVoucherDesc(String voucherName, String voucherDesc) {
        classDecl.commonKeyword.waitForElementVisible(lblVoucherDesc, voucherName, voucherDesc);
    }

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

    public void verifyVoucherNotDisplayInNearbySection(String voucherName, String voucherDesc) {
        classDecl.commonKeyword.elementNotVisible(lblVoucherNameUnderNearby, voucherName, voucherDesc);
    }

    public void verifySearchBar(String voucherShortAdd) {
        classDecl.commonKeyword.waitForElementVisible(lblSearchBarName, voucherShortAdd);
    }

}

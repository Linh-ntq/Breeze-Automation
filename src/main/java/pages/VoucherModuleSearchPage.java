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
                System.out.println("test1");
            } else {
                classDecl.commonKeyword.waitForElementVisible(lblVoucherExpiry, voucherDesc, "Valid until " + endD);
                System.out.println("test2");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
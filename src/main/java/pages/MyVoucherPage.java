package pages;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyVoucherPage extends BaseTest {
    public String btnView = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"View\"]"; // voucher detail
    public String btnSearch = "//android.widget.TextView[@text=\"My Vouchers\"]/ancestor::android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView";
    public String btnFilterPill = "//android.widget.TextView[@text=\"%s\"]";
    public String lblCategory = "//android.widget.TextView[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = \"%s\"]";
    public String btnApply = "//android.widget.TextView[@text=\"Apply\"]";
    public String lblTabName = "//android.widget.TextView[contains(@text, \"%s\")]";

    public void clickViewBtn(String voucherDesc) {
        if (voucherDesc.contains("TM")){
            voucherDesc = voucherDesc.replaceAll("TM",  "™");
        }
        if (voucherDesc.contains("%")){
            String newXpath = btnView.replace("%s", voucherDesc);
            waitDriverApp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpath)));
            driver.findElement(By.xpath(newXpath)).click();

        } else {
            classDecl.commonKeyword.clickElement(btnView, voucherDesc);
        }

    }

    public void clickSearchBtn(){
        classDecl.commonKeyword.pause(2); // pause to wait until the search button ready to click
        classDecl.commonKeyword.clickElement(btnSearch);
    }

    public void clickVoucherPill(String pillName){
        classDecl.commonKeyword.clickElement(btnFilterPill, pillName);
    }

    public void selectCategory(String voucherCategory){
        classDecl.commonKeyword.scrollUntilElementVisible(lblCategory, voucherCategory);
        classDecl.commonKeyword.clickElement(lblCategory, voucherCategory);
    }

    public void clickApplyBtn(){
        classDecl.commonKeyword.clickElement(btnApply);
    }

    public void clickOnTab(String tabName){
        classDecl.commonKeyword.clickElement(lblTabName, tabName);
    }

    public void verifyUtilizationDate() {
        Logger logger = Logger.getLogger(getClass().getName());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        try {
            String formattedDate = outputFormat.format(new Date());
            classDecl.commonKeyword.waitForElementVisible(classDecl.voucherDetailPage.lblVoucherExpiry, "Used on " + formattedDate);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing voucher expiry dates", e);
        }
    }

    public void verifyUsedTag() {
        classDecl.commonKeyword.waitForElementVisible(classDecl.voucherDetailPage.lblVoucherExpiry, "Used");
    }
}

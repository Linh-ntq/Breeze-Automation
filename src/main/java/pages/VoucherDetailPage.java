package pages;

import commons.BaseTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoucherDetailPage extends BaseTest {
    public static final Logger logger = Logger.getLogger(VoucherDetailPage.class.getName());
    public String pageTitle = "//android.widget.TextView[@text=\"Claim Voucher\"]";
    public String lblVoucherDesc = "//android.widget.TextView[@text=\"%s\"]";
    public String lblVoucherExpiry = "//android.widget.TextView[@text=\"%s\"]";
    public String lblAboutVoucherDesc = "//android.widget.TextView[@text=\"About this voucher\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"•\"]/following-sibling::android.view.ViewGroup[@resource-id=\"html\"]//android.widget.TextView[@text=\"%s\"]";
    public String btnViewMap = "//android.widget.TextView[@text=\"Where to use voucher?\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"View map\"]";
    public String lblHowToUseDesc = "//android.widget.TextView[@text=\"How to use voucher?\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"%s\"]/following-sibling::android.view.ViewGroup[@resource-id=\"html\"]//android.widget.TextView[@text=\"%s\"]";
    public String xpath1 = "//android.widget.TextView[@text=\"Terms & Conditions\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"•\"]/following-sibling::android.view.ViewGroup[@resource-id=\"html\"]//android.widget.TextView[@text=\"%s\"]";
    public String xpath2 = "//android.view.ViewGroup//android.widget.TextView[@text=\"•\"]/following-sibling::android.view.ViewGroup[@resource-id=\"html\"]//android.widget.TextView[@text=\"%s\"]";
    public String lblTermnCondition = xpath1 + " | " + xpath2;


    public String btnClaim = "//android.widget.TextView[@text=\"Claim on FairPrice Group app\"]";

    public void verifyPageTitle(){
        classDecl.commonKeyword.waitForElementVisible(pageTitle);
    }

    public void verifyVoucherDesc(String voucherName, String voucherDesc){
        if (voucherName.matches(".*-\\d.*")) {
            String voucherOriginalName = voucherName.replaceAll("-\\d", "");
            classDecl.commonKeyword.waitForElementVisible(lblVoucherDesc, voucherOriginalName + " - " + voucherDesc);
        } else {
            classDecl.commonKeyword.waitForElementVisible(lblVoucherDesc, voucherName + " - " + voucherDesc);
        }

    }

    public void verifyVoucherExpiry(String startDate, String endDate) {
        Logger logger = Logger.getLogger(getClass().getName());

        Date currentDate = new Date();
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);  // Parse input like 01-Jan-2025
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH); // Desired output like 01 Jan 2025

        try {
            Date startD = inputFormat.parse(startDate);
            Date endD = inputFormat.parse(endDate);

            String formattedStart = outputFormat.format(startD);
            String formattedEnd = outputFormat.format(endD);

            if (startD.before(currentDate)) {
                classDecl.commonKeyword.waitForElementVisible(lblVoucherExpiry, "Valid until " + formattedEnd);

                System.out.println("test111");
            } else {
                classDecl.commonKeyword.waitForElementVisible(lblVoucherExpiry, "Valid from " + formattedStart + " to " + formattedEnd);
                System.out.println("test222");
            }
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Error parsing voucher expiry dates", e);
        }
    }

//    public void verifyVoucherExpiry(String startDate, String endDate) {
//        Date currentDate = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
//
//        try {
//            Date startD = sdf.parse(startDate);
//            Date endD = sdf.parse(endDate);
//
//            if (startD.before(currentDate)) {
//                classDecl.commonKeyword.waitForElementVisible(lblVoucherExpiry, "Valid from " + startD + " to " + endD);
//                System.out.println("test111");
//            } else {
//                classDecl.commonKeyword.waitForElementVisible(lblVoucherExpiry, "Valid until " + endD);
//                System.out.println("test222");
//            }
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Error parsing voucher expiry dates", e);
//        }
//    }

    public void verifyAboutVoucherSection(List<String> aboutVoucherList){
        for (String item : aboutVoucherList) {
            System.out.println("About this voucher: " + item);
            classDecl.commonKeyword.scrollUntilElementVisible(lblAboutVoucherDesc, item.trim());
        }
    }

    public void verifyHowToUseVoucherSection(List<String> howToUseList){
        for (int i = 0; i < howToUseList.size(); i++){
            String index = (i + 1) + ".";
            System.out.println("How to use voucher: " + index + " " + howToUseList.get(i));
            classDecl.commonKeyword.scrollUntilElementVisible(lblHowToUseDesc, index, howToUseList.get(i).trim());
        }
    }

    public void verifyTermnConditionSection(List<String> TCList) {
        for (String item : TCList) {
            System.out.println("Terms & Conditions: " + item);
            classDecl.commonKeyword.scrollUntilElementVisible(lblTermnCondition, item.trim(), item.trim());
        }
    }

    public void verifyViewMapBtn(){
        classDecl.commonKeyword.scrollUntilElementVisible(btnViewMap);
        classDecl.commonKeyword.waitForElementVisible(btnViewMap);
    }

    public void verifyClaimBtn(){
        classDecl.commonKeyword.waitForElementVisible(btnClaim);
    }
}

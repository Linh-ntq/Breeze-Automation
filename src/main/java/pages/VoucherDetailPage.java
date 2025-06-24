package pages;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoucherDetailPage extends BaseTest {
    public String lblPageTitle = "//android.widget.TextView[@text=\"%s\"]";
    public String lblVoucherDesc = "//android.widget.TextView[@text=\"%s\"]";
    public String lblVoucherExpiry = "//android.widget.TextView[@text=\"%s\"]";
    public String lblAboutVoucherDesc = "//android.widget.TextView[@text=\"About this voucher\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"•\"]/following-sibling::android.view.ViewGroup[@resource-id=\"html\"]//android.widget.TextView[@text=\"%s\"]";
    public String xpath1 = "//android.widget.TextView[@text=\"Where to use voucher?\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"View map\"]";
    public String xpath2 = "//android.widget.TextView[@text=\"Where to use this voucher?\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"View map\"]";
    public String lblWhereToUse = xpath1 + " | " + xpath2;
    public String lblHowToUseDesc = "//android.widget.TextView[@text=\"How to use voucher?\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"%s\"]/following-sibling::android.view.ViewGroup[@resource-id=\"html\"]//android.widget.TextView[@text=\"%s\"]";
    public String xpath3 = "//android.widget.TextView[@text=\"Terms & Conditions\"]/following-sibling::android.view.ViewGroup//android.widget.TextView[@text=\"•\"]/following-sibling::android.view.ViewGroup[@resource-id=\"html\"]//android.widget.TextView[@text=\"%s\"]";
    public String xpath4 = "//android.view.ViewGroup//android.widget.TextView[@text=\"•\"]/following-sibling::android.view.ViewGroup[@resource-id=\"html\"]//android.widget.TextView[@text=\"%s\"]";
    public String lblTermnCondition = xpath3 + " | " + xpath4;
    public String btnClaim = "//android.widget.TextView[@text=\"%s\"]";
    public String lblClaimPopup = "//android.widget.TextView[@text=\"%s\"]";
    public String btnUseVoucher = "//android.widget.TextView[@text=\"Use voucher now\"]";
    public String lblCarpark = "//android.widget.TextView[@text=\"Vouchers claimed will be registered for use for your current vehicle details \"]";
    public String lblVehicleNo = "//android.widget.TextView[@text=\"Vehicle licence plate no.\"]";
    public String lblVehicleNoEmpty = "//android.widget.EditText[@text=\"Vehicle licence plate no.\"]";
    public String lblOBUNo = "//android.widget.TextView[@text=\"IU/ OBU no.\"]";
    public String lblOBUNoEmpty = "//android.widget.EditText[@text=\"IU/ OBU no.\"]";

    // Create a new wait element method to reduce wait time
    public void waitForElementVisible(String xpathExpression, String... text) {
        // Temporarily disable implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            if (text != null && text.length > 0) {
                String xpath = String.format(xpathExpression, (String[]) text);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            }
        } finally {
            // Restore implicit wait
            driver.manage().timeouts().implicitlyWait(timeoutExWait);
        }
    }

    // Create a new scroll to element method to reduce wait time
    public void scrollUntilElementVisible(String xpathExpression, String... text) {
        boolean hasText = text != null && text.length > 0;
        int maxScrolls = 8;
        for (int attempt = 0; attempt < maxScrolls; attempt++) {
            try {
                if (hasText) {
                    waitForElementVisible(xpathExpression, text);
                } else {
                    waitForElementVisible(xpathExpression);
                }
                // Element found, exit method
                return;
            } catch (Exception e) {
                System.out.println("Attempt " + (attempt + 1) + ": Element not found. Scrolling...");
                classDecl.commonKeyword.scroll("DOWN", 0.2);
            }
        }

        // If element not found, throw error
        String message = "Element still not found after scrolling " + maxScrolls + " times. Element: " + xpathExpression;
        if (hasText) {
            message += ", text: " + String.join(", ", text);
        }
        throw new Error(message);
    }

    public void verifyPageTitle(String pageTitle) {
        classDecl.commonKeyword.waitForElementVisible(lblPageTitle, pageTitle);
    }

    public void verifyVoucherDesc(String voucherName, String voucherDesc) {
        if (voucherName.contains("'")) {
            voucherName = voucherName.replaceAll("'", "’");
        }

        if (voucherDesc.contains("TM")) {
            voucherDesc = voucherDesc.replaceAll("TM", "™");
        }
        classDecl.commonKeyword.waitForElementVisible(lblVoucherDesc, voucherName + " - " + voucherDesc);

    }

    public void verifyVoucherExpiry(String startDate, String endDate) {
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
                waitForElementVisible(lblVoucherExpiry, "Valid until " + formattedEnd);
            } else {
                waitForElementVisible(lblVoucherExpiry, "Valid from " + formattedStart + " to " + formattedEnd);
            }
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Error parsing voucher expiry dates", e);
        }
    }

    public void verifyAboutVoucherSection(List<String> aboutVoucherList) {
        for (String item : aboutVoucherList) {
            if (item.contains("TM")) {
                item = item.replaceAll("TM", "™");
            }
            System.out.println("About this voucher: " + item);
            if (item.contains("<")) {
                String regex = "<\\s*a[^>]*>(.*?)<\\s*/\\s*a\\s*>";
                String removeTag = item.replaceAll("(?i)" + regex, "$1");
                scrollUntilElementVisible(lblAboutVoucherDesc, removeTag.trim());
            } else {
                scrollUntilElementVisible(lblAboutVoucherDesc, item.trim());
            }
        }
    }

    public void verifyHowToUseVoucherSection(List<String> howToUseList) {
        for (int i = 0; i < howToUseList.size(); i++) {
            String item = "";
            if (howToUseList.get(i).contains("TM")) {
                item = howToUseList.get(i).replaceAll("TM", "™");
            } else {
                item = howToUseList.get(i);
            }
            String index = (i + 1) + ".";
            System.out.println("How to use voucher: " + index + " " + item);
            if (item.contains("<")) {
                String regex = "<\\s*a[^>]*>(.*?)<\\s*/\\s*a\\s*>";
                String removeTag = item.replaceAll("(?i)" + regex, "$1");
                scrollUntilElementVisible(lblHowToUseDesc, index, removeTag.trim());
            } else {
                scrollUntilElementVisible(lblHowToUseDesc, index, item.trim());
            }
        }
    }

    public void verifyTermnConditionSection(List<String> TCList) {
        for (String item : TCList) {
            if (item.contains("TM")) {
                item = item.replaceAll("TM", "™");
            }
            System.out.println("Terms & Conditions: " + item);
            if (item.contains("<")) {
                String regex = "<\\s*a[^>]*>(.*?)<\\s*/\\s*a\\s*>";
                String removeTag = item.replaceAll("(?i)" + regex, "$1");
                scrollUntilElementVisible(lblTermnCondition, removeTag.trim(), removeTag.trim());
            } else {
                scrollUntilElementVisible(lblTermnCondition, item.trim(), item.trim());
            }
        }
    }

    public void verifyWhereToUsSection() {
        scrollUntilElementVisible(lblWhereToUse);
    }

    public void verifyClaimBtn(String merchantName, String isExternalClaimable, String status) {
        String ClaimBtnText;
        if (merchantName.equals("ntuc")){
            if (isExternalClaimable.equals("TRUE")) {
                ClaimBtnText = "Claim on FairPrice Group app";
            } else {
                ClaimBtnText = "Claim";
            }
        } else {
            ClaimBtnText = "Claim on My Singtel App";
        }

        if (status.equals("display")) {
            classDecl.commonKeyword.waitForElementVisible(btnClaim, ClaimBtnText);

        } else {
            classDecl.commonKeyword.elementNotVisible(btnClaim, ClaimBtnText);
        }
    }

    public void verifyUseBtn(String status) {
        if (status.equals("display")) {
            classDecl.commonKeyword.waitForElementVisible(btnUseVoucher);
        } else {
            classDecl.commonKeyword.elementNotVisible(btnUseVoucher);
        }
    }

    public void clickClaimBtn(String isExternalClaimable) {
        String ClaimBtnText;
        if (isExternalClaimable.equals("TRUE")) {
            ClaimBtnText = "Claim on FairPrice Group app";
        } else {
            ClaimBtnText = "Claim";
        }
        classDecl.commonKeyword.clickElement(btnClaim, ClaimBtnText);
    }

    public void verifyRepeatedClaimTitle() {
        classDecl.commonKeyword.waitForElementVisible(lblClaimPopup, "Repeated voucher claim");
    }

    public void verifyRepeatedClaimDesc() {
        classDecl.commonKeyword.waitForElementVisible(lblClaimPopup, "You have already successfully claimed this voucher with your current membership details. Please look out for other vouchers in future!");
    }

    public void verifyRepeatedClaimButton() {
        classDecl.commonKeyword.waitForElementVisible(lblClaimPopup, "Close");
    }

    public void verifySuccessfullyClaimedTitle() {
        classDecl.commonKeyword.waitForElementVisible(lblClaimPopup, "Successfully claimed");
    }

    public void verifySuccessfullyClaimedDesc(String voucherTitle) {
        classDecl.commonKeyword.waitForElementVisible(lblClaimPopup, voucherTitle + " voucher is now ready to use");
    }

    public void verifySuccessfullyClaimedDate(String startDate, String endDate) {
        Logger logger = Logger.getLogger(getClass().getName());

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

        try {
            Date startD = inputFormat.parse(startDate);
            Date endD = inputFormat.parse(endDate);

            String formattedStart = outputFormat.format(startD);
            String formattedEnd = outputFormat.format(endD);

            classDecl.commonKeyword.waitForElementVisible(lblClaimPopup, "Valid for use from " + formattedStart + " to " + formattedEnd);
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Error parsing voucher expiry dates", e);
        }

    }

    public void verifyViewVoucherDetailsBtn() {
        classDecl.commonKeyword.waitForElementVisible(lblClaimPopup, "View voucher details");
    }

    public void verifyBackToHomeBtn() {
        classDecl.commonKeyword.waitForElementVisible(lblClaimPopup, "Back to home");
    }

    public void clickViewVoucherDetailsBtn() {
        classDecl.commonKeyword.clickElement(lblClaimPopup, "View voucher details");
    }

    public void verifyRegisterVehicleText() {
        classDecl.commonKeyword.waitForElementVisible(lblCarpark);
    }

    public void verifyVehicleDetailSection(String status) {
        if (status.equals("display")) {
            scrollUntilElementVisible(lblVehicleNo);
            scrollUntilElementVisible(lblVehicleNoEmpty);
            scrollUntilElementVisible(lblOBUNo);
            scrollUntilElementVisible(lblOBUNoEmpty);
            scrollUntilElementVisible(classDecl.vehicleSettingPage.lblGuideTitle);
            classDecl.commonKeyword.clickElement(classDecl.vehicleSettingPage.btnArrow);
            classDecl.vehicleSettingPage.verifyGuidanceField("expand");
        } else {
            classDecl.commonKeyword.elementNotVisible(lblVehicleNo);
            classDecl.commonKeyword.elementNotVisible(lblVehicleNoEmpty);
            classDecl.commonKeyword.elementNotVisible(lblOBUNo);
            classDecl.commonKeyword.elementNotVisible(lblOBUNoEmpty);
            classDecl.commonKeyword.elementNotVisible(classDecl.vehicleSettingPage.lblGuideTitle);
        }

    }

    public void clickBackToHomeBtn() {
        classDecl.commonKeyword.clickElement(lblClaimPopup, "Back to home");
    }

    public void enterVehicleDetail(String vehicleNo) {
        classDecl.commonKeyword.sendKey(lblVehicleNoEmpty, vehicleNo);
        classDecl.commonKeyword.closeKeyboard();
        classDecl.commonKeyword.sendKey(lblOBUNoEmpty, classDecl.datas.UINumber);
        classDecl.commonKeyword.closeKeyboard();
    }

}

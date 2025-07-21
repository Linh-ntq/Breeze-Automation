package features;

import commons.BaseTest;
import org.testng.Assert;
import java.util.*;
import java.util.regex.Pattern;
import static datas.ExcelReader.getValueByRowAndColumnName;

public class VoucherDiscoveryFeature extends BaseTest {

    public void verifyVoucherCard(String filePath, String sheetName, String voucherName, String startDate, String endDate, String voucherPosition, String ... carNo) {
        String voucherDesc = getValueByRowAndColumnName(filePath, sheetName, voucherName, "Voucher card details");
        String xpath1 = "//android.widget.TextView[@text=\"Voucher Details\"]/ancestor::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView";
        String xpath2 = "//android.widget.TextView[@text=\"Voucher Details\"]/ancestor::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup//android.widget.ImageView";
        String xpath = xpath1 + " | " + xpath2;
        String description = "Enjoy this parking voucher for use at Lendlease retail mall carparks.";

        if (carNo != null && carNo.length > 0 && voucherPosition.equals("Claimed tab")) { // Lendlease cp > Claimed tab
            if (classDecl.commonKeyword.elementIsVisible(xpath)) { // If user is in Voucher Details page, tap back button
                classDecl.commonKeyword.tapOnInAppBackBtn("Voucher Details");
            }
            classDecl.myVoucherPage.clickOnTab("Claimed");
            classDecl.voucherModuleSearchPage.verifyVoucherAtSearchedDestSection(voucherName, description);
            classDecl.voucherModuleSearchPage.verifyVoucherExpiry(description, startDate, endDate, carNo);
            classDecl.voucherModuleSearchPage.verifyViewMapBtn(description);
            classDecl.voucherModuleSearchPage.verifyViewBtn(description);
        } else { // Other vouchers
            if (voucherPosition.equals("History tab")) {
                if (classDecl.commonKeyword.elementIsVisible(xpath)) { // If user is in Voucher Details page, tap back button
                    classDecl.commonKeyword.tapOnInAppBackBtn("Voucher Details");
                }
                classDecl.myVoucherPage.clickOnTab("History");
                classDecl.voucherModuleSearchPage.verifyVoucherAtSearchedDestSection(voucherName, voucherDesc);
                classDecl.myVoucherPage.verifyUtilizationDate();
                classDecl.myVoucherPage.verifyUsedTag();
                classDecl.commonKeyword.verifyElementNotVisible(classDecl.voucherModuleSearchPage.btnViewMap, voucherDesc);
                classDecl.commonKeyword.verifyElementNotVisible(classDecl.voucherModuleSearchPage.btnView, voucherDesc);
            } else {
                if (voucherPosition.equals("Vouchers nearby section")) {
                    classDecl.voucherModuleSearchPage.verifyVoucherAtNearbySection(voucherName, voucherDesc);
                } else if (voucherPosition.equals("Claimed tab")) {
                    if (classDecl.commonKeyword.elementIsVisible(xpath)) { // If user is in Voucher Details page, tap back button
                        classDecl.commonKeyword.tapOnInAppBackBtn("Voucher Details");
                    }
                    classDecl.myVoucherPage.clickOnTab("Claimed");
                    classDecl.voucherModuleSearchPage.verifyVoucherAtSearchedDestSection(voucherName, voucherDesc);
                } else {
                    classDecl.voucherModuleSearchPage.verifyVoucherAtSearchedDestSection(voucherName, voucherDesc);
                }
                classDecl.voucherModuleSearchPage.verifyVoucherExpiry(voucherDesc, startDate, endDate);
                classDecl.voucherModuleSearchPage.verifyViewMapBtn(voucherDesc);
                classDecl.voucherModuleSearchPage.verifyViewBtn(voucherDesc);
            }
        }
    }

    public void goToVoucherModulePage() {
        classDecl.commonPage.tabOnMenu("More");
        classDecl.commonPage.tabOnMenu("My Vouchers");
    }

    public void verifyVoucherDetail(String voucherTab, String filePath, String sheetName, String voucherName, String voucherDesc, String startDate, String endDate, List<String> aboutVoucher, List<String> howToUseVoucher, List<String> termnCondition, String ... carNo) {
        String hideVehicleDetails = classDecl.excelReader.getVoucherData(filePath, sheetName, voucherName, "hideVehicleDetailsInput");
        String isClaimRepeatable = classDecl.excelReader.getVoucherData(filePath, sheetName, voucherName, "isClaimRepeatable");
        String isExternalClaimable = classDecl.excelReader.getVoucherData(filePath, sheetName, voucherName, "isExternalClaimable");

        System.out.println("hideVehicleDetails: " + hideVehicleDetails
                + " isClaimRepeatable: " + isClaimRepeatable
                + " isExternalClaimable: " + isExternalClaimable);
        String btnClaimFairPrice = "//android.widget.TextView[@text='Claim on FairPrice Group app']";

        if (hideVehicleDetails.equals("TRUE")) { // vehicle detail is hidden - merchant non cp voucher
            if (voucherTab.equals("Unclaimed")) { // before claim - unclaimed tab
                System.out.println("Log01");
                classDecl.voucherDetailPage.verifyPageTitle("Claim Voucher");
                classDecl.commonKeyword.verifyElementNotVisible(classDecl.voucherDetailPage.lblCarpark);
                classDecl.voucherDetailPage.verifyVoucherDesc(voucherName, voucherDesc);
                classDecl.voucherDetailPage.verifyVoucherExpiry(startDate, endDate);
                classDecl.voucherDetailPage.verifyAboutVoucherSection(aboutVoucher);
                classDecl.voucherDetailPage.verifyWhereToUsSection();
                classDecl.voucherDetailPage.verifyHowToUseVoucherSection(howToUseVoucher);
                classDecl.voucherDetailPage.verifyTermnConditionSection(termnCondition);
                classDecl.voucherDetailPage.verifyVehicleDetailSection("not display");
                classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "display");
            } else if (voucherTab.equals("Claimed")) { // after claim - claimed tab
                System.out.println("Log02");
                // If claim button = Claim on FairPrice Group app > Tap on native back button to return Breeze app
                if (classDecl.commonKeyword.elementIsVisible(btnClaimFairPrice)) {
                    System.out.println("Log03");
                    classDecl.voucherDetailPage.clickClaimBtn(isExternalClaimable);
                    if (isClaimRepeatable.equals("TRUE")
                            || (isClaimRepeatable.equals("FALSE")
                            && !classDecl.commonKeyword.elementIsVisible(classDecl.voucherDetailPage.lblClaimPopup, "Repeated voucher claim"))) {
                        System.out.println("Log04");

                        classDecl.commonKeyword.pause(2);
                        classDecl.commonKeyword.tapOnNativeRecentTabsBtn();
                        classDecl.commonKeyword.pause(2);
                        classDecl.commonKeyword.tapOnNativeRecentTabsBtn();

                        // Scroll up to go to top of page
                        classDecl.commonKeyword.pause(3);
                        classDecl.commonKeyword.scroll("UP", 0.5);
                        classDecl.commonKeyword.scroll("UP", 0.5);
                        classDecl.voucherDetailPage.verifyPageTitle("Voucher Details");
                        classDecl.commonKeyword.verifyElementNotVisible(classDecl.voucherDetailPage.lblCarpark);
                        classDecl.voucherDetailPage.verifyVoucherDesc(voucherName, voucherDesc);
                        classDecl.voucherDetailPage.verifyVoucherExpiry(startDate, endDate);
                        classDecl.voucherDetailPage.verifyAboutVoucherSection(aboutVoucher);
                        classDecl.voucherDetailPage.verifyWhereToUsSection();
                        classDecl.voucherDetailPage.verifyHowToUseVoucherSection(howToUseVoucher);
                        classDecl.voucherDetailPage.verifyTermnConditionSection(termnCondition);
                        if (isExternalClaimable.equals("TRUE")) {
                            System.out.println("Log05: hideVehicleDetails = TRUE, isClaimRepeatable = " + isClaimRepeatable + ", isExternalClaimable = TRUE, button Use voucher now and Claim on FairPrice Group");
                            classDecl.voucherDetailPage.verifyUseBtn("display");
                            classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "display");

                        } else {
                            System.out.println("Log06: hideVehicleDetails = TRUE, isClaimRepeatable = " + isClaimRepeatable + ", isExternalClaimable = FALSE, button Claim on FairPrice Group");
                            classDecl.voucherDetailPage.verifyUseBtn("display");
                            classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "not display");
                        }

                    } else {
                        System.out.println("Log07: hideVehicleDetails = TRUE, isClaimRepeatable = " + isClaimRepeatable + ", isExternalClaimable = " + isExternalClaimable + ", repeated claim voucher popup");
                        verifyRepeatedClaimPopup();
                        classDecl.extentReport.attachScreenshotToReport(voucherName + " - Repeated claim voucher");
                    }
                } else {  // If claim button = Claim  > Tap on the button return claim popup
                    System.out.println("Log08");
                    classDecl.voucherDetailPage.clickClaimBtn(isExternalClaimable);
                    // if voucher can be claimed repeatedly or voucher can be claimed only one time per eid but this is the first time voucher claimed
                    if (isClaimRepeatable.equals("TRUE")
                            || (isClaimRepeatable.equals("FALSE")
                            && !classDecl.commonKeyword.elementIsVisible(classDecl.voucherDetailPage.lblClaimPopup, "Repeated voucher claim"))) {
                        System.out.println("Log09");
                        verifySuccessfullyClaimedPopup(voucherName, startDate, endDate);
                        classDecl.voucherDetailPage.clickViewVoucherDetailsBtn();

                        // Scroll up to go to top of page
                        classDecl.commonKeyword.pause(3);
                        classDecl.commonKeyword.scroll("UP", 0.5);
                        classDecl.commonKeyword.scroll("UP", 0.5);
                        classDecl.voucherDetailPage.verifyPageTitle("Voucher Details");
                        classDecl.commonKeyword.verifyElementNotVisible(classDecl.voucherDetailPage.lblCarpark);
                        classDecl.voucherDetailPage.verifyVoucherDesc(voucherName, voucherDesc);
                        classDecl.voucherDetailPage.verifyVoucherExpiry(startDate, endDate);
                        classDecl.voucherDetailPage.verifyAboutVoucherSection(aboutVoucher);
                        classDecl.voucherDetailPage.verifyWhereToUsSection();
                        classDecl.voucherDetailPage.verifyHowToUseVoucherSection(howToUseVoucher);
                        classDecl.voucherDetailPage.verifyTermnConditionSection(termnCondition);
                        if (isExternalClaimable.equals("TRUE")) {
                            System.out.println("Log10: hideVehicleDetails = TRUE, isClaimRepeatable = " + isClaimRepeatable + ", isExternalClaimable = TRUE, button Use voucher now and Claim");
                            classDecl.voucherDetailPage.verifyUseBtn("display");
                            classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "display");

                        } else {
                            System.out.println("Log11: hideVehicleDetails = TRUE, isClaimRepeatable = " + isClaimRepeatable + ", isExternalClaimable = FALSE, button Use voucher now");
                            classDecl.voucherDetailPage.verifyUseBtn("display");
                            classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "not display");
                        }
                    } else { // if voucher can be claimed only one time per eid, and it was claimed by another before
                        System.out.println("Log12: hideVehicleDetails = TRUE, isClaimRepeatable = " + isClaimRepeatable + ", isExternalClaimable = " + isExternalClaimable + ", repeated claim voucher popup");
                        verifyRepeatedClaimPopup();
                        classDecl.extentReport.attachScreenshotToReport(voucherName + " - Repeated claim voucher");
                    }
                }
            } else { // after claim - history tab ((voucherTab.equals("History"))
                System.out.println("Log13");
                // Click on voucher description in voucher card
                classDecl.commonKeyword.clickElement(classDecl.voucherDetailPage.lblVoucherDesc, voucherDesc);

                classDecl.voucherDetailPage.verifyPageTitle("Voucher Details");
                classDecl.commonKeyword.verifyElementNotVisible(classDecl.voucherDetailPage.lblCarpark);
                classDecl.voucherDetailPage.verifyVoucherDesc(voucherName, voucherDesc);
                classDecl.voucherDetailPage.verifyVoucherExpiry(startDate, endDate);
                classDecl.voucherDetailPage.verifyAboutVoucherSection(aboutVoucher);
                classDecl.voucherDetailPage.verifyWhereToUsSection();
                classDecl.voucherDetailPage.verifyHowToUseVoucherSection(howToUseVoucher);
                classDecl.voucherDetailPage.verifyTermnConditionSection(termnCondition);
                classDecl.voucherDetailPage.verifyVehicleDetailSection("not display");
                classDecl.voucherDetailPage.verifyUseBtn("not display");
                classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "not display");

            }
        } else { // hideVehicleDetails.equals("FALSE") - vehicle detail is show - cp voucher
            if (voucherTab.equals("Unclaimed")) { // before claim - unclaimed tab
                System.out.println("Log14");
                classDecl.voucherDetailPage.verifyPageTitle("Claim Voucher");
                classDecl.voucherDetailPage.verifyRegisterVehicleText();
                classDecl.voucherDetailPage.verifyVoucherDesc_LendleaseCP(voucherName);
                classDecl.voucherDetailPage.verifyVoucherExpiry(startDate, endDate);
                classDecl.voucherDetailPage.verifyAboutVoucherSection_LendleaseCP(aboutVoucher);
                classDecl.voucherDetailPage.verifyWhereToUsSection();
                classDecl.voucherDetailPage.verifyHowToUseVoucherSection(howToUseVoucher);
                classDecl.voucherDetailPage.verifyTermnConditionSection(termnCondition);
                classDecl.voucherDetailPage.verifyVehicleDetailSection("display");
                classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "display");

            } else { // after claim - claimed tab
                classDecl.voucherDetailPage.enterVehicleDetail(classDecl.datas.nonIncomeInsuredNo);
                classDecl.voucherDetailPage.clickClaimBtn(isExternalClaimable);
                    verifySuccessfullyClaimedPopup(voucherName, startDate, endDate, "Carpark");
                    classDecl.voucherDetailPage.clickViewVoucherDetailsBtn();

                    // Scroll up to go to top of page
                    classDecl.commonKeyword.pause(3);
                    classDecl.commonKeyword.scroll("UP", 0.5);
                    classDecl.commonKeyword.scroll("UP", 0.5);
                    classDecl.voucherDetailPage.verifyPageTitle("Voucher Details");
                    classDecl.commonKeyword.verifyElementNotVisible(classDecl.voucherDetailPage.lblCarpark);
                    classDecl.voucherDetailPage.verifyVoucherDesc_LendleaseCP(voucherName);
                    classDecl.voucherDetailPage.verifyVoucherExpiry(startDate, endDate, carNo);
                    classDecl.voucherDetailPage.verifyAboutVoucherSection_LendleaseCP(aboutVoucher);
                    classDecl.voucherDetailPage.verifyWhereToUsSection();
                    classDecl.voucherDetailPage.verifyHowToUseVoucherSection(howToUseVoucher);
                    classDecl.voucherDetailPage.verifyTermnConditionSection(termnCondition);
                    classDecl.voucherDetailPage.verifyVehicleDetailSection("not display");
                    classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "not display");
                    classDecl.voucherDetailPage.verifyUseBtn("not display");
            }
        }
    }

    public void verifyRepeatedClaimPopup() {
        classDecl.voucherDetailPage.verifyRepeatedClaimTitle();
        classDecl.voucherDetailPage.verifyRepeatedClaimDesc();
        classDecl.voucherDetailPage.verifyRepeatedClaimButton();
        // Forcefully fail the test
        Assert.fail("Forcing test to fail: The voucher has already been claimed using the same NTUC details previously");
    }

    public void verifySuccessfullyClaimedPopup(String voucherTitle, String startD, String endD, String ... category) {
        if (voucherTitle.contains("'")) {
            voucherTitle = voucherTitle.replaceAll("'", "’");
        }
        classDecl.voucherDetailPage.verifySuccessfullyClaimedTitle();
        if (category != null && category.length > 0) {
            classDecl.voucherDetailPage.verifySuccessfullyClaimedDesc(voucherTitle, "Carpark");
        } else {
            classDecl.voucherDetailPage.verifySuccessfullyClaimedDesc(voucherTitle);
        }
        classDecl.voucherDetailPage.verifySuccessfullyClaimedDate(startD, endD);
        classDecl.voucherDetailPage.verifyViewVoucherDetailsBtn();
        classDecl.voucherDetailPage.verifyBackToHomeBtn();
    }

    public void verifyVoucherDestinationSearch (String input, String filePath, String sheetName, String rowName, String startDate, String endDate, String ... category) {
        String voucherDesc = String.valueOf(classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Voucher card details").get(0));
        List<String> failedLst = new ArrayList<>();
        List<String> inputLst = new ArrayList<>();
        List<String> baseLst = new ArrayList<>();
        Map<String, String> list;
        String item = "";

        if (voucherDesc.contains("TM")) {
            voucherDesc = voucherDesc.replaceAll("TM", "™");
        }

        if (voucherDesc.contains("'")) {
            voucherDesc = voucherDesc.replaceAll("'", "’");
        }

        switch (input) {
            case "Postal code":
                item = "Postal code";
                inputLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Merchant locations");
                baseLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Address");
                break;
            case "Building name":
                item = "Building";
                if (category != null && category.length > 0) {
                    inputLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Building");
                } else {
                    list = getAddressFromDestinationSearch(filePath, sheetName, rowName);
                    inputLst = new ArrayList<>(list.keySet());
                }
                baseLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Address");
                break;
            case "Full address":
                item = "Full add";
                if (category != null && category.length > 0) {
                    inputLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Full address");
                } else {
                    list = getAddressFromDestinationSearch(filePath, sheetName, rowName);
                    inputLst = new ArrayList<>(list.values());
                }
                baseLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Address");
                break;
            case "One map address":
                item = "One map";
                inputLst = getOneMapAddress(filePath, sheetName, rowName);
                baseLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "One Map Address");
                break;
            case "GG map address":
                item = "GG map";
                inputLst = getGGMapAddress(filePath, sheetName, rowName);
                baseLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Google Map Addresses");
                break;
        }

        if (input.equals("Postal code")) {
            for (int i = 0; i < inputLst.size(); i++) {
                int index = i + 1;
                // input postal code
                System.out.println("Postal code " + index + ": " + inputLst.get(i));
                classDecl.commonKeyword.closeInAppAlertsIfVisible();
                classDecl.searchDestinationPage.inputAddress(inputLst.get(i).trim());
                classDecl.commonKeyword.closeKeyboard();
                if ((!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "not display"))
                        && (!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "display"))) {

                    failedLst.add(inputLst.get(i));
                    classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - DS - " + item + " - " + inputLst.get(i));
                    classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnClearSearch);

                } else {

                    String buildingName = classDecl.searchDestinationPage.getVoucherAddressText(filePath, sheetName, rowName);
                    String baseAddress = baseLst.get(i);

                    System.out.println("Short address when entering postal code " + index + ": " + buildingName);
                    if (baseAddress.toLowerCase().contains(buildingName.toLowerCase())) {
                        Assert.assertTrue(true);
                        classDecl.commonKeyword.closeInAppAlertsIfVisible();
                        classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - DS - " + item + " - " + inputLst.get(i));
                    } else {
                        System.out.println("The full address " + baseAddress + " does not contain building name " + buildingName);
                        failedLst.add(inputLst.get(i));
                        classDecl.commonKeyword.closeInAppAlertsIfVisible();
                        classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - DS - " + item + " - " + inputLst.get(i));
                    }

                    // Click on the address with attached voucher
                    if (voucherDesc.contains("%")) {
                        classDecl.searchDestinationPage.lblVoucherAddress = classDecl.searchDestinationPage.lblVoucherAddress.replace("%s", voucherDesc);
                    }

                    if (classDecl.commonKeyword.elementIsVisible(classDecl.searchDestinationPage.lblVoucherAddress, voucherDesc)) {
                        classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.lblVoucherAddress, voucherDesc);
                    } else {
                        classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.lblhiddenVoucher, buildingName.toLowerCase(), buildingName.toLowerCase());
                    }

                    if (!classDecl.commonKeyword.getText(classDecl.landingPage.lblPromptBarDest).isEmpty()) {
                        classDecl.landingPage.verifyPromptBarText();
                        classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - Prompt bar - " + inputLst.get(i));

                        // tap on the prompt bar & verify voucher card in the voucher search page
                        classDecl.landingPage.tapOnPromptBar();
                        classDecl.commonKeyword.closeInAppAlertsIfVisible();

                        classDecl.voucherModuleSearchPage.verifySearchBar(buildingName);
                        classDecl.voucherDiscoveryFeature.verifyVoucherCard(filePath, sheetName, rowName, startDate, endDate, "Vouchers nearby section");
                        classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - Voucher nearby - " + inputLst.get(i));

                        // Back to landing
                        classDecl.commonKeyword.tapOnNativeBackBtn();
                        classDecl.commonKeyword.closeInAppAlertsIfVisible();

                    } else {
                        Assert.assertTrue(true);
                        classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Prompt bar - " + inputLst.get(i));
                    }

                    if (inputLst.size() != index && classDecl.commonKeyword.elementIsVisible(classDecl.landingPage.lblHomeTab)) {
                        classDecl.commonKeyword.clickElement(classDecl.landingPage.btnSearch);
                    }

                    if (inputLst.size() == index) {
                        classDecl.commonKeyword.clickElement(classDecl.landingPage.btnSearch);
                        classDecl.searchDestinationPage.clearHistory();
                        classDecl.commonKeyword.closeKeyboard();
                        classDecl.commonKeyword.tapOnNativeBackBtn();
                        classDecl.commonKeyword.closeInAppAlertsIfVisible();
                    }
                }

            }

            System.out.println("List of failed " + item + ": " + failedLst);

        } else if (input.equals("Building name") || input.equals("Full address") || input.equals("One map address") || input.equals("GG map address")) {
            if (classDecl.commonKeyword.elementIsVisible(classDecl.landingPage.lblSearchBar)) {
                classDecl.landingPage.clickOnSearchBar();
            } else if (classDecl.commonKeyword.elementIsVisible(classDecl.landingPage.lblHomeTab)) {
                classDecl.commonKeyword.clickElement(classDecl.landingPage.btnSearch);
            }

            for (int i = 0; i < inputLst.size(); i++) {
                    int index = i + 1;
                    String inputAddress = inputLst.get(i);
                    String baseAddress = baseLst.get(i);
                    classDecl.searchDestinationPage.inputAddress(inputAddress);
                    classDecl.commonKeyword.closeKeyboard();

                    if ((!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "not display"))
                            && (!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "display"))) {

                        failedLst.add(inputAddress);
                        classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - DS - " + item + " - " + inputLst.get(i));
                        classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnClearSearch);

                    } else {
                        if (baseAddress.toLowerCase().contains(inputAddress.toLowerCase())) {
                            Assert.assertTrue(true);
                            classDecl.commonKeyword.closeInAppAlertsIfVisible();
                            classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - DS - " + item + " - " + inputLst.get(i));
                        } else {
                            System.out.println("The full address " + baseAddress + " does not contain the input address " + inputAddress);
                            failedLst.add(inputAddress);
                            classDecl.commonKeyword.closeInAppAlertsIfVisible();
                            classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - DS - " + item + " - " + inputLst.get(i));
                        }
                        classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnClearSearch);
                    }

                    if (inputLst.size() == index) {
                        // Go back to landing page
                        classDecl.commonKeyword.tapOnNativeBackBtn();
                        classDecl.commonKeyword.closeInAppAlertsIfVisible();
                    }
                }

            System.out.println("List of failed " + item + ": " + failedLst);

        }
    }

    public void verifyVoucherModuleSearch(String input, String filePath, String sheetName, String rowName, String startDate, String endDate, String ... category) {
        List<String> inputLst = new ArrayList<>();
        List<String> failedLst = new ArrayList<>();

        String voucherDesc = getValueByRowAndColumnName(filePath, sheetName, rowName, "Voucher card details");
        String item = "";
        String btBack = "//android.widget.TextView[@text=\"My Vouchers\"]/ancestor::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView";

        switch (input) {
            case "Postal code":
                item = "Postal code";
                inputLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Merchant locations");
                break;
            case "Building name":
                item = "Building";
                if (classDecl.commonKeyword.elementIsVisible(btBack)) {
                    classDecl.commonKeyword.clickElement(btBack);
                }
                classDecl.commonPage.tabOnMenu("Home");
                if (category != null && category.length > 0) {
                    inputLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Building name");
                } else {
                    Map<String, String> listBuilding = getAddressFromDestinationSearch(filePath, sheetName, rowName);
                    inputLst = new ArrayList<>(listBuilding.keySet());
                }
                classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
                break;
            case "Full address":
                item = "Full add";
                if (classDecl.commonKeyword.elementIsVisible(btBack)) {
                    classDecl.commonKeyword.clickElement(btBack);
                }
                classDecl.commonPage.tabOnMenu("Home");
                if (category != null && category.length > 0) {
                    inputLst = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Full address");
                } else {
                    Map<String, String> listFullAddress = getAddressFromDestinationSearch(filePath, sheetName, rowName);
                    inputLst = new ArrayList<>(listFullAddress.values());
                }
                classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
                break;
            case "One map address":
                item = "One map";
                inputLst = getOneMapAddress(filePath, sheetName, rowName);
                break;
            case "GG map address":
                item = "GG map";
                inputLst = getGGMapAddress(filePath, sheetName, rowName);
                break;
        }

        classDecl.myVoucherPage.clickSearchBtn();


        for (int i = 0; i < inputLst.size(); i++) {
            int index = i + 1;

            System.out.println("Address " + index + ": " + inputLst.get(i));
            classDecl.voucherModuleSearchPage.inputAddress(inputLst.get(i).trim());
            classDecl.commonKeyword.closeKeyboard();
            if (classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblNoVoucherFound)) { // If no vouchers found
                failedLst.add(inputLst.get(i));
                classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - VM - " + item + " - " + inputLst.get(i));
            } else {
                int maxScrolls = 5;
                for (int j = 0; j < maxScrolls; j++) {
                    if (classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVoucherDesc, rowName, voucherDesc)
                            && !classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVouchersNearby)) { // If the voucher is visible, and nearby section is not visible
                        verifyVoucherCard(filePath, sheetName, rowName, startDate, endDate, "Matched address vouchers section");
                        classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - VM - " + item + " - " + inputLst.get(i));
                        break;

                    } else if (classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVouchersNearby)
                            && !classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVoucherDesc, rowName, voucherDesc)) { // If the voucher is not visible, and nearby section is visible
                        failedLst.add(inputLst.get(i));
                        classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - VM - " + item + " - " + inputLst.get(i));
                        break;

                    } else if (classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVoucherDesc, rowName, voucherDesc) // If both voucher and nearby is visible at the same time
                            && classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVouchersNearby)) {
                        int voucherLocation = classDecl.commonKeyword.getElementLocation(classDecl.voucherModuleSearchPage.lblVoucherDesc, new Object[]{rowName, voucherDesc});
                        int nearbyLocation = classDecl.commonKeyword.getElementLocation(classDecl.voucherModuleSearchPage.lblVouchersNearby, null);
                        if (nearbyLocation > voucherLocation) { // If the voucher is displayed before nearby section
                            verifyVoucherCard(filePath, sheetName, rowName, startDate, endDate, "Matched address vouchers section");
                            classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - VM - " + item + " - " + inputLst.get(i));
                            break;

                        } else { // If the voucher is displayed after nearby section
                            failedLst.add(inputLst.get(i));
                            classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - VM - " + item + " - " + inputLst.get(i));
                            break;

                        }
                    }

                    classDecl.commonKeyword.scroll("DOWN", 0.3);

                }

                if (!classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVoucherDesc, rowName, voucherDesc)
                        && !classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVouchersNearby)) { // If neither the voucher or nearby section found
                    failedLst.add(inputLst.get(i));
                    classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - VM - " + item + " - " + inputLst.get(i));
                }
            }

            if (inputLst.size() != index) {
                // clear text
                classDecl.commonKeyword.clickElement(classDecl.voucherModuleSearchPage.btnClearSearch);
            } else {
                classDecl.commonKeyword.closeInAppAlertsIfVisible();
                classDecl.commonKeyword.tapOnNativeBackBtn();
            }
        }

        System.out.println("List of failed " + item + ": " + failedLst);
    }


    public Map<String, String> getAddressFromDestinationSearch (String filePath, String sheetName, String rowName) {
        Map<String, String> address = new LinkedHashMap<>();
        String buildingName = "";
        String fullAddress = "";
        List<String> postalCodeList = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Merchant locations");
        List<String> addressList = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Address");

        if (classDecl.commonKeyword.elementIsVisible(classDecl.landingPage.lblSearchBar)) {
            classDecl.landingPage.clickOnSearchBar();
        } else if (classDecl.commonKeyword.elementIsVisible(classDecl.landingPage.lblHomeTab)) {
            classDecl.commonKeyword.clickElement(classDecl.landingPage.btnSearch);
        }

        for (int i = 0; i < addressList.size(); i++) {
            int index = i + 1;
            // input postal code
            System.out.println("Postal code " + index + ": " + postalCodeList.get(i));
            classDecl.searchDestinationPage.inputAddress(postalCodeList.get(i).trim());
            classDecl.commonKeyword.closeKeyboard();
            if ((!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "not display"))
                    && (!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "display"))) {
                address.put(postalCodeList.get(i) + " does not return voucher", postalCodeList.get(i) + " does not return voucher");
                classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnClearSearch);

            } else {
                String shortAddress = classDecl.searchDestinationPage.getVoucherAddressText(filePath, sheetName, rowName);
                shortAddress = Pattern.quote(shortAddress); // Use pattern to escape all special regex characters in the building name (eg. SAFRA CLUBHOUSE (PUNGGOL))
                if (shortAddress.toLowerCase().contains("singapore")) {
                    buildingName = shortAddress.replaceAll("(?i)" + " singapore \\d+", "");
                    fullAddress = addressList.get(i).replaceAll("(?i)" + " singapore \\d+", "");
                } else {
                    buildingName = shortAddress;
                    fullAddress = addressList.get(i).replaceAll("(?i)" + shortAddress + " singapore \\d+", "");
                }
                buildingName = buildingName.replaceAll("\\\\Q", "").replaceAll("\\\\E", "");
                address.put(buildingName.trim(), fullAddress.trim());
            }

            if (classDecl.commonKeyword.elementIsVisible(classDecl.searchDestinationPage.btnClearSearch)) {
                classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnClearSearch);
            }
        }

        // Go back to landing
        classDecl.commonKeyword.tapOnNativeBackBtn();
        classDecl.commonKeyword.closeInAppAlertsIfVisible();
        System.out.println("List of building name & full address return by searching postal code: " + address);

        return address;
    }

    public List<String> getGGMapAddress(String filePath, String sheetName, String rowName) {
        List<String> addressExcelList = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Google Map Addresses");
        List<String> ggMapAddressLst = new ArrayList<>();
        String address;
        for (int i = 0; i < addressExcelList.size(); i++) {
            String[] splitPipe = addressExcelList.get(i).split("\\|", -1);
            if (splitPipe.length == 3) { // If there are 2 pipes (3 parts) => add middle part
                address =  splitPipe[1].trim();
            } else if (splitPipe.length == 2) { // If there are 1 pipe (2 parts)
                if (splitPipe[0].toLowerCase().contains("singapore")) { // If the first part contain 'singapore' => add first part
                    address = splitPipe[0].trim();
                } else { // If the second part contain 'singapore' => add second part
                    address = splitPipe[1].trim();
                }
            } else { // If there are no parentheses, keep it as-is
                address = addressExcelList.get(i).trim();
            }
            ggMapAddressLst.add(address);
        }
        System.out.println("GG map address list: " + ggMapAddressLst);
        return ggMapAddressLst;
    }

    public List<String> getOneMapAddress(String filePath, String sheetName, String rowName) {
        List<String> addressExcelList = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "One Map Address");
        List<String> oneMapAddressLst = new ArrayList<>();
        String address;
        for (int i = 0; i < addressExcelList.size(); i++) {
            String[] splitPipe = addressExcelList.get(i).split("\\|", -1);
            if (splitPipe.length == 3) { // If there are 2 pipes (3 parts) => add middle part
                address =  splitPipe[1].trim();
            } else { // If there are no pipe, keep it as-is
                address = addressExcelList.get(i).trim();
            }

            if (address.toLowerCase().contains("singapore")) {
                address = address.replaceAll("(?i)" + " singapore \\d+", "");
            }
            oneMapAddressLst.add(address);
        }
        System.out.println("One map address list: " + oneMapAddressLst);
        return oneMapAddressLst;
    }

    public void selectVoucherCategory(String voucherCategory) {
        voucherCategory = voucherCategory.toLowerCase();
        if (voucherCategory.contains("_")) {
            voucherCategory = voucherCategory.replace("_", " & ");
        }
        classDecl.myVoucherPage.clickVoucherPill("Filter");
        classDecl.myVoucherPage.selectCategory(voucherCategory);
        classDecl.myVoucherPage.clickApplyBtn();
    }

    public void verifyConfirmUsePopup() {
        classDecl.voucherDetailPage.verifyConfirmUsePopupTitle();
        classDecl.voucherDetailPage.verifyConfirmUsePopupDesc();
        classDecl.voucherDetailPage.verifyConfirmUsePopupBtn();
        classDecl.voucherDetailPage.verifyCloseConfirmUseBtn();
    }

    public void verifyUsedVoucherPopup(String voucherName, String voucherDesc) {
        classDecl.voucherDetailPage.verifyUsedOnTitle();
        classDecl.voucherDetailPage.verifyUsedDateTime();
        classDecl.voucherDetailPage.verifyUsedVoucherDesc(voucherName + " - " + voucherDesc);
        classDecl.voucherDetailPage.verifyUsedGuideDesc();
        classDecl.voucherDetailPage.verifyBackToVouchersBtn();
        classDecl.voucherDetailPage.clickBackToVouchersBtn();

    }

    public void confirmUseVoucher(String voucherName, String voucherDesc) {
        classDecl.voucherDetailPage.clickUseVoucherNowBtn();
        classDecl.voucherDiscoveryFeature.verifyConfirmUsePopup();
        classDecl.voucherDetailPage.clickConfirmUseVoucher();
        classDecl.voucherDiscoveryFeature.verifyUsedVoucherPopup(voucherName, voucherDesc);

    }

}

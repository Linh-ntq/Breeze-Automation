package features;

import commons.BaseTest;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static datas.ExcelReader.getValueByRowAndColumnName;

public class VoucherDiscoveryFeature extends BaseTest {

    public void verifyVoucherCard(String filePath, String sheetName, String voucherName, String startDate, String endDate, String voucherPosition) {
        String voucherDesc = getValueByRowAndColumnName(filePath, sheetName, voucherName, "Voucher card details");
        String xpath1 = "//android.widget.TextView[@text=\"Voucher Details\"]/ancestor::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView";
        String xpath2 = "//android.widget.TextView[@text=\"Voucher Details\"]/ancestor::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup//android.widget.ImageView";
        String xpath = xpath1 + " | " + xpath2;

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

    public void goToVoucherModulePage() {
        classDecl.commonPage.tabOnMenu("More");
        classDecl.commonPage.tabOnMenu("My Vouchers");
    }

    public void verifyVoucherDetail(String voucherTab, String filePath, String sheetName, String voucherName, String voucherDesc, String startDate, String endDate, List<String> aboutVoucher, List<String> howToUseVoucher, List<String> termnCondition) {
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
                classDecl.voucherDetailPage.verifyVoucherDesc(voucherName, voucherDesc);
                classDecl.voucherDetailPage.verifyVoucherExpiry(startDate, endDate);
                classDecl.voucherDetailPage.verifyAboutVoucherSection(aboutVoucher);
                classDecl.voucherDetailPage.verifyWhereToUsSection();
                classDecl.voucherDetailPage.verifyHowToUseVoucherSection(howToUseVoucher);
                classDecl.voucherDetailPage.verifyTermnConditionSection(termnCondition);
                classDecl.voucherDetailPage.verifyVehicleDetailSection("display");
                classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "display");

            } else { // after claim - claimed tab
                System.out.println("Log15");
                classDecl.voucherDetailPage.enterVehicleDetail(classDecl.datas.nonIncomeInsuredNo);
                classDecl.voucherDetailPage.clickClaimBtn(isExternalClaimable);
                // if voucher can be claimed repeatedly or voucher can be claimed only one time per eid but this is the first time voucher claimed
                if (isClaimRepeatable.equals("TRUE")
                        || (isClaimRepeatable.equals("FALSE")
                        && !classDecl.commonKeyword.elementIsVisible(classDecl.voucherDetailPage.lblClaimPopup, "Repeated voucher claim"))) {
                    System.out.println("Log16");
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
                    classDecl.voucherDetailPage.verifyVehicleDetailSection("not display");
                    classDecl.voucherDetailPage.verifyClaimBtn(isExternalClaimable, "not display");
                    classDecl.voucherDetailPage.verifyUseBtn("not display");
                } else { // if voucher can be claimed only one time per eid, and it was claimed by another before
                    System.out.println("Log17");
                    verifyRepeatedClaimPopup();
                    classDecl.extentReport.attachScreenshotToReport(voucherName + " - Repeated claim voucher");
                }

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

    public void verifySuccessfullyClaimedPopup(String voucherTitle, String startD, String endD) {
        if (voucherTitle.contains("'")) {
            voucherTitle = voucherTitle.replaceAll("'", "’");
        }
        classDecl.voucherDetailPage.verifySuccessfullyClaimedTitle();
        classDecl.voucherDetailPage.verifySuccessfullyClaimedDesc(voucherTitle);
        classDecl.voucherDetailPage.verifySuccessfullyClaimedDate(startD, endD);
        classDecl.voucherDetailPage.verifyViewVoucherDetailsBtn();
        classDecl.voucherDetailPage.verifyBackToHomeBtn();
    }

    public String handleForNumberWord(String buildingName) {
        if (buildingName.toUpperCase().endsWith("ONE")) {
            buildingName = buildingName.replaceAll("(?i)one", "1");
        } else if (buildingName.toUpperCase().endsWith("TWO")) {
            buildingName = buildingName.replaceAll("(?i)two", "2");
        } else if (buildingName.toUpperCase().endsWith("THREE")) {
            buildingName = buildingName.replaceAll("(?i)three", "3");
        } else if (buildingName.toUpperCase().endsWith("FOUR")) {
            buildingName = buildingName.replaceAll("(?i)four", "4");
        } else if (buildingName.toUpperCase().endsWith("FIVE")) {
            buildingName = buildingName.replaceAll("(?i)five", "5");
        } else if (buildingName.toUpperCase().endsWith("SIX")) {
            buildingName = buildingName.replaceAll("(?i)six", "6");
        } else if (buildingName.toUpperCase().endsWith("SEVEN")) {
            buildingName = buildingName.replaceAll("(?i)seven", "7");
        } else if (buildingName.toUpperCase().endsWith("EIGHT")) {
            buildingName = buildingName.replaceAll("(?i)eight", "8");
        } else if (buildingName.toUpperCase().endsWith("NINE")) {
            buildingName = buildingName.replaceAll("(?i)nine", "9");
        } else if (buildingName.toUpperCase().endsWith("TEN")) {
            buildingName = buildingName.replaceAll("(?i)ten", "10");
        }
        return buildingName;
    }

    public void verifyVoucherDestinationSearch(String filePath, String sheetName, String rowName, String startDate, String endDate) {
        List<String> postalCodeList = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Merchant locations");
        List<String> addressList = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Address");
        String voucherDesc = String.valueOf(classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Voucher card details").get(0));
        List<String> failedPostalCodeLst = new ArrayList<>();
        List<String> failedBuildingNameLst = new ArrayList<>();
        if (voucherDesc.contains("TM")) {
            voucherDesc = voucherDesc.replaceAll("TM", "™");
        }

        if (voucherDesc.contains("'")) {
            voucherDesc = voucherDesc.replaceAll("'", "’");
        }

        for (int i = 0; i < postalCodeList.size(); i++) {
            int index = i + 1;
            // input postal code
            System.out.println("Postal code " + index + ": " + postalCodeList.get(i));
            classDecl.commonKeyword.closeInAppAlertsIfVisible();
            classDecl.searchDestinationPage.inputAddress(postalCodeList.get(i));
            classDecl.commonKeyword.closeKeyboard();
            if ((!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "not display"))
                    && (!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "display"))) {

                failedPostalCodeLst.add(postalCodeList.get(i));
                classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Destination Search - " + postalCodeList.get(i));
                classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnClearSearch);

            } else {

                String buildingName = classDecl.searchDestinationPage.getVoucherAddressText(filePath, sheetName, rowName);
                System.out.println("Full address " + index + ": " + addressList.get(i));
                String fullAddress = addressList.get(i);

                // handle for exceptional cases before comparing the returned building name from postal code searching
                if (buildingName.equals("DJIT SUN MALL")) {
                    buildingName = "DJITSUN MALL";
                }

                System.out.println("Short address when entering postal code " + index + ": " + buildingName);
                if (fullAddress.toLowerCase().contains(buildingName.toLowerCase())) {
                    Assert.assertTrue(true);
                    classDecl.commonKeyword.closeInAppAlertsIfVisible();
                    classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - Destination Search - " + postalCodeList.get(i));
                } else {
                    System.out.println("The full address " + fullAddress + " does not contain building name " + buildingName);
                    failedPostalCodeLst.add(postalCodeList.get(i));
                    classDecl.commonKeyword.closeInAppAlertsIfVisible();
                    classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Destination Search - " + postalCodeList.get(i));
                }

                classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnClearSearch);

                // handle for exceptional cases before entering building name
                if (buildingName.equals("RAFFLES CITY SHOPPING CENTRE")) {
                    buildingName = "RAFFLES CITY";
                } else if (buildingName.equals("SAFRA CLUBHOUSE (PUNGGOL)")) {
                    buildingName = "SAFRA PUNGGOL";
                } else if (buildingName.contains("Singapore") || buildingName.contains("SINGAPORE")) {
                    buildingName = buildingName.replaceAll(" SINGAPORE \\d+", "")
                            .replaceAll(" Singapore \\d+", "")
                            .replaceAll(" \\d{6}$", "");
                }

                // input building name
                classDecl.commonKeyword.closeInAppAlertsIfVisible();
                classDecl.searchDestinationPage.inputAddress(buildingName);
                classDecl.commonKeyword.closeKeyboard();

                // handle for exceptional cases after entering building name
                if (buildingName.equals("HEARTLAND MALL-KOVAN")) {
                    buildingName = "HEARTLAND MALL KOVAN";
                } else if (buildingName.equals("NOVENA SQUARE")) {
                    buildingName = "NOVENA SQUARE SHOPPING MALL";
                } else if (buildingName.equals("313 @ SOMERSET")) {
                    buildingName = "313@SOMERSET";
                } else if (buildingName.equals("LOT ONE, SHOPPERS' MALL")) {
                    buildingName = buildingName.replace(",", "");
                } else if (buildingName.equals("SINGAPORE POST CENTRE")) {
                    buildingName = "SingPost Centre";
                } else if (buildingName.equals("ANCHORPOINT SHOPPING CENTRE")) {
                    buildingName = "Anchorpoint";
                } else if (buildingName.equals("BUANGKOK SQUARE")) {
                    buildingName = "BUANGKOK SQUARE MALL";
                } else if (buildingName.equals("SAFRA CLUBHOUSE (TOA PAYOH)")) {
                    buildingName = "SAFRA TOA PAYOH";
                } else if (buildingName.equals("PASIR RIS SPORTS CENTRE")) {
                    buildingName = "ActiveSG Pasir Ris Sport Centre";
                } else if (buildingName.equals("SHELL HOUGANG")) {
                    buildingName = "SHELL (HOUGANG)";
                } else if (buildingName.equals("SHELL HAVELOCK")) {
                    buildingName = "Shell (Havelock )";
                } else if (buildingName.equals("SHELL TAMPINES AVENUE 2")) {
                    buildingName = "Shell";
                } else if (buildingName.equals("26 CHOA CHU KANG DRIVE")) {
                    buildingName = "McDonald's Choa Chu Kang Park (Drive-Thru)";
                } else if (buildingName.contains("Singapore") || buildingName.contains("SINGAPORE")) {
                    buildingName = buildingName.replaceAll(" SINGAPORE \\d+", "")
                            .replaceAll(" Singapore \\d+", "")
                            .replaceAll(" \\d{6}$", "");
                }

                if ((!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "not display"))
                        && (!classDecl.searchDestinationPage.voucherIsVisible(filePath, sheetName, rowName, "display"))) {

                    failedBuildingNameLst.add(buildingName);
                    classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Destination Search - " + buildingName);
                    classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnClearSearch);

                } else {
                    // Handle for number (eg. TAMPINES ONE > TAMPINES 1)
                    if (!classDecl.commonKeyword.elementIsVisible(classDecl.searchDestinationPage.lblVoucher, buildingName, voucherDesc, buildingName.toLowerCase(), voucherDesc)
                            && !classDecl.commonKeyword.elementIsVisible(classDecl.searchDestinationPage.lblhiddenVoucher, buildingName.toLowerCase(), buildingName.toLowerCase())) {
                        buildingName = handleForNumberWord(buildingName);

                    }

                    if (buildingName.equalsIgnoreCase(classDecl.searchDestinationPage.getVoucherAddressText(filePath, sheetName, rowName))) {
                        Assert.assertTrue(true);
                        classDecl.commonKeyword.closeInAppAlertsIfVisible();
                        classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - Destination Search - " + buildingName);
                    } else {
                        System.out.println("The building name searched by " + buildingName + " does not match the building name searched by " + postalCodeList.get(i));
                        failedBuildingNameLst.add(buildingName);
                        classDecl.commonKeyword.closeInAppAlertsIfVisible();
                        classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Destination Search - " + buildingName);
                    }

                    // Get the building name again to match search bar in the next step
                    buildingName = classDecl.searchDestinationPage.getVoucherAddressText(filePath, sheetName, rowName);
                    System.out.println("Short address when entering building name " + index + ": " + buildingName);

                    if (voucherDesc.contains("%")) {
                        classDecl.searchDestinationPage.lblVoucherAddress = classDecl.searchDestinationPage.lblVoucherAddress.replace("%s", voucherDesc);
                        classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.lblVoucherAddress);
                    } else if (classDecl.commonKeyword.elementIsVisible(classDecl.searchDestinationPage.lblVoucherAddress, voucherDesc)) {
                        classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.lblVoucherAddress, voucherDesc);
                    } else {
                        classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.lblhiddenVoucher, buildingName.toLowerCase(), buildingName.toLowerCase());
                    }

                    if (!classDecl.commonKeyword.getText(classDecl.landingPage.lblPromptBarDest).isEmpty()) {
                        classDecl.landingPage.verifyPromptBarText();
                        classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - Prompt bar - " + buildingName);

                        // tap on the prompt bar & verify voucher card in the voucher search page
                        classDecl.landingPage.tapOnPromptBar();
                        classDecl.commonKeyword.closeInAppAlertsIfVisible();

                        classDecl.voucherModuleSearchPage.verifySearchBar(buildingName);
                        classDecl.voucherDiscoveryFeature.verifyVoucherCard(filePath, sheetName, rowName, startDate, endDate, "Vouchers nearby section");
                        classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - Voucher Search - " + buildingName);

                        if (postalCodeList.size() != index) {
                            // Go back to destination search page
                            classDecl.commonKeyword.tapOnNativeBackBtn();
                            classDecl.commonKeyword.closeInAppAlertsIfVisible();
                            classDecl.landingPage.tapOnSearchBarName(buildingName);

                        } else {
                            System.out.println("index i " + index + " is equal to address size " + postalCodeList.size() + ". No more items to search");
                        }
                    } else {
                        Assert.assertTrue(true);
                        classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Prompt bar - " + buildingName);
                    }
                }
            }
        }

        System.out.println("List of failed postal code: " + failedPostalCodeLst);
        System.out.println("List of failed building name: " + failedBuildingNameLst);

    }

    public List<String> getBuildingNameListFromDestinationSearch(String filePath, String sheetName, String rowName) {
        List<String> postalCodeList = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Merchant locations");
        List<String> buildingNameList = new ArrayList<>();

        classDecl.landingPage.clickOnSearchBar();

        // Get the list of building name from destination search
        for (int i = 0; i < postalCodeList.size(); i++) {
            int index = i + 1;
            // input postal code
            System.out.println("Postal code " + index + ": " + postalCodeList.get(i));
            classDecl.searchDestinationPage.inputAddress(postalCodeList.get(i));
            classDecl.commonKeyword.closeKeyboard();
            String buildingName = classDecl.searchDestinationPage.getVoucherAddressText(filePath, sheetName, rowName);

            if (buildingName.contains("Singapore") || buildingName.contains("SINGAPORE")) {
                buildingName = buildingName.replaceAll(" SINGAPORE \\d+", "")
                        .replaceAll(" Singapore \\d+", "")
                        .replaceAll(" \\d{6}$", "");
            }

            System.out.println("Get building name " + buildingName + " by searching postal code " + postalCodeList.get(i));
            buildingNameList.add(buildingName);

            // clear text
            classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnClearSearch);
        }

        System.out.println("List of building names retrieved from the destination search: " + buildingNameList);
        classDecl.commonKeyword.clickElement(classDecl.searchDestinationPage.btnBack);

        return buildingNameList;
    }

    public void verifySearchingByPostalCodeInVoucherModule_WithoutValidation (String filePath, String sheetName, String rowName, String startDate, String endDate) {
        List<String> postalCodeList = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Merchant locations");
        String voucherDesc = getValueByRowAndColumnName(filePath, sheetName, rowName, "Voucher card details");

        List<String> failedPostalCodeLst = new ArrayList<>();

        classDecl.myVoucherPage.clickSearchBtn();

        for (int i = 0; i < postalCodeList.size(); i++) {
            int index = i + 1;
            // input postal code
            System.out.println("Postal code " + index + ": " + postalCodeList.get(i));
            classDecl.voucherModuleSearchPage.inputAddress(postalCodeList.get(i));
            classDecl.commonKeyword.closeKeyboard();
            if (classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblNoVoucherFound)) { // If no vouchers found
                failedPostalCodeLst.add(postalCodeList.get(i));
                classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Voucher module - " + postalCodeList.get(i));
            } else {
                int maxScrolls = 5;
                for (int j = 0; j < maxScrolls; j++) {
                    if (classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVoucherDesc, rowName, voucherDesc)
                    && !classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVouchersNearby)) { // If the voucher is visible, and nearby section is not visible
                        verifyVoucherCard(filePath, sheetName, rowName, startDate, endDate, "Matched address vouchers section");
                        classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - Voucher module - " + postalCodeList.get(i));
                        break;

                    } else if (classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVouchersNearby)
                    && !classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVoucherDesc, rowName, voucherDesc)) { // If the voucher is not visible, and nearby section is visible
                        failedPostalCodeLst.add(postalCodeList.get(i));
                        classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Voucher module - " + postalCodeList.get(i));
                        break;

                    } else if (classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVoucherDesc, rowName, voucherDesc) // If both voucher and nearby is visible at the same time
                        && classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVouchersNearby)) {
                        int voucherLocation = classDecl.commonKeyword.getElementLocation(classDecl.voucherModuleSearchPage.lblVoucherDesc, new Object[]{rowName, voucherDesc});
                        int nearbyLocation = classDecl.commonKeyword.getElementLocation(classDecl.voucherModuleSearchPage.lblVouchersNearby, null);
                        if (nearbyLocation > voucherLocation) { // If the voucher is displayed before nearby section
                            verifyVoucherCard(filePath, sheetName, rowName, startDate, endDate, "Matched address vouchers section");
                            classDecl.extentReport.attachScreenshotToReport("P " + rowName + " - Voucher module - " + postalCodeList.get(i));
                            break;

                        } else { // If the voucher is displayed after nearby section
                            failedPostalCodeLst.add(postalCodeList.get(i));
                            classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Voucher module - " + postalCodeList.get(i));
                            break;

                        }
                    }

                    classDecl.commonKeyword.scroll("DOWN", 0.3);

                }

                if (!classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVoucherDesc, rowName, voucherDesc)
                && !classDecl.commonKeyword.elementIsVisible(classDecl.voucherModuleSearchPage.lblVouchersNearby)) { // If neither the voucher or nearby section found
                    failedPostalCodeLst.add(postalCodeList.get(i));
                    classDecl.extentReport.attachScreenshotToReport("F " + rowName + " - Voucher module - " + postalCodeList.get(i));
                }
            }

            // clear text
            classDecl.commonKeyword.clickElement(classDecl.voucherModuleSearchPage.btnClearSearch);
        }

        System.out.println("List of failed postal code: " + failedPostalCodeLst);
    }

    public void verifySearchingByPostalCodeInVoucherModule_WithValidation(String filePath, String sheetName, String rowName, String startDate, String endDate) {
        List<String> postalCodeList = classDecl.excelReader.getVoucherDataList(filePath, sheetName, rowName, "Merchant locations");

        classDecl.myVoucherPage.clickSearchBtn();

        for (int i = 0; i < postalCodeList.size(); i++) {
            int index = i + 1;
            // input postal code
            System.out.println("Postal code " + index + ": " + postalCodeList.get(i));
            classDecl.voucherModuleSearchPage.inputAddress(postalCodeList.get(i));
            classDecl.commonKeyword.closeKeyboard();
            verifyVoucherCard(filePath, sheetName, rowName, startDate, endDate, "Matched address vouchers section");
            classDecl.extentReport.attachScreenshotToReport(rowName + " - Voucher module search - " + postalCodeList.get(i));

            // clear text
            classDecl.commonKeyword.clickElement(classDecl.voucherModuleSearchPage.btnClearSearch);
        }
    }

    public void verifySearchingByBuildingNameInVoucherModule_WithValidation(String filePath, String sheetName, String rowName, String startDate, String endDate) {
        List<String> buildingNameList = getBuildingNameListFromDestinationSearch(filePath, sheetName, rowName);

        classDecl.voucherDiscoveryFeature.goToVoucherModulePage();
        classDecl.myVoucherPage.clickSearchBtn();

        for (int i = 0; i < buildingNameList.size(); i++) {
            int index = i + 1;
            System.out.println("Building name " + index + ": " + buildingNameList.get(i));
            classDecl.voucherModuleSearchPage.inputAddress(buildingNameList.get(i));
            classDecl.commonKeyword.closeKeyboard();
            verifyVoucherCard(filePath, sheetName, rowName, startDate, endDate, "Matched address vouchers section");
            classDecl.extentReport.attachScreenshotToReport(rowName + " - Voucher module search - " + buildingNameList.get(i));

            // clear text
            classDecl.commonKeyword.clickElement(classDecl.voucherModuleSearchPage.btnClearSearch);

        }
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

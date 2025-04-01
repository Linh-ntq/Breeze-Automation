package features;

import commons.BaseTest;

public class LandingFeature extends BaseTest {

    public void verifySearchBarText(String userName){
        classDecl.landingPage.verifySearchBarText(userName);
    }

    public void verifyFocusedTab(String headerTab){
        classDecl.commonKeyword.pause(3); // wait until page completely load
        classDecl.landingPage.expandBottomSheet();
        classDecl.landingPage.verifyFocusedTab(headerTab);
    }

    public void searchByKeyword(String address){
        classDecl.landingPage.clickOnSearchBar();
        classDecl.searchDestinationPage.inputAddress(address);
    }

}

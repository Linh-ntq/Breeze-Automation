package pages;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyVoucherPage extends BaseTest {
    public String btnView = "//android.widget.TextView[@text=\"%s\"]/ancestor::android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text=\"View\"]"; // voucher detail

    public void clickViewBtn(String voucherDesc) {
        if (voucherDesc.contains("%")){
            String newXpath = btnView.replace("%s", voucherDesc);
            waitDriverApp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpath)));
            driver.findElement(By.xpath(newXpath)).click();

        } else {
            classDecl.commonKeyword.clickElement(btnView, voucherDesc);
        }

    }
}

package commons;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonKeyword extends BaseTest{

    public void waitForElementVisible(String xpathExpression, String... text) {
        if (text != null && text.length > 0) {
            String xpath = String.format(xpathExpression, (String[]) text);
            waitDriverApp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        } else {
            waitDriverApp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        }
    }

    public void scrollUntilElementVisible(String xpathExpression, String... text) {
        boolean hasText = text != null && text.length > 0;
        int maxScrolls = 8;
        for (int attempt = 0; attempt < maxScrolls; attempt++){
            try {
                if (hasText){
                    classDecl.voucherDetailPage.waitForElementVisible(xpathExpression, text);
                } else {
                    classDecl.voucherDetailPage.waitForElementVisible(xpathExpression);
                }
                // Element found, exit method
                return;
            } catch (Exception e) {
                System.out.println("Attempt " + (attempt + 1) + ": Element not found. Scrolling...");
                scroll("DOWN", 0.2);
            }
        }

        // If element not found, throw error
        String message = "Element still not found after scrolling " + maxScrolls + " times. Element: " + xpathExpression;
        if (hasText) {
            message += ", text: " + String.join(", ", text);
        }
        throw new Error(message);
    }

    public void scrollUntilEitherElementVisible(String xpathA, String xpathB, String... text) {
        boolean hasText = text != null && text.length > 0;
        int maxScrolls = 8;

        for (int attempt = 0; attempt < maxScrolls; attempt++) {
            try {
                boolean isElementAVisible = false;
                boolean isElementBVisible = false;

                if (hasText) {
                    try {
                        classDecl.voucherDetailPage.waitForElementVisible(xpathA, text);
                        isElementAVisible = true;
                    } catch (Exception ignored) {}

                    try {
                        classDecl.voucherDetailPage.waitForElementVisible(xpathB, text);
                        isElementBVisible = true;
                    } catch (Exception ignored) {}

                } else {
                    try {
                        classDecl.voucherDetailPage.waitForElementVisible(xpathA);
                        isElementAVisible = true;
                    } catch (Exception ignored) {}

                    try {
                        classDecl.voucherDetailPage.waitForElementVisible(xpathB);
                        isElementBVisible = true;
                    } catch (Exception ignored) {}
                }

                if (isElementAVisible || isElementBVisible) {
                    // At least one element is found, exit method
                    return;
                }

                System.out.println("Attempt " + (attempt + 1) + ": Neither element found. Scrolling...");
                scroll("DOWN", 0.2);

            } catch (Exception e) {
                // Catch unexpected issues, continue attempting
                System.out.println("Unexpected error on attempt " + (attempt + 1) + ": " + e.getMessage());
            }
        }

        // If neither element is found after max attempts
        String message = "Neither element was found after scrolling " + maxScrolls + " times. Elements: " + xpathA + " OR " + xpathB;
        if (hasText) {
            message += ", text: " + String.join(", ", text);
        }
        throw new Error(message);
    }

    public void elementNotVisible(String xpathExpression, String... text) {
        // Temporarily disable implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            if (text != null && text.length > 0) {
                String xpath = String.format(xpathExpression, (String[]) text);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
            } else {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathExpression)));
            }
        } finally {
            // Restore the global implicit wait after the check
            driver.manage().timeouts().implicitlyWait(timeoutExWait);
        }
    }

        public void clickElement(String xpathExpression) {
        waitForElementVisible(xpathExpression);
        driver.findElement(By.xpath(xpathExpression)).click();
    }

    public void clickElement(String xpathExpression, String ... text) {
        String dynamicXpath = String.format(xpathExpression, text);
        waitForElementVisible(dynamicXpath);
        driver.findElement(By.xpath(dynamicXpath)).click();
    }

    public void sendKey(String xpathExpression, String keyWord){
        waitForElementVisible(xpathExpression);
        driver.findElement(By.xpath(xpathExpression)).click();
        driver.findElement(By.xpath(xpathExpression)).sendKeys(keyWord);
        String dynamicXpath = String.format("//android.widget.EditText[@text=\"%s\"]", keyWord);
        waitForElementVisible(dynamicXpath);
    }

    public void closeKeyboard() {
        driver.executeScript("mobile: hideKeyboard");
    }

    public void scroll(String pageDirection, double scrollRatio){
        Duration SCROLL_DUR = Duration.ofMillis(300);
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }

        Dimension size = driver.manage().window().getSize();
        System.out.println("Screen Size = " + size);

        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));

        int a = (int) (midPoint.x * scrollRatio); //half of midPoint.x
        int b = (int) (midPoint.y * scrollRatio); //half of midPoint.y

        int bottom = midPoint.y + b;
        int top = midPoint.y - b;
        int left = midPoint.x + a;
        int right = midPoint.x - a;

        if (pageDirection == "UP") {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (pageDirection == "DOWN") {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (pageDirection == "LEFT") {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }

    }

    public void swipe(Point start, Point end, Duration duration){
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }

    public String getText(String xpathExpression, String...texts){
        if (texts != null && texts.length > 0){
            String xpath = String.format(xpathExpression, (Object[]) texts);
            waitForElementVisible(xpath);
            return driver.findElement(By.xpath(xpath)).getText();

        } else {
            waitForElementVisible(xpathExpression);
            return driver.findElement(By.xpath(xpathExpression)).getText();
        }
    }

    public List<String> getListText(String xpathExpression, String...texts){
        String xpath = String.format(xpathExpression, (Object[]) texts);
        if (texts != null){
            List<WebElement> elements = driver.findElements(By.xpath(xpath));
            List<String> listStrings = new ArrayList<>();
            for (WebElement el : elements) {
                String text = el.getText();
                listStrings.add(text);
            }
            return listStrings;
        } else {
            List<WebElement> elements = driver.findElements(By.xpath(xpathExpression));
            List<String> listStrings = new ArrayList<>();
            for (WebElement el : elements) {
                String text = el.getText();
                listStrings.add(text);
            }
            return getListText(xpathExpression);
        }

    }

    public void scrollToElementByText(String text){
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
        ));
    }

    public void scrollToElementByXPath(String xpathExpression) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + scrollTimeOut;
        while (System.currentTimeMillis() < endTime) {
            try {
                driver.findElement(AppiumBy.xpath(xpathExpression));
                return;
            } catch (NoSuchElementException e) {
                    driver.findElement(AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollForward();"
                    ));
            }
        }
        throw new NoSuchElementException(
                "Element with XPath '" + xpathExpression + "' not found within " + scrollTimeOut + " seconds."
        );
    }

    public String getRandom10Digits(){
        Random random = new Random();
        long getRandom10DigitNumber = 1_000_000_000L + (long)(random.nextDouble() * 9_000_000_000L);
        return String.valueOf(getRandom10DigitNumber);
    }

    public void verifyText(String xpathExpression, String expectedText){
        String actualText = driver.findElement(By.xpath(xpathExpression)).getText();
        Assert.assertEquals(actualText, expectedText);
    }

    private boolean isAlertDisplayed(String xpath) {
        try {
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void closeInAppAlertsIfVisible() {
        String btnCloseDialogue = "//android.widget.ImageView[@resource-id='com.ncs.breeze.demo:id/imgCloseDialog']";

        while (elementIsVisible(btnCloseDialogue)) {
            try {
                classDecl.commonKeyword.clickElement(btnCloseDialogue);
                System.out.println("Close the in-app alert");
                pause(3);
            } catch (Exception e) {
                System.out.println("Error while closing the alert: " + e.getMessage());
                break;
            }
        }
    }

    public void tapOnNativeBackBtn(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void tapOnVirtualDoneBtn(){
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void tapOnNativeRecentTabsBtn(){
        driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
    }

    public void tapOnInAppBackBtn(String pageTitle){
        String xpath1 = "//android.widget.TextView[@text=\"" + pageTitle + "\"]/ancestor::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView";
        String xpath2 = "//android.widget.TextView[@text=\"" + pageTitle + "\"]/ancestor::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup//android.widget.ImageView";
        String xpath = xpath1 + " | " + xpath2;

        clickElement(xpath);
    }

    public void pause(int seconds) {
        int timeInSeconds = seconds * 1000;
        try {
            if (seconds >= 1) {
                for (int i = 0; i < seconds; i++) {
                    if (seconds > 1) {
                        System.out.println("Sleep " + (i + 1) + "s");
                    }
                    Thread.sleep(1000);
                }
            } else {
                Thread.sleep(timeInSeconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean elementIsVisible(String xpathExpression, String... texts) {
        // Temporarily disable implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            if (texts != null && texts.length > 0){
                String xpath = String.format(xpathExpression, (Object[]) texts);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            } else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            }
            return true;
        } catch (TimeoutException e) {
            return false;
        } catch (Exception e) {
            System.out.println("Error in elementIsVisible: " + e.getMessage());
            return false;
        } finally {
            // Restore implicit wait
            driver.manage().timeouts().implicitlyWait(timeoutExWait);
        }
    }
}

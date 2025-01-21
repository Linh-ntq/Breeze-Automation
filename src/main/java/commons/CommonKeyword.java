package commons;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumBy;
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

public class CommonKeyword extends Setup{

    public void waitForElementVisible(String xpathExpression, String... text) {
        if (text != null) {
            String xpath = String.format(xpathExpression, (String[]) text);
            waitDriverApp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        } else {
            waitDriverApp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        }
    }

    public void elementNotVisible(String xpathExpression, String... text) {
        if (text != null) {
            String xpath = String.format(xpathExpression, (String[]) text);
            waitDriverApp.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));

        } else {
            waitDriverApp.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathExpression)));
        }
    }

    public void clickElement(String xpathExpression) {
        waitForElementVisible(xpathExpression);
        driver.findElement(By.xpath(xpathExpression)).click();
    }

    public void clickElement(String xpathExpression, String text) {
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

    public List<String> getListText(String xpathExpression){
        List<WebElement> elements = driver.findElements(By.xpath(xpathExpression));
        List<String> listStrings = new ArrayList<>();
        for (WebElement el : elements) {
            String text = el.getText();
            listStrings.add(text);
        }
        return getListText(xpathExpression);
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

}

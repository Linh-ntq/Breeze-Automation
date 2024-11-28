package commons;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;

import java.time.Duration;
import java.util.Optional;

public class CommonKeyword extends Setup{
    public void pause(int time) {
        try {
            if (time >= 1000) {
                for (int i = 0; i < time / 1000; i++) {
                    if (time > 1000) {
                        System.out.println("Sleep " + (i + 1) + "s");
                    }
                    Thread.sleep(1000);
                }
            } else {
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementVisible(String xpathExpression, String... text) {
        if (text != null) {
            String xpath = String.format(xpathExpression, (String[]) text);
            waitDriverApp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        } else {
            waitDriverApp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        }
    }

    public void clickElement(String xpathExpression) {
        waitForElementVisible(xpathExpression);
        driver.findElement(By.xpath(xpathExpression)).click();
    }

    public void clickIdElement(String idExpression) {
        waitForElementVisible(idExpression);
        driver.findElement(By.id(idExpression)).click();
    }

    public void sendKey(String xpathExpression, String keyWord){
        waitForElementVisible(xpathExpression);
        driver.findElement(By.xpath(xpathExpression)).click();
        driver.findElement(By.xpath(xpathExpression)).sendKeys(keyWord);
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
        System.out.println("");

        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));

        int a = (int) (midPoint.x * scrollRatio); //half of midPoint.x
        int b = (int) (midPoint.y * scrollRatio); //half of midPoint.y

        int bottom = midPoint.y + b; // 50 + 25 -> B
        int top = midPoint.y - b;    // 50 - 25 -> A
        int left = midPoint.x + a;   // 25 - 12.5 -> N
        int right = midPoint.x - a;  // 25 + 12.5 -> M

        System.out.println("Midpoint: " + midPoint);
        System.out.println("a - half of midPoint.x: " + a);
        System.out.println("b - half of midPoint.y: " + b);
        System.out.println("Bottom: " + bottom);
        System.out.println("Top: " + top);
        System.out.println("Left: " + left);
        System.out.println("Right: " + right);

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

}

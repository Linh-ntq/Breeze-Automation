package commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport extends BaseTest{
    public ExtentReports extentReports;
    public ExtentTest genTestReportName;

    public void initiateExtentReport(){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    public void startTest(String testName) {
        genTestReportName = extentReports.createTest(testName);
    }

    public String captureScreenshot(String imageName) {
        File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
        String dateFolder = new SimpleDateFormat("ddMMMyyyy").format(new Date());
        String screenshotPath = "target/screenshots/" + dateFolder + "/" + imageName + "_" + System.currentTimeMillis() + ".png";
        File destinationFile = new File(screenshotPath);

        try {
            FileUtils.copyFile(screenshotFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }

    public void attachScreenshotToReport(String imgName) {
        String screenshotPath = captureScreenshot(imgName);
        String relativePath = screenshotPath.replace("target/", "");
        genTestReportName.info(imgName,
                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
    }

}

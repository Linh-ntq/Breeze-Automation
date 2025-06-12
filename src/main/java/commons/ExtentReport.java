package commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
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

    public void startRecordingScreen() {
        driver.startRecordingScreen();
    }

    public void attachScreenRecordingToReport(String videoName) {
        // Stop and get Base64 string
        String base64Video = driver.stopRecordingScreen();

        // Decode and save the video
        String dateFolder = new SimpleDateFormat("ddMMMyyyy").format(new Date());
        String videoPath = "target/screenshots/" + dateFolder + "/" + videoName + "_" + System.currentTimeMillis() + ".mp4";
        File videoFile = new File(videoPath);
        videoFile.getParentFile().mkdirs();

        try (FileOutputStream stream = new FileOutputStream(videoFile)) {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Video);
            stream.write(decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Attach to Extent Report
        String relativePath = videoPath.replace("target/", "");

        // NOTE: ExtentReports does not directly support video embedding, but you can log a link
        genTestReportName.info("<a href='" + relativePath + "' target='_blank'>Watch Screen Recording</a>");
    }

}

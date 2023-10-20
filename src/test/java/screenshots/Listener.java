package screenshots;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.util.Date;

public class Listener implements ITestListener {
    @AfterMethod
    public void onTestFailure (ITestResult result) {
        try {
            WebDriver driver = (WebDriver)result.getTestClass().getRealClass().getSuperclass()
                    .getDeclaredField("driver").get(result.getInstance());
            Actions actions = new Actions(driver);
            actions.pause(5000).perform();
            captureScreenShot(result.getName(), driver);
        }
        catch(Exception e) {
            System.out.println("Catch = " + e.getMessage());
        }
    }
@Attachment
    private void captureScreenShot(String methodName, WebDriver driver) {
        try {
            String timeStamp = new Date().toString().replace(":", "_").replace(" ", "_");
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("target/allure-results/" + methodName + timeStamp + ".png"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
//    private byte[] saveScreenshotOnFailure(WebDriver driver) {
//        System.out.println("screenForAllure");
//        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//    }
}

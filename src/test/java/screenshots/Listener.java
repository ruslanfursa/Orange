package screenshots;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;

public class Listener implements ITestListener {
    @AfterMethod
    public void onTestFailure (ITestResult result) {
        System.out.println("Test is failed");
        try {
            WebDriver driver = (WebDriver)result.getTestClass().getRealClass().getSuperclass()
                    .getDeclaredField("driver").get(result.getInstance());
            Actions actions = new Actions(driver);
            actions.pause(5000).perform();
            captureScreenShot(result.getName(), driver);
        }
        catch(Exception e) {
            System.out.println("catc: " + e.getMessage());
        }
    }

    private void captureScreenShot(String methodName, WebDriver driver) {
        try {
            System.out.println("Start capturescreen");
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("target/allure-results/" + methodName + ".jpg"));
            System.out.println("Screenshot is saved");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        finally {
//            screenDriver.quit();
//        }
    }
}

package model.page;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class NewTabPage extends BasePage {
    public NewTabPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened(String newTab) {
        String currentUrl = getDriver().getCurrentUrl();
        String homeTab = getTabId();
        getDriver().close();
        getDriver().switchTo().window(homeTab);
        return currentUrl.contains(newTab);
    }

    private String getTabId() {
        String currentHandle = getDriver().getWindowHandle();
        Set<String> handles = getDriver().getWindowHandles();
        for (String s : handles) {
            if (!s.equals(currentHandle)) {
                return s;
            }
        }
        return "";
    }
}


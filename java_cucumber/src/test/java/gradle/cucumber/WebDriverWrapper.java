package gradle.cucumber;

import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverWrapper {

    private static ChromeDriver driver = null;

    public WebDriverWrapper() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        if (driver == null) {
            driver = new ChromeDriver();
        }
    }

    public ChromeDriver getDriver() {
        return driver;
    }
}

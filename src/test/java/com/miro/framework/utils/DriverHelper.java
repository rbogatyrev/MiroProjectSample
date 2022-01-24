package com.miro.framework.utils;

import com.codeborne.selenide.Configuration;
import com.miro.framework.configuration.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

public class DriverHelper {

    private static WebDriverConfig driverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    /**
     * Open a blank browser tab and switch to it
     */
    public static void switchToBlankTab() {
        executeJavaScript("window.open('about:blank', '_blank')");
        switchTo().window(1);
    }

    /**
     * Set up a chrome driver
     */
    public static void configureDriver() {
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";

        if (driverConfig.isRemote()) {
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability("enableVNC", true);
            caps.setCapability("enableVideo", true);
            caps.setCapability("browserName", "chrome");
            caps.setCapability("env", new String[]{"LANG=ru_RU.UTF-8", "LANGUAGE=ru:en", "LC_ALL=ru_RU.UTF-8"});
            caps.setCapability("version", driverConfig.chromeVersion());
            Configuration.remote = driverConfig.remoteUrl();
            Configuration.browserCapabilities = caps;
        }
    }

}

package com.miro.framework.utils;

import com.codeborne.selenide.Configuration;
import com.miro.framework.configuration.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

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

    /**
     * Get the url of the attached video-file
     *
     * @param sessionId Current browser's session
     * @return Url of the attached video-file
     */
    public static URL getVideoUrl(String sessionId) {
        String videoUrl = driverConfig.videoUrlPart() + sessionId + ".mp4";
        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            Logger.getInstance().warn(format("[ALLURE VIDEO ATTACHMENT ERROR] Wrong test video url, {}", videoUrl));
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get current browser's session
     *
     * @return current browser's session
     */
    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

}

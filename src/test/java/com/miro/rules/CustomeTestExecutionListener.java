package com.miro.rules;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.miro.framework.configuration.WebDriverConfig;
import com.miro.framework.utils.DateFormat;
import com.miro.framework.utils.DriverHelper;
import com.miro.framework.utils.Logger;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.google.common.io.Files.toByteArray;
import static java.lang.String.format;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class CustomeTestExecutionListener implements TestExecutionListener {

    private WebDriverConfig driverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    /**
     * Make screenshot and attach to Allure
     *
     * @return Screenshot file
     */

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] makeScreenshot(TestIdentifier testIdentifier) {

        DateFormat date = new DateFormat() {
        };
        File file = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
        String path = "target/generated-test-sources/" + testIdentifier.getClass().getName().replace("Tests", "") + "_"
                + testIdentifier.getDisplayName() + date.get(Calendar.DAY_OF_MONTH) + date.get(Calendar.MONTH) + date.get(Calendar.HOUR) + date.get(Calendar.MINUTE) + "/" + file.getName();
        File copiedFile = new File(path);
        try {
            FileUtils.copyFile(file, new File(path));
            Logger.getInstance().info(format("Screenshot path is: %s", copiedFile.getAbsolutePath()));
            return java.nio.file.Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Record console-log and attach to Allure
     *
     * @param testIdentifier - test execution context
     * @return file as byteArray
     */
    @Attachment(value = "Console log", type = "text/plain")
    public static byte[] saveConsoleLog(TestIdentifier testIdentifier) {
        DateFormat date = new DateFormat();
        String logs = String.join("\n", Selenide.getWebDriverLogs(BROWSER));
        try {
            File file = new File("target/generated-test-sources/" + testIdentifier.getClass().getName().replace("Tests", "") + "_" + testIdentifier.getDisplayName() + date.get(Calendar.DAY_OF_MONTH) + date.get(Calendar.MONTH) + date.get(Calendar.HOUR) + date.get(Calendar.MINUTE) + "/consoleLogs.txt");
            FileUtils.writeStringToFile(file, logs, Charset.forName("UTF-8"));
            byte[] consoleLog = toByteArray(file);
            return consoleLog;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static byte[] fetchWithRetry(String sessionId) throws TimeoutException {
        URL videoUrl = DriverHelper.getVideoUrl(sessionId);
        Logger.getInstance().info("Downloading: " + videoUrl);
        for (int i = 0; i < 10; i++) {
            Response response = RestAssured.get(videoUrl);
            if (response.getStatusCode() == HttpStatus.SC_OK) {
                return response.asByteArray();
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
        throw new TimeoutException();
    }

    /**
     * Attach an mp4-file of a test execution to allure-report
     *
     * @param sessionId current browser's session
     * @return mp4 file as byteArray
     */
    @Attachment(value = "Video", type = "video/mp4")
    public static byte[] getVideo(String sessionId) {
        try {
            return fetchWithRetry(sessionId);
        } catch (TimeoutException e) {
            Logger.getInstance().error("Failed fetching URL");
            return null;
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        String sessionId = DriverHelper.getSessionId();
        if (testExecutionResult.getThrowable().isPresent()) {
            makeScreenshot(testIdentifier);
            saveConsoleLog(testIdentifier);
        }

        Selenide.closeWebDriver();
        if (driverConfig.isRemote()) {
            getVideo(sessionId);
        }
    }
}

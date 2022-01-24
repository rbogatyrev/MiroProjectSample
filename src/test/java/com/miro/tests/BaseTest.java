package com.miro.tests;

import com.codeborne.selenide.Selenide;
import com.miro.framework.utils.DriverHelper;
import com.miro.framework.utils.Logger;
import com.miro.steps.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static java.lang.String.format;

/**
 * Super class that contains basic configuration for all test classes
 */
public abstract class BaseTest {

    protected Logger logger = Logger.getInstance();
    protected InboxSteps inboxSteps = new InboxSteps();
    protected NavigationSteps navigationSteps = new NavigationSteps();
    protected SignUpSteps signUpSteps = new SignUpSteps();
    protected MainPageSteps mainPageSteps = new MainPageSteps();
    protected WorkSpaceSteps boardSteps = new WorkSpaceSteps();

    @BeforeAll
    public static void config() {
        DriverHelper.configureDriver();
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        Selenide.open();
        logger.info(format("Test %s started", testInfo.getDisplayName()));
    }

    @AfterEach()
    public void tearDown(TestInfo testInfo) {
        Selenide.closeWebDriver();
        logger.info(format("Test %s is finished", testInfo.getDisplayName()));
    }

}

package com.miro.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.miro.framework.utils.FluentWaitCondition;
import com.miro.framework.utils.Logger;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class BasePage {

    Logger logger = Logger.getInstance();

    protected abstract String getMainPageElement();

    protected abstract String getPageUrl();

    /**
     * Check, that the page is Loaded
     */
    public void assertPageIsLoaded() {
        String elementSelector = getMainPageElement();
        SelenideElement element = getSelectorType(elementSelector) == "xpath" ? $x(elementSelector) : $(elementSelector);

        assertThat(FluentWaitCondition.shouldBeVisible(element, 10))
                .as("Page %s is not loaded", this.getClass().getSimpleName()).isTrue();
    }

    /**
     * Navigate to page by URL.
     */
    @Step("Navigate to page by URL")
    public void open() {
        String url = getPageUrl();
        logger.info("Navigate to URL: " + url);
        WebDriverRunner.getWebDriver().navigate().to(url);
    }

    private String getSelectorType(String selector) {
        return selector.charAt(0) == '/' ? "xpath" : "css";
    }

}

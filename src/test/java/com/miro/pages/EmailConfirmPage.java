package com.miro.pages;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.miro.framework.enums.Urls.EMAIL_CONFIRM;

public class EmailConfirmPage extends BasePage {

    private static final String MAIN_ELEMENT = ".signup__subtitle-form";
    private static final String INPUT_CODE = "input#code";
    private static final String SEND_AGAIN_BUTTON = "a[href*='get-new-code'] span";

    @Override
    protected String getMainPageElement() {
        return MAIN_ELEMENT;
    }

    @Override
    protected String getPageUrl() {
        return EMAIL_CONFIRM.getValue();
    }

    @Step("Set an email confirmation code")
    public void setDigitCode(@NotNull String code) {
        $(INPUT_CODE).shouldBe(visible).sendKeys(code);
    }

    @Step("Click 'Send code again' button")
    public void clickSendCodeAgainButton() {
        $(SEND_AGAIN_BUTTON).shouldBe(visible).click();
    }
}

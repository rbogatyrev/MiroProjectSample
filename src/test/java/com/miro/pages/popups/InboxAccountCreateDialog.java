package com.miro.pages.popups;

import com.codeborne.selenide.SelenideElement;
import com.miro.framework.utils.FluentWaitCondition;
import com.miro.framework.utils.SimpleWait;
import com.miro.pages.BasePopover;
import com.miro.pages.InboxPage;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.miro.framework.enums.InboxAccountMenuItems.CREATE;
import static java.lang.String.format;

public class InboxAccountCreateDialog extends BasePopover {

    private static final String POPOVER_ELEMENT = "div[role='dialog']";
    private static final String EMAIL_INPUT = "input#username";
    private static final String PASSWORD_INPUT = "input#password";
    private static final String CREATE_BUTTON = ".//button[contains(text(), '%s')]";

    private InboxPage inboxPage;

    InboxAccountCreateDialog(InboxPage inboxPage) {
        this.inboxPage = inboxPage;
    }


    @Override
    protected SelenideElement currentPopup() {
        return $(POPOVER_ELEMENT).shouldBe(visible);
    }

    @Step("Set email")
    public InboxAccountCreateDialog setEmail(@NotNull String email) {
        currentPopup().$(EMAIL_INPUT).sendKeys(email);
        return this;
    }

    @Step("Set password")
    public InboxAccountCreateDialog setPassword(@NotNull String password) {
        currentPopup().$(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    @Step("Click on create account button")
    public InboxPage clickCreateButton() {
        int tries = 0;
        do {
            currentPopup().$x(format(CREATE_BUTTON, CREATE.getValue())).click();
            tries++;
        }
        while (!(FluentWaitCondition.shouldNotBeVisible($(POPOVER_ELEMENT), 3)) && tries < 3);

        return inboxPage;
    }


}

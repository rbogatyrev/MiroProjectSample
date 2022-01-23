package com.miro.pages.forms;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InviteForm {

    private static final String MAIN_ELEMENT = "div.welcome-screen-slide__form";
    private static final String SKIP_BUTTON = "button[hm-tap='ctrl.addTeamMembersLater()']";

    private UsePurposeForm usePurposeForm = new UsePurposeForm();

    private SelenideElement waitForLoading() {
        return $(MAIN_ELEMENT).shouldBe(visible);

    }

    @Step("Click skip button")
    public UsePurposeForm clickSkipButton() {
        waitForLoading().$(SKIP_BUTTON).click();
        return usePurposeForm;
    }

}

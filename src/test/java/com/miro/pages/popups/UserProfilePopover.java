package com.miro.pages.popups;

import com.codeborne.selenide.SelenideElement;
import com.miro.pages.BasePopover;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class UserProfilePopover extends BasePopover {

    private static final String POPOVER_ELEMENT = "div[class*='onlineUserPopup__menu']";
    private static final String WORKSPACE_USERNAME_INFO = "div[class*='onlineUserPopup__caption_name']";

    @Override
    protected SelenideElement currentPopup() {
        return $(POPOVER_ELEMENT).shouldBe(visible);
    }

    @Step("Get name from user profile")
    public String getUserNameInfo() {
        return currentPopup().$(WORKSPACE_USERNAME_INFO).getText();
    }

}

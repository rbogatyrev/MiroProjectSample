package com.miro.pages.topbars;

import com.miro.pages.popups.UserProfilePopover;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BoardPageTopbar {

    private static String USER_PROFILE_BUTTON = "[data-testid='people-bar__user-self']";

    private UserProfilePopover userProfile = new UserProfilePopover();

    @Step("Open user profile in topbar")
    public UserProfilePopover openUserProfile(){
        $(USER_PROFILE_BUTTON).shouldBe(visible).click();
        return userProfile;
    }

}

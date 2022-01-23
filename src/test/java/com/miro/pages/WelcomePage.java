package com.miro.pages;

import com.miro.pages.forms.InviteForm;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.miro.framework.enums.Urls.WELCOME_PAGE;

public class WelcomePage extends BasePage {

    private static final String MAIN_ELEMENT = "div[class*='welcome-screen__slide-container']";
    private static final String FIRST_QUESTION_INPUT = "[data-testid='setupTeamSlide__teamTitleInput']";
    private static final String SUBMIT_BUTTON = "[data-testid='setupTeamSlide__submitButton']";

    private InviteForm inviteForm = new InviteForm();

    @Override
    protected String getMainPageElement() {
        return MAIN_ELEMENT;
    }

    @Override
    protected String getPageUrl() {
        return WELCOME_PAGE.getValue();
    }

    @Step("Set organization name")
    public WelcomePage setFirstQuestion(String text) {
        $(FIRST_QUESTION_INPUT).shouldBe(visible).setValue(text);
        return this;
    }

    @Step("Click submit button")
    public InviteForm clickSubmitButton() {
        $(SUBMIT_BUTTON).click();
        return inviteForm;
    }


}

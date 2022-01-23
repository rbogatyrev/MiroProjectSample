package com.miro.pages;

import com.miro.framework.utils.FluentWaitCondition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.miro.framework.enums.Urls.SIGNUP_PAGE;
import static java.lang.String.format;

public class SignupPage extends BasePage {

    private static final String MAIN_ELEMENT = ".overlay-signup__form-container";
    private static final String NAME_INPUT = "input#name";
    private static final String EMAIL_INPUT = "input#email";
    private static final String PASSWORD_INPUT = "input#password";
    private static final String SIGNUP_TERMS_CHECKBOX = "[data-testid='mr-form-signup-terms-1'] label";
    private static final String SIGNUP_SUBSCRIBE_CHECKBOX = "input#signup-subscribe";
    private static final String SIGNUP_SUBMIT_BUTTON = "[data-testid='mr-form-signup-btn-start-1']";
    private static final String FIELD_VALIDATION_ERROR_LABEL = "//*[@id='%s'][text()='%s']";

    @Override
    protected String getMainPageElement() {
        return MAIN_ELEMENT;
    }

    @Override
    protected String getPageUrl() {
        return SIGNUP_PAGE.getValue();
    }

    @Step("Set user name")
    public SignupPage setName(String name) {
        $(NAME_INPUT).setValue(name);
        return this;
    }

    @Step("Set user email")
    public SignupPage setWorkEmail(String email) {
        $(EMAIL_INPUT).setValue(email);
        return this;
    }

    @Step("Set user password")
    public SignupPage setPassword(String password) {
        $(PASSWORD_INPUT).setValue(password);
        return this;
    }

    @Step("Click on submit button")
    public void clickSubmitButton() {
        $(SIGNUP_SUBMIT_BUTTON).click();
    }

    @Step("Click on signup terms checkbox")
    public SignupPage clickTermsCheckbox() {
        $(SIGNUP_TERMS_CHECKBOX).click();
        return this;
    }

    @Step("Click on subscribe checkbox")
    public SignupPage clickSubscribeCheckbox() {
        $(SIGNUP_SUBSCRIBE_CHECKBOX).click();
        return this;
    }

    public boolean checkIfFieldContainsErrorByText(String errorKey, String errorText) {
        return FluentWaitCondition.shouldBeVisible($x(format(FIELD_VALIDATION_ERROR_LABEL, errorKey, errorText)), 3);
    }

}

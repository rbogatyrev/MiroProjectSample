package com.miro.steps;

import com.miro.framework.enums.SignupPageValidationErrors;
import com.miro.models.User;
import com.miro.pages.SignupPage;
import com.miro.pages.WelcomePage;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Step;

public class SignUpSteps {

    SignupPage signupPage = new SignupPage();
    WelcomePage welcomePage = new WelcomePage();

    @Step("Sign up a new Miro User")
    public void signUpNewMiroUser(@NotNull User user) {
        signupPage.setName(user.getName())
                .setWorkEmail(user.getWorkEmail())
                .setPassword(user.getPassword())
                .clickTermsCheckbox()
                .clickSubmitButton();
    }

    @Step("Pass Miro Qualification")
    public void passWelcomeQualification(@NotNull User user) {
        welcomePage.setFirstQuestion(user.getOrganizationName())
                .clickSubmitButton()
                .clickSkipButton()
                .clickTryButton();
    }

    @Step("Check if a field contains '{errorText}' error")
    public boolean checkFieldValidationErrorExists(String errorKey, String errorText) {
        return signupPage.checkIfFieldContainsErrorByText(errorKey, errorText);
    }

}

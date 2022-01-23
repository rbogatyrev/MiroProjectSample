package com.miro.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.miro.framework.enums.Urls.MAIN_PAGE;

public class MiroMainPage extends BasePage {

    private static final String MAIN_ELEMENT = "div#header";
    private static final String SIGNUP_BUTTON = "[aria-label = 'Header'] a[href='/signup/']";

    SignupPage signupPage = new SignupPage();

    @Override
    protected String getMainPageElement() {
        return MAIN_ELEMENT;
    }

    @Override
    protected String getPageUrl() {
        return MAIN_PAGE.getValue();
    }

    public SignupPage clickSignUpButton() {
        $(SIGNUP_BUTTON).click();
        return signupPage;
    }


}

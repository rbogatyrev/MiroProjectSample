package com.miro.steps;

import com.miro.pages.MiroMainPage;
import io.qameta.allure.Step;

public class MainPageSteps {

    private MiroMainPage miroMainPage = new MiroMainPage();

    @Step("Click sign up for free button")
    public void clickSignUpForFree() {
        miroMainPage.clickSignUpButton()
                .assertPageIsLoaded();
    }

}

package com.miro.steps;

import com.miro.pages.MiroMainPage;
import io.qameta.allure.Step;

public class NavigationSteps {

    private MiroMainPage miroMainPage = new MiroMainPage();

    @Step("Navigate to Miro Main Page")
    public void openMiroMainPage() {
        miroMainPage.open();
        miroMainPage.assertPageIsLoaded();
    }

}

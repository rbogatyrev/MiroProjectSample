package com.miro.pages.forms;

import com.miro.pages.WorkSpacePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class UsePurposeForm {

    private static final String CLICK_TRY_BUTTON = "[data-testid='use-case-slide-content__start-from-scratch-button']";
    private static final String PANEL_ITEM = "//div[@class='use-case-slide-content__select-panels']//div[text()='%s']";

    private WorkSpacePage boardPage = new WorkSpacePage();

    public WorkSpacePage clickTryButton() {
        $(CLICK_TRY_BUTTON).shouldBe(visible).click();
        return boardPage;
    }

    public WorkSpacePage selectPanelItem() {
        $x(format(PANEL_ITEM, "123")).shouldBe(visible).click();
        return boardPage;
    }


}

package com.miro.pages.popups;

import com.codeborne.selenide.SelenideElement;
import com.miro.pages.BasePopover;
import com.miro.pages.WorkSpacePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class TemplatePickerPopover extends BasePopover {

    WorkSpacePage boardPage;

    public TemplatePickerPopover(WorkSpacePage boardPage) {
        this.boardPage = boardPage;
    }

    private static String POPOVER_ELEMENT = "[data-testid='template-picker__container']";
    private static String CLOSE_BUTTON = "[data-testid='template-picker__close-button']";

    @Override
    protected SelenideElement currentPopup() {
        return $(POPOVER_ELEMENT).shouldBe(visible, ofSeconds(10));
    }

    public WorkSpacePage closePopup() {
        currentPopup().$(CLOSE_BUTTON).click();
        return boardPage;
    }
}

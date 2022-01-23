package com.miro.pages.popups;

import com.codeborne.selenide.SelenideElement;
import com.miro.pages.BasePopover;
import com.miro.pages.InboxPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.miro.framework.enums.InboxAccountMenuItems.CREATE_ACCOUNT;
import static java.lang.String.format;

public class InboxAccountsListPopover extends BasePopover {

    private static final String POPOVER_ELEMENT = "//div[@id='accounts-list']";
    private static final String MENU_ITEM = ".//a[contains(text(),'%s')]";
    private InboxAccountCreateDialog inboxAccountCreateDialog;

    public InboxAccountsListPopover(InboxPage inboxPage) {
        inboxAccountCreateDialog = new InboxAccountCreateDialog(inboxPage);
    }

    @Override
    protected SelenideElement currentPopup() {
        return $x(POPOVER_ELEMENT).shouldBe(visible);
    }

    @Step("Click on create account button")
    public InboxAccountCreateDialog clickCreateAccountButton() {
        currentPopup().$x(format(MENU_ITEM, CREATE_ACCOUNT.getValue())).click();
        return inboxAccountCreateDialog;
    }


}

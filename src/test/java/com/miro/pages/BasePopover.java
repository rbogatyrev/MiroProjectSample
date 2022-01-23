package com.miro.pages;

import com.codeborne.selenide.SelenideElement;

/**
 * Super class that contains basic methods for all popup windows
 */
public abstract class BasePopover {

    protected abstract SelenideElement currentPopup();

}

package com.miro.pages;

import com.miro.pages.popups.TemplatePickerPopover;
import com.miro.pages.topbars.BoardPageTopbar;
import lombok.Getter;

import static com.miro.framework.enums.Urls.BOARD_PAGE;

public class WorkSpacePage extends BasePage {

    private static final String MAIN_ELEMENT = "#desktop-ui";

    @Getter
    private BoardPageTopbar topbar = new BoardPageTopbar();

    @Getter
    private TemplatePickerPopover templatePickerPopover = new TemplatePickerPopover(this);

    @Override
    protected String getMainPageElement() {
        return MAIN_ELEMENT;
    }

    @Override
    protected String getPageUrl() {
        return BOARD_PAGE.getValue();
    }


}

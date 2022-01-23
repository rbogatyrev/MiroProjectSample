package com.miro.steps;

import com.miro.pages.WorkSpacePage;
import io.qameta.allure.Step;

public class WorkSpaceSteps {

    WorkSpacePage boardPage = new WorkSpacePage();

    /**
     *
     * @param expectedProfileName expected user name to be in workspace profile
     * @return true - User name in workspace profile matches the expected one, false - User name in workspace profile doesn't match the expected one
     */
    @Step("Check current user name in workspace profile")
    public boolean checkWorkspaceUserName(String expectedProfileName) {
        String currentProfileUserName = boardPage.getTopbar()
                .openUserProfile()
                .getUserNameInfo()
                .replaceFirst(" \\(you\\)", "");

        return currentProfileUserName.equals(expectedProfileName);
    }

    @Step("Close template picker dialog")
    public void closeTemplatePicker() {
        boardPage.getTemplatePickerPopover()
                .closePopup();
    }

}

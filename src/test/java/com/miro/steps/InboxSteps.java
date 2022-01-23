package com.miro.steps;

import com.miro.models.User;
import com.miro.pages.InboxPage;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Step;

import static com.miro.framework.enums.EmailSubjects.EMAIL_CONFIRMATION;

public class InboxSteps {

    private InboxPage inboxPage = new InboxPage();

    @Step("Navigate to Mail.tm service and create a new account")
    public void createNewInboxAccount(@NotNull User user){
        inboxPage.open();
        inboxPage.assertPageIsLoaded();
        inboxPage.createNewInbox(user);
    }

    @Step("Follow the link from email confirmation letter")
    public void followLinkFromEmailConfirmationLetter(){
        inboxPage.waitForMessageWithSubjectReceived(EMAIL_CONFIRMATION.getValue())
                .clickOnEmailConfirmLink()
                .assertPageIsLoaded();
    }

}
